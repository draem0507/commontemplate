<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>1. What's the CommonTemplate?</b><br/>
								CommonTemplate is a "template engine",<br/>
								a generic tool to generate text output: HTML, XML, Mail, Java source code, etc.<br/>
								<br/>
								<b>2. Which feature of CommonTemplate?</b> <br/>
								1) Only one syntax rule: $directive{expression}<br/>
								2) WYSWYG in Dreamweaver and syntax coat is extensible. <br/>
								3) Microkernel. Everything is additional except core API. Standard directives such as "for" and "if" could be replaced.<br/>
								4) Better extensibility. The engine is just on the duty of translating the template into directive-tree. The directives will finish the rest of the job by themselves.<br/>
								<br/>
								<b>3. Where download CommonTemplate?</b><br/>
								Download from <a target="_blank" href="http://sourceforge.net">SourceForge</a> platform:<br/>
								<table>
									<tr>
										<td width="64" height="24" align="center" style="background-color: #003399;"><a href="downloads.html" style="color: #FFFFFF; font-weight: bold;">Download</a></td>
									</tr>
								</table>
								<br/>
								<b>4. How to write CTL?</b> <a href="template_guide.html">more...</a><br/>
								Only one syntax rule: $<a href="template_guide.html">directive</a>{<a href="expression_guide.html">expression</a>}<br/>
<font color="#3f7f5f">&lt;html&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;body&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$if{users&nbsp;!=&nbsp;null&nbsp;&amp;&amp;&nbsp;users.size&nbsp;&gt;&nbsp;0}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;table&nbsp;<font color="#7f0055">border</font><font color="#000000">=</font><font color="#2a00ff">"1"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$for{user&nbsp;:&nbsp;users}<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tr&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font>${for.index&nbsp;+&nbsp;1}<font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font>${user.name}<font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font>${user.coins}<font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/tr&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$end<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/table&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$end<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/body&gt;</font><br/>
<font color="#3f7f5f">&lt;/html&gt;</font><br/>
								<br/>
								HTML syntax coat: <font color="green">(WYSWYG)</font><br/>
								(1) Comment Syntax Coat<br/>
<font color="#3f7f5f">&lt;html&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;body&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$if{users&nbsp;!=&nbsp;null&nbsp;&amp;&amp;&nbsp;users.size&nbsp;&gt;&nbsp;0}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;table&nbsp;<font color="#7f0055">border</font><font color="#000000">=</font><font color="#2a00ff">"1"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$for{user&nbsp;:&nbsp;users}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tr&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f5fbf">&lt;!--$out{for.index&nbsp;+&nbsp;1}--&gt;</font>1<font color="#3f5fbf">&lt;!--$end--&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f5fbf">&lt;!--$out{user.name}--&gt;</font>james<font color="#3f5fbf">&lt;!--$end--&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f5fbf">&lt;!--$out{user.coins}--&gt;</font>2.00<font color="#3f5fbf">&lt;!--$end--&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/tr&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/table&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/body&gt;</font><br/>
<font color="#3f7f5f">&lt;/html&gt;</font><br/>
								(2) Attribute Syntax Coat<br/>
<font color="#3f7f5f">&lt;html&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;body&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;table&nbsp;<font color="#7f0055">m:if</font><font color="#000000">=</font><font color="#2a00ff">"users&nbsp;!=&nbsp;null&nbsp;&amp;&amp;&nbsp;users.size&nbsp;&gt;&nbsp;0"</font>&nbsp;<font color="#7f0055">border</font><font color="#000000">=</font><font color="#2a00ff">"1"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tr&nbsp;<font color="#7f0055">m:for</font><font color="#000000">=</font><font color="#2a00ff">"user&nbsp;:&nbsp;users"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f7f5f">&lt;span&nbsp;<font color="#7f0055">m:out</font><font color="#000000">=</font><font color="#2a00ff">"for.index&nbsp;+&nbsp;1"</font>&gt;</font>james<font color="#3f7f5f">&lt;/span&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f7f5f">&lt;span&nbsp;<font color="#7f0055">m:out</font><font color="#000000">=</font><font color="#2a00ff">"user.name"</font>&gt;</font>james<font color="#3f7f5f">&lt;/span&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f7f5f">&lt;span&nbsp;<font color="#7f0055">m:out</font><font color="#000000">=</font><font color="#2a00ff">"user.coins"</font>&gt;</font>2.00<font color="#3f7f5f">&lt;/span&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/tr&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/table&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/body&gt;</font><br/>
<font color="#3f7f5f">&lt;/html&gt;</font><br/>
								<br/>
								<b>5 .How to use CommonTemplate API?</b> <a href="use_guide.html">more...</a><br/>
								<b>(1) Java: </b><font color="green">(&gt;=JRE1.5)</font><br/>
<font color="#3f7f5f">// import commontemplate module </font><br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.core.*; <br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.engine.*; <br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.standard.*; <br/>
<br/>
<font color="#7f0055"><b>import</b></font> java.util.*; <br/>
<br/>
<font color="#3f7f5f">// config and build factory </font><br/>
StandardConfiguration config = <font color="#7f0055"><b>new</b></font> StandardConfiguration(); <br/>
config.loadStandardConfiguration();<br/>
config.set...<br/>
config.add...<br/>
...<br/>
Factory factory = <font color="#7f0055"><b>new</b></font> Engine(config); <br/>
<br/>
<font color="#3f7f5f">// setting global context</font><br/>
GlobalContext globalContext = factory.getGlobalContext();<br/>
globalContext.defineVariable(<font color="#2a00ff">"name"</font>, <font color="#2a00ff">"value"</font>); <br/>
...<br/>
<br/>
<font color="#3f7f5f">// define data </font><br/>
Map model = ... <br/>
Appendable output = ... <br/>
Locale locale = ... <br/>
TimeZone timeZone = ... <br/>
<br/>
<font color="#3f7f5f">// setting context</font><br/>
Context context = factory.createContext(output, locale, timeZone);<br/>
context.defineAllVariables(model); <br/>
context.defineVariable(<font color="#2a00ff">"name"</font>, <font color="#2a00ff">"value"</font>); <br/>
... <br/>
<br/>
<font color="#3f7f5f">// run </font><br/>
Template template = factory.getTemplate("mytemplate.mtl"); <br/>
template.render(context); <br/>
<br/>
<font color="#3f7f5f">// clean (try finally) </font><br/>
context.clear(); <br/>
output.flush(); <br/>
output.close(); <br/>
								<br/>
								<b>(2) .Net: </b><font color="green">(&gt;=CLR2.0)</font><br/>
								......<br/>
!$
	<!--$end-->
<!--$end-->