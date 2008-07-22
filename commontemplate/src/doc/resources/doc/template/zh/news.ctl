<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>0.8.4 版本发布！(2008-06-24)</b> <a href="downloads.html">下载...</a><br/>
								加强各DriectiveHandler的单元测试.<br/>
								修复不解释块"$! !$!\$$!"状态机图的BUG, 并全面整理状态机图.<br/>
								采用Jericho实现属性语法外套.<br/>
								完成Debug拦截器, 实现单步执行.<br/>
								完成Debug单步调试窗口，可通过脱离于编辑器的GUI进行单步调试. 参见: <a href="debug.html">调试窗口说明...</a><br/>
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
								<br/>
								<b>0.8.3 版本发布！(2008-03-30)</b> <a href="downloads.html">下载...</a><br/>
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
								<b>0.8.2 版本发布！(2008-02-12)</b> <a href="downloads.html">下载...</a><br/>
								核心包类结构全面稳定.<br/>
								修复了非public内部类,匿名类等的getter取值安全异常的bug。<br/>
								修复了各数字类型混合运算时与Java不一致的bug。<br/>
								加入数字类型间转换属性:toByte,toShort,toInteger,toLong,toFloat,toDouble。<br/>
								修复for指令对Iterator和Enumeration的支持。<br/>
								加入局部包含功能, $inline{"xxx.ctl#body"}, $include{"xxx.ctl#body"}, $import{button: "xxx.cm#button"}。参见：<a href="template.html">模板指南...</a><br/>
								加入包含时编码功能, $inline{"xxx.ctl", "UTF-8"}, $include{"xxx.ctl", "UTF-8"}, $display{"xxx.txt", "UTF-8"}。<br/>
								<br/>
								<b>0.8.1 版本发布！(2008-01-30)</b> <a href="downloads.html">下载...</a><br/>
								增加JspTagLib适配器支持, 可以在CTL使用Jsp标签库。<br/>
								增加对struts2(ui)标签库的配置, 参见下载列表中commontemplate-example.zip内的ct_struts2.war。<br/>
								增加对SpringMVC的集成支持，参见下载列表中commontemplate-example.zip内的ct_springmvc.war。参见：<a href="integration.html">集成指南...</a><br/>
								进一步加强EngineHolder.renderTemplate(), 简化Web框架集成, 将Context的创建, response.setContentType()等均内部封装.<br/>
								核心包增加Constant, Variable, Text, Comment等领域实体。<br/>
								注释语法采用 $## 和 $** 表示保留在编译结果中, 否则编译后即抛弃。参见：<a href="template.html">模板指南...</a><br/>
								<br/>
								<b>0.8.0 版本发布！(2008-01-01)</b> <a href="downloads.html">下载...</a><br/>
								将宏指令的特殊语法(加减号前缀)去掉，改为命名规则。<br/>
								增加与struts及struts2的集成example，见下载列表的commontemplate-example.zip。<br/>
								<br/>
								<b>0.7.9 版本发布！(2007-12-27)</b> <a href="downloads.html">下载...</a><br/>
								完善集成方案。<br/>
								<br/>
								<b>0.7.8 版本发布！(2007-12-21)</b> <a href="downloads.html">下载...</a><br/>
								加入集合过滤器表达式支持。<br/>
								调整tools包，集成方案测试通过。<br/>
								<br/>
								<b>0.7.7 版本发布！(2007-12-13)</b> <a href="downloads.html">下载...</a><br/>
								解决模板文件编码问题。<br/>
								重构配置文件，通过引用名称，给用户留出简单的最基本的配置项。<br/>
								参见：<a href="config.html">配置指南...</a><br/>
								<br/>
								<b>0.7.6 版本发布！(2007-12-06)</b> <a href="downloads.html">下载...</a><br/>
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