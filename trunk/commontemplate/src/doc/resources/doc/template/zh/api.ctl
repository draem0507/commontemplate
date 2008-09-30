<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
<b>一. 模板引擎使用</b> <a href="../javadoc/index.html">JavaDoc...</a><br/>
<b>(1) Java版: </b><font color="green">(环境需求JRE1.4.2以上)</font> <a href="http://java.sun.com/j2se/1.4.2/downloads.html" target="_blank">下载jre...</a><br/>
$code{java}// 导入util和io包
import java.util.*;
import java.io.*;

// 导入commontemplate模块
import org.commontemplate.core.*;
import org.commontemplate.engine.*;
import org.commontemplate.standard.*;
import org.commontemplate.tools.*;

// 配置并建造引擎 (Engine是内同步线程安全的，并且Engine的装配比较耗时，请单实例重用Engine类)
ConfigurationSettings config = PropertiesConfigurationLoader.loadConfiguration("commontemplate.properties");
// 或者：ConfigurationSettings config = PropertiesConfigurationLoader.loadStandardConfiguration();
// 或者：StandardConfiguration config = new StandardConfiguration();
// config.setXXX();
Engine engine = new Engine(config);

// 设置全局上下文 (GlobalContext在同一Engine创建的Context间共享)
GlobalContext globalContext = engine.getGlobalContext();
globalContext.put("name", "value");
...

// 定义运行期数据
Map model = ...
Writer out = ...

// 创建上下文 (Context非线程安全，应为每次执行创建新的Context)
Context context = engine.createContext(out);
context.putAll(model);
context.put("name", "value");
...

// 执行模板
Template template = engine.getTemplate("mytemplate.ctl");
template.render(context);

// 清理上下文及输出(最好放在finally块中)
context.clear();
out.flush();
out.close();
$end<br/>
<br/>
<b>关系图如下：</b><br/>
<img src="../images/uml/api.gif"/><br/>
<br/>
<b>工具类：</b> org.commontemplate.tools.TemplateRenderer<br/>
Writer out = ...;<br/>
<font color="#7f0055"><b>new</b></font> TemplateRenderer(<font color="#2a00ff">"\$for{times} \${user.name} \$end"</font>).put(<font color="#2a00ff">"times"</font>, 5).put(<font color="#2a00ff">"user"</font>, <font color="#7f0055"><b>new</b></font> User(1, <font color="#2a00ff">"james"</font>)).render(out);<br/>
或者：<br/>
String result = <font color="#7f0055"><b>new</b></font> TemplateRenderer(<font color="#2a00ff">"\$for{times} \${user.name} \$end"</font>).put(<font color="#2a00ff">"times"</font>, 5).put(<font color="#2a00ff">"user"</font>, <font color="#7f0055"><b>new</b></font> User(1, <font color="#2a00ff">"james"</font>)).evaluate();<br/>
<br/>
<b>(2) .Net版: </b><font color="green">(环境需求CLR1.1以上)</font><br/>
......<br/>
<br/>
<b>二. 表达式引擎使用</b><br/>
<b>Java API:</b><br/>
$code{java}// 导入相关模块
import org.commontemplate.core.*;
import org.commontemplate.engine.expression.*;
import org.commontemplate.standard.*;
import org.commontemplate.tools.*;

// 配置并建造引擎
ExpressionConfigurationSettings config = PropertiesConfigurationLoader.loadExpressionConfiguration("commonexpression.properties");
// 或者：ExpressionConfigurationSettings config = PropertiesConfigurationLoader.loadStandardExpressionConfiguration();
// config.setXXX();
ExpressionEngine engine = new ExpressionEngine(config);

// 创建上下文
ExpressionContext context = new ExpressionContext();
context.put("price", new Integer(17));

// 解析表达式
Expression expression = engine.parseExpression("price * 3 + 5");
Object result = expression.evaluate(context);
$end<br/>
<br/>
<b>工具类:</b> org.commontemplate.tools.ExpressionEvaluator<br/>
Object result = <font color="#7f0055"><b>new</b></font> ExpressionEvaluator(<font color="#2a00ff">"book.price * discount + 1"</font>).put(<font color="#2a00ff">"book"</font>, <font color="#7f0055"><b>new</b></font> Book()).put(<font color="#2a00ff">"discount"</font>, 0.8).evaluate();<br/>
	<!--$end-->
<!--$end-->