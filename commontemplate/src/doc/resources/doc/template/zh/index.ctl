<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
								<b>一、什么是CommonTemplate?</b><br/>
								CommonTemplate是一个易于<a href="extension.html">扩展</a>的开源模板引擎，用于编译运行<a href="template.html">CTL模板</a>语言，并且模板可以在Java，.Net，JS等中通用；<br/>
								其主要目标是作为JSP，ASP.Net等页面技术的另一种选择方案，以保证用简单的语法，良好的结构，不混杂业务逻辑的方式书写页面；<br/>
								适合于充当Model-View-Controller(MVC)模式应用的View角色，以使能更好的分离页面设计人员与业务开发人员的职责；<br/>
								也可以作为动态文本生成工具，生成HTML、XML、Mail、Java源代码或其它文本等。<br/>
								<br/>
								<b>二、CommonTemplate有哪些特性?</b><br/>
								1. 只有一个语法规则：\$指令名{参数表达式}，使用更简单，更一致。<a href="template.html">模板语法...</a><br/>
								2. 强大的调试器功能，支持模板指令单步执行，查错更方便。<a href="debugger.html">调试器...</a><br/>
								3. 方便的查看器(exe程序)，可在Windows下，双击*.ctl文件，直接生成.html文件，并自动用浏览器打开，方便于独立测试模板。<a href="viewer.html">查看器...</a><br/>
								4. Eclipse模板编辑器插件，支持指令语法高亮，指令提示助手，指令折叠，错误指令检查及提示，指令大纲等。<a href="editor.html">编辑器...</a><font color="gray">(未全部完成)</font><br/>
								5. 支持多语言(Java/C#.Net/JavaScript/C++)，同一模板可以在不同的运行环境(JRE/CLR/Browser/CGI)上运行，使用JavaScript版可以将解析任务转移到客户端执行。<font color="gray">(未全部完成)</font><br/>
								6. 支持语法外套，可以在Dreamwear下正常使用WYSWYG(所见即所得编辑)，并且语法外套是可扩展的。<a href="coat.html">语法外套...</a><br/>
								7. 支持一等公民的动态宏指令(与标准指令同样的调用方式)，更有利于模板组件化。<br/>
								8. 支持模板继承，采用面向对象的多态思想，方便于页面Layout，比宏指令更灵活。<a href="demo_extends.html">示例...</a><br/>
								9. 支持JSP标签库适配，可用指令的方式调用JSP标签，如：\$textfield{name:"",value:""}。<br/>
								10. 内置的国际化支持，国际化信息处理更方便。<br/>
								11. 完备的表达式支持，在全面兼容Java表达式的基础上，增加了更富表达力的操作符，包括BGGA闭包表达式等。<a href="expression.html">表达式...</a><br/>
								12. 禁止void函数调用，避免在模板中引入业务逻辑。(根据契约式设计原则，void函数通常是有副作用的，即修改状态)<br/>
								13. 对已有的不可变类(String,Number,Date等)，采用open class思想，允许在类的外部给类添加新的属性或方法，如：String本没有“首字母大写”的功能，可以外部给String注册一个capitalize属性：\${"james".capitalize}。(类似javascript的prototype)<br/>
								14. 引擎采用微核设计理念，除了核心API及解析器外，其它如语言定义，资源管理等都是外置的，可以基于同一个引擎，设计另一套完全不同的模板语言。<a href="architecture.html">架构设计...</a><br/>
								15. 高度可扩展，平等对待标准包与第三方扩展，标准包所能实现的任何功能均可被替换。<a href="extension.html">扩展点...</a><br/>
								16. 使用主控迭代器模式替代传统的被动访问器模式,引擎只负责将模板解析成指令树，其它所有处理均由扩展指令自身完成，给扩展指令以最大的控制权。(类似于StAX与SAX的区别)<br/>
								17. 多种优化措施，性能更优：<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;(1) 采用针对特定语法写的底层专有DFA解析器解析，试验表明比使用通用抽象的BNF语法体系解析(Java中一般用JavaCC或AntLR)更快，因为BNF为了通用，考虑了太多因素，导致其效率降低，当然，BNF在处理复杂语法(比如要解析C/C++/Java等语法时)很有优势，但CommonTemplate的语法非常简单且一致，所以使用专有的底层解析器是比较好的选择。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;(2) 对常量进行编译期运算，如：表达式"2 + 3"，将直接编译成"5"放入表达式树，避免在运行期重复计算，并且减小了表达式树的大小及内存占用。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;(3) 解析结果被表示成一个线程安全(不变类级)的指令树，单个实例可以在多线程中任意重复使用，避免使用重复解析或克隆实例等性能损耗。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;(4) 解析结果可以在内存中缓存起来，以减少解析次数及IO量，并且缓存策略是可扩展的。(已内置实现了NONE, STRONG, SOFT, WEAK, FIFO, LRU, MRU, OSCACHE, EHCACHE等缓存策略) <br/>
								&nbsp;&nbsp;&nbsp;&nbsp;(5) 并且解析结果可以被序列化冻结，系统重启后，可以从冻结结果直接还原，不必再解析，也就是说模板只有第一次加载时才需要解析 (当然热加载时也会重新解析)。<br/>
								18. 站在巨人的肩膀上，参考并借鉴了 WebMacro, Velocity, FreeMarker, JavaFX, OGNL, Perl6, Python/Django, PHP/Smarty, JavaScript/JSON, XML/XPath 等开源项目或规范，感谢它们做出的成果。<br/>
								<br/>
								<b>三、怎么书写CommonTemplate模板?</b><br/>
								<b>(1) 标准语法：</b> <a href="template.html">更多...</a><br/>
								CommonTemplate只有一个语法规则: \$指令名{参数表达式}<br/>
								<b>(a) HTML页面生成：</b><br/>
$code{html}$!<html>
    <body>
        $if{users != null && users.size &gt; 0}
        <table border="1">
            $for{user : users}
            <tr>
                <td>${for.index + 1}</td>
                <td>${user.name}</td>
                <td>${user.coins}</td>
            </tr>
            $end
        </table>
        $end
    </body>
</html>
!$$end
								<b>(b) Java代码生成：</b><br/>
$code{java}$!package com.${project.company}.${project.name}.domain;
$if{project.framework != project.name}
import com.${project.company}.${project.framework}.domain.BaseEntity;
$end
public class ${entity.name} extends BaseEntity {
	$for{field : entity.fields}
	private ${field.type} ${field.name};

	public ${field.type} get${field.name.capitalize}() {
		return ${field.name};
	}

	public void set${field.name.capitalize}(${field.type} ${field.name}) {
		this.${field.name} = ${field.name};
	}
	$end
}
!$$end
								<b>(2) 语法外套：</b> <a href="coat.html">更多...</a><br/>
								<b>(a) HTML标签注释版语法外套：</b><br/>
$code{html}$!<html>
    <body>
        <!--$if{users != null && users.size &gt; 0}-->
        <table border="1">
            <!--$for{user : users}-->
            <tr>
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
								<b>(b) HTML标签版语法外套：</b><br/>
$code{html}<html>
    <body>
    	<ct:if param="users != null && users.size &gt; 0">
	        <table border="1">
	        	<ct:for param="user : users">
	            <tr>
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
								<b>(c) HTML标签属性版语法外套：</b><br/>
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
								<b>(d) Java注释版语法外套：</b><br/>
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
								<br/>
								<b>四、特性语法举例</b><br/>
								(1) 并行迭代：\$for{i : (1..10), user : users} \$end 多个集合并行取next值，以最长的集合作为结束，短集合自动补null值<br/>
								(2) break及continue支持：\$break{for.count > 5} 条件与指令合并以避免冗长的语句：\$if {for.count > 5} \$break \$end<br/>
								(3) 简化的BGGA闭包表达式过滤器：\${users[u => u.name != 'guest']} 返回名称不为guest的users子集<br/>
								(4) 日期字段加减法 \${user.registerDate + 3.year} 年份加3，其中year为数字的外部扩展属性(OpenClass)<br/>
								(5) ~= 约等于号：\${"aa" ~= "AA"} 字符串忽略大小写比较<br/>
								(6) Boolean运算符支持非空判断：\${user1 || user2 || user3} 从左至右返回第一个不为空的user对象<br/>
								(7) # 格式化操作符, 如: \${date # "yyyy-MM-dd"} \${num # "##0.###"}<br/>
								......<br/>
								<br/>
								<b>五、怎么下载CommonTemplate?</b><br/>
								包括：版本发行，每日构建，源码控制等。<br/>
								下载服务由<a href="http://code.google.com" target="_blank">GoogleCode</a>提供支持:<br/>
								<table>
									<tr>
										<td width="64" height="24" align="center" style="background-color: #003399;"><a href="downloads.html" style="color: #FFFFFF; font-weight: bold;">下载</a></td>
									</tr>
								</table>
								<br/>
								<b>六、交流社区</b><br/>
								如果您有任何疑问或建议，请移步到论坛发帖，我们将尽快答复。<br/>
								论坛服务由<a href="http://www.redsaga.com" target="_blank">RedSaga</a>提供支持:<br/>
								<table>
									<tr>
										<td width="64" height="24" align="center" style="background-color: #003399;"><a target="_blank" href="http://forum.commontemplate.org/index.php" style="color: #FFFFFF; font-weight: bold;">论坛</a></td>
									</tr>
								</table>
	<!--$end-->
<!--$end-->