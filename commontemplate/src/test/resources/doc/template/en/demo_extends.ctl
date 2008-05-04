<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
Common Page: (common.ctl)<br/>
<font color="#3f7f5f">&lt;html&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;head&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$zone{"head"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;title&gt;</font>$msg{"application.title"}<font color="#3f7f5f">&lt;/title&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;meta&nbsp;<font color="#7f0055">http-equiv</font><font color="#000000">=</font><font color="#2a00ff">"Content-Type"</font>&nbsp;<font color="#7f0055">content</font><font color="#000000">=</font><font color="#2a00ff">"text/html;charset=UTF-8"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;link&nbsp;<font color="#7f0055">rel</font><font color="#000000">=</font><font color="#2a00ff">"stylesheet"</font>&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"text/css"</font>&nbsp;<font color="#7f0055">href</font><font color="#000000">=</font><font color="#2a00ff">"styles/common.css"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/head&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;body&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$zone{"menu"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;div&gt;</font>common&nbsp;menu...<font color="#3f7f5f">&lt;/div&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$zone{"body"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$zone{"copyright"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$msg{"application.copyright"}...<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/body&gt;</font><br/>
<font color="#3f7f5f">&lt;/html&gt;</font><br/>
<br/>
Extends: <br/>
<font color="#3f5fbf">&lt;!--$extends{"common.ctl"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$overzone{"body"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;custom&nbsp;body<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
<br/>
Override Head: <br/>
<font color="#3f5fbf">&lt;!--$extends{"common.ctl"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$overzone{"head"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$superzone--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;scrpt&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"text/javascript"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;custom&nbsp;head<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/scrpt&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$overzone{"body"}--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;custom&nbsp;body<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
<font color="#3f5fbf">&lt;!--$end--&gt;</font><br/>
								<br/>
								<a href="javascript:window.history.back(-1)">&lt;&lt;Back</a><br/>
!$
	<!--$end-->
<!--$end-->