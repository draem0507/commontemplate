<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. 语法规则:</b><br/>
								整个模板语言只有一个语法规则：<br/>
								<b>$指令名{<a href="expression.html">参数表达式</a>}</b><br/>
								<font color="green">(注：指令名只能包含字母，下划线，数字，点号)</font><br/>
								<font color="green">(注：指令名或参数表达式均可以为空，但不可以同时为空，参数表达式为空时，大括号可省)</font><br/>
								<br/>
								简化语法规则：<font color="green">(注：从0.8.5版本开始)</font><br/>
								<b>$指令名:参数名</b><br/>
								等价于：<br/>
								$指令名{"参数名"}<br/>
								如：<br/>
								$end:if 等价于 $end{"if"}<br/>
								$data:xml 等价于 $data{"xml"}<br/>
								$block:search 等价于 $block{"search"}<br/>
								$macro:button 等价于 $macro{"button"}<br/>
								$zone:body 等价于 $zone{"body"}<br/>
								$time:xxx 等价于 $time{"xxx"}<br/>
								<br/>
								<b>二. 特殊符转义:</b><br/>
								<b>(1) 使用 \$ 转义，输出非指令 $ 符</b><br/>
								如：“\${name}”输出“${name}”<br/>
								<b>(2) 使用 \\ 取消转义，输出非转义 \ 符</b><br/>
								如：“\\${name}”输出“\value”<br/>
								“\\\${name}”等价于“\\”+“\${name}”的组合，输出“\” + “${name}”，即“\${name}”<br/>
								“\\\\${name}”等价于“\\”+“\\”+“${name}”的组合，输出“\” + “\” +  “value”，即“\\value”<br/>
								<font color="green">(注：这里的“\”都是指紧挨在“$”前的“\”，未紧挨在“$”前的任意“\”均不作任何处理，以避免模板中到处使用双斜杠)</font><br/>
								<br/>
								<b>三. 特殊指令: </b><br/>
								<b>(1) 行注释:</b> 忽略其后同一行的内容 <font color="green">(注: 可注释不合法的指令内容)</font><br/>
								运行期不保留: <font color="green">(注: 编译后即抛弃)</font><br/>
								$# line comment ...<br/>
								运行期保留: <font color="green">(注: 可通过Visitor访问到)</font><br/>
								$## line comment ...<br/>
								<b>(2) 多行块注释:</b> 忽略其包含的内容 <font color="green">(注: 可注释不合法的指令内容，自身不可嵌套，但可嵌套行注释和不解释块等)</font><br/>
								运行期不保留: <font color="green">(注: 编译后即抛弃)</font><br/>
								$*<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;block comment ...<br/>
								*$<br/>
								运行期保留: <font color="green">(注: 可通过Visitor访问到)</font><br/>
								$**<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;block comment ...<br/>
								*$<br/>
								<b>(3) 不解释块:</b> 将其包含的内容作为纯文本输出 <font color="green">(注: 可包含不合法的指令内容，自身不可嵌套，但可嵌套行注释和块注释等)</font><br/>
								$!<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;no parse, eg: $if ... <br/>
								!$!\$$!<br/>
								<b>(4) 通用结束指令:</b><br/>
								$end 或者 $end{"if"} 或者 $end:if<br/>
								<font color="green">(注: 参数表示被结束块指令的名称, 编译时将进行检查(不匹配将抛出异常), 没有参数表示自动匹配)</font><br/>
								<br/>
								<b>四. 标准指令:</b><br/>
								<b>(1) 输出指令:</b><br/>
								表达式结果输出: <font color="green">(注：指令名称为空的指令)</font><br/>
								${user.name}<br/>
								${user.coins + 100}<br/>
								国际化信息输出:<br/>
								$msg{"home.title"}<br/>
								$msg{"home.title", arg0, arg1}<br/>
								输出块指令：<font color="green">(注：与上面两个指令功能相同，用于注释版语法外套)</font><br/>
								表达式结果输出：忽略指令内部块内容<br/>
								$out{user.name} James $end<br/>
								&lt;!--$out{user.name}--&gt; James &lt;!--$end--&gt;<br/>
								&lt;!--$out{user.coins + 100}--&gt; 112 &lt;!--$end--&gt;<br/>
								国际化信息输出：将内部块内容作为默认值<br/>
								$message{"key", arg0, arg1} default value $end<br/>
								$message{"home.title"} welcome $end<br/>
								&lt;!--$message{"home.title"}--&gt; welcome &lt;!--$end--&gt;<br/>
								<b>(2) 条件指令:</b><br/>
								$if{user.name == "james"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$elseif{ user.name == "kent"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$else<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								其中，if和elseif指令均可以直接判断null和empty对象，如：<br/>
								$if{user} 等价于 $if{user != null}<br/>
								$if{list} 等价于 $if{list != null && list.size > 0}<br/>
								$if{string} 等价于 $if{string != null && string.length > 0}<br/>
								<b>(3) 迭代指令:</b><br/>
								定义循环显示项:<br/>
								$cycle{color: ("red", "blue", "green")}<br/>
								迭代集合或数组:<br/>
								$for{user : users}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;从循环显示项中取值 <font color="green">(注：cycle变量每次next取值时向后滚动，到最后一个值时将循环到第一个值)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${color.next} ${color.value} ${color.index}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;中断循环 <font color="green">(注：条件判定与指令合并，以避免冗长的语句：$if {for.count &gt; 5} $break $end)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$break{for.count &gt; 5}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;继续循环<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$continue{user.name == null}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;状态<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">(注：for变量(非关键字)持有循环过程的状态，多级嵌套循环时，可以用super.for.index获取上级循环的状态)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">(注：index从0开始，count从1开始，也就是count = index + 1)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${for.index} ${for.count} ${for.size} ${for.first} ${for.middle} ${for.last} ${for.odd} ${for.even}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;递归级别状态<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">(注：非递归迭代时level总是为0，递归迭代如：$for{menu -> children : menus})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${for.level}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;取值<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${user.name}<br/>
								$forelse <font color="green">(注：仿$if ... $else ...语法，以避免多重判断语句，当循环集合为空时执行)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;have no users...<br/>
								$end<br/>
								特殊迭代举例：<br/>
								<b>a.</b> 简单次数迭代：$for{10}，迭代10次，不产生迭代项数据，但可以用状态信息${for.index}等，当次数小于或等于零时不迭代<br/>
								<b>b.</b> 递归迭代：$for{menu -> children : menus}，递归迭代menu的children属性，直到为空，通常用于输出树结构。<br/>
								<b>c.</b> 并行迭代：$for{item1 : list1, item2 : list2}，$for{i : (1..10), user : users}，多个集合并行取next值，以最长的集合作为结束，短集合自动补null值<br/>
								<b>d.</b> 数字序列迭代：$for{num : 1..9}，$for{num : -2..5}，$for{num : 5..-2}<br/>
								<b>e.</b> 子集合迭代：$for{item : list[0..2]}，迭代list中索引号从0到2的元素(包含边界)，索引号越界时忽略，也可以用$for{item : list[2..*]}表示直到列表结束<br/>
								<b>f.</b> 过滤迭代：$for{item : list[=> item != 'xxx']}，$for{user : users[u => u.name != 'guest']}，过滤掉用户名为"guest"的用户，参见"=&gt;"操作符使用<br/>
								<b>g.</b> 排序迭代：$for{item : list orderby ("+property1", "-property2")}，$for{book : books orderby "+price"} 将books内的元素按price属性值升序排列后再迭代 (参见orderby操作符使用)<br/>
								<b>h.</b> 非空选择迭代：$for{item : list1 || list2 || list3}，从左至右选第一个非空的集合进行迭代<br/>
								<b>(4) 变量指令:</b><br/>
								声明局部变量: <font color="green">(注：通常用于隐藏上级同名变量)</font><br/>
								$local{name = "james"}<br/>
								当前模板根级上下文变量定义: <br/>
								$root{name = "james"}<br/>
								全局上下文变量定义: <br/>
								$global{name = "james"}<br/>
								任意区间变量定义: <br/>
								$var{super -&gt; name = "james"}<br/>
								$var{session -&gt; name = "james"}<br/>
								$var{global -&gt; name = "james"} <font color="green">(注：与$global相同)</font><br/>
								$var{root -&gt; name = "james"} <font color="green">(注：与$root相同)</font><br/>
								$var{local -&gt; name = "james"} <font color="green">(注：与$local相同)</font><br/>
								$var{name = "james"} <font color="green">(注：等价于$var{local -> name = "james"})</font><br/>
								给最近区域的变量赋值: <font color="green">(注：若直到根区域均未找到相应变量，则在当前区域创建局部变量)</font><br/>
								$set{name = "james"} <font color="green">(注：不能修改即有数据模型状态，也就是不能使用像：$set{user.name = "james"}的层级设值方式，以遵守模板无副作用契约，避免引入业务逻辑)</font><br/>
								如果变量为空或未定义，则给其赋初始值: <br/>
								$init{name = "guest"}<br/>
								<b>(5) 块变量指令:</b><br/>
								将模板块定义为变量: <font color="green">(注: 如果需要将模板块传递到宏或其它模板中, 可以使用块变量)</font><br/>
								$block{"myblock"} 或者 $block:myblock<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								显示块变量: (执行块变量所指模板块)<br/>
								$show{"myblock"}<br/>
								<b>(6) 数据指令:</b><br/>
								数据块：<br/>
								$data{"xml"} 或者 $data:xml<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								装载外部数据：<br/>
								$load{"xxx.xml"} <font color="green">(注：根据文件扩展名识别类型)</font><br/>
								$load{xml: "xxx.xml"} <br/>
								<font color="green">(注：内置支持xml,json,properties,yaml等数据格式，并且数据格式是可扩展的)</font> <a href="viewer.html">格式说明...</a><br/>
								<b>(7) 包含指令:</b><br/>
								内嵌其它模板: <font color="green">(注：被内嵌的文件<b>可以</b>访问当前上下文的变量，<b>不可以</b>传参)</font><br/>
								$embed{"common.ctl"}<br/>
								$embed{"common.ctl", "UTF-8"}<br/>
								内嵌其它模板的一部分: <font color="green">(注：#后为zone的名称, 参见$zone指令)</font><br/>
								$embed{"common.ctl#body"}<br/>
								包含其它模板的输出: <font color="green">(注：只包含输出，被包含的文件在新的上下文中执行，<b>不能</b>访问当前上下文的变量，<b>可以</b>传参)</font><br/>
								$include{"common.ctl"}<br/>
								$include{"common.ctl", "UTF-8"}<br/>
								$include{"common.ctl", (param1: "value1", param2: "value2")}<br/>
								$include{"common.ctl", "UTF-8", (param1: "value1", param2: "value2")}<br/>
								包含其它模板的一部分: <font color="green">(注：#后为zone的名称, 参见$zone指令)</font><br/>
								$include{"common.ctl#body"}<br/>
								显示文件的内容: <font color="green">(注：不解析其内容)</font><br/>
								$display{"article.txt"}<br/>
								$display{"article.txt", "UTF-8"}<br/>
								抓取远程文件的内容: <font color="green">(注：只在Web环境下有效)</font><br/>
								$snatch{"list.jsp"} 相对于当前页面路径目录<br/>
								$snatch{"../list.jsp"} 相对于当前页面路径的上级目录<br/>
								$snatch{"/list.jsp"} 相对于Web根目录<br/>
								$snatch{"/list.jsp", 'UTF-8'} 指定编码<br/>
								$snatch{"http://www.163.com"} 远程页面<br/>
								<b>(8) 宏指令:</b><br/>
								<font color="red">(注：0.8.5版本对宏指令进行不兼容改动，宏调用方式改为可访问上下文，块指令调用后缀由"_block"改成".begin"，$using指令代替原有$import指令，$import指令重新实现)</font><br/>
								宏定义:<br/>
								$macro{"button"} 或者 $macro:button<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;回调调用者的内部块，如果为行指令方式调用，则inner为空<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$inner{param3: "value3"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$return{name == null}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								宏的行指令(Line)方式调用: <font color="green">(注：宏调用<b>可以</b>传参，也<b>可以</b>访问当前上下文的变量)</font><br/>
								$button{param1: "value1", param2: "value2"}<br/>
								宏的块指令(Block)方式调用:<br/>
								$button.begin{param1: "value1", param2: "value2"} <font color="green">(注：以".begin"结尾表示块指令调用)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								导入模板文件中所有的宏: <br/>
								$import{"mymacro.ctl"}<br/>
								导入模板文件中所有的宏, 并指定名称空间: <br/>
								$import{my : "mymacro.ctl"} <font color="green">(注：调用时需带上"名称空间.", 如：$my.button{xxx})</font><br/>
								导入模板文件中指定的宏:<br/>
								$import{"mymacro.ctl#button"} <font color="green">(注：#后为macro的名称, 参见$macro指令)</font><br/>
								导入模板文件中指定的宏, 并指定名称空间:<br/>
								$import{my : "mymacro.ctl#button"} <font color="green">(注：调用时需带上"名称空间.", 如：$my.button{xxx})</font><br/>
								使用模板文件作为宏:<br/>
								$using{button : "button.ctl"}<br/>
								使用模板文件中的宏作为宏:<br/>
								$using{button : "mymacro.ctl#button"} <font color="green">(注：#后为macro的名称, 参见$macro指令)</font><br/>
								<b>(9) 继承指令:</b> <font color="green">(注：通常用于布局layout)</font> <a href="demo_extends.html">示例&gt;&gt;</a><br/>
								模板块区域定义: <font color="green">(注：在父模板中)</font><br/>
								$zone{"body"} 或者 $zone:body<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								继承模板: <font color="green">(注：在子模板中)</font><br/>
								$extends{"super.ctl"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;覆盖父模板块:<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$overzone{"body"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调用父模板块:<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$superzone<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$end<br/>
								$end<br/>
								<b>(10) 动态指令:</b><br/>
								动态执行模板:<br/>
								$exec{templateString}<br/>
								动态表达式求值:<br/>
								$eval{expressionString}<br/>
								<b>(11) 过滤指令:</b><br/>
								输出过滤指令:  <font color="green">(注：只过滤动态内容，不过滤文本块)</font><br/>
								$filter{x => "&lt;b&gt;" + x.escapeHtml + "&lt;/b&gt;"} <font color="green">(注：缺省名称为value，如：$filter{=> value.escapeHtml})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								输出全过滤指令:  <font color="green">(注：过滤所有输出，包括文本块)</font><br/>
								$filterAll{x => x.escapeHtml} <font color="green">(注：缺省名称为value，如：$filterAll{=> value.escapeHtml})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								压缩空格: <font color="green">(注: 将多个连续的空白符压成一个空格)</font><br/>
								$compress<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								转义特殊符:<br/>
								$escape{"html", "js", "url", "base64"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								<!--
								代码着色:<br/>
								$code{"java"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								关键字加亮:<br/>
								$keyword{"word1", "word2"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								-->
								<b>(12) 调试指令:</b><br/>
								停止页面解析：<br/>
								$stop<br/>
								停止页面解析, 并标明原因：<br/>
								$stop{"reason"}<br/>
								性能监测：(记录其内部块的运行时间，并将时间存入全局的变量中)<br/>
								$time{"xxxTime"} 或者 $time:xxxTime<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								above block spend time: ${xxxTime} ms<br/>
								调试日志：<br/>
								$log{"debug messages..."}<br/>
								$log{debug: "debug messages..."}<br/>
								$log{info: "info messages..."}<br/>
								$log{warn: "warn messages..."}<br/>
								$log{error: "error messages..."}<br/>
								单步调试断点：(此指令将在其所在行设置断点) <a href="debug.html">更多...</a><br/>
								$breakpoint 或者 $. <font color="green">(注: 指令名称为点号的指令)</font><br/>
								<br/>
								<b>五. 变量区间:</b><br/>
								页面内的每一个块指令(如$if,$for等)都会创建相应的LocalContext, 变量取值时逐级向上查找, <br/>
								如果当前LocalContext中的变量与上级变量重名，可以使用super关键字跳到上级取值，如：${super.var}<br/>
								Web应用中变量查找顺序：页面内, root, model, request, parameter, header, session, cookie, application, global<br/>
								可以用context["区域名"]在指定范围内查找，如：${context["session"].loginUser}<br/>
								另外，也可以用context["指令名"]查找最近的某个块指令区域内的变量，如：${context["for"].xxx} ${context["if"].xxx}<br/>
								<br/>
								<b>六. HTML语法扩展</b><br/>
								<b>(1) 注释版语法外套</b><br/>
								自动去除指令两边的HTML注释符，如：
								&lt;!--$指令{表达式}--&gt;<br/>
								<b>(2) 属性版语法外套</b><br/>
								自动将名称空间为“ct:”的HTML标签属性转换成指令，如：<br/>
								&lt;table ct:if="users != null && users.size &gt; 0"&gt;...&lt;table&gt; (只能用于块指令)<br/>
								<br/>
								<b>七. 举例:</b><br/>
								<b>(1) 标准语法：</b><br/>
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
								<b>(2) 注释版语法外套：</b><br/>
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
								<b>(3) 属性版语法外套：</b><br/>
<font color="#3f7f5f">&lt;html&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;body&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;table&nbsp;<font color="#7f0055">ct:if</font><font color="#000000">=</font><font color="#2a00ff">"users&nbsp;!=&nbsp;null&nbsp;&amp;&amp;&nbsp;users.size&nbsp;&gt;&nbsp;0"</font>&nbsp;<font color="#7f0055">border</font><font color="#000000">=</font><font color="#2a00ff">"1"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tr&nbsp;<font color="#7f0055">ct:for</font><font color="#000000">=</font><font color="#2a00ff">"user&nbsp;:&nbsp;users"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f7f5f">&lt;span&nbsp;<font color="#7f0055">ct:out</font><font color="#000000">=</font><font color="#2a00ff">"for.index&nbsp;+&nbsp;1"</font>&gt;</font>1<font color="#3f7f5f">&lt;/span&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f7f5f">&lt;span&nbsp;<font color="#7f0055">ct:out</font><font color="#000000">=</font><font color="#2a00ff">"user.name"</font>&gt;</font>james<font color="#3f7f5f">&lt;/span&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;td&gt;</font><font color="#3f7f5f">&lt;span&nbsp;<font color="#7f0055">ct:out</font><font color="#000000">=</font><font color="#2a00ff">"user.coins"</font>&gt;</font>2.00<font color="#3f7f5f">&lt;/span&gt;</font><font color="#3f7f5f">&lt;/td&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/tr&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/table&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/body&gt;</font><br/>
<font color="#3f7f5f">&lt;/html&gt;</font><br/>
!$
	<!--$end-->
<!--$end-->