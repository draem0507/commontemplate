<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>1. 概述</b><br/>
								声明：各模板引擎设计目标不同，纯功能性对比可能有失偏颇，此对比清单仅供参考。<br/>
								Velocity的设计目标是功能最简化，所以它只提供10个左右的最基本的指令。<br/>
								FreeMarker的设计目标是功能完备，所以它提供尽可能多的指令和builtin处理。<br/>
								CommonTemplate的设计目标是高度可扩展和功能完备，所以它提供较多的扩展点。<br/>
								<br/>
								<a name="velocity" /><b>2. 与Velocity特性对比清单</b><br/>
								Velocity项目地址：<a href="http://velocity.apache.org">http://velocity.apache.org</a><br/>
								Java Side:<br/>
								Velocity只支持Tools扩展, 可外部扩展函数.<br/>
								Velocity未作微核处理, 第三方包扩展较困难.<br/>
								Velocity过程拦截面, 状态事件未处理.<br/>
								Velocity指令不可扩展.<br/>
								Velocity操作符不可扩展.<br/>
								Template Side:<br/>
								Velocity不支持宏导入.<br/>
								Velocity不支持宏的内部块回调.<br/>
								Velocity不支持模板继承, 较难实现框架页模板, 可借助sitemesh等实现.<br/>
								Velocity不支持文本过滤指令.<br/>
								Velocity不支持递归迭代, 并行迭代.<br/>
								Velocity不支持break和continue迭代.<br/>
								Velocity不支持数据块加载.<br/>
								Velocity不支持国际化.<br/>
								Velocity不支持块变量.<br/>
								Velocity不支持闭包表达式<br/>
								Velocity不支持日期操作符<br/>
								Velocity不支持集合操作符<br/>
								待补充...<br/>
								<br/>
								<a name="freemarker" /><b>3. 与FreeMarker特性对比清单</b><br/>
								FreeMarker项目地址：<a href="http://www.freemarker.org">http://www.freemarker.org</a><br/>
								(1) 语法<br/>
								CommonTemplate有更一致的语法规则: $指令{表达式}, 而FreeMarker的&lt;#&gt;,${},#{},&lt;@&gt;等各式各样的前导符实在有点多.<br/>
								(2) 表达式<br/>
								CommonTemplate中所有指令的表达式模型是一致的, 所有操作符在所有指令中有效, 而FreeMarker每个指令表达式均不相同, 如: &lt;#list xxx as x&gt;中的"as"<br/>
								(3) 外部属性<br/>
								对于不可变类, FreeMarker采用?号操作符进行BuildIn处理, 而CommonTemplate采用属性扩展, 保持与原有属性一样的调用方式, 如: ${"james".capitalize}, 并且可以自行扩展.<br/>
								(4) 扩展点<br/>
								CommonTemplate在扩展点方面比FreeMarker做得更多, 包括各种拦截器,过滤器,事件,处理器等, 并且平等对待标准包与第三方包(也就是第三方包能实现或覆盖标准包所有功能).<br/>
								(5) 宏指令<br/>
								CommonTemplate的宏设计与FreeMarker类似, 同样支持回调内部块, 而且CommonTemplate将宏作为一等公民对待(也就是与标准指令同样的调用方式), 而FreeMarker采用特殊的&lt;@&gt;调用.<br/>
								(6) 继承指令<br/>
								除了宏, CommonTemplate支持模板继承(子模板可覆盖父模板中部分内容), 对布局非常有帮助.<br/>
								(7) 集合迭代<br/>
								CommonTemplate支持简单次数迭代，(用于输出树的)递归迭代, (多集合)并行迭代等<br/>
								(8) 数据加载<br/>
								CommonTemplate支持json, yaml, properties等格式数据加载.<br/>
								(9) 工具<br/>
								CommonTemplate拥有脱离开发环境的调试器, 查看器等工具, 可以使页面开发人员更独立的测试模板.<br/>
								待补充...<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->