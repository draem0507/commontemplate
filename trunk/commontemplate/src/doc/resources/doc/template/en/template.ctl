<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>1. Syntax:</b><br/>
								Only syntax rule: <br/>
								$directiveName{<a href="expression_guide.html">expression</a>}<br/>
								<br/>
								<b>2. Escape:</b><br/>
								(1) \$ escape directive leader character<br/>
								eg: \${name} output: ${name}<br/>
								(2) \\ cancel the escape character<br/>
								eg: \\${name} output: \value<br/>
								<br/>
								<b>3. Special Directives:</b><br/>
								(1) Line Comment Directive:<br/>
								$# comment text ...<br/>
								<br/>
								(2) Block Comment Directive:<br/>
								$*<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;comment text ...<br/>
								*$<br/>
								<br/>
								(3) No Parse Block Directive:<br/>
								$!<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;no parse, eg: $if ... <br/>
								!\$<br/>
								<br/>
								(4) General End Block Directive:<br/>
								$end<br/>
								<br/>
								<b>4. Standard Directives:</b><br/>
								<b>(1) Output Directives:</b><br/>
								${user.name}<br/>
								$msg{"home.title"} <br/>
								$msg{"home.title", arg0, arg1} <br/>
								<b>(2) Condition Directives:</b><br/>
								$if{user.name == "james"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$elseif{ user.name == "kent"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$else<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								<b>(3) Iteration Directives:</b><br/>
								$cycle{color: "red", "blue", "green"}<br/>
								$for{users, "user", "status"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${color.next}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$beak{for.count &gt; 5}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$continue{user.name == null}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${for.index} ${for.index} ${for.count} ${for.size} ${for.first} ${for.last} ${for.middle} ${for.even} ${for.odd}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${user.name}<br/>
								$forelse<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;have no users...<br/>
								$end<br/>
								<b>(4) Variable Directives:</b><br/>
								$var{name = "james"}<br/>
								$set{name = "james"}<br/>
								$init{name = "guest"}<br/>
								<b>(5) Include Directives:</b><br/>
								$include{"common.mtl", (param1: value1, param2: value2)}<br/>
								$inline{"common.mtl"}<br/>
								$display{"article.txt"}<br/>
								<b>(6) Macro Directives:</b><br/>
								$macro{"mymacro"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$import{mymacro : "mymacro.ctm"}<br/>
								$-mymacro{param1: "value1", param2: "value2"}<br/>
								$+mymacro{param1: "value1", param2: "value2"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								<b>(7) Extend Directives:</b> <a href="demo_extends.html">Demo&gt;&gt;</a><br/>
								$zone{"myzone"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$extend{"super.ctl"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$overzone{"myzone"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$superzone<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$end<br/>
								$end<br/>
								<b>(8) Block Directives:</b><br/>
								$block{"myblock"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$show{"myblock"}<br/>
								$using{mymacro : "myblock"}<br/>
								<b>(9) Dynamic Directives:</b><br/>
								$exec{templateString}<br/>
								$eval{expressionString}<br/>
								<b>(10) Debug Directives:</b><br/>
								$stop<br/>
								$time{"xxx_time"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$log{"debug messages..."}<br/>
								$log{debug: "debug messages..."}<br/>
								$log{info: "info messages..."}<br/>
								$log{warn: "warn messages..."}<br/>
								$log{error: "error messages..."}<br/>
								<b>(11) Filter Directives:</b><br/>
								$compress<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								$escape{"html", "js", "url", "base64"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$code{"java"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$keyword{"word1", "word2"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								<br/>
								<b>3. Html Syntax Cost:</b><br/>
								(1) &lt;!--$name{expression}--&gt; Comment Directive<br/>
								(2) &lt;table m:if="users != null && users.size &gt; 0"&gt;...&lt;table&gt; Attribute Directive (if, for, out)<br/>
								<br/>
								<b>4. Servlet Root Model:</b><br/>
								Servlet Scope: param, header, request, session, cookie, application<br/>
								<br/>
								<b>5. For Example:</b><br/>
								(1) Standard Syntax:<br/>
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
								(2) Comment Syntax Cost:<br/>
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
								<br/>
								(3) Attribute Syntax Cost:<br/>
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
!$
	<!--$end-->
<!--$end-->