<!--$extends{"/doc/template/frame.ctl"}-->
<!--$zone{"content"}-->
<b>一. 语法外套配置</b><br/>
<b>(1) XML/HTML标签注释版语法外套</b><br/>
编译模板时自动去除指令两边的XML/HTML注释符。<br/>
如：<font color="green">(注: 注释符与指令间不能有空格)</font><br/>
&lt;!--\$if{users != null && users.size &amp;gt; 0}--&gt;...&lt;!--\$end{if}--&gt; <br/>
注：如果在Web环境下，即：@extends=org/commontemplate/tools/<b>web</b>/commontemplate.properties，此语法外套默认开启，如需关闭，可配置：<br/>
$code{properties}
commentSyntaxCoatAvailable=false
$end<br/>
其它环境需配置：<br/>
$code{properties}
textFilters[100]=org.commontemplate.standard.coat.CommentSyntaxCoatFilter()
#注释起始符
textFilters[100].begin=&lt;!--
#注释结束符
textFilters[100].end=--&gt;
#当指令所在行没有其它内容时，清除该空白行
textFilters[100].clearSpaceline=false
$end<br/>
<font color="green">(注：此语法外套使用TextFilter扩展点实现，在编译期过滤文本，编译模板时有过滤时间消耗，但消耗不大，如需关闭可配置：textFilters[100]=null)</font><br/>
<b>(2) XML/HTML标签版语法外套</b><br/>
自动将名称空间为"ct:"的XML/HTML标签转换成指令，将"param"属性转换为表达式参数。<br/>
如：<br/>
块指令：<font color="green">(注：必须使用&lt;xxx&gt;&lt;/xxx&gt;方式调用)</font><br/>
&lt;ct:if param="users != null && users.size &amp;gt; 0"&gt;...&lt;/cf:if&gt;<br/>
行指令：<font color="green">(注：必须使用&lt;xxx/&gt;方式调用)</font><br/>
&lt;ct:out param="user.name" /&gt;<br/>
注：如果在Web环境下，即：@extends=org/commontemplate/tools/<b>web</b>/commontemplate.properties，则只需配置开关：<br/>
$code{properties}
tagSyntaxCoatAvailable=true
$end<br/>
其它环境需配置：<br/>
$code{properties}
resourceFilters[100]=org.commontemplate.standard.coat.TagSyntaxCoatFilter()
resourceFilters[100].syntaxSettings=\$syntax
resourceFilters[100].namespace=ct
resourceFilters[100].expressionAttributeName=param
$end<br/>
<font color="green">(注：此语法外套使用ResourceFilter扩展点实现，在资源加载时使用简单的正则表达式替换，不解析HTML语法，没有不规则HTML语法兼容问题，加载模板资源时有内存消耗和转换时间消耗，但消耗不是很大，如需关闭可配置：resourceFilters[100]=null)</font><br/>
<b>(3) XML/HTML标签属性版语法外套</b><br/>
自动将名称空间为"ct:"的XML/HTML标签属性转换成指令，<br/>
<b>块指令</b>将按先后顺序扩充到标签的两端，<b>行指令</b>将替换整个标签及其内部块，一个标签上<b>只能有一个</b>行指令属性。<br/>
如：<br/>
&lt;table ct:if="users != null && users.size &amp;gt; 0"&gt;&lt;tr&gt;&lt;td&gt;&lt;span ct:out="user.name"&gt;&lt;/span&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;<br/>
等价于：<br/>
\$if{users != null && users.size &gt; 0}&lt;table&gt;&lt;tr&gt;&lt;td&gt;\$out{user.name}&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;\$end{if}<br/>
"if"<b>块指令</b>向外扩充到&lt;table&gt;标签的外面(如果有多个块指令，按顺序排列)，而"out"<b>行指令</b>替换了整个&lt;span&gt;标签及其内部块<br/>
注：如果在Web环境下，即：@extends=org/commontemplate/tools/<b>web</b>/commontemplate.properties，则只需配置开关：<br/>
$code{properties}
attributeSyntaxCoatAvailable=true
$end<br/>
其它环境需配置：<br/>
$code{properties}
resourceFilters[200]=org.commontemplate.standard.coat.AttributeSyntaxCoatFilter()
resourceFilters[200].syntaxSettings=\$syntax
resourceFilters[200].directiveHandlerProvider=\$directiveHandlerProvider
resourceFilters[200].namespace=ct
$end<br/>
<font color="green">(注：此语法外套使用ResourceFilter扩展点实现，在资源加载时使用jericho进行html解析，对不规则HTML语法的兼容性，以jericho的实现为准，转换时间消耗较大，需jericho.jar支持)</font><br/>
<b>(4) Java/C++/C#代码注释版语法外套</b> <font color="green">(注：与上面的(1)语法外套实现相同)</font><br/>
编译模板时自动去除指令两边的Java/C++/C#代码注释符。<br/>
如： <font color="green">(注: 注释符与指令间不能有空格)</font><br/>
/*\$if{xxx}*/ ... /*\$end{if}*/<br/>
注：如果在代码生成器环境下，即：@extends=org/commontemplate/tools/<b>generator</b>/commontemplate.properties，此语法外套默认开启，如需关闭，可配置：<br/>
$code{properties}
commentSyntaxCoatAvailable=false
$end<br/>
其它环境需配置：<br/>
$code{properties}
textFilters[100]=org.commontemplate.standard.coat.CommentSyntaxCoatFilter()
#注释起始符
textFilters[100].begin=/*
#注释结束符
textFilters[100].end=*/
#当指令所在行没有其它内容时，清除该空白行
textFilters[100].clearSpaceline=true
$end<br/>
<br/>
<b>二. 语法外套举例：</b><br/>
<b>(1) HTML标签注释版语法外套：</b><br/>
$code{html}$!<html>
    <body>
        <!--$if{users != null && users.size &gt; 0}-->
        <table border="1">
            <!--$for{user : users}-->
            <tr>
                <td><!--$output{for.index + 1}-->1<!--$end--></td>
                <td><!--$output{user.name}-->james<!--$end--></td>
                <td><!--$output{user.coins}-->2.00<!--$end--></td>
                或者
                <td><!--${for.index + 1}--></td>
                <td><!--${user.name}--></td>
                <td><!--${user.coins}--></td>
            </tr>
            <!--$end-->
        </table>
        <!--$end-->
    </body>
</html>
!$$end
<b>(2) HTML标签版语法外套：</b><br/>
$code{html}<html>
    <body>
    	<ct:if param="users != null && users.size &gt; 0">
	        <table border="1">
	        	<ct:for param="user : users">
	            <tr>
	                <td><ct:output param="for.index + 1">1</ct:output></td>
	                <td><ct:output param="user.name">james</ct:output></td>
	                <td><ct:output param="user.coins">2.00</ct:output></td>
	                或者: (out为行指令, output为块指令)
	                <td><ct:out param="for.index + 1"/></td>
	                <td><ct:out param="user.name"/></td>
	                <td><ct:out param="user.coins"/></td>
	            </tr>
	            </ct:for>
	        </table>
        </ct:if>
    </body>
</html>
$end
<b>(3) HTML标签属性版语法外套：</b><br/>
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
<b>(4) Java注释版语法外套：</b><br/>
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
<!--$end-->
<!--$end-->