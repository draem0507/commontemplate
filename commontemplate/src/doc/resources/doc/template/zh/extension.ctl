<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>一. 扩展总接口</b> <a href="architecture.html">整体设计...</a><br/>
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
								<a name="event"/><b>1. 事件监听与扩展</b><br/>
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
								引擎发布的事件：<br/>
								com.commontemplate.core.event包下相关类<br/>
								<br/>
								<a name="loader"/><b>2. 模板资源加载器扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.core.SourceLoader (模板源加载接口)<br/>
								org.commontemplate.core.Source (模板源)<br/>
								注册方法：<br/>
								setSourceLoader(SourceLoader)<br/>
								配置方法：<br/>
								sourceLoader=com.xxx.YourSourceLoader()<br/>
								参考实现：<br/>
								org.commontemplate.standard.loader包下相关类<br/>
								<br/>
								<a name="initializer"/><b>3. 上下文初始化器扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.ContextInitializer<br/>
								注册方法：<br/>
								setContextInitializer(ContextInitializer)<br/>
								配置方法：<br/>
								contextInitializers[200]=com.xxx.XXXInitializer()<br/>
								参考实现：<br/>
								org.commontemplate.standard.context包下相关类<br/>
								<br/>
								<a name="cache"/><b>4. 缓存策略扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.Cache<br/>
								注册方法：<br/>
								setTemplateCache(Cache)<br/>
								setTemplatePersistentCache(Cache)<br/>
								setStandardCache(String cacheStrategy, int cacheMaxSize) <font color="green">(注：标准cacheStrategy支持NONE, STRONG, SOFT, WEAK, FIFO, LRU, MRU, OSCACHE, EHCACHE)</font><br/>
								配置方法：<br/>
								templateCache=org.commontemplate.standard.cache.LRUCache()<br/>
								templateCache.maxSize=1000<br/>
								templatePersistentCache=org.commontemplate.standard.cache.DiskSerialCache()<br/>
								templatePersistentCache.directory=C:/cache<br/>
								参考实现：<br/>
								org.commontemplate.standard.cache包下相关类<br/>
								<br/>
								<a name="reload"/><b>5. 热加载控制</b><br/>
								引擎在每次从缓存中返回模板之前都会回调ReloadController.shouldReload(String templateName)，<br/>
								如果返回true，引擎将重新读取模板源，<br/>
								并调用SourceComparator.isModified(Template template, Source source)进行比较，<br/>
								若模板源已更改，则重新解析模板源并刷新缓存。<br/>
								相关接口：<br/>
								org.commontemplate.config.ReloadController<br/>
								org.commontemplate.config.SourceComparator<br/>
								注册方法：<br/>
								setReloadController(ReloadController)<br/>
								setModificationCheckInterval(long) <font color="green">(注：使用内置的org.commontemplate.standard.reload.IntervalReloadController)</font><br/>
								setSourceComparator(SourceComparator)<br/>
								配置方法：<br/>
								reloadController.modificationCheckInterval=36000<br/>
								参考实现：<br/>
								org.commontemplate.standard.reload包下相关类<br/>
								<br/>
								<a name="template"/><b>6. 模板名称过滤器扩展</b><br/>
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
								<a name="resouce"/><b>7. 模板源过滤器扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.SourceFilter<br/>
								注册方法：<br/>
								addSourceFilter(SourceFilter)<br/>
								配置方法：<br/>
								sourceFilters[200]=com.xxx.XxxFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.coat.AttributeSyntaxCoatFilter<br/>
								<br/>
								<a name="text"/><b>8. 非指令文本块过滤器扩展</b><br/>
								在<b>编译</b>期过滤非指令文本块.<br/>
								相关接口：<br/>
								org.commontemplate.config.TextFilter<br/>
								注册方法：<br/>
								addTextFilter(TextFilter)<br/>
								配置方法：<br/>
								textFilters[200]=com.xxx.XxxFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.coat.CommentSyntaxCoatFilter<br/>
								<br/>
								<a name="expressionfilter"/><b>9. 表达式过滤器扩展</b><br/>
								在表达式<b>编译</b>之前过滤表达式文本串.<br/>
								相关接口：<br/>
								org.commontemplate.config.ExpressionFilter<br/>
								注册方法：<br/>
								addExpressionFilter(ExpressionFilter)<br/>
								配置方法：<br/>
								expressionFilters[200]=com.xxx.XxxFilter<br/>
								expressionReplacement{&amp;lt;}=&lt; <font color="green">(注：使用内置的ExpressionReplacementFilter)</font><br/>
								参考实现：<br/>
								org.commontemplate.standard.filter.ExpressionReplacementFilter<br/>
								<br/>
								<a name="render"/><b>10. 模板元素渲染过程拦截器扩展</b><br/>
								拦截render()过程.<br/>
								相关接口：<br/>
								org.commontemplate.config.RenderInterceptor<br/>
								注册方法：<br/>
								addRenderInterceptor(RenderInterceptor)<br/>
								配置方法：<br/>
								renderInterceptors[200]=com.xxx.XxxInterceptor<br/>
								参考实现：<br/>
								org.commontemplate.standard.debug.DebugInterceptor<br/>
								<br/>
								<a name="evaluate"/><b>11. 表达式求值过程拦截器扩展</b><br/>
								拦截evaluate()过程.<br/>
								相关接口：<br/>
								org.commontemplate.config.EvaluateInterceptor<br/>
								注册方法：<br/>
								addEvaluateInterceptor(EvaluateInterceptor)<br/>
								配置方法：<br/>
								evaluateInterceptors[200]=com.xxx.XxxInterceptor<br/>
								参考实现：<br/>
								无<br/>
								<br/>
								<a name="syntax"/><b>12. 语法扩展</b><br/>
								相关类：<br/>
								org.commontemplate.config.Syntax (指令语法及特殊指令设置)<br/>
								注册方法：<br/>
								setSyntax(Syntax)<br/>
								配置方法：<br/>
								syntax.directiveLeader='$'<br/>
								syntax.expressionBegin='{'<br/>
								syntax.expressionEnd='}'<br/>
								syntax.lineComment='#'<br/>
								syntax.blockComment='*'<br/>
								syntax.noParse='!'<br/>
								syntax.endDirectiveName=end<br/>
								默认使用：<br/>
								Syntax.DEFAULT<br/>
								<br/>
								<a name="keywords"/><b>13. 关键字扩展</b><br/>
								相关类：<br/>
								org.commontemplate.config.Keywords (表达式关键字设置)<br/>
								注册方法：<br/>
								setKeywords(Keywords)<br/>
								配置方法：<br/>
								keywords.null="null"<br/>
								keywords.true="true"<br/>
								keywords.false="false"<br/>
								keywords.current="this"<br/>
								keywords.parent="super"<br/>
								默认使用：<br/>
								Keywords.DEFAULT<br/>
								<br/>
								<a name="scope"/><b>14. 区域变量扩展</b><br/>
								相关接口：<br/>
								org.commontemplate.config.ScopeHandler<br/>
								注册方法：<br/>
								addScopeHandler(name, ScopeHandler)<br/>
								配置方法：<br/>
								scopeHandler{xxx}=com.xxx.XXXScopeHandler()<br/>
								参考实现：<br/>
								org.commontemplate.standard.scope包下相关类<br/>
								<br/>
								<a name="directive"/><b>15. 指令扩展</b><br/>
								相关接口和基类：<br/>
								org.commontemplate.config.DirectiveHandler<br/>
								org.commontemplate.config.BlockDirectiveHandler<br/>
								org.commontemplate.config.MiddleDirectiveHandler<br/>
								org.commontemplate.standard.directive.DirectiveHandlerSupport<br/>
								org.commontemplate.standard.directive.BlockDirectiveHandlerSupport<br/>
								org.commontemplate.standard.directive.MiddleDirectiveHandlerSupport<br/>
								注册方法：<br/>
								addDirectiveHandler(String name, DirectiveHandler)<br/>
								配置方法：<br/>
								directive{xxx}=com.xxx.XXXDirective<br/>
								参考实现：<br/>
								org.commontemplate.standard.directive及其子包下相关类<br/>
								注意事项：<br/>
								DirectiveHandler应该保证线程安全，最简单的保证方式是：<br/>
								(1) 若DirectiveHandler实现类中没有任何属性，则该实现类一定是线程安全的。<br/>
								(2) 若DirectiveHandler实现类中所有属性都是final的(或只在构造函数中赋值)，则该实现类也是线程安全的。<br/>
								(3) 若DirectiveHandler实现类中所有属性状态都是正确同步的，则该实现类也是线程安全的。<br/>
								表达式状态：<br/>
								(a) 表达式名称化：<br/>
								如果处理类的isExpressionNamed()方法返回true，表示当表达式为变量名时，作为名称串处理，<br/>
								如：$macro{button}等价于$macro{"button"}<br/>
								如果特殊情况需要使用变量，可使用反斜线处理：$macro{\name}<br/>
								(b) 表达式是否必需：<br/>
								如果处理类的isExpressionRequired()方法返回true，表示当指令必需有表达式，否则报错。<br/>
								<br/>
								<a name="operator"/><b>16. 操作符扩展</b><br/>
								相关接口和基类：<br/>
								org.commontemplate.config.BinaryOperatorHandler<br/>
								org.commontemplate.config.UnaryOperatorHandler<br/>
								org.commontemplate.standard.operator.BinaryOperatorHandlerSupport<br/>
								org.commontemplate.standard.operator.UnaryOperatorHandlerSupport<br/>
								注册方法：<br/>
								addBinaryOperatorHandler(String name, BinaryOperatorHandler)<br/>
								addUnaryOperatorHandler(String name, UnaryOperatorHandler)<br/>
								addBinaryOperatorPriority(String name, int priority) <font color="green">(注：不设置将默认为0)</font><br/>
								addUnaryOperatorPriority(String name, int priority) <font color="green">(注：不设置，符号型默认为0，名称型默认为functionPriority)</font><br/>
								setFunctionPriority(int priority)<br/>
								配置方法：<br/>
								binaryOperator{*}=com.xxx.XXXBinaryOperatorHandler()<br/>
								unaryOperator{-}=com.xxx.XXXUnaryOperatorHandler()<br/>
								如果一个操作符针对不同的操作数类型有不同的处理类，可以使用Chain封装:<br/>
								binaryOperator{*}=org.commontemplate.standard.operator.BinaryOperatorHandlerChain()<br/>
								binaryOperator{*}.binaryOperatorHandlers=multiplyBinaryOperator[]<br/>
								multiplyBinaryOperator[100]=com.xxx.NumberMultiplyOperatorHandler()<br/>
								multiplyBinaryOperator[200]=com.xxx.StringRepeatOperatorHandler()<br/>
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
								(d) 关于函数：<br/>
								函数其实就是非符号(与变量名规则相同)的一元操作符，注册及使用方式与一元操作符一致，<br/>
								非符号一元操作符UnaryOperatorHandler.isKeyword()的返回值：<br/>
								为true时，该操作符名称将不可作为变量名。<br/>
								为false时，操作符的操作数必需有括号，否则将视为变量。<br/>
								如：注册“abs”一元操作符，则必需用abs(operand)， 而不能用abs operand，否则在复杂表达式中与变量引起歧义，<br/>
								如果abs的isKeyword()为true, 则可以用abs operand, 但abs将不能再作为变量名。<br/>
								对比：符号一元操作符“!”，可以用“! operand”，也可以用“!(operand)”<br/>
								(e) 编译器优化：<br/>
								当操作数都为常量时，编译器会在编译期求值，并保存到表达式树，操符符应保证传入相同的参数，总是得到相同的结果<br/>
								如果不希望被优化，覆写isOptimize()方法，使其返回false<br/>
								<br/>
								<a name="property"/><b>17. 属性扩展</b><br/>
								用于为"."点号操作符提供数据<br/>
								属性调用方式如: ${bean.property}<br/>
								静态属性调用方式如: ${.now}<br/>
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
								<a name="function"/><b>18. 方法扩展</b><br/>
								用于为"."点号操作符提供数据，并且只有在配置functionAvailable=true时才有效<br/>
								方法调用方式如: ${obj.func(arg1,arg2)}<br/>
								静态方法调用方式如: ${.func(arg1,arg2)}<br/>
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
								<a name="sequence"/><b>19. 序列扩展</b><br/>
								用于为".."双点号操作符提供数据<br/>
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
								<br/>
								<a name="message"/><b>20. 国际化扩展</b><br/>
								用于为$msg或$message指令提供国际化信息内容<br/>
								相关接口：<br/>
								org.commontemplate.standard.i18n.ResourceBundleProvider<br/>
								注册方法：<br/>
								setResourceBundleProvider(ResourceBundleProvider)<br/>
								setMessageBaseName(String baseName) <font color="green">(注：使用内置的PropertiesResourceBundleProvider)</font><br/>
								配置方法：<br/>
								resourceBundleProvider=org.commontemplate.standard.i18n.PropertiesResourceBundleProvider()<br/>
								resourceBundleProvider.baseName=messages<br/>
								参考实现：<br/>
								org.commontemplate.standard.i18n.PropertiesResourceBundleProvider<br/>
								<br/>
								<a name="logger"/><b>21. 日志扩展</b><br/>
								用于$log指令的输出.<br/>
								相关接口：<br/>
								org.commontemplate.util.log.LoggerProvider<br/>
								org.commontemplate.util.log.Logger<br/>
								配置方法：<br/>
								loggerProvider=org.commontemplate.standard.log.CommonsLoggerProvider()<br/>
								参考实现：<br/>
								org.commontemplate.util.log包下相关类<br/>
								<br/>
								<a name="converter"/><b>22. 迭代数据集合转换器扩展</b><br/>
								用于为"$for"指令提供迭代数据<br/>
								相关接口和基类：<br/>
								org.commontemplate.standard.collection.CollectionConverter<br/>
								配置方法：<br/>
								collectionConverter{com.xxx.XXX}=com.xxx.XXXCollectionConverter<br/>
								参考实现：<br/>
								org.commontemplate.standard.collection包下相关类<br/>
								<br/>
								<a name="data"/><b>23. 数据加载类型扩展</b><br/>
								用于为"$data"指令提供数据<br/>
								相关接口和基类：<br/>
								org.commontemplate.standard.data.DataProvider<br/>
								org.commontemplate.standard.data.InputStreamDataProvider<br/>
								org.commontemplate.standard.data.StringDataProvider<br/>
								配置方法：<br/>
								dataProvider{xxx}=com.xxx.XXXDataProvider<br/>
								参考实现：<br/>
								org.commontemplate.standard.data包下相关类<br/>
								<br/>
								<a name="code"/><b>24. 代码过滤扩展</b><br/>
								用于为"$code"指令提供过滤器<br/>
								相关接口和基类：<br/>
								org.commontemplate.core.OutputFilter<br/>
								配置方法：<br/>
								codeFilter{xxx}=com.xxx.XXXCodeFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.directive.filter.code包下相关类<br/>
								<br/>
								<a name="escape"/><b>25. 转义过滤扩展</b><br/>
								用于为"$escape"指令提供过滤器<br/>
								相关接口和基类：<br/>
								org.commontemplate.core.OutputFilter<br/>
								配置方法：<br/>
								escapeFilter{xxx}=com.xxx.XXXEscapeFilter<br/>
								参考实现：<br/>
								org.commontemplate.standard.directive.filter.escape包下相关类<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->