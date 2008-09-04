<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>一. 表达式规则:</b><br/>
								(1) 常规中缀表达式规则<br/>
								(2) 支持Java所有表达式<br/>
								<br/>
								<b>二. 特殊表达式符号:</b><br/>
								<b>1. 括号：</b>() 用于加强优先级<br/>
								<b>2. 字符串：</b>" ' ` (三种)引号<br/>
								其中，单双引号使用与Java相似<br/>
								字符串中转义符反斜线(\)，支持\b, \t, \n, \f, \r, \uXXXX, \", \', \\<br/>
								与Java不同点：<br/>
								引号允许多行字符串<br/>
								忽略不识别的转义字符，如：${"\h"} 输出两个字符：\h<br/>
								增加反单引号(`)表示不转义串，内容不能包含反单引号本身，通常用于输出Windows地址，如：${`C:\native\user\file.txt`}<br/>
								<b>3. 数字：</b>以0-9开头表示数字<br/>
								以0x开头的为16进制数字，如：0xF5A7<br/>
								以0开头的整数为8进制数字，如：07<br/>
								后缀B/b, S/s, I/i, L/l, F/f, D/d，分别表示Byte, Short, Integer, Long, Float, Double<br/>
								没有后缀的数字，无点号缺省为Integer，有点号缺省为Double<br/>
								.12 等价于 0.12<br/>
								E表示10的n次方，也就是科学计数法，如：1.25E3 等于 1250<br/>
								* 星号代表无穷数，如：${list[2..*]} <font color="green">(注：星号不可以作为一元操作符)</font><br/>
								<b>4. 关键字：</b>表示特殊值 <font color="green">(注：不可以作为变量名)</font> <a href="extension.html#keywords">配置...</a><br/>
								null 空值<br/>
								true, false 布尔值<br/>
								this 代表当前LocalContext<br/>
								super 代表上级LocalContext<br/>
								context 代表当前Context<br/>
								<b>5. 操作符关键字： <font color="green">(注：不可以作为变量名)</font></b>
								new 一元操作符(优先于变量)<br/>
								<br/>
								<b>三. 标准操作符:</b> <a href="extension.html#operator">扩展...</a><br/>
								<b>(1) 对象(Object)：</b><br/>
								. 点号, 属性取值, 如: ${user.name}<br/>
								[ ] 方括号, 索引属性, 如: ${user["name"]} ${user[variable]}<br/>
								$ 一元美元符号，创建类实例, 如: ${$com.xxx.User(1, "james")} 或 ${$com.xxx.User(id: 1, name: "james")} 或 ${$com.xxx.User()} 或 ${$com.xxx.User}<br/>
								<font color="green">(注：参数若为List则传入构造函数, 若为Map则调用无参构造函数, 然后调用setXXX()初始化属性)</font><br/>
								& 一元与号，包名或类名前缀, 如: ${&com.xxx.AppConstant.PREFIX} 或 ${&com.xxx.AppUtils.calc(xxx)} 等价于 ${"com.xxx.AppUtils".toClass.calc(xxx)}<br/>
								is 或 instanceof，类型判断，如：${user1 is "com.xxx.User"} 或 ${user1 instanceof "com.xxx.User"}<br/>
								== 值相等比较，名称符eq，如: ${user1 == user2} ${user1 eq user2}<font color="green">(注：相近类型如char与string,int与long等对比时，将自动转换类型)</font></br>
								!= 值不相等比较，名称符ne，如: ${user1 != user2} ${user1 ne user2}<br/>
								=== 内存地址相等比较，名称符aeq，如: ${user1 === user2} ${user1 aeq user2}<br/>
								!== 内存地址不相等比较，名称符ane，如: ${user1 !== user2} ${user1 ane user2}<br/>
								<b>(2) 布尔值(Boolean)：</b><br/>
								&& 逻辑与，名称符and, 如: ${user1.agree && user2.agree} ${user1.agree and user2.agree}<br/>
								|| 逻辑或，名称符or, 如: ${user1.agree || user2.agree} ${user1.agree or user2.agree} $for{user : (users1 || users2 || users3} <font color="green">(注：对非Boolean值对象，将返回非null对象，与JavaScript规则相同)</font><br/>
								^ 逻辑异或，名称符xor, 如: ${user1.agree ^ user2.agree} ${user1.agree xor user2.agree}<br/>
								!  逻辑非, 如: ${! user1.agree}<br/>
								?: 逻辑选择, 如: ${user1.agree ? "yes" : "no"}<br/>
								<!--
								? 一元问号，判断对象是否存在, 如: $if{ ? obj} $if{ ! ? obj} <font color="green">(注：如果是String或集合，还会判断其length和size是否为0)</font></br>
								-->
								<b>(3) 数字(Number)：</b><br/>
								+  一元加号，正数，如：${3 * +2} ${+user1.coins}<br/>
								-  一元减号，负数, 如: ${3 * -2} ${-user1.coins}<br/>
								+  加法运算, 如: ${user1.coins + 3.2D}<br/>
								-  减法运算, 如: ${user1.coins - user2.coins}<br/>
								*  乘法运算, 如: ${user.coins * 2}<br/>
								/  除法运算, 如: ${user.coins / 2}<br/>
								%  求模/求余运算, 如: ${user.coins % 2}<br/>
								**  求幂次方运算, 如: ${2 ** 2 ** 3}<br/>
								| 按位与运算，如: ${128 | 256}<br/>
								& 按位或运算，如: ${128 & 2}<br/>
								^ 按位异或运算，如: ${~128 ^ 2}<br/>
								~ 按位取反运算，如: ${~128}<br/>
								&gt;&gt; 按位右移运算，如: ${32 &gt;&gt; 2}<br/>
								&lt;&lt; 按位左移运算，如: ${128 &lt;&lt; 2}<br/>
								&gt;&gt;&gt; 按位无符号右移运算，如: ${32 &gt;&gt;&gt; 2}<br/>
								&gt;  数字大于比较，名称符gt, 如: ${user1.coins &gt; user2.coins} ${user1.coins gt user2.coins}<br/>
								&lt;  数字小于比较，名称符lt, 如: ${user1.coins &lt; user2.coins} ${user1.coins lt user2.coins}<br/>
								&gt;= 数字大于等于比较，名称符ge, 如: ${user1.coins &gt;= user2.coins} ${user1.coins ge user2.coins}<br/>
								&lt;= 数字小于等于比较，名称符le, 如: ${user1.coins &lt;= user2.coins} ${user1.coins le user2.coins}<br/>
								&lt;=&gt; 数字大小全比较，名称符cmp，0表示相等，1表示大于，-1表示小于, 如: ${user1.coins &lt;=&gt; user2.coins} ${user1.coins cmp user2.coins}<br/>
								~= 数字近似值等于比较(任何数字类型只比较intValue相等), 如: ${user1.coins ~= user2.coins}<br/>
								#  数字格式化, 如: ${user1.coins # "###,##0.###"}<br/>
								max 取最大值, 如: ${max(1,5,3)} 输出：5<br/>
								min 取最小值, 如: ${min(1,5,3)} 输出：1<br/>
								sum 求和, 如: ${sum(1,5,3)} 输出：9<br/>
								avg 求平均值, 如: ${avg(1,5,3)} 输出：3<br/>
								<b>(4) 字符串(String)：</b><br/>
								[ ] 方括号，字符索引，如：${"abcdefg"[1..2]} 输出：bc <font color="green">(注：可以将String看成char[]数组)</font><br/>
								+ 加号，两个字符串相连, 非字符将调用其toString, 如: ${user.firstname + user.lastname}<br/>
								- 减号, 过滤字符串, 如: ${"ab.cd.ef" - "."} 输出：abcdef<br/>
								* 乘号，字符串重复, 如: ${"abc" * 3} 或者 ${3 * "abc"} 输出：abcabcabc<br/>
								/ 除号, 字符串分割, 并忽略空段, 如：${"aaa.bbb.ccc" / "."} 输出数组：[aaa, bbb, ccc]<br/>
								% 截余/缩略, 如: ${"abcdefghijklmn" % 10} 输出：abcdefg...<br/>
								- 一元减号，将String倒序, 如: ${- str}<br/>
								~ 字符串正则表达式匹配, 如: $if{code ~ "^[0-9]+$"}<br/>
								!~ 字符串正则表达式不匹配, 如: $if{code !~ "^[0-9]+$"}<br/>
								&gt;  字符串大于比较, 如: $if{user1.name &gt; user2.name}<br/>
								&lt;  字符串小于比较, 如: $if{user1.name &lt; user2.name}<br/>
								&gt;= 字符串大于等于比较, 如: $if{user1.name &gt;= user2.name}<br/>
								&lt;= 字符串小于等于比较, 如: $if{user1.name &lt;= user2.name}<br/>
								&lt;=&gt; 字符串大小全比较，0表示相等，1表示大于，-1表示小于, 如: ${user1.name &lt;=&gt; user2.name}<br/>
								~= 字符串忽略大小写比较，如: ${"aa" ~= "AA"} $if{user1.name ~= user2.name}<br/>
								<font color="green">(注：以下操作符中，^ 代表开头，$ 代表结尾，* 代表全串匹配，来源于正则表达式)</font><br/>
								^= 字符串是否以另一字符串开头，也就是startsWith, 如: ${"abcd" ^= "ab"} $if{user1.name ^= "james"}<br/>
								$= 字符串是否以另一字符串结尾，也就是endsWith, 如: ${"abcd" $= "cd"} $if{user1.name $= "lee"}<br/>
								*= 字符串是否包含另一字符串，也就是containsWith, 如: ${"abcd" *= "bc"} $if{user1.name *= "lee"}<br/>
								^~ (忽略大小写比较)字符串是否以另一字符串开头，也就是startsWith, 如: ${"abcd" ^~ "ab"} $if{user1.name ^~ "james"}<br/>
								$~ (忽略大小写比较)字符串是否以另一字符串结尾，也就是endsWith, 如: ${"abcd" $~ "cd"} $if{user1.name $~ "lee"}<br/>
								*~ (忽略大小写比较)字符串是否包含另一字符串，也就是containsWith, 如: ${"abcd" *~ "bc"} $if{user1.name *~ "lee"}<br/>
								^? 从最前开始匹配子串所在位置，也就是indexOf，如：${"aaa.bbb.ccc" ^* "."} 输出：3<br/>
								$? 从最后开始匹配子串所在位置，也就是lastIndexOf，如：${"aaa.bbb.ccc" $* "."} 输出：7<br/>
								*? 整个字符串中匹配子串的个数，如：${"xxxabcxxxabcxxx" *? "abc"} 输出：2<br/>
								^- 截取前缀，如：${"note.txt" ^- "."} 输出：note<br/>
								$- 截取后缀，如：${"note.txt" $- "."} 输出：txt<br/>
								<b>(5) 日期(Date)：</b><br/>
								+  日期年数后推, 如: ${user.registerDate + 3.year} <font color="green">(注：参见数字属性扩展)</font><br/>
								-  日期年数前推, 如: ${user.registerDate - 3.year}<br/>
								&gt;  日期大于比较, 如: ${user1.registerDate &gt; user2.registerDate}<br/>
								&lt;  日期小于比较, 如: ${user1.registerDate &lt; user2.registerDate}<br/>
								&gt;= 日期大于等于比较, 如: ${user1.registerDate &gt;= user2.registerDate}<br/>
								&lt;= 日期小于等于比较, 如: ${user1.registerDate &lt;= user2.registerDate}<br/>
								&lt;=&gt; 日期大小全比较，0表示相等，1表示大于，-1表示小于, 如: ${user1.registerDate &lt;=&gt; user2.registerDate}</span><br/>
								~= 日期忽略时分秒比较, 如: ${user1.registerDate ~= user2.registerDate}<br/>
								# 日期格式化, 如: ${user.registerDate # "yyyy-MM-dd"}<br/>
								<b>(6) 集合(Collection)：</b><br/>
								[ ] 方括号, 数组或List下标，如:  ${list[1]} ${list[1..3]} ${list[1,2,4]} ${string[1..3]}<br/>
								<span style="color: green;">注：${String[0..2]}会将String看成char[&nbsp;]使用，数组或List下标为负数时表示倒数：${list[-1]}，取list的倒数第一个元素</span><br/>
								[ ] 方括号, Map索引, 如: ${map["key"]} ${map[keyVar]}，同样可以用${map.key}，但点号优先查getter属性，方括优先查entry键值对，如：${map.size}和${map["size"]}，如果map中有size的entry，则返回结果可能不一样。<br/>
								[prop:value] 属性索引，List如：${users[name:"james"].coins}将从列表中返回第一个属性"name"值为"james"的user对象，也可以是多个属性${users[name:"james",role:"admin"]}，Map如：${map[value:"james"].key}通过value查key，${map[key:"james"].value}与${map["james"]}等价<br/>
								=> 推导符号，表示筛选过滤器(lambda表达式)，隐含状态信息index,size,count，index为当前项索引号，size为集合大小，count为已接收数，List缺省变量名为item，如：${list[=> item ~ "[0-9]+" && count < 3]}  ${users[u => u.name != 'guest']}，Map缺省变量名为entry，如：${map[=> entry.key != 'xxx' && count < 3]} ${map[x => x.key != 'xxx']}<br/>
								-&gt; 箭头号, 表示层级名称，左右参数均为名称串，用于需要多个层级名称的指令中，如：$var{global -&gt; user = "james"} $for{menu -&gt; children : menus}<br/>
								:  冒号，表示键值对(Entry)，如：${name : "james"} ${user.name : "james"} <br/>
								<span style="color: green;">注：entry采用JSON的设计风格，因为key通常是字符串，而非变量，所以当key为无引号名称时，将会作为字符串处理，而不是变量取值，如果要用变量作为key，在key前加反斜线: ${\name : "james"}，另请注意：${user.name : "james"}会取user.name变量的值作为key, 因为点号先运算.</span><br/>
								=  等号，与冒号功能相同，表示键值对(Entry)，但优先级更低(其它操作符均先于它运行)，如：$set{xxx = aa:bb,cc:dd}，可以不需要括号。<br/>
								.. 双点号, 表示序列(Sequence), 如: ${1..5} $for{num : 1..5} $for{weekDay : "Sunday".."Saturday"}<br/>
								,  逗号, 表示列表(List), 如: ${1,2,5,7} $for{num : (1,2,5,7)}<br/>
								<font color="green">(注：序列和列表可以合并使用，如：${1,3..6,9} 输出展开式列表：[1,3,4,5,6,9]，而：${1,(3..6),9} 或者 ${1,[3..6],9} 输出两级列表：[1,[3,4,5,6],9])</font><br/>
								:, 冒号加逗号, 表示哈希表(Map), 如: ${a : 1, b: 2, c : 7} $for{rel : (a : 1, b: 2, c : 7)}<br>
								[ ] 一元方括号, 转换成集合, 将Object转成List, 将Entry转成Map,  如: $for{num : [100]} $for{num : [name:value]}<br/>
								<span style="color: green;">注：如果[ ]中已经是List或Map, 则将保持不变, 相当于( ), 如: $for{num : [20,50,100]} $for{num : [name1:value1, name2:value2]}</span><br/>
								+ 加号，两个List或Map相连, 如: ${items + ("a","b")}<br/>
								* 乘号，重复集合内容, 如: ${["a","b"] * 2} 输出：[a, b, a, b]<br/>
								- 一元减号，将List或Array倒序, 如: ${-items}<br/>
								~ 判断集合内是否有匹配项，如：$if{state ~ (1, 2, 4)} $if{user1 ~ users}<br/>
								!~ 判断集合内是否没有匹配项，如：$if{state !~ (1, 2, 4)} $if{user1 !~ users}<br/>
								orderby 按属性排序，+号升序(可省)，-号降序， 如: $for{book : books orderby ("-price","+title","author")}<br/>
								<b>(7) 系统(System)：</b><br/>
								. 一元点号，取系统属性，如：${.now} ${.random} ${.uuid} ${.system.currentTimeMillis} ${.system.properties["user.dir"]} ${.engine.version} ${.engine.released} ${.engine.vendor} <br/>
								<br/>
								<b>四. 操作符结合律及优先级</b><br/>
								<b>结合律：</b><font color="green">(注：可以用括号改变结合)</font><br/>
								优先级高的先结合，<br/>
								一元操作符之间总是从右到左结合，<br/>
								同优先级的二元操作符从左到右结合。<br/>
								<b>优先级：</b><font color="green">(注：括号的优先级总是最高)</font><br/>
								操作符优先级从低到高依次为：<br/>
									"=>"<br/>
									"="<br/>
									".."<br/>
									","<br/>
									":"<br/>
									"?"<br/>
									"#"<br/>
									"||"<br/>
									"&&"<br/>
									"|"<br/>
									"^"<br/>
									"&"<br/>
									"==", "!=", "~=", "~", "!~", "^=", "$=", "*=", "^~", "$~", "*~"<br/>
									"&lt;", "&gt;", "&lt;=", "&gt;=", "&lt;=&gt;", "is", "instanceof"<br/>
									"&gt;&gt;", "&lt;&lt;", "&gt;&gt;&gt;"<br/>
									"orderby"<br/>
									"^?", "$?", "*?", "^-", "$-"<br/>
									"+", "-"<br/>
									"*", "/", "%"<br/>
									"**"<br/>
									一元："+", "-", "!", "~", "?"<br/>
									".", "[ ]", "-&gt;"<br/>
									一元：".", "[ ]", "\", "&", "$", "max", "min", "sum", "avg"<br/>
									"( )"<br/>
								<br/>
								<b>五. 对象属性及扩展属性</b> <a href="extension.html#property">扩展...</a><br/>
								<b>属性调用格式</b><br/>
								${对象.属性}<br/>
								<b>属性查找顺序</b><br/>
								以${obj.XXX}为例<br/>
								(1) 首先查找obj类型的外部扩展属性(可覆盖原有属性) <font color="green">(注：参见下一节)</font><br/>
								(2) 再查找obj.getXXX()函数<br/>
								(3) 再查找obj.isXXX()函数<br/>
								(4) 再查找obj.XXX()函数<br/>
								(5) 再查找obj.XXX属性<br/>
								<b>标准扩展属性</b><br/>
								<font color="green">(注：根据上面的规则，没有参数，但有返回值的函数，可以直接作为属性，如：toString, hashCode, trim, toUpperCase, size等，下面不再列出)</font><br/>
								<b>(1) 空值(Null):</b><br/>
								toString 转为String类型，如：null.toString 返回"null"字符串<br/>
								null的其它任意属性都返回null，如：null.xxx 返回null<br/>
								任意对象的null属性都返回null，如：obj.null 返回null<br/>
								null的null属性也返回null，如：null.null 返回null<br/>
								<b>(2) 对象(Object/Bean):</b><br/>
								toJson 输出JSON格式的字符串表示，如：${user.toJson}, ${map.toJson}<br/>
								toXml 输出XML格式的字符串表示，如：${user.toXml}, ${map.toXml}<br/>
								<b>(3) 字符串(String):</b><br/>
								capitalize 首字母大写, eg: ${"james".capitalize} (输出：James)<br/>
								uncapitalize 首字母小写, eg: ${"James".uncapitalize} (输出：james)<br/>
								md5Code MD5摘要密文, 如:  ${"mypassword".md5Code}<br/>
								shaCode SHA摘要密文, 如:  ${"mypassword".shaCode}<br/>
								base64Code BASE64编码, 如:  ${"mail".base64Code}<br/>
								escapeHtml HTML转码, 将&lt;和&gt;分别转成&amp;lt;和&amp;gt;等, 如:  ${"&lt;b&gt;".escapeHtml}<br/>
								escapeXml XML转码, 如:  ${"&lt;book&gt;&lt;book&gt;".escapeXml}<br/>
								escapeUrl URL转码, 如:  ${"http://xxx.com?xx=xx&yy=yy".escapeUrl}<br/>
								escapeJs JS转码, 如:  ${"\"\n\"".escapeJs}<br/>
								toCamelNaming 转为骆驼命名, 如：${"user_name".toCamelNaming} 输出：userName<br/>
								toCapitalNaming 转为大写命名, 如：${"user_name".toCapitalNaming} 输出：UserName<br/>
								toUnderlineNaming 转为下划线命名, 如：${"userName".toUnderlineNaming} 输出：user_name<br/>
								toAscii 将Unicode码转为ASCII码表示, 如：${"中国".toAscii} 输出：\u4E2D\u56FD<br/>
								toUnicode 将ASCII码转为Unicode码表示, 如：${"\u4E2D\u56FD".toUnicode} 输出：中国<br/>
								toSwapCase 交换大小写, 把串中大写的改小写, 小写的改为大写. 如：${"ABCdef".toSwapCase} 输出：abcDEF <font color="green">(注：与toUpperCase, toLowerCase相对应)</font><br/>
								toClass 将字符串转为类元, 如: ${"com.xxx.User".toClass}<br/>
								toDate 以yyyy-MM-dd格式转换为Date, 如:  ${"2007-01-01".toDate}<br/>
								toTime 以HH:mm:ss格式转换为Date, 如:  ${"22:10:15".toTime}<br/>
								toDateTime 以yyyy-MM-dd HH:mm:ss格式转换为Date, 如:  ${"2007-01-01 23:10:05".toDateTime}<br/>
								toBoolean 将字符串转成Boolean类型, 如: ${"true".toBoolean}<br/>
								toCharacter 转为Character类型, 如:  ${"a".toCharacter}<br/>
								toByte 转为Byte类型, 如:  ${"1".toByte + 2}<br/>
								toShort 转为Short类型, 如:  ${"1".toShort + 2}<br/>
								toInteger 转为Integer类型, 如:  ${"1".toInteger + 2}<br/>
								toLong 转为Long类型, 如:  ${"25".toLong + 2}<br/>
								toFloat 转为Float类型, 如:  ${"1.2".toFloat + 2} <br/>
								toDouble 转为Double类型, 如:  ${"2.3".toDouble + 2}<br/>
								empty 是否为空字符串, 如：${"".empty} 输出：true<br/>
								whitespace 是否为空白字符串, 如：${" ".whitespace} 输出：true<br/>
								naming 是否为命名字符串, 如：${"abc".naming} 输出：true<br/>
								number 是否为数字字符串, 如：${"123".number} 输出：true<br/>
								<b>(4) 日期(Date):</b><br/>
								year, month, day, hour, minute, second, millisecond 时间各个部分取值, 如: ${createDate.day} ${createDate.hour}<br/>
								week 星期几， 如: ${createDate.week}<br/>
								dayOfYear 一年中的第多少天, 如: ${createDate.dayOfYear}<br/>
								weekOfYear 一年中的第几周, 如: ${createDate.weekOfYear}<br/>
								weekOfMonth 一月中的第几周, 如: ${createDate.weekOfMonth}<br/>
								era 纪元, 如: ${createDate.era}<br/>
								century 世纪, 如: ${createDate.century}<br/>
								timezone 时间参照的时区, 如: ${createDate.timezone}<br/>
								leap  是否为润年, 如: $if{now.leap}<br/>
								toDateString 以yyyy-MM-dd格式化日期, 如: ${createDate.toDateString}<br/>
								toTimeString 以HH:mm:ss格式化日期, 如: ${createDate.toTimeString}<br/>
								toDateTimeString 以yyyy-MM-dd HH:mm:ss格式化日期, 如: ${createDate.toDateTimeString}<br/>
								toLong 计算机时间, 即相对于1970-01-01 00:00:00.000的毫秒数, 如: ${createDate.toLong}<br/>
								<b>(5) 数字(Number):</b><br/>
								toDate 以相对于1970-01-01 00:00:00.000的毫秒数转换为Date, 如: ${9999999.toDate}<br/>
								year, month, week, day, hour, minute, second, millisecond，分别表示转换为年，月，周，日，时，分，秒，毫秒等数字单位，如：${createDate + 3.day}<br/>
								toByte, toShort, toInteger, toLong, toFloat, toDouble 类型间转换, 如: ${3L.toFloat}<br/>
								toBoolean 0为false, 非0为true, 如: ${1.toBoolean} 输出：true<br/>
								toSize 根据大小显示Bytes,KB,MB等单位, 如: ${917.toSize}(输出：917Bytes) ${(11 * 1024).toSize}(输出：11KB)<br/>
								toChinese 数字转中文, 如: ${123.toChinese} 输出：一百二十三<br/>
								toChineseCurrency 数字转中文货币, 如: ${123.toChineseCurrency} 输出：壹佰贰拾叁圆整<br/>
								abs 取绝对值, 如: ${(-10).abs} 输出：10<br/>
								sign 取符号, 如: ${(-10).sign} 输出：-1<br/>
								positive 是否为正数(非负数), 如: ${(-10).positive} 输出：false<br/>
								negative 是否为负数, 如: ${(-10).negative} 输出：true<br/>
								toBinaryString 十进制整型转为二进制串，如: ${12.toBinaryString} 输出：1100<br/>
								toOctalString 十进制整型转为八进制串，如: ${12.toOctalString} 输出：14<br/>
								toHexString 十进制整型转为十六进制串，如: ${12.toHexString} 输出：c<br/>
								toCeilingInteger 浮点型向上取整，如: ${12.3.toCeilingInteger} 输出：13<br/>
								toFloorInteger 浮点型向下取整，如: ${12.3.toFloorInteger} 输出：12<br/>
								<b>(6) 布尔值(Boolean):</b><br/>
								not 取反, 如: ${true.not} ${user.allow.not}<br/>
								toInteger false为0, true为1, 如: ${true.toInteger} 输出：1<br/>
								<b>(7) 数组(Array):</b><br/>
								size 数组长度，保持与List统一，如：${arr.size}<br/>
								sort 数组或List排序，如：${arr.sort} ${list.sort} ${['f','a','d'].sort} <font color="green">(注：集合中的项需实现Comparable接口)</font><br/>
								<br/>
								<b>六. 对象函数及扩展函数</b> <a href="extension.html#function">扩展...</a><br/>
								<b>函数调用格式</b><br/>
								${对象.函数(参数1, 参数2)}<br/>
								<font color="green">(注：不允许调用返回类型为void的函数)</font><br/>
								<font color="green">(注：如果是没有参数的函数，建议采用属性方式调用，也就是省略括号，如：${obj.toString}，而不是${obj.toString()})</font><br/>
								<b>函数查找顺序</b><br/>
								以${obj.XXX(123)}为例<br/>
								(1) 首先查找obj类型的外部扩展函数(可覆盖原有函数) <font color="green">(注：参见下一节)</font><br/>
								(2) 再查找参数类型匹配的函数obj.XXX(Integer)<br/>
								(3) 再查找参数类型相近的函数obj.XXX(int)<br/>
								<b>标准扩展函数</b><br/>
								<font color="green">(注：根据上面的规则，有返回值的函数，可以直接调用，如：replaceAll, substring, split等，下面不再列出)</font><br/>
								<b>(1) 字符串(String):</b><br/>
								replace(String, String) 替换，如：${"a-b-c".replace('-', '.')} 输出：a.b.c <font color="green">(注：重载了JRE1.4中String原有replace函数，使其支持字符串替换)</font><br/>
								abbreviate(int) 缩略，如：${"abcdefghijk".abbreviate(6)} 输出：abc... <font color="green">(注：参数必需大于3)</font><br/>
								abbreviate(int, int) 从偏移位置开始缩略，如：${"abcdefghijk".abbreviate(2, 6)} 输出：cde... <br/>
								leftPad(int, String) 左填充对齐，如：${"123".leftPad(6, '0')} 输出：000123 <br/>
								leftPad(int) 以空格左填充对齐，如：${"123".leftPad(6)} 输出：□□□123 <br/>
								rightPad(int, String) 右填充对齐，如：${"123".rightPad(6, '-')} 输出：123--- <br/>
								rightPad(int) 以空格右填充对齐，如：${"123".rightPad(6)} 输出：123□□□ <br/>
								<b>函数与操作符</b><br/>
								函数与操作符部分功能是重叠的，建议采用操作符，如：<br/>
								${"abcdefgh"[1..2]} 等价于 ${"abcdefgh".substring(1, 3)}<br/>
								${"ab.cd.ef" / "."} 等价于 ${"ab.cd.ef".split("\\.")}<br/>
								${"abcdefghijk" % 6} 等价于 ${"abcdefghijk".abbreviate(6)}<br/>
								<br/>
								<b>七. 序列扩展</b> <a href="extension.html#sequence">扩展...</a><br/>
								(1) 数字: ${1 .. 20} ${20 .. -12}<br/>
								(2) 字母: ${'a' .. 'z'} ${'z' .. 'a'} ${'A' .. 'Z'} ${'b' .. 'k'}<br/>
								(3) 季度: ${"Spring" .. "Winter"}<br/>
								(4) 月份: ${"January" .. "December"}<br/>
								(5) 星期: ${"Sunday" .. "Saturday"}<br/>
								<br/>
								<b>八. 遗留：</b> <font color="green">(注: 将在1.0版本统一删除)</font><br/>
								(1) @ 一元操作符<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.8.3加入，0.8.5废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>用动态操作符实现不转义字符串有BUG，并且不希望特殊化@操作符<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>改用反单引号实现不转义字符串<br/>
								(3) $ 一元操作符<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.8.3加入，0.8.7废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>创建类实例操作符，保持与Java一致，改为"new"，并设为键字<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>new<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->