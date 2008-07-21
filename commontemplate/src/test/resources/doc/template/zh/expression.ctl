<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. 表达式规则:</b><br/>
								(1) 常规中缀表达式规则<br/>
								(2) 支持Java所有表达式<br/>
								<br/>
								<b>二. 特殊表达式符号:</b><br/>
								<b>1. () 括号：</b>加强优先级<br/>
								<b>2. " ' ` 三种引号：</b>字符串表示符，字符串中转义符"\"，与Java中使用方式相同 <font color="green">(注：除了双引号，单引号，还加入了反单引号，便于多层嵌套)</font><br/>
								<b>3. 数字：</b>以0-9开头表示数字<br/>
								以0x开头的为16进制数字，如：0xF5A7<br/>
								以0开头的整数为8进制数字，如：07<br/>
								后缀B/b, S/s, I/i, L/l, F/f, D/d，分别表示Byte, Short, Integer, Long, Float, Double<br/>
								无点号缺省为Integer，有点号缺省为Double<br/>
								<b>4. 关键字：</b>表示特殊值 <font color="green">(注：不可以作为变量名)</font><br/>
								null 空值<br/>
								true, false 布尔值<br/>
								this 代表当前LocalContext<br/>
								super 代表上级LocalContext<br/>
								context 代表当前Context<br/>
								<br/>
								<b>三. 标准操作符:</b><br/>
								<b>(1) 对象(Object)：</b><br/>
								. 点号, 属性取值, 如: ${user.name}<br/>
								[ ] 方括号, 索引属性, 如: ${user["name"]} ${user[nameVar]}<br/>
								一元 & 类或包名前缀, 如: ${&com.xxx.XxxUtils.xxx} ${&XxxUtils.xxx}<br/>
								instanceof 类型判断，如：${user1 instanceof "com.xxx.User"}<br/>
								== 值相等比较，名称符eq, 如: ${user1 == user2} ${user1 eq user2}<font color="green">(注：相近类型如char与string,int与long等对比时，将自动转换类型)</font></br>
								!= 值不相等比较，名称符ne, 如: ${user1 != user2} ${user1 ne user2}<br/>
								<!--
								? 一元问号，判断对象是否存在, 如: $if{ ? obj} $if{ ! ? obj} <font color="green">(注：如果是String或集合，还会判断其length和size是否为0)</font></br>
								-->
								<b>(2) 布尔值(Boolean)：</b><br/>
								&& 逻辑与，名称符and, 如: ${user1.agree && user2.agree} ${user1.agree and user2.agree}<br/>
								|| 逻辑或，名称符or, 如: ${user1.agree || user2.agree} ${user1.agree or user2.agree} $for{user : (users1 || users2 || users3} <font color="green">(注：对非Boolean值对象，将返回非null对象，与JavaScript规则相同)</font><br/>
								^ 逻辑异或，名称符xor, 如: ${user1.agree ^ user2.agree} ${user1.agree xor user2.agree}<br/>
								!  逻辑非, 如: ${! user1.agree}<br/>
								?: 逻辑选择, 如: ${user1.agree ? "yes" : "no"}<br/>
								<b>(3) 数字(Number)：</b><br/>
								+  正数，如：${3 * +2} ${+user1.coins}<br/>
								-  负数, 如: ${3 * -2} ${-user1.coins}<br/>
								+  加法运算, 如: ${user1.coins + 3.2D}<br/>
								-  减法运算, 如: ${user1.coins - user2.coins}<br/>
								*  乘法运算, 如: ${user.coins * 2}<br/>
								/  除法运算, 如: ${user.coins / 2}<br/>
								%  求模运算, 如: ${user.coins % 2}<br/>
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
								~= 数字近似值等于比较, 如: ${user1.coins ~= user2.coins}<br/>
								#  数字格式化, 如: ${user1.coins # "###,##0.###"}<br/>
								<b>(4) 字符串(String)：</b><br/>
								+  两个字符串相连, 非字符将调用其toString, 如: ${user.firstname + user.lastname}<br/>
								*  字符串重复, 如: ${"aaa" * 3} ${3 * "aaa"}<br/>
								- 将String倒序, 如: ${-str}<br/>
								@ 输出不转义地址串, 如: ${@"C:\native\user\file.txt"}<br/>
								&gt;  字符串大于比较, 如: $if{user1.name &gt; user2.name}<br/>
								&lt;  字符串小于比较, 如: $if{user1.name &lt; user2.name}<br/>
								&gt;= 字符串大于等于比较, 如: $if{user1.name &gt;= user2.name}<br/>
								&lt;= 字符串小于等于比较, 如: $if{user1.name &lt;= user2.name}<br/>
								&lt;=&gt; 字符串大小全比较，0表示相等，1表示大于，-1表示小于, 如: ${user1.name &lt;=&gt; user2.name}<br/>
								~= 字符串忽略大小写比较，并且忽略两端空格, 如: ${"aa" ~= "AA"} $if{user1.name ~= user2.name}<br/>
								^= 字符串是否以另一字符串开头，也就是startsWith, 如: ${"abcd" ^= "ab"} $if{user1.name ^= "james"}<br/>
								$= 字符串是否以另一字符串结尾，也就是endsWith, 如: ${"abcd" $= "cd"} $if{user1.name $= "lee"}<br/>
								*= 字符串是否包含另一字符串，也就是containsWith, 如: ${"abcd" *= "bc"} $if{user1.name *= "lee"}<br/>
								~ 字符串正则表达式匹配, 如: $if{code ~ "^[0-9]+$"}<br/>
								!~ 字符串正则表达式不匹配, 如: $if{code !~ "^[0-9]+$"}<br/>
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
								[prop=value] 属性索引，List如：${users[name="james"].coins}将从列表中返回第一个属性"name"值为"james"的user对象，也可以是多个属性${users[name="james",role="admin"]}，Map如：${map[value="james"].key}通过value查key，${map[key="james"].value}与${map["james"]}等价<br/>
								=> 筛选过滤器，隐含状态信息index,size,count，index为当前项索引号，size为集合大小，count为已接收数，List缺省变量名为item，如：${list[=> item ~ "[0-9]+" && count < 3]}  ${users[u => u.name != 'guest']}，Map缺省变量名为entry，如：${map[=> entry.key != 'xxx' && count < 3]} ${map[x => x.key != 'xxx']}<br/>
								.. 双点号, 表示序列(Sequence), 如: ${1..5} $for{seq : 1..5} $for{weekDay : "Sunday".."Saturday"}<br/>
								,  逗号, 表示列表(List), 如: ${1,2,5,7} $for{seq : (1,2,5,7)}<br/>
								:  冒号, 表示键值对(Entry), 如: ${user.nameVar : "james"} ${name : "james"} <br/>
								<span style="color: green;">注：entry采用JSON的设计风格，因为key通常是字符串，而非变量，所以当key为无引号名称时，将会作为字符串处理，而不是变量取值，如果要用变量作为key，在key前加反斜杠: ${\name : "james"}，另请注意：${user.name : "james"}会取user.name变量的值作为key, 因为点号先运算.</span><br/>
								:, 冒号加逗号, 表示哈希表(Map), 如: ${a : 1, b: 2, c : 7} $for{rel : (a : 1, b: 2, c : 7)}<br>
								[ ] 一元方括号, 转换成集合, 将Object转成List, 将Entry转成Map,  如: $for{num : [100]} $for{num : [name:value]}<br/>
								<span style="color: green;">注：如果[ ]中已经是List或Map, 则将保持不变, 相当于( ), 如: $for{num : [20,50,100]} $for{num : [name1:value1, name2:value2]}</span><br/>
								+ 加号，两个List或Map相连, 如: ${items + ("a","b")}<br/>
								- 一元减号，将List或Array倒序, 如: ${-items}<br/>
								~ 判断集合内是否有匹配项，如：$if{state ~ (1, 2, 4)} $if{user1 ~ users}<br/>
								!~ 判断集合内是否没有匹配项，如：$if{state !~ (1, 2, 4)} $if{user1 !~ users}<br/>
								orderby 按属性排序，+号升序(可省)，-号降序， 如: $for{book : books orderby ("-price","+title","author")}<br/>
								<b>(7) 系统(System)：</b><br/>
								. 一元点号，取系统属性，如：${.now} $if{user.timeLimit &gt;= .now} ${.random} ${.uuid} ${.system.currentTimeMillis} ${.system.properties["user.dir"]} ${.engine.versionNumber} ${.engine.releaseDate} ${.engine.vendor} <br/>
								<br/>
								<b>七. 操作符结合律及优先级</b><br/>
								<b>结合律：</b><font color="green">(注：可以用括号改变结合)</font><br/>
								优先级高的先结合，<br/>
								一元操作符之间总是从右到左结合，<br/>
								同优先级的二元操作符从左到右结合。<br/>
								<b>优先级：</b><font color="green">(注：括号的优先级总是最高)</font><br/>
								操作符优先级从低到高依次为：<br/>
									一元："=>"<br/>
									"="<br/>
									","<br/>
									":"<br/>
									"?:"<br/>
									"~", "!~"<br/>
									"#"<br/>
									"|", "&", "&gt;&gt;", "&lt;&lt;", "&gt;&gt;&gt;"<br/>
									"^" "||"<br/>
									"&&"<br/>
									"==" "!=" "~="<br/>
									"&lt;="  "&gt;=" "&lt;" "&gt;"<br/>
									".."<br/>
									"+" "-"<br/>
									"*" "/" "%"<br/>
									"**"<br/>
									一元："+", "-", "!", "~", "?"<br/>
									".", "[ ]"<br/>
									一元：".", "[ ]", "\"<br/>
								<br/>
								<b>四. 属性扩展</b><br/>
								<font color="green">(注：无参数有返回值函数可以直接作为属性，如：trim, toString, size等，下面不再列出)</font><br/>
								<b>(1) 空值(Null):</b><br/>
								toString 转为String类型，如：null.toString 返回"null"字符串<br/>
								null的其它任意属性都返回null，如：null.xxx 返回null<br/>
								任意对象的null属性都返回null，如：obj.null 返回null<br/>
								null的null属性也返回null，如：null.null 返回null<br/>
								<b>(2) 对象(Object/Bean):</b><br/>
								toJson 输出JSON格式的字符串表示，如：${user.toJson}, ${map.toJson}<br/>
								toXml 输出XML格式的字符串表示，如：${user.toXml}, ${map.toXml}<br/>
								<b>(3) 字符串(String):</b><br/>
								capital 首字母大写, eg: ${"james".capital} (输出：James)<br/>
								toDate 以yyyy-MM-dd格式转换为Date, 如:  $if{now &gt; "2007-01-01".toDate}<br/>
								toTime 以HH:mm:ss格式转换为Date, 如:  ${"22:10:15".toTime}<br/>
								toDateTime 以yyyy-MM-dd HH:mm:ss格式转换为Date, 如:  $if{now &gt; "2007-01-01 23:10:05".toDateTime}<br/>
								toBoolean 将字符串转成Boolean类型, 如: ${"true".toBoolean}<br/>
								toCharacter 转为Character类型, 如:  ${"a".toCharacter}<br/>
								toByte 转为Byte类型, 如:  ${"1".toByte + 2}<br/>
								toShort 转为Short类型, 如:  ${"1".toShort + 2}<br/>
								toInteger 转为Integer类型, 如:  ${"1".toInteger + 2}<br/>
								toLong 转为Long类型, 如:  ${"25".toLong + 2}<br/>
								toFloat 转为Float类型, 如:  ${"1.2".toFloat + 2} <br/>
								toDouble 转为Double类型, 如:  ${"2.3".toDouble + 2}<br/>
								md5Code MD5摘要密文, 如:  ${"mypassword".md5Code}<br/>
								shaCode SHA摘要密文, 如:  ${"mypassword".shaCode}<br/>
								base64Code BASE64编码, 如:  ${"mail".base64Code}<br/>
								escapeHtml HTML转码, 将&lt;和&gt;分别转成&amp;lt;和&amp;gt;等, 如:  ${"&lt;b&gt;".escapeHtml}<br/>
								escapeXml XML转码, 如:  ${"&lt;book&gt;&lt;book&gt;".escapeXml}<br/>
								escapeUrl URL转码, 如:  ${"http://xxx.com?xx=xx&yy=yy".escapeUrl}<br/>
								escapeJs JS转码, 如:  ${"\"\n\"".escapeJs}<br/>
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
								toSize 根据大小显示Bytes,KB,MB等单位, 如: ${917.toSize}(输出：917Bytes) ${(11 * 1024).toSize}(输出：11KB)<br/>
								year, month, week, day, hour, minute, second, millisecond，分别表示转换为年，月，周，日，时，分，秒，毫秒等数字单位，如：${createDate + 3.day}<br/>
								toByte, toShort, toInteger, toLong, toFloat, toDouble 类型间转换, 如: ${3L.toFloat}<br/>
								toBoolean 0为false, 非0为true, 如: ${1.toBoolean} 输出：true<br/>
								toChinese 数字转中文, 如: ${123.toChinese} 输出：一百二十三<br/>
								toChineseCurrency 数字转中文货币, 如: ${123.toChineseCurrency} 输出：壹佰贰拾叁圆整<br/>
								<b>(6) 布尔值(Boolean):</b><br/>
								not 取反, 如: ${true.not} ${user.allow.not}<br/>
								toInteger false为0, true为1, 如: ${true.toInteger} 输出：1<br/>
								<b>(7) 数组(Array):</b><br/>
								size 数组长度，保持与List统一，如：${arr.size}<br/>
								<br/>
								<b>五. 序列扩展</b><br/>
								(1) 数字: ${1 .. 20} ${20 .. -12}<br/>
								(2) 字母: ${'a' .. 'z'} ${'z' .. 'a'} ${'A' .. 'Z'} ${'b' .. 'k'}<br/>
								(3) 季度: ${"Spring" .. "Winter"}<br/>
								(4) 月份: ${"January" .. "December"}<br/>
								(5) 星期: ${"Sunday" .. "Saturday"}<br/>
!$
	<!--$end-->
<!--$end-->