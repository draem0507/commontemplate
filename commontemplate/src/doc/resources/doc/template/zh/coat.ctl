<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>一. 语法外套配置</b><br/>
								<b>(1) XML/HTML标签注释版语法外套</b><br/>
								编译模板时自动去除指令两边的XML/HTML注释符，如：<br/>
								&lt;!--$指令{表达式}--&gt; <font color="green">(注: 注释符与指令间不能有空格)</font><br/>
								注：此语法外套在Web环境下(即@extends=org/commontemplate/tools/<b>web</b>/commontemplate.properties)默认开启，其它环境需配置：<br/>
								textFilters[100]=<font color="#2a00ff">org.commontemplate.standard.coat.CommentSyntaxCoatFilter()</font><br/>
								textFilters[100].begin=<font color="#2a00ff">&lt;!--</font> <font color="green">(注：注释起始符)</font><br/>
								textFilters[100].end=<font color="#2a00ff">--&gt;</font> <font color="green">(注：注释结束符)</font><br/>
								textFilters[100].clearSpaceline=<font color="#2a00ff">false</font> <font color="green">(注：当指令所在行没有其它内容时，清除该空白行)</font><br/>
								<font color="green">(注：此语法外套使用TextFilter扩展点实现，在编译期过滤文本，编译模板时有过滤时间消耗，但消耗不大，如需关闭可配置：textFilters[100]=null)</font><br/>
								<b>(2) Java/C++/C#代码注释版语法外套</b><br/>
								编译模板时自动去除指令两边的Java/C++/C#代码注释符，如：<br/>
								/*$指令{表达式}*/ <font color="green">(注: 注释符与指令间不能有空格)</font><br/>
								注：此语法外套在代码生成器环境下(即@extends=org/commontemplate/tools/<b>generator</b>/commontemplate.properties)默认开启，其它环境需配置：<br/>
								textFilters[100]=<font color="#2a00ff">org.commontemplate.standard.coat.CommentSyntaxCoatFilter()</font><br/>
								textFilters[100].begin=<font color="#2a00ff">/*</font> <font color="green">(注：注释起始符)</font><br/>
								textFilters[100].end=<font color="#2a00ff">*/</font> <font color="green">(注：注释结束符)</font><br/>
								textFilters[100].clearSpaceline=<font color="#2a00ff">true</font> <font color="green">(注：当指令所在行没有其它内容时，清除该空白行)</font><br/>
								<b>(3) XML/HTML标签版语法外套</b><br/>
								自动将名称空间为"ct:"的XML/HTML标签转换成指令，将"param"属性转换为表达式参数，如：<br/>
								&lt;ct:if param="users != null && users.size &gt; 0"&gt;...&lt;/cf:if&gt;<br/>
								注：此语法外套在Web环境下(即@extends=org/commontemplate/tools/<b>web</b>/commontemplate.properties)默认开启，其它环境需配置：<br/>
								resourceFilters[100]=<font color="#2a00ff">org.commontemplate.standard.coat.TagSyntaxCoatFilter()</font><br/>
								resourceFilters[100].namespace=<font color="#2a00ff">ct</font><br/>
								resourceFilters[100].expressionAttributeName=<font color="#2a00ff">param</font><br/>
								<font color="green">(注：此语法外套使用ResourceFilter扩展点实现，在资源加载时使用简单的正则表达式替换，不解析HTML语法，没有不规则HTML语法兼容问题，加载模板资源时有内存消耗和转换时间消耗，但消耗不是很大，如需关闭可配置：resourceFilters[100]=null)</font><br/>
								<b>(4) XML/HTML标签属性版语法外套</b><br/>
								自动将名称空间为"ct:"的XML/HTML标签属性转换成指令，如：<br/>
								&lt;table ct:if="users != null && users.size &gt; 0"&gt;...&lt;/table&gt; <font color="green">(注: 只能用于块指令)</font><br/>
								注：此语法外套因解析HTML语法，有性能损耗，所以未默认开启，需自行配置：<br/>
								resourceFilters[200]=<font color="#2a00ff">org.commontemplate.standard.coat.AttributeSyntaxCoatFilter()</font><br/>
								resourceFilters[200].namespace=<font color="#2a00ff">ct</font><br/>
								<font color="green">(注：此语法外套使用ResourceFilter扩展点实现，在资源加载时使用jericho进行html解析，对不规则HTML语法的兼容性，以jericho的实现为准，转换时间消耗较大，需jericho.jar支持)</font><br/>
								<br/>
!$
								<b>二. 语法外套举例：</b><br/>
								<b>(1) HTML标签注释版语法外套：</b><br/>
$code{html}$!<html>
    <body>
        <!--$if{users != null && users.size &gt; 0}-->
        <table border="1">
            <!--$for{user : users}-->
            <tr>
                <td><!--${for.index + 1}--></td>
                <td><!--${user.name}--></td>
                <td><!--${user.coins}--></td>
                  或者
                <td><!--$output{for.index + 1}-->1<!--$end--></td>
                <td><!--$output{user.name}-->james<!--$end--></td>
                <td><!--$output{user.coins}-->2.00<!--$end--></td>
            </tr>
            <!--$end-->
        </table>
        <!--$end-->
    </body>
</html>
!$$end
								<b>(2) Java注释版语法外套：</b><br/>
$code{java}$!package com.${project.company}.${project.name}.domain;
/*$if{project.framework != project.name}*/
import com.${project.company}.${project.framework}.domain.BaseEntity;
/*$end*/
public class ${entity.name} extends BaseEntity {
	/*$for{field : entity.fields}*/
	private ${field.type} ${field.name};

	public ${field.type} get${field.name.capitalize}() {
		return ${field.name};
	}

	public void set${field.name.capitalize}(${field.type} ${field.name}) {
		this.${field.name} = ${field.name};
	}
	/*$end*/
}
!$$end
								<b>(3) HTML标签版语法外套：</b><br/>
$code{html}<html>
    <body>
    	<ct:if param="users != null && users.size &gt; 0">
	        <table border="1">
	        	<ct:for param="user : users">
	            <tr>
	                <td><ct:out param="for.index + 1"/></td>
	                <td><ct:out param="user.name"/></td>
	                <td><ct:out param="user.coins"/></td>
	                  或者
	                <td><ct:output param="for.index + 1">1</ct:output></td>
	                <td><ct:output param="user.name">james</ct:output></td>
	                <td><ct:output param="user.coins">2.00</ct:output></td>
	            </tr>
	            </ct:for>
	        </table>
        </ct:if>
    </body>
</html>
$end
								<b>(4) HTML标签属性版语法外套：</b><br/>
$code{html}<html>
    <body>
        <table ct:if="users != null && users.size &gt; 0" border="1">
            <tr ct:for="user : users">
                <td><span ct:output="for.index + 1">1</span></td>
                <td><span ct:output="user.name">james</span></td>
                <td><span ct:output="user.coins">2.00</span></td>
            </tr>
        </table>
    </body>
</html>
$end
	<!--$end-->
<!--$end-->