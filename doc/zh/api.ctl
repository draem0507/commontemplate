<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. 模板引擎使用</b> <a href="../javadoc/index.html">JavaDoc&gt;&gt;</a><br/>
								<b>(1) Java版: </b><font color="green">(环境需求JRE1.4.2_10以上)</font> <a href="http://java.sun.com/j2se/1.4.2/download.html" target="_blank">下载...</a><br/>
<font color="#7f0055"><b>import</b></font> java.util.*; <br/>
<br/>
<font color="#3f7f5f">// 导入commontemplate模块 </font><br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.core.*; <br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.engine.*; <br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.standard.*; <br/>
<font color="#7f0055"><b>import</b></font> org.commontemplate.tools.*; <br/>
<br/>
<font color="#3f7f5f">// 配置并建造引擎 (Engine是内同步线程安全的，可单例重用) </font><br/>
ConfigurationSettings config = PropertiesConfigurationLoader.loadStandardConfiguration(); <br/>
<font color="#3f7f5f">// 或者：ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration("xxx.properties"); </font><br/>
<font color="#3f7f5f">// 或者：StandardConfiguration config = new StandardConfiguration(); </font><br/>
Engine engine = <font color="#7f0055"><b>new</b></font> Engine(config); <br/>
<br/>
<font color="#3f7f5f">// 设置全局上下文 (GlobalContext在同一Engine创建的Context间共享)</font><br/>
GlobalContext globalContext = engine.getGlobalContext();<br/>
globalContext.put(<font color="#2a00ff">"name"</font>, <font color="#2a00ff">"value"</font>); <br/>
...<br/>
<br/>
<font color="#3f7f5f">// 定义运行期数据 </font><br/>
Map model = ... <br/>
Writer out = ... <br/>
<br/>
<font color="#3f7f5f">// 创建上下文 (Context非线程安全，应为每次执行创建新的Context)</font><br/>
<font color="#3f7f5f">// 注：国际化信息传入可用factory.createContext(output, locale, timeZone);</font><br/>
Context context = engine.createContext(output);<br/>
context.putAll(model); <br/>
context.put(<font color="#2a00ff">"name"</font>, <font color="#2a00ff">"value"</font>); <br/>
... <br/>
<br/>
<font color="#3f7f5f">// 执行模板 </font><br/>
Template template = engine.getTemplate(<font color="#2a00ff">"mytemplate.ctl"</font>); <br/>
template.render(context); <br/>
<br/>
<font color="#3f7f5f">// 清理上下文及输出(最好放在finally块中) </font><br/>
context.clear(); <br/>
output.flush(); <br/>
output.close(); <br/>
								<br/>
								<b>关系图如下：</b><br/>
								<img src="../images/uml/api.gif"/><br/>
								<br/>
								<b>工具类：</b> org.commontemplate.tools.TemplateRenderer<br/>
								Writer out = ...;<br/>
								<font color="#7f0055"><b>new</b></font> TemplateRenderer(<font color="#2a00ff">"$for{times} ${user.name} $end"</font>).put(<font color="#2a00ff">"times"</font>, 5).put(<font color="#2a00ff">"user"</font>, <font color="#7f0055"><b>new</b></font> User()).render(out);<br/>
								或者：<br/>
								String result = <font color="#7f0055"><b>new</b></font> TemplateRenderer(<font color="#2a00ff">"$for{times} ${user.name} $end"</font>).put(<font color="#2a00ff">"times"</font>, 5).put(<font color="#2a00ff">"user"</font>, <font color="#7f0055"><b>new</b></font> User()).evaluate();<br/>
								<br/>
								<b>(2) .Net版: </b><font color="green">(环境需求CLR1.1以上)</font><br/>
								......<br/>
								<br/>
								<b>二. 表达式引擎使用</b><br/>
								<b>Java API:</b><br/>
								<font color="#3f7f5f">// 导入相关模块 </font><br/>
								<font color="#7f0055"><b>import</b></font> org.commontemplate.core.*; <br/>
								<font color="#7f0055"><b>import</b></font> org.commontemplate.engine.expression.*;<br/>
								<font color="#7f0055"><b>import</b></font> org.commontemplate.standard.*; <br/>
								<font color="#7f0055"><b>import</b></font> org.commontemplate.tools.*; <br/>
								<br/>
								<font color="#3f7f5f">// 配置并建造引擎</font><br/>
								ExpressionConfigurationSettings config = PropertiesConfigurationLoader.loadStandardExpressionConfiguration();<br/>
								<font color="#3f7f5f">// 或者：ExpressionConfigurationSettings config = PropertiesConfigurationLoader.loadExpressionConfiguration("xxx.properties");</font><br/>
								ExpressionEngine engine = <font color="#7f0055"><b>new</b></font> ExpressionEngine(config);<br/>
								<br/>
								<font color="#3f7f5f">// 创建上下文</font><br/>
								VariableResolver context = ...<br/>
								<br/>
								<font color="#3f7f5f">// 解析表达式</font><br/>
								Expression expression = engine.parseExpression(<font color="#2a00ff">"1 + 1"</font>);<br/>
								Object result = expression.evaluate(context);<br/>
								<br/>
								<b>工具类:</b> org.commontemplate.tools.ExpressionEvaluator<br/>
								Object result = <font color="#7f0055"><b>new</b></font> ExpressionEvaluator(<font color="#2a00ff">"book.price * discount + 1"</font>).put(<font color="#2a00ff">"book"</font>, <font color="#7f0055"><b>new</b></font> Book()).put(<font color="#2a00ff">"discount"</font>, 0.8).evaluate();<br/>
!$
	<!--$end-->
<!--$end-->