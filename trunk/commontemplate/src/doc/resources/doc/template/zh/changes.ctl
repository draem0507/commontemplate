<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>0.8.7 版本更新：(未发布)</b> <a href="downloads.html">下载...</a><br/>
								注释语法外套, 增加clearSpaceline属性, 如果指令所在行没有其它内容, 则清除该空白行.<br/>
								完成热加载国际化信息文件.<br/>
								修复Object.toJson在两对象互相引用时死循环的BUG<br/>
								增加ExpressionFilter扩展点, 在表达式编译之前过滤表达式字符串.<br/>
								Web环境中, 表达式增加支持&amp;lt; &amp;gt;等XML转义符, 如: $var{session -&amp;gt; name = "kent"}<br/>
								拆分Visitor为TemplateVisitor和ExpressionVisitor<br/>
								修复静态方法不能调用的BUG, 如: ${&com.xxx.Utils.calc(xxx)}<br/>
								操作符处理器增加isOperandDotNamed()状态设置,用于将点号作为名称处理.<br/>
								修复$setting指令String到Locale转换的BUG.<br/>
								UnaryOperatorHandler扩展点增加isKeyword()状态的设置, 如: new操作符, 设为关键字的操作符名不能再作为变量名使用.<br/>
								将$操符符改为new, 与Java保持一致.<br/>
								修复"&"和"new"在处理类元和属性上的歧义, 最大匹配类元, 如: ${new com.xxx.User.name} ${&com.xxx.User.PREFIX}<br/>
								增加String.newInstance扩展属性和String.newInstance(args)方法<br/>
								is或instanceof操作符的右参字符串引号可省，如：${user is com.xxx.User} 或者 ${user is "com.xxx.User"}<br/>
								实现ReloadMessageSource, 热加载国际化配置.<br/>
								$load指令加入指定编码支持. 如: $load{xml: "xxx.ctl", "utf-8"} 或 $load{"xxx.ctl", "utf-8"}<br/>
								加入标签语法外套支持，如：&lt;ct:if param="users != null && users.size &gt; 0"&gt;...&lt;/cf:if&gt;<br/>
								使用$output代替原有$out指令，$out指令改为与${}空名称指令等价<br/>
								调试器Output内容与线程绑定, 可切换显示.<br/>
								修复调试器在处理层级包含模板时, Step Return不能回到上一模板的BUG.<br/>
								修复toJson防止循环引用的BUG.<br/>
								实现PropertiesCodeFilter, 使用如: $code{properties}...$end 或者 $code{ini}...$end<br/>
								增加扩展属性Object.toXstream, String.fromJson, String.fromXml, String.fromXstream<br/>
								不再内嵌json和jyaml的源码, 直接依赖json.jar和jyaml.jar<br/>
								增加$escape{xhtml}和${String.escapeXhtml}, 类似XML, 但"&amp;apos;"改为"&amp;#39;"<br/>
								修复LruCache在移动Key顺序时出现的空指针异常.<br/>
								$embed, $include, $display 支持国际化后缀查找, 如: xxx.ctl, 首先查找xxx_zh_CN.ctl, 再查找xxx_zh.ctl, 否则查找xxx.ctl, 配置项: localizedLookup=true 决定否开启此功能.<br/>
								修复静态函数被编译器优化后缓存了结果的问题，排除对函数的优化.<br/>
								修复磁盘缓存并发异常, 并忽略乐观并发无锁读取时的异常.<br/>
								修复ContextImpl.clear()未调用super.clear()的BUG.<br/>
								支持"*/"通配目录：$include{"*/xxx.ctl"} 在当前目录以上的目录中逐级查找xxx.ctl<br/>
								修复磁盘缓存无效的BUG.<br/>
								改为通过配置状态开闭语法外套: commentSyntaxCoatAvailable=true, tagSyntaxCoatAvailable=false, attributeSyntaxCoatAvailable=false<br/>
								增加与JDK1.6中的javax.script的脚本引擎集成. <a href="javaxscript.html">脚本引擎集成...</a><br/>
								增加Object[].asList扩展属性, 数组转列表.<br/>
								增加与JCache的集成适配. <a href="cache.html">缓存集成...</a><br/>
								将log相应类从standard包移至util包，并增加对Log4J, Avalon, SLF4J, JDKLogging等的集成. <a href="logger.html">日志集成...</a><br/>
								补全List有，而Array没有的操作符，使Array和List的处理一致，如："~","!~","orderby"等.<br/>
								引擎分层结构中，将资源加载与缓存划分到TemplateEngine.<br/>
								增加与Map接口适配的ExpressionContext,方便表达式引擎使用.<br/>
								修改属性语法外套在处理HTML注释时空指针异常，并重新简化实现属性语法外套.<br/>
								重新实现this和super语义, 并增加ScopeHandler扩展点.<br/>
								修复orderby操作符PropertyComparator值覆盖的BUG.<br/>
								修复${super.super.for}，在$for指令嵌套$if指令时，将$if指令也作为一层super的BUG.<br/>
								修复${super.super.for}，在$for指令嵌套$if指令时，将$if指令也作为一层super的BUG.<br/>
								完成TemplateRenderTask，使用户可以通过ant调用模板解析任务。<a href="renderer.html">模板渲染器说明...</a><br/>
								-----------------<br/>
								感谢吴勇智加入开发插件.<br/>
								感谢<a href="http://code.google.com/p/jrest4guice">JRest4Guice</a>开发小组的支持.<br/>
								<br/>
								<b>0.8.6 版本更新：(2008-08-26)</b> <a href="downloads.html">下载...</a><br/>
								Bug Fixed:<br/>
								修复BeanUtils在访问级安全控制上的BUG<br/>
								将Class.forName()统一改为ClassUtils.forName(), 使用当前线程类加载器加载类, 并支持基本类型.<br/>
								修复$for指令以及操作符不能处理基本类型数组(如:int[])的BUG.<br/>
								修复ExpressionOptimizer处理前导小数点".15"的BUG.<br/>
								Function Changes:<br/>
								指令$filterAll改为小写的$filterall, 保持指令命名统一.<br/>
								$leftTrim, $rightTrim 改为 $ltrim, $rtrim<br/>
								废弃$forelse指令, $for和$if统一使用$else指令作为否则逻辑.<br/>
								废弃$overzone指令, 区域定义与覆写统一为$zone.<br/>
								$block, $time, $capture等指令支持变量区间，如：$block{global -> xxx}<br/>
								名称定义性指令(如: $block, $macro, $zone等), 参数名称引号可省, 如: $macro{button}, 而不再需要:　$macro{"button"}<br/>
								Visitor加入返回值控制.<br/>
								指令语法配置分离, 如: syntax.directiveLeader='$'<br/>
								将TemplateRenderer和ExpressionEvaluator内置的引擎单例化.<br/>
								修改标准包中的data, code, coat三个子包的类结构.<br/>
								实现配置缺省继承, 不再必需配置@extends=, 将自动根据所处环境继承默认配置.<br/>
								New Feature:<br/>
								增加扩展属性List.sort, Object[].sort<br/>
								增加扩展属性String.toClass<br/>
								调试器加入捕获输出功能, 可实时观察输出内容. <a href="debugger.html">调试器说明...</a><br/>
								调试器加入打开模板功能, 可同时打开多个模板, 便于设置断点.<br/>
								调试器线程列表和断点集合树加入右键菜单.<br/>
								查看器加入直接以调试模式打开方式, 不必再在模板内写$.断点指令. <a href="viewer.html">查看器说明...</a><br/>
								自动加载查看器安装目录和当前模板所在目录下的jar包，使得模板配置中的扩展类以及数据相关类可以正常加载.<br/>
								查看器安装目录和当前模板所在目录,commontemplate.properties配置逐级查找.<br/>
								增加迭代数据集合转换器(CollectionConverter)扩展点.<br/>
								增加$setting指令, 用于设置模板上下文参数.<br/>
								增加$super指令,与$root,$global同为$var的简化指令.<br/>
								增加$buffer指令, 用于缓冲输出, 将其内部块缓冲为单一字符串输出.<br/>
								增加boolean值格式化功能，可配置：defaultBooleanValue=true|false<br/>
								增加OutputConverter接口, 用于拦截输出前对象进行AOP处理.<br/>
								增加ContextInitializer接口, 用于初始化上下文.<br/>
								增加autoImport配置项，自动导入宏.<br/>
								增加EvaluateInterceptor拦截器接口，用于拦截表达式求值过程.<br/>
								<br/>
								<b>0.8.5 版本更新：(2008-08-04)</b> <a href="downloads.html">下载...</a><br/>
								此版本主要完善核心引擎和标准指令集.<br/>
								Bug Fixed:<br/>
								修复BeanUtils查找对象属性的BUG.<br/>
								修复调试器变量树栈溢出BUG.<br/>
								调试窗口在Tomcat下无效的BUG.<br/>
								调试窗口变量树改为延迟加载方式(点击节点时加载其子节点), 避免变量相互引用时, 树节点无穷递归.<br/>
								修复调试器线程列表显示错误的BUG.<br/>
								修复三目运算符不能处理null值的BUG, ${null ? "a" : "b"}原来错误输出:"a:b", 改正后输出:"b"<br/>
								加入对表达式未结束括号的检查, 并抛出正确的异常信息.<br/>
								修改当有多余的$end指令时抛出友好异常信息, 以前抛出空栈异常.<br/>
								修改ObjectToJsonPropertyHandler, JavaScriptEscapeFilter, StringEscapeJsHandler, 采用JavaScriptUtils实现转义.<br/>
								对$if, $elseif, $for等指令进行必需有参数表达式检查, 如果没有则抛出异常信息.<br/>
								修复StringConvertUtils在转换单一字符上的BUG.<br/>
								修复ClassUtils对参数相近类型的识别, 使表达式中的函数对泛型等均能正常调用.<br/>
								修复表达式解析出错时, 没有提示出错位置信息的BUG.<br/>
								Function Changed:<br/>
								宏指令改为即可以传参, 又可以访问变量上下文.<br/>
								块指令调用后缀默认值由"_block"改成".block"(可配置)), 如: $table.block{name : "xxx"} ... $end<br/>
								$using指令代替原有$import指令的功能(将整个模板作为宏), $import指令改为导入模板内的宏定义. <br/>
								改为采用反单引号表示不转义串, 不再提倡使用@符.<br/>
								New Feature:<br/>
								调试窗口模板面板右键菜单加入"属性"项, 显示模板名称,编码,修改时间等信息.<br/>
								增加对YAML数据格式的支持. <a href="viewer.html">查看器</a><br/>
								完成SpringConfigurationLoader, 通过Spring的beans方式组装配置.<br/>
								实现外部数据加载指令$load{"xxx.xml"}.<br/>
								增加结束指令对块指令名称的检查, 如: $end{"if"}, 如果参数名称与被结束的块指令不匹配时抛出异常信息.<br/>
								实现冒号简化语法: $macro:xxx 等价于 $macro{"xxx"}, $end:if 等价于 $end{"if"}<br/>
								实现任意区间变量定义指令:　$var{session -> user = name}, $var{global -> user = name}<br/>
								增加===和!==操作符, 表示内存地址相等, 保证功能上的完备性.<br/>
								增加$return指令, 用于中断$macro.<br/>
								实现递归迭代, 如: $for{menu -> children : menus}<br/>
								实现注释结束符, 不解释块结束符的转义, 如: $* \*$ *$ 以及 $! \\\!$ \!$<br/>
								实现展开式列表，如：${1,3..6,9} 输出展开式列表：[1,3,4,5,6,9]，而：${1,(3..6),9} 或者 ${1,[3..6],9} 输出两级列表：[1,[3,4,5,6],9]<br/>
								增加对".12"格式的小数支持, 保持与Java一致.<br/>
								增加集合乘法支持, 如: ${["a", "b"] * 2} 输出: [a, b, a, b]<br/>
								增加字符串除号("/")运算, 表示分割字符串, 如: ${"aaa.bbb.ccc" / '.'} 输出数组: [aaa, bbb, ccc]<br/>
								增加字符串减号("-")运算, 表示过滤字符串, 如: ${"aaa.bbb.ccc" - '.'} 输出: aaabbbccc<br/>
								增加操作符"^~", "$~", "*~", 与原有的"^=", "$=", "*="功能相似, 不同点在于忽略大写小比较.<br/>
								增加操作符"^-", "$-" 分别表示截取前后缀，如：${"note.txt" ^- "."} 输出：note ，而：${"note.txt" $- "."} 输出：txt<br/>
								增加操作符"^?", "$?" 分别表示indexOf, lastIndexOf，如：${"aaa.bbb.ccc" ^? "."} 输出：3 ，而：${"aaa.bbb.ccc" $? "."} 输出：7<br/>
								增加操作符"*?", 表示整个字符串中匹配子串的个数，如：${"xxxabcxxxabcxxx" *? "abc"} 输出：2<br/>
								增加C#.Net的is操作符, 功能与instanceof相同, 但更简洁, 提倡使用is.<br/>
								增加$操作符, 表示创建实例, 如: ${$com.xxx.User(id: 1, name:""james)}<br/>
								增加sum,avg,max,min等聚合函数, 如: ${sum(3,4,7)} 输出: 14<br/>
								增加命名转换扩展属性: String.toCamelNaming, String.toCapitalNaming, String.toUnderlineNaming.<br/>
								增加uncapitalize与capitalize相对应. 去除首字母大写.<br/>
								增加String.toAscii和toUnicode, 分别表示Unicode码与Ascii码之间的转换.<br/>
								增加String.toSwapCase, 交换大小写, 把字符串中大写的改为小写, 小写的改为大写, 与toUpperCase,toLowerCase对应.<br/>
								增加字符填充leftPad和rightPad实现, 如: ${"123".leftPad(6, '0')} 输出：000123<br/>
								增加 $ignore...$end 指令, 执行指令内部块, 但忽略输出.<br/>
								增加 $capture{"variableName"} ... $end 指令, 捕获指令内部块输出到变量中.<br/>
								增加 $strip, $trim, $leftTrim, $rightTrim 等指令, 用于删除空白符和截短两端空白符.<br/>
								增加 $try $catch 指令, 用于捕获$exec和$eval等动态指令错误.<br/>
								增加 $assert 指令, 用于断言前置条件,不变式等.<br/>
								增加数字扩展属性: positive, negative, abs, sign<br/>
								增加Float和Double类型取整属性 toCeilingInteger(向上取整), toFloorInteger(向下取整)<br/>
								增加Integer和Long类型转为二进制,八进制,十六进制表示串属性 toBinaryString, toOctalString, toHexString<br/>
								增加String.empty, whitespace, naming, number等字符串类型状态属性.<br/>
								------------<br/>
								感谢James.Li和Andrew.Chen的热心帮助<br/>
								<br/>
								<b>0.8.4 版本更新：(2008-06-24)</b> <a href="downloads.html">下载...</a><br/>
								加强各DriectiveHandler的单元测试.<br/>
								修复不解释块"$! \!$"状态机图的BUG, 并全面整理状态机图.<br/>
								采用Jericho实现属性语法外套.<br/>
								完成Debug拦截器, 实现单步执行.<br/>
								完成Debug单步调试窗口，可通过脱离于编辑器的GUI进行单步调试. 参见: <a href="debugger.html">调试窗口说明...</a><br/>
								完成查看器功能，双击*.ctl文件，自动读取数据文件，解析模板，生成html，并用IE打开，如果出错，使用Swing界面弹出错误信息, 使页面开发人员独立于业务开发进行模板测试, 参见: <a href="viewer.html">查看器说明...</a><br/>
								完成$snatch指令的实现, $snatch{"../list.jsp"}, $snatch{"/list.jsp", 'UTF-8'}, $snatch{"http://www.163.com"}<br/>
								修复当$break, $continue 没有表达式时的处理, 即: 支持 $if{xxx} $break $end, 不再需要写成 $if{xxx} $break{true} $end.<br/>
								修改VariableStorage为弱检查方式, 去掉DefiendException和UndefiendException.<br/>
								将VariableResolver的lookupVariable改成getVariable, 隐藏实现方式意图.<br/>
								将MessageSource和Logger移出core包, 放入standard包中实现.<br/>
								Context加入各属性的setter方法, 以及EventListener添加方法等.<br/>
								增加数字转换为中文: 如: ${123.toChinese} 输出: 一百二十三<br/>
								增加数字转换为中文货币: 如: ${123.toChineseCurrency} 输出: 壹佰贰拾叁圆整<br/>
								增加Boolean.toInteger, false为0, true为1,<br/>
								增加Number.toBoolean, 0为flase, 非0为true,<br/>
								增加String.toBoolean, 如: ${"true".toBoolean}<br/>
								修改"行注释指令", 使其保留换行符, 如: $#xxx \n yyy 将输出: \n yyy<br/>
								支持无穷数, 用"*"号表示无穷数, 如: ${users[2..*]}<br/>
								完成异常国际化重构, 将所有异常信息放入国际化配置中.<br/>
								增加字符串abbreviate操作, 如: ${"abcdefghijk" % 6} 或者 ${'abcdefghijk'.abbreviate(6)}, 输出: abc...<br/>
								core包增加ExpressionFactory, ExpressionBuilder, TemplateElementFactory, TemplateBuidler, 使第三方工具能脱离模板解析过程构建模板.<br/>
								Element增加getTemplate(),获取元素所在模板的引用.<br/>
								------------<br/>
								感谢Guileen加入开发<br/>
								<br/>
								<b>0.8.3 版本更新：(2008-03-30)</b> <a href="downloads.html">下载...</a><br/>
								此版本新功能较少, 主要为加强测试与BUG修复. <br/>
								将操作符类型重载处理方式从职责链模式改成配置分派模式.<br/>
								加入数组格式化器, Object[].toString不再出现地址符, 相关配置: defaultArraySeparator=,<br/>
								加入输出类型定义配置: response.contentType=text/html<br/>
								优化编译期常量计算算法. 已可以处理var + 3 + 2等左匹配常量.<br/>
								修复Taglib输出栈的一个空指针BUG.<br/>
								加入String.escapeXml, escapeUrl, escapeJs, shaCode, base64Code, 进一步完善转码处理.<br/>
								加入Object.toJson, toXml, 使数据格式转换更简单.<br/>
								默认开启函数功能.<br/>
								实现外部文件引入List和Map.<br/>
								修复TemplateRenderer求值无效的BUG.<br/>
								修复list[0..10]下标越界异常.<br/>
								修复各操作符对null的处理.<br/>
								增强操作符测试覆盖率.<br/>
								操作符实现类注释完善.<br/>
								$for{-1} 参数小于0时改为不迭代(以前为取绝对值)<br/>
								NumberArithmetic加入对BigDecimal和BigInteger的支持<br/>
								ListAddOperatorHandler当两个Set相加时, 返回Set.(以前全部返回ArrayList)<br/>
								修复Boolean判断时0.3约等于0的Bug, 加入对小数的支持<br/>
								加入@一元操作符, 输出不转义地址字符串, 如: ${@"C:\native\room\u5678\user\java\3333\file.txt"}<br/>
								全面检查所有操作符与指令实现, 防止抛出NullPointerException与IndexOutOfBoundsException以及ClassCastException<br/>
								将与第三方适配实现的配置方案放入ext包.<br/>
								将与struts2集成包, 按struts2的plugin方式打包, 这样在struts.xml的配置可以直接用:&lt;package name="my-app" extends="commontemplate-default"&gt;<br/>
								------------------<br/>
								感谢yananay参与开发,优化与测试.<br/>
								感谢saro和keel发现的bug及提出的建议.<br/>
								<br/>
								<b>0.8.2 版本更新：(2008-02-12)</b> <a href="downloads.html">下载...</a><br/>
								核心包类结构全面稳定.<br/>
								修复了非public内部类,匿名类等的getter取值安全异常的bug。<br/>
								修复了各数字类型混合运算时与Java不一致的bug。<br/>
								加入数字类型间转换属性:toByte,toShort,toInteger,toLong,toFloat,toDouble。<br/>
								修复for指令对Iterator和Enumeration的支持。<br/>
								加入局部包含功能, $embed{"xxx.ctl#body"}, $include{"xxx.ctl#body"}, $import{button: "xxx.cm#button"}。参见：<a href="template.html">模板指南...</a><br/>
								加入包含时编码功能, $embed{"xxx.ctl", "UTF-8"}, $include{"xxx.ctl", "UTF-8"}, $display{"xxx.txt", "UTF-8"}。<br/>
								<br/>
								<b>0.8.1 版本更新：(2008-01-30)</b> <a href="downloads.html">下载...</a><br/>
								增加JspTagLib适配器支持, 可以在CTL使用Jsp标签库。<br/>
								增加对struts2(ui)标签库的配置, 参见下载列表中commontemplate-example.zip内的ct_struts2.war。<br/>
								增加对SpringMVC的集成支持，参见下载列表中commontemplate-example.zip内的ct_springmvc.war。参见：<a href="integration.html">集成指南...</a><br/>
								进一步加强EngineHolder.renderTemplate(), 简化Web框架集成, 将Context的创建, response.setContentType()等均内部封装.<br/>
								核心包增加Constant, Variable, Text, Comment等领域实体。<br/>
								注释语法采用 $## 和 $** 表示保留在编译结果中, 否则编译后即抛弃。参见：<a href="template.html">模板指南...</a><br/>
								<br/>
								<b>0.8.0 版本更新：(2008-01-01)</b> <a href="downloads.html">下载...</a><br/>
								将宏指令的特殊语法(加减号前缀)去掉，改为命名规则。<br/>
								增加与struts及struts2的集成example，见下载列表的commontemplate-example.zip。<br/>
								<br/>
								<b>0.7.9 版本更新：(2007-12-27)</b> <a href="downloads.html">下载...</a><br/>
								完善集成方案。<br/>
								<br/>
								<b>0.7.8 版本更新：(2007-12-21)</b> <a href="downloads.html">下载...</a><br/>
								加入集合过滤器表达式支持。<br/>
								调整tools包，集成方案测试通过。<br/>
								<br/>
								<b>0.7.7 版本更新：(2007-12-13)</b> <a href="downloads.html">下载...</a><br/>
								解决模板文件编码问题。<br/>
								重构配置文件，通过引用名称，给用户留出简单的最基本的配置项。<br/>
								参见：<a href="config.html">配置指南...</a><br/>
								<br/>
								<b>0.7.6 版本更新：(2007-12-06)</b> <a href="downloads.html">下载...</a><br/>
								此版本重构了tools包的集成方案，将所有web集成统一用Listener进行初始化：<br/>
								<font color="#3f7f5f">&lt;listener&gt;</font><br/>	&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
								<font color="#3f7f5f">&lt;/listener&gt;</font><br/>
								配置默认查找：web应用目录下的/WEB-INF/commontemplate.properties，以及ClassPath根目录下的commontemplate.properties，也可以用context-param指定位置。<br/>
								EngineInitializeListener会初始化引擎，并将其放入EngineHolder中，<br/>
								在各集成方案中只需：<br/>
								Engine engine = org.commontemplate.tools.web.EngineHolder.getEngine();<br/>
								然后使用engine处理模板...<br/>
								当然，EngineHolder还有其它一些工具方法，用于辅助常见方案，如：createContext,renderTemplate等。<br/>
								参见：<a href="integration.html">集成指南...</a><br/>
!$
	<!--$end-->
<!--$end-->