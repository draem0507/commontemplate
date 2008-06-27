<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>1. Using API</b> <a href="../javadoc/index.html">JavaDoc&gt;&gt;</a><br/>
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
Writer out = ... <br/>
<br/>
<font color="#3f7f5f">// setting context</font><br/>
Context context = factory.createContext(out);<br/>
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
out.flush(); <br/>
out.close(); <br/>
!$
	<!--$end-->
<!--$end-->