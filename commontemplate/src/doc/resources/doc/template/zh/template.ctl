<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. 语法规则:</b><br/>
								整个模板语言只有一个语法规则：<br/>
								<b>$指令名{<a href="expression.html">参数表达式</a>}</b><br/>
								<font color="green">(注：指令名只能包含字母，下划线，数字，点号)</font><br/>
								<font color="green">(注：指令名或参数表达式均可以为空，参数表达式为空时，大括号可省)</font><br/>
								<font color="green">(注：如果指令名称与字母相连，大括号不可省，如：$else{}xxxx，而不能为$elsexxxx，否则引起歧义)</font><br/>
								<br/>
								<b>二. 特殊符转义:</b><br/>
								<b>(1) \ 为转义符，\\ 取消转义(或自转义)</b><br/>
								<font color="green">(注：未处于转义位置的斜线不作任何处理，以避免模板中到处使用双斜线)</font><br/>
								<b>(2) 使用 \$ 转义指令前导符</b><br/>
								如：“\${name}”输出“${name}”<br/>
								如：“\\${name}”输出“\value” <font color="green">(注：前导符未被转义)</font><br/>
								如：“\\\${name}”输出“\${name}”<br/>
								<b>(3) 使用 \\\!$ 转义不解释块结束符</b><br/>
								如：“$!aaa\\\!$bbb\!$”输出“aaa\!$bbb”<br/>
								如：“$!aaa\\\\\!$”输出“aaa\” <font color="green">(注：结束符未被转义)</font><br/>
								如：“$!aaa\\\\\\\!$bbb\!$”输出“aaa\\\!$bbb”<br/>
								<b>(4) 使用 \*$ 转义注释块结束符</b><br/>
								如：“$*aaa\*$bbb*$” 注释内容为“aaa*$bbb”<br/>
								如：“$*aaa\\*$” 注释内容为“aaa\” <font color="green">(注：结束符未被转义)</font><br/>
								如：“$*aaa\\\*$bbb*$” 注释内容为“aaa\*$bbb”<br/>
								<br/>
								<b>三. 编译指令: </b><br/>
								<b>(1) 行注释:</b> 忽略其后同一行的内容 <font color="green">(注: 可注释不合法的指令内容，注释内容<b>不包含</b>最后的换行符)</font><br/>
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
								\!$<br/>
								<b>(4) 通用结束指令:</b><br/>
								$end 或者 $end{if}<br/>
								<font color="green">(注: 参数表示被结束块指令的名称, 编译时将进行检查(不匹配将抛出异常), 没有参数表示自动匹配)</font><br/>
								<font color="green">(注: 因$end指令为编译指令，参数表达式不解析，直接作为字符串)</font><br/>
								<font color="green">(注: $end总是与最近的块指令匹配，如：$if{}...$end{if} 和 $if{}...$else...$end{else})</font><br/>
								<br/>
								<b>四. 标准指令:</b><br/>
								<b>(1) 输出指令:</b><br/>
								表达式结果输出: <font color="green">(注：指令名称为空的指令)</font><br/>
								${user.name}<br/>
								${user.coins + 100}<br/>
								国际化信息输出:<br/>
								$msg{"home.title"}<br/>
								$msg{"home.title", arg0, arg1}<br/>
								块指令：<font color="green">(注：与上面两个指令功能相同，用于语法外套)</font><br/>
								表达式结果输出：忽略指令内部块内容<br/>
								$out{user.name} James $end<br/>
								&lt;!--$out{user.name}--&gt; James &lt;!--$end--&gt;<br/>
								&lt;!--$out{user.coins + 100}--&gt; 112 &lt;!--$end--&gt;<br/>
								国际化信息输出：将内部块内容作为默认值<br/>
								$message{"key", arg0, arg1} default value $end<br/>
								$message{"home.title"} welcome $end<br/>
								&lt;!--$message{"home.title"}--&gt; welcome &lt;!--$end--&gt;<br/>
								<b>(2) 条件指令:</b><br/>
								如果参数条件为真则执行其内部块:<br/>
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
								&nbsp;&nbsp;&nbsp;&nbsp;从循环显示项中取值 <font color="green">(注：可用于交替颜色的表格行)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${color.next} <font color="green">(注：cycle变量每次next取值时向后滚动，到最后一个值时将循环到第一个值)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${color.value} ${color.index} <font color="green">(注：取值但不滚动)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${color.values} <font color="green">(注：取原始定义数据集合)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;中断循环<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$break{for.count &gt; 5} <font color="green">(注：条件判定与指令合并，以避免冗长的语句：$if {for.count &gt; 5} $break $end)</font><br/>
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
								$else <font color="green">(注：类似$if ... $else ...语法，以避免多重判断语句，当循环集合为空时执行)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;have no users...<br/>
								$end<br/>
								特殊迭代举例：<br/>
								<b>a.</b> 简单次数迭代：$for{10}，迭代10次，不产生迭代项数据，但可以用状态信息${for.index}等，当次数小于或等于零时不迭代<br/>
								<b>b.</b> 递归迭代：$for{menu -> children : menus}，递归迭代menu的children属性，直到为空，通常用于输出树结构。<br/>
								<b>c.</b> 并行迭代：$for{item1 : list1, item2 : list2}，$for{i : (1..10), user : users}，多个集合并行取next值，以最长的集合作为结束，短集合自动补null值<br/>
								<b>d.</b> 数字序列迭代：$for{num : (1..9)}，$for{num : (-2..5)}，$for{num : (5..-2)} <font color="green">(注：双点号的优先级低于冒号，必需用括号)</font><br/>
								<b>e.</b> 子集合迭代：$for{item : list[0..2]}，迭代list中索引号从0到2的元素(包含边界)，索引号越界时忽略，也可以用$for{item : list[2..*]}表示直到列表结束<br/>
								<b>f.</b> 过滤迭代：$for{item : list[=> item != 'xxx']}，$for{user : users[u => u.name != 'guest']}，过滤掉用户名为"guest"的用户，参见"=&gt;"操作符使用<br/>
								<b>g.</b> 排序迭代：$for{item : list orderby ("+property1", "-property2")}，$for{book : books orderby "+price"} 将books内的元素按price属性值升序排列后再迭代 (参见orderby操作符使用)<br/>
								<b>h.</b> 非空选择迭代：$for{item : list1 || list2 || list3}，从左至右选第一个非空的集合进行迭代<br/>
								<b>(4) 变量指令:</b><br/>
								变量定义:<br/>
								局部变量定义<br/>
								$var{name = "james"} <font color="green">(注：通常用于隐藏上级同名变量)</font><br/>
								在上一级上下文定义变量<br/>
								$var{super -&gt; name = "james"}<br/>
								在根级上下文定义变量<br/>
								$var{root -&gt; name = "james"}<br/>
								在全局上下文定义变量(整个引擎内共享)<br/>
								$var{global -&gt; name = "james"}<br/>
								在指定上下文定义变量<br/>
								$var{session -&gt; name = "james"}<br/>
								常见上下文简化指令:<br/>
								$super{name = "james"} 等价于 $var{super -> name = "james"}<br/>
								$root{name = "james"} 等价于 $var{root -> name = "james"}<br/>
								$global{name = "james"} 等价于 $var{global -> name = "james"}<br/>
								给最近区域的变量赋值: <font color="green">(注：若直到根区域均未找到相应变量，则在当前区域创建局部变量)</font><br/>
								$set{name = "james"} <font color="green">(注：不能修改即有数据模型状态，也就是不能使用像：$set{user.name = "james"}的层级设值方式，以遵守模板无副作用契约，避免引入业务逻辑)</font><br/>
								如果变量为空或未定义，则给其赋初始值: <br/>
								$init{name = "guest"}<br/>
								<b>(5) 块变量指令:</b><br/>
								将模板块定义为变量: <font color="green">(注: 如果需要将模板块传递到宏或其它模板中, 可以使用块变量)</font><br/>
								$block{myblock} <font color="green">(注：参数名称化指令，如果名称需取变量，可使用"\"一元操作符：$block{\name})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$block{global -&gt; myblock} <font color="green">(注: 将块变量定义到指定区域)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								显示块变量: <font color="green">(注：执行块变量所指模板块，模板块可以通过变量传递到其它模板中再show)</font><br/>
								$show{myblock}<br/>
								<b>(6) 数据指令:</b><br/>
								<font color="green">(注：内置支持xml,json,properties,yaml等数据格式，并且数据格式是可扩展的)</font> <a href="data.html">格式说明...</a><br/>
								数据块：<br/>
								$data{xml} <font color="green">(注：参数名称化指令，如果类型需取变量，可使用"\"一元操作符：$data{\type})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								装载外部数据：<br/>
								$load{"xxx.xml"} <font color="green">(注：根据文件扩展名识别类型)</font><br/>
								$load{xml: "xxx.xml"} <br/>
								<b>(7) 宏指令:</b><br/>
								宏定义:<br/>
								$macro{button} <font color="green">(注：参数名称化指令，如果名称需取变量，可使用"\"一元操作符：$macro{\name})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;回调调用者的内部块，如果为行指令方式调用，则inner为空<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$inner{param3: "value3"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;表达式结果为真时，中断宏调用<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$return{name == null}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								宏的行指令方式调用: <font color="green">(注：宏调用既<b>可以</b>传参，也<b>可以</b>访问当前上下文的变量)</font><br/>
								$button{param1: "value1", param2: "value2"}<br/>
								宏的块指令方式调用:<br/>
								$button.block{param1: "value1", param2: "value2"} <font color="green">(注：以".block"结尾表示块指令调用)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								导入模板文件中所有的宏: <font color="green">(注：在新的上下文执行导入的模板, 并忽略输出, 从执行后的上下文取出所定义的宏, 包括层级$import和$embed的宏)</font><br/>
								$import{"mymacro.ctl"}<br/>
								$import{my : "mymacro.ctl"} <font color="green">(注：调用时需带上"名称空间.", 如：$my.button{xxx})</font><br/>
								<font color="green">(注：如果以a为名称空间导入a.ctl，而a.ctl中又以b为名称空间导入b.ctl，且b.ctl中有一个button宏，则可以使用$a.b.button进行调用)</font><br/>
								使用模板文件作为宏:<br/>
								$using{button : "button.ctl"}<br/>
								$using{"button.ctl"} <font color="green">(注：将使用文件名作为宏的名称)</font><br/>
								使用模板文件中的宏作为宏: <font color="green">(注：查找时，如果宏的名称不是常量，则在当前上下文中执行)</font><br/>
								$using{button : "mymacro.ctl#button"} <font color="green">(注：#后为macro的名称, 参见$macro指令)</font><br/>
								$using{"mymacro.ctl#button"} <font color="green">(注：将使用原始宏的名称(#号后的名称)作为宏的名称)</font><br/>
								<b>(8) 继承指令:</b> <font color="green">(注：通常用于布局layout)</font> <a href="demo_extends.html">示例&gt;&gt;</a><br/>
								模板区域定义: <font color="green">(注：在父模板中)</font><br/>
								$zone{body} <font color="green">(注：参数名称化指令，如果名称需取变量，可使用"\"一元操作符：$zone{\name})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								继承模板: <font color="green">(注：在子模板中)</font><br/>
								$extends{"super.ctl"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;覆盖父模板区域:<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$overzone{"body"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调用被覆盖的父模板区域:<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$superzone<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$end<br/>
								$end<br/>
								<b>(9) 包含指令:</b><br/>
								内嵌其它模板: <font color="green">(注：被内嵌的文件，<b>可以</b>访问当前上下文的变量，<b>不可以</b>传参)</font><br/>
								$embed{"common.ctl"}<br/>
								$embed{"common.ctl", "UTF-8"}<br/>
								内嵌其它模板的一部分: <font color="green">(注：#后为zone的名称, 参见$zone指令)</font><br/>
								$embed{"common.ctl#body"} <font color="green">(注：查找时，如果区域的名称不是常量，则在当前上下文中执行)</font><br/>
								包含其它模板的输出: <font color="green">(注：只包含输出，<b>不可以</b>访问当前上下文的变量，<b>可以</b>传参)</font><br/>
								$include{"common.ctl"}<br/>
								$include{"common.ctl", "UTF-8"}<br/>
								$include{"common.ctl", (param1: "value1", param2: "value2")}<br/>
								$include{"common.ctl", "UTF-8", (param1: "value1", param2: "value2")}<br/>
								包含其它模板的一部分: <font color="green">(注：#后为zone的名称, 参见$zone指令)</font><br/>
								$include{"common.ctl#body"} <font color="green">(注：查找时，如果区域的名称不是常量，则在当前上下文中执行)</font><br/>
								显示文件的内容: <font color="green">(注：不解析其内容)</font><br/>
								$display{"article.txt"}<br/>
								$display{"article.txt", "UTF-8"}<br/>
								抓取远程文件的内容: <font color="green">(注：非Web环境只支持完整URL的远程页面)</font><br/>
								$snatch{"list.jsp"} 相对于当前页面路径目录<br/>
								$snatch{"../list.jsp"} 相对于当前页面路径的上级目录<br/>
								$snatch{"/list.jsp"} 相对于Web根目录<br/>
								$snatch{"/list.jsp", 'UTF-8'} 指定编码<br/>
								$snatch{"http://www.163.com"} 远程页面<br/>
								<b>(10) 动态指令:</b><br/>
								动态执行模板:<br/>
								$exec{templateString}<br/>
								动态表达式求值:<br/>
								$eval{expressionString}<br/>
								<b>(11) 过滤指令:</b><br/>
								输出过滤指令: <font color="green">(注：只过滤动态内容，不过滤文本块)</font><br/>
								$filter{x => "&lt;b&gt;" + x.escapeHtml + "&lt;/b&gt;"} <font color="green">(注：缺省名称为value，如：$filter{=> value.escapeHtml})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								输出全过滤指令:  <font color="green">(注：过滤所有输出，包括文本块)</font><br/>
								$filterAll{x => x.escapeHtml} <font color="green">(注：缺省名称为value，如：$filterAll{=> value.escapeHtml})</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								输出缓冲指令: <font color="green">(注：将内部块输出缓冲为单一字符串输出)</font><br/>
								$buffer<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								缓冲并过滤 <font color="green">(注：过滤方式同$filter指令)</font><br/>
								$buffer{x => "&lt;b&gt;" + x.escapeHtml + "&lt;/b&gt;"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								捕获输出指令: <font color="green">(注：捕获指令内部块输出内容到指定变量)</font><br/>
								$capture{xxx}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								$capture{global -&gt; xxx} <font color="green">(注: 指定变量区间)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								忽略输出指令: <font color="green">(注：执行内部指令，但忽略输出)</font><br/>
								$ignore<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								压缩空白符: <font color="green">(注: 将多个连续的空白符压成一个空格)</font><br/>
								$compress<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								删除空白符: <font color="green">(注: 将包含的空白符全部删除)</font><br/>
								$strip<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								截短空白符: <font color="green">(注: 将两端的空白符截掉)</font><br/>
								$trim<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								$ltrim <font color="green">(注: 只将左端的空白符截掉)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								$rtrim <font color="green">(注: 只将右端的空白符截掉)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end <br/>
								转义特殊符:<br/>
								$escape{"html", "js", "url", "base64"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								代码HTML着色: <font color="green">(注: 内置支持java,xml,html,properties,ini等格式，可扩展)</font> <a href="extension.html">扩展...</a><br/>
								$code{java}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								关键字HTML加亮:<br/>
								$keyword{"word1", "word2"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								<b>(12) 模板控制指令:</b><br/>
								模板上下文设置：<br/>
								$setting{debug:ture,locale:"zh_CN"}<br/>
								可设置项：<br/>
								debug:ture<br/>
								locale:"zh_CN"<br/>
								timeZone:"GMT+8"<br/>
								dateFormat:"yyyy-MM-DD HH:mm:ss"<br/>
								numberFormat:"###,##0.00"<br/>
								booleanValue="true|false"<br/>
								nullValue:""<br/>
								停止模板解析：<br/>
								$stop<br/>
								$stop{loginUser == null} <font color="green">(注: 表达式结果为真时停止页面解析)</font><br/>
								<b>(13) 调试指令:</b><br/>
								断言指令：<br/>
								$assert{user != null}  <font color="green">(注: 如果表达式结果不为真, 则抛出异常)</font><br/>
								$assert{(user != null), "error messages"}<br/>
								异常捕获指令：<br/>
								$try<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$exec{"a$$ff$$"}<br/>
								$catch{e: "org.commontemplate.core.RenderingException"}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${e}<br/>
								$end<br/>
								或者：<br/>
								$try<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$exec{"a$$ff$$"}<br/>
								$catch{"org.commontemplate.core.RenderingException"} <font color="green">(注: 缺省变量名为exception)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${exception}<br/>
								$end<br/>
								或者：<br/>
								$try<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;$exec{"a$$ff$$"}<br/>
								$catch <font color="green">(注: 没有参数表示捕获所有异常)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;${exception}<br/>
								$end<br/>
								调试日志：<br/>
								$log{"debug messages..."}<br/>
								$log{debug: "debug messages..."}<br/>
								$log{info: "info messages..."}<br/>
								$log{warn: "warn messages..."}<br/>
								$log{error: "error messages..."}<br/>
								性能监测：(记录其内部块的运行时间，并将时间存入变量中)<br/>
								$time{xxx}<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								$time{global -&gt; xxx} <font color="green">(注: 指定变量区间)</font><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;...<br/>
								$end<br/>
								单步调试断点：(此指令将在其所在行设置断点) <a href="debugger.html">更多...</a><br/>
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
								编译模板时自动去除指令两边的HTML注释符，如：<br/>
								&lt;!--$指令{表达式}--&gt; <font color="green">(注: 注释符与指令间不能有空格)</font><br/>
								<font color="green">注: 此语法外套在web环境下(即@extends=org/commontemplate/tools/<b>web</b>/commontemplate.properties)默认开启，其它环境需配置：<br/>
								textFilters[100]=org.commontemplate.standard.coat.CommentSyntaxCoatFilter()</font><br/>
								<b>(2) 属性版语法外套</b><br/>
								自动将名称空间为“ct:”的HTML标签属性转换成指令，如：<br/>
								&lt;table ct:if="users != null && users.size &gt; 0"&gt;...&lt;table&gt; <font color="green">(注: 只能用于块指令)</font><br/>
								<font color="green">注: 此语法外套因解析HTML语法，有性能损耗，所以未默认开启，需自行配置：<br/>
								resourceFilters[100]=org.commontemplate.standard.coat.AttributeSyntaxCoatFilter()</font><br/>
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
								<br/>
								<b>八. 遗留：</b> <font color="green">(注: 将在1.0版本统一删除)</font><br/>
								(1) $local<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.8.3加入，0.8.5废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>与$var功能重复<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>$var<br/>
								(2) $forelse<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.7.6加入，0.8.6废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>$for和$if统一使$else指令作为否则逻辑<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>$else<br/>
								(3) $overzone<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.7.6加入，0.8.6废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>区域定义与覆写均采用$zone指令，保持统一及语义完整<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>$zone<br/>
								(4) 简化语法规则：<b>$指令名:参数名</b> 等价于 <b>$指令名{"参数名"}</b><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.8.5加入，0.8.6废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>语法规则不统一<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>名称定义性指令(如：$block, $macro, $zone等)参数名引号可省，如：$指令名{参数名} 等价于 $指令名{"参数名"}<br/>
								(5) $leftTrim, $rightTrim<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.8.5加入，0.8.6废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>多单词指令名，参照其它模板语法进行简化<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>$ltrim, $rtrim<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->