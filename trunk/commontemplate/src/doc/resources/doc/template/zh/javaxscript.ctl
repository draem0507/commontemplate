<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
<b>1. 脚本引擎框架说明</b><br/>
javax.script是JDK标准的脚本引擎接口，为各种脚本引擎统一入口。<br/>
详细请参见：<a href="http://java.sun.com/javase/6/docs/api/javax/script/package-summary.html" target="_blank">http://java.sun.com/javase/6/docs/api/javax/script/package-summary.html</a><br/>
命令行脚本执行工具：<a href="http://java.sun.com/javase/6/docs/technotes/tools/share/jrunscript.html" target="_blank">http://java.sun.com/javase/6/docs/technotes/tools/share/jrunscript.html</a><br/>
如果使用JDK1.6以前的版本，需JSR223支持：<a href="http://www.jcp.org/en/jsr/detail?id=223" target="_blank">http://www.jcp.org/en/jsr/detail?id=223</a><br/>
<br/>
<b>2. 集成方式</b><br/>
导入集成jar包: commontemplate-javaxscript.jar <a href="download.html">下载...</a><br/>
此jar包采用javax.script所规定的方式打包，JDK将自动发现其中的引擎实现。<br/>
<br/>
<b>3. 调用方式</b><br/>
(1) 模板引擎调用方式:<br/>
$code{java}$!// 导入脚本引擎库
import javax.script.*;
// 创建脚本引擎管理器
ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
scriptEngineManager.put("name", "value"); // 设置全局变量
// 获取模板脚本引擎
ScriptEngine templateScriptEngine = scriptEngineManager.getEngineByName("commontemplate");
// 或者: ScriptEngine templateScriptEngine = scriptEngineManager.getEngineByExtension("ctl");
// 或者: ScriptEngine templateScriptEngine = scriptEngineManager.getEngineByMimeType("text/ctl");
templateScriptEngine.put("name", "value"); // 设置执行变量
String result = (String)templateScriptEngine.eval("$for{user : users} ${user.name} $end"); // 执行模板
!$
$end<br/>
(2) 表达式引擎调用方式:<br/>
$code{java}$!// 导入脚本引擎库
import javax.script.*;
// 创建脚本引擎管理器
ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
scriptEngineManager.put("name", "value"); // 设置全局变量
// 获取表达式脚本引擎
ScriptEngine expressionScriptEngine = scriptEngineManager.getEngineByName("commonexpression");
// 或者: ScriptEngine expressionScriptEngine = scriptEngineManager.getEngineByExtension("cel");
// 或者: ScriptEngine expressionScriptEngine = scriptEngineManager.getEngineByMimeType("text/cel");
expressionScriptEngine.put("name", "value"); // 设置执行变量
Integer result = (Integer)expressionScriptEngine.eval("user.coins + 2 * inc"); // 执行表达式
!$
$end<br/>
<br/>
	<!--$end-->
<!--$end-->