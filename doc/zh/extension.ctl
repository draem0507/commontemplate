<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. 扩展总接口</b><br/>
								org.commontemplate.config.Configuration<br/>
								配置总接口，通过getXXX方式向引擎提供配置<br/>
								org.commontemplate.standard.ConfigurationSettings<br/>
								配置的setter实现，提供与Configuration的getter配对的setter，通常用于IoC注入方式，也可以编程调用setter<br/>
								org.commontemplate.standard.StandardConfiguration<br/>
								标准配置实现，内置了chain的封装，可以直接调用addXXX方法，简化编程设置方式<br/>
								<br/>
								<b>二. 扩展点</b><br/>
								说明：<br/>
								“注册方法”均指：StandardConfiguration的设置函数<br/>
								“配置方法”均指：内置的properties配置方案，并且继承了标准配置<br/>
								<br/>
								<b>1. 国际化扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.ResourceBundleProvider<br/>
								注册方法：<br/>
								setResourceBundleProvider(ResourceBundleProvider)<br/>
								setMessageBaseName(String baseName) <font color="green">(注：使用内置的PropertiesResourceBundleProvider)</font><br/>
								配置方法：<br/>
								resourceBundleProvider=org.commontemplate.standard.i18n.PropertiesResourceBundleProvider()<br/>
								resourceBundleProvider.baseName=messages<br/>
								参考实现：<br/>
								org.commontemplate.standard.i18n.PropertiesResourceBundleProvider<br/>
								<br/>
								<b>2. 模板资源加载器扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.core.ResourceLoader (模板源加载接口)<br/>
								org.commontemplate.core.Resource (模板源)<br/>
								注册方法：<br/>
								setResourceLoader(ResourceLoader)<br/>
								配置方法：<br/>
								resourceLoader=com.xxx.YourResourceLoader()<br/>
								参考实现：<br/>
								org.commontemplate.standard.loader包下相关类<br/>
								<br/>
								<b>3. 缓存策略扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.Cache<br/>
								注册方法：<br/>
								setTemplateCache(Cache)<br/>
								setStandardCache(String cacheStrategy, int cacheMaxSize) <font color="green">(注：标准cacheStrategy支持NONE, STRONG, SOFT, WEAK, FIFO, LRU, MRU, OSCACHE, EHCACHE)</font><br/>
								配置方法：<br/>
								templateCache=org.commontemplate.standard.cache.LRUCache()<br/>
								templateCache.maxSize=1000<br/>
								参考实现：<br/>
								org.commontemplate.standard.cache包下相关类<br/>
								<br/>
								<b>4. 热加载控制</b><br/>
								引擎在每次从缓存中返回模板之前都会回调ReloadController.shouldReload(String templateName)，<br/>
								如果返回true，引擎将重新读取模板源，<br/>
								并调用ResourceComparator.isModified(Resource oldSource, Resource newSource)进行比较，<br/>
								若模板源已更改，则重新解析模板源并刷新缓存。<br/>
								相关接口：<br/>
								org.commontemplate.config.ReloadController<br/>
								org.commontemplate.config.ResourceComparator<br/>
								注册方法：<br/>
								setReloadController(ReloadController)<br/>
								setModificationCheckInterval(long) <font color="green">(注：使用内置的org.commontemplate.standard.reload.IntervalReloadController)</font><br/>
								setResourceComparator(ResourceComparator)<br/>
								配置方法：<br/>
								reloadController.modificationCheckInterval=36000<br/>
								参考实现：<br/>
								org.commontemplate.standard.reload包下相关类<br/>
								<br/>
								<b>5. 模板名称过滤器扩展</b><br/>
								在加载及缓存之前进行路径过滤，用于保证同一个模板的引用名称总是相同。<br/>
								如：/xxx/yyy.mtl与/xxx/zzz/../yyy.mtl表示同一模板，但会被解析及缓存多次，所以应该用过滤器将其转换成相同的名称。<br/>
								相关接口：<br/>
								org.commontemplate.config.TemplateNameFilter<br/>
								注册方法：<br/>
								addTemplateNameFilter(TemplateNameFilter) <font color="green">(注：添加的TemplateNameFilter会按顺序组成过滤链)</font><br/>
								配置方法：<br/>
								templateNameFilters[200]=com.xxx.XxxFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.filter.TemplateNameRelativer <font color="green">(注：此过滤器过滤 ../ 和 ./ 等相对路径)</font><br/>
								<br/>
								<b>6. 模板源过滤器扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.TemplateReaderFilter<br/>
								注册方法：<br/>
								addTemplateReaderFilter(TemplateReaderFilter)<br/>
								配置方法：<br/>
								templateReaderFilters[200]=com.xxx.XxxFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.webapp.AttributeSyntaxCoatFilter<br/>
								<br/>
								<b>7. 非指令文本块过滤器扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.TextFilter<br/>
								注册方法：<br/>
								addTextFilter(TextFilter)<br/>
								配置方法：<br/>
								textFilters[200]=com.xxx.XxxFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.webapp.CommentSyntaxCoatFilter<br/>
								<br/>
								<b>8. 日志扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.core.Logger<br/>
								注册方法：<br/>
								setLogger(Logger)<br/>
								setCommonsLogging()<br/>
								配置方法：<br/>
								logger=org.commontemplate.standard.log.CommonsLogging()<br/>
								参考实现：<br/>
								org.commontemplate.core.Logger.DEFAULT<br/>
								org.commontemplate.standard.log包下相关类<br/>
								<br/>
								<b>9. 事件监听与扩展</b><br/>
								相关接口：<br/>
								com.commontemplate.core.EventListener<br/>
								com.commontemplate.core.Event<br/>
								注册方法：<br/>
								addEventListener(EventListener)<br/>
								addAsynchronousEventListener(EventListener)<br/>
								配置方法：<br/>
								eventListeners[100]=com.xxx.XxxListener <font color="green">(注：下标号任意，只用于排序)</font><br/>
								asynchronousEventListeners[100]=com.xxx.XxxListener<br/>
								参考实现：<br/>
								…<br/>
								引擎发布的事件：<br/>
								com.commontemplate.core.event包下相关类<br/>
								<br/>
								<b>10. 语法扩展</b><br/>
								相关类：<br/>
								org.commontemplate.config.Syntax (指令语法及特殊指令设置)<br/>
								org.commontemplate.config.Keywords (表达式关键字设置)<br/>
								注册方法：<br/>
								setSyntax(Syntax)<br/>
								setKeywords(Keywords)<br/>
								配置方法：<br/>
								syntaxString="${}#*!end"<br/>
								keywordsString=null,true,false,this,super,context<br/>
								默认使用：<br/>
								Syntax.DEFAULT<br/>
								Keywords.DEFAULT<br/>
								<br/>
								<b>11. 指令扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.LineDirectiveHandler<br/>
								org.commontemplate.config.StartDirectiveHandler<br/>
								org.commontemplate.config.MiddleDirectiveHandler<br/>
								注册方法：<br/>
								addDirectiveHandler(String name, LineDirectiveHandler)<br/>
								addDirectiveHandler(String name, StartDirectiveHandler)<br/>
								<font color="green">(注：方法名重载，用于表明Line, Start, Middle等指令注册在同一个集合中，相互之间名称不能重复)</font><br/>
								配置方法：<br/>
								directive{xxx}=com.xxx.XXXDirective<br/>
								参考实现：<br/>
								org.commontemplate.standard.directive及其子包下相关类<br/>
								注意事项：<br/>
								DirectiveHandler应该保证线程安全，最简单的保证方式是：<br/>
								(1) 若DirectiveHandler实现类中没有任何属性，则该实现类一定是线程安全的。<br/>
								(2) 若DirectiveHandler实现类中所有属性都是final的(或只在构造函数中赋值)，则该实现类也是线程安全的。<br/>
								(3) 正确同步所有属性修改。<br/>
								<br/>
								<b>12. 操作符扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.BinaryOperatorHandler<br/>
								org.commontemplate.config.UnaryOperatorHandler<br/>
								注册方法：<br/>
								addBinaryOperatorHandler(String name, BinaryOperatorHandler)<br/>
								addUnaryOperatorHandler(String name, UnaryOperatorHandler)<br/>
								addBinaryOperatorPriority(String name, int priority) <font color="green">(注：不设置将默认为0)</font><br/>
								addUnaryOperatorPriority(String name, int priority) <font color="green">(注：不设置，符号型默认为0，名称型默认为functionPriority)</font><br/>
								setFunctionPriority(int priority)<br/>
								参考实现：<br/>
								org.commontemplate.standard.operator及其子包下相关类<br/>
								注意事项：<br/>
								OperatorHandler应该保证线程安全，方式同上面的DirectiveHandler<br/>
								OperatorHandler不应该改变操作数的状态，如：两个集合相加，应将两个集合的项添加到一个新的集合中，而不是将其中一个集合的项添加到另一个集合中。<br/>
								特殊操作数：<br/>
								(a) 操作数延迟求值：<br/>
								用于短路情况，声明Lazy后，参数将以LazyOperand的实例传入，需使用operand = ((LazyOperand)operand).evaluate()方式取其真实值。<br/>
								如：Boolean的And/Or操作，左操作数决定右操作数是否需要求值，就要用到RightOperandLazy。<br/>
								相关标识：BinaryOperatorHandler.isLeftOperandLazy(), BinaryOperatorHandler.isRightOperandLazy(), UnaryOperatorHandler.isOperandLazy()<br/>
								(b) 操作数名称化：<br/>
								如果操作数为无引号字符串时，不将其作为变量取值，而作为字符串，<br/>
								如：对象的属性${bean.property}，键值对的键${key:value}等，<br/>
								与加号对比：bean + property，property将作为变量，而不是名称，<br/>
								相关标识：BinaryOperatorHandler.isLeftOperandNamed(), BinaryOperatorHandler.isRightOperandNamed(), UnaryOperatorHandler.isOperandNamed()<br/>
								(c) 操作数函数化：<br/>
								如果操作数为函数时，不将其作为函数一元操作符运算，而作为org.commontemplate.util.Funtion封装，<br/>
								如：对象的属性${bean.function()} <br/>
								与加号对比：bean + function()，bean将与function()的返回值相加，<br/>
								相关标识：BinaryOperatorHandler.isRightOperandFunctioned(), UnaryOperatorHandler.isOperandFunctioned()<br/>
								关于函数：<br/>
								函数其实就是非符号(与变量名规则相同)的一元操作符，注册及使用方式与一元操作符一致，<br/>
								非符号一元操作符的操作数必需有括号，否则将视为变量，<br/>
								如：已注册了“abs”一元操作符，则必需用abs(operand)， 而不能用abs operand，否则在复杂表达式中与变量引起歧义，<br/>
								对比：符号一元操作符“!”，可以用“! operand”，也可以用“!(operand)”<br/>
								<br/>
								<b>13. 属性扩展</b> <font color="green">(注：该扩展用于为"."点号操作符提供数据)</font><br/>
								相关接口：<br/>
								org.commontemplate.standard.property.PropertyHandler<br/>
								org.commontemplate.standard.property.StaticPropertyHandler<br/>
								注册方法：<br/>
								addPropertyHandler(Class beanClass, String propertyName, PropertyHandler)<br/>
								addStaticPropertyHandler(String propertyName, StaticPropertyHandler)<br/>
								配置方法：<br/>
								property{java.lang.String.xxx}=com.xxx.XXXPropertyHandler<br/>
								staticProperty{xxx}=com.xxx.XXXPropertyHandler<br/>
								参考实现：<br/>
								org.commontemplate.standard.property包及其子包相关类<br/>
								<br/>
								<b>14. 方法扩展</b> <font color="green">(该扩展用于为"."点号操作符提供数据，并且只有在配置functionAvailable=true时才有效)</font><br/>
								相关接口：<br/>
								org.commontemplate.standard.function.FunctionHandler<br/>
								org.commontemplate.standard.function.StaticFunctionHandler<br/>
								注册方法：<br/>
								addFunctionHandler(Class beanClass, String functionName, FunctionHandler)<br/>
								addStaticFunctionHandler(String functionName, StaticFunctionHandler)<br/>
								配置方法：<br/>
								function{java.lang.String.xxx}=com.xxx.XXXFunctionHandler<br/>
								staticFunction{xxx}=com.xxx.XXXFunctionHandler<br/>
								参考实现：<br/>
								org.commontemplate.standard.function包及其子包相关类<br/>
								<br/>
								<b>15. 序列扩展</b> <font color="green">(该扩展用于为".."双点号操作符提供数据)</font><br/>
								相关接口：<br/>
								org.commontemplate.standard.operator.sequence.StringSequenceOperatorHandler<br/>
								org.commontemplate.standard.operator.sequence.StringSequence<br/>
								注册方法：<br/>
								addStringSequence(String[] sequence)<br/>
								addStringSequence(String[] sequence, boolean cycle, boolean ignoreCase)<br/>
								配置方法：<br/>
								sequence&lt;weekDays&gt;=Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday<br/>
								参考实现：<br/>
								org.commontemplate.standard.operator.sequence包下相关类<br/>
!$
	<!--$end-->
<!--$end-->