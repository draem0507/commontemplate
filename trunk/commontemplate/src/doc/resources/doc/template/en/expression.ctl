<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>1. Expression Rule:</b><br/>
								...<br/>
								<br/>
								<b>2. Standard Expression:</b><br/>
								(1) getter:<br/>
								.  Property Getter, eg: ${user.name}<br/>
								[] Index Getter, eg: ${user["name"]} ${user[nameVaribale]} ${items[1]} ${items[1..2]} ${items[1,2,4]}<br/>
								[] Change to collection, eg: $for{num : [100]} $for{num : [name:value]}<br/>
								(2) sequence:<br/>
								:  Entry Literal, eg: ${user.nameVar : "james"} ${name : "james"} <span style="color: green;">Comment: variable key: ${$name : "james"}</span><br/>
								.. Sequence Literal, eg: ${1..5} $for{seq : 1..5} $for{weekDay : "Sunday".."Saturday"}<br/>
								,  List Literal, eg: ${1,2,5,7} $for{seq : (1,2,5,7)}<br/>
								:, Map Literal, eg: ${a : 100, b: 2, c : 7} $for{rel : (a : 100, b: 2, c : 7)}<br/>
								(3) object:<br/>
								== Value Equal Comparator , eg: ${user1 == user2}<br/>
								!= Value Not Equal Comparator , eg: ${user1 != user2}<br/>
								|  Not Empty Selector , eg: ${user1 | user2} $for{user : (users1 | users2 | users3}<br/>
								(4) boolean:<br/>
								&& Logic And, eg: ${user1.agree && user2.agree}<br/>
								|| Logic Or, eg: ${user1.agree || user2.agree}<br/>
								!  Logic Not, eg: ${! user1.agree}<br/>
								?: Logic Select, eg: ${user1.agree ? "yes" : "no"}<br/>
								(5) number: <br/>
								+  Add Operator, eg: ${user1.coins + user2.coins}<br/>
								-  Subtract Operator, eg: ${user1.coins - user2.coins}<br/>
								*  Multiply Operator, eg: ${user.coins * 2}<br/>
								/  Divide Operator, eg: ${user.coins / 2}<br/>
								%  Modulus Operator, eg: ${user.coins % 2}<br/>
								&gt;  Greater Than Comparator, eg: ${user1.coins &gt; user2.coins}<br/>
								&lt;  Less Than Comparator, eg: ${user1.coins &lt; user2.coins}<br/>
								&gt;= Greater Equal Comparator, eg: ${user1.coins &gt;= user2.coins}<br/>
								&lt;= Less Equal Comparator, eg: ${user1.coins &lt;= user2.coins}<br/>
								~  Number Format, eg: ${user1.coins ~ "###,##0.###"}<br/>
								(6) string: <br/>
								+  String Concatenate, eg: ${user.firstname + user.lastname}<br/>
								&gt;  Greater Than Comparator, eg: ${user1.name &gt; user2.name}<br/>
								&lt;  Less Than Comparator, eg: ${user1.name &lt; user2.name}<br/>
								&gt;= Greater Equal Comparator, eg: ${user1.name &gt;= user2.name}<br/>
								&lt;= Less Equal Comparator, eg: ${user1.name &lt;= user2.name}<br/>
								(7) date:<br/>
								+  After Days, eg: ${user.registerDate + 3}<br/>
								-  Before Days, eg: ${user.registerDate - 3}<br/>
								&gt;  Greater Than Comparator, eg: ${user1.registerDate &gt; user2.registerDate}<br/>
								&lt;  Less Than Comparator, eg: ${user1.registerDate &lt; user2.registerDate}<br/>
								&gt;= Greater Equal Comparator, eg: ${user1.registerDate &gt;= user2.registerDate}<br/>
								&lt;= Less Equal Comparator, eg: ${user1.registerDate &lt;= user2.registerDate}<br/>
								~  Date Format, eg: ${user.registerDate ~ "yyyy-MM-dd"}<br/>
								(8) array,list,map:<br/>
								+ List or Map Concatenate, eg: ${items + ("a","b")}<br/>
								<br/>
								<b>3. Property:</b><br/>
								(1) boolean:<br/>
								not, eg: ${true.not} ${user.allow.not}<br/>
								(2) string:<br/>
								capital , eg: ${"james".capital} <br/>
								toDate, Convert To Date Type By "yyyy-MM-dd" Format, eg:  $if{now &gt; "2007-01-01".toDate}<br/>
								toDateTime, Convert To Date Type By "yyyy-MM-dd HH:mm:ss" Format, eg:  $if{now &gt; "2007-01-01 23:10:05".toDateTime}<br/>
								escapeHtml, eg:  ${"&lt;b&gt;".escapeHtml}<br/>
								toInteger, Convert To Integer Type, eg:  ${"1".toInteger + 2}<br/>
								toLong, Convert To Long Type, eg:  ${"25".toInteger + 2}<br/>
								toFloat, Convert To Float Type, eg:  ${"1.2".toInteger + 2} <br/>
								toDouble, Convert To Double Type, eg:  ${"2.3".toInteger + 2}<br/>
								(3) date:<br/>
								year, month, day, hour, minute, second, millisecond  Date parts getter, eg: ${createDate.day} ${createDate.hour}<br/>
								dayInYear, eg: ${createDate.dayInYear}<br/>
								week, eg: ${createDate.week}<br/>
								era, eg: ${createDate.era}<br/>
								century, eg: ${createDate.century}<br/>
								timezone, eg: ${createDate.timezone}<br/>
								leap, eg: $if{now.lenient}<br/>
								toLong, compare with 1970-01-01 00:00:00.000, eg: ${createDate.toLong}<br/>
								(4) long:<br/>
								toDate, Long To Date, eg: ${9999999.toDate}<br/>
								<br/>
								<b>4. Sequence:</b><br/>
								(1) Number: ${1 .. 20} ${20 .. -12}<br/>
								(2) Letter: ${a .. z} ${z .. a} ${A .. Z} ${b .. k}<br/>
								(3) Season: ${"Spring" .. "Winter"}<br/>
								(4) Month: ${"January" .. "December"}<br/>
								(5) Week: ${"Sunday" .. "Saturday"}<br/>
!$
	<!--$end-->
<!--$end-->