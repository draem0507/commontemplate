<!--$extends{"/doc/template/frame.ctl"}-->
<!--$zone{"content"}-->
$!
									<table border="1" align="center"
										style="border-style: solid; border-collapse: collapse; border-width: 1px; border-color: #000000;">
										<tr style="background-color: #DBEEF3;">
											<td>任务描述</td>
											<td>类型</td>
											<td>工作量</td>
											<td>紧急性</td>
											<td>重要性</td>
										</tr>
										<tr>
											<td>实现命令行调试器.</td>
											<td>需求</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>服务器以命令行启动时,Swing不可用 (自动适应命令行调试器).</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>实现远程调试器,通过配置服务器IP,端口, TCP连接到服务器进行调试,客户端同样可用Swing或命令行.</td>
											<td>需求</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>配置文件容错处理, 可设置当类不存在时忽略.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>CTE脱离MVC框架运行. codebehind.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>加入commontemplate-plugin.properties支持, 自动抓取.</td>
											<td>需求</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>加入对DOM模型的支持,root,parent,children,ancestors,name,type,namespace.</td>
											<td>需求</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>(自动导入)全局宏定义, 全局父模板.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>相似函数调用时, String转为char处理.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>数字操作符: round 基于有效位四舍五入.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>扩展属性也应能处理get和is前缀.</td>
											<td>BUG</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>扩展属性number.sin, cos, tan, ctan.</td>
											<td>需求</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>Element不必保持其原生文本, 只要有Location, 到Template中统一读取, 以减小模板树占用的内存大小.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>允许函数调用, 禁止void函数调用, 正则表达式匹配禁止, 如delete*,remove*,save*,update*</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>配置中的集合, 过滤空项.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>明显为List的配置, 将Chain放入引擎中实现.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>可动态编辑Debug窗口中的变量.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>Debug窗口中的变量, 有变化的用红色显示.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>属性外套实现有BUG, 空指针检测不全.</td>
											<td>BUG</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>四则运算优化器有BUG, 已暂时屏蔽.</td>
											<td>BUG</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>context不作为关键字.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>清理不明确的操作符,并讨论操作符是否合理.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>Bpackage.name与package["name"],一个先查子包,一个先查属性.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>实现外部文件引入List和Map.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>出错位置信息准确性, 当抛出异常时, 应显示准确的出错元素在模板中的行列位置, 并显示出错位置附件的模板内容.</td>
											<td>BUG</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>错误信息友好性, 抛出来的异常信息应有助于解决错误, 不应该出现底层的无意义信息, 可以通过故意写错模板内容等方式来测试.</td>
											<td>优化</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>对象存储加一个不分级控制的存储空间. 此存储空间在单一Context内有效(非全局),
											但不受LocalContext栈的隔离.</td>
											<td>优化</td>
											<td>小</td>
											<td>中</td>
											<td>中</td>
										</tr>
										<tr>
											<td>测试JspTagLib的集成方案, 尤其是对上下级关联的Tag测试.</td>
											<td>测试</td>
											<td>中</td>
											<td>高</td>
											<td>高</td>
										</tr>
										<tr>
											<td>加入对jsp2.1的taglib支持, 主要是EL的转变</td>
											<td>需求</td>
											<td>中</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>指令单元测试, 完善边界值测试, 提高测试覆盖率.</td>
											<td>测试</td>
											<td>中</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>操作符单元测试, 操作符的个数比较多. 急需测试.</td>
											<td>测试</td>
											<td>大</td>
											<td>高</td>
											<td>高</td>
										</tr>
										<tr>
											<td>操作符重构, 操作符的分包, 命名等都不是很规范, 应尽快做一次全面重构.</td>
											<td>优化</td>
											<td>中</td>
											<td>高</td>
											<td>高</td>
										</tr>
										<tr>
											<td>写一个验证性论坛, 用CT做全部页面, 用CT做代码生成, 用CT做SQL拼接模板. 并做压力测试.</td>
											<td>集成</td>
											<td>大</td>
											<td>低</td>
											<td>高</td>
										</tr>
										<tr>
											<td>写一个Ant脚本, 自动提取EL相关类打成jar包</td>
											<td>集成</td>
											<td>中</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>使用Maven发布</td>
											<td>集成</td>
											<td>中</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>全面检查序列化, 任何接口都不应继承Serializable, 但要保证整个模板树都是可序列化的.</td>
											<td>优化</td>
											<td>中</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>完成.Net版的基础实现, 先从Java版导过去, 再重构. 工作量比较大.</td>
											<td>需求</td>
											<td>大</td>
											<td>低</td>
											<td>高</td>
										</tr>
										<tr>
											<td>使用Spring作为配置工具. 通过<beans>配置完成ConfigurationSettings的组装.
											可以写一个转换器, 从现有的properies配置转换过去.</td>
											<td>需求</td>
											<td>大</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>实现XSLT解析, Velocity和FreeMarker都支持对XSLT的解析, CT也考虑实现, 但延后.</td>
											<td>需求</td>
											<td>大</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>Html标签属性语法外套优化, 主要是对不合法html标签的容错性的良好支持. 此功能可单独提取,
											作为Velocity和FreeMarker等的扩展.</td>
											<td>需求</td>
											<td>大</td>
											<td>中</td>
											<td>高</td>
										</tr>
										<tr>
											<td>"完成与AntTask的集成, 可用于代码生成等, 实现:org.commontemplate.tools.ant.TemplateTask"</td>
											<td>需求</td>
											<td>小</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>"实现热加载国际化信息:org.commontemplate.standard.i18n.ReloadablePropertiesResourceBundleProvider"</td>
											<td>需求</td>
											<td>小</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>实现国际化信息与struts2的集成, 也就是可分包, 分级放置properties文件,
											而不是单一的properties文件, 并保证集成的简便性, 如需要, 可重构ResourceBundleProvider接口.</td>
											<td>需求</td>
											<td>中</td>
											<td>低</td>
											<td>高</td>
										</tr>
										<tr>
											<td>写Eclipse/NetBeans的编辑器插件, 可在某Html编辑器插件的基础上扩展. 完成高亮显示, 自动提示.</td>
											<td>需求</td>
											<td>大</td>
											<td>低</td>
											<td>高</td>
										</tr>
										<tr>
											<td>"完成properties高亮显示的html过滤, 通用在内容中加入<font color=""""></font>实现:org.commontemplate.standard.directive.filter.highlight.PropertiesCodeFilter"</td>
											<td>需求</td>
											<td>小</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>完成网站的英文版</td>
											<td>文档</td>
											<td>大</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>ExpressionConfigurationSettings是否应与ConfigurationSettings合并?</td>
											<td>疑问</td>
											<td>小</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>StandardConfiguration重构, 加入: static StandardConfiguration
											loadStandardConfiguration()</td>
											<td>优化</td>
											<td>小</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>代码生成时, 自动去掉块指令所在的空白行. 如: 模板通常会将$if和$end等放在单独的行中,
											生成的文本会在指令位置出现空白行, 应实现一过滤器, 自动去掉该空白行.</td>
											<td>需求</td>
											<td>中</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>重构异常, 分析ParsingException, RenderingException,
											EvaluationException等的关系.</td>
											<td>优化</td>
											<td>小</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>缓存更新方式, 抽取策略接口, 加入Job线程定时更新缓存策略.</td>
											<td>优化</td>
											<td>小</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>"用多线程(多核CPU)模拟并发获取模板, 测试并发."</td>
											<td>测试</td>
											<td>中</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>通过跟踪内存栈, 记录模板编译后占用内存大小, 以及模板源内容大小, 给出测试结果Excel对照表 .</td>
											<td>测试</td>
											<td>中</td>
											<td>低</td>
											<td>中</td>
										</tr>
										<tr>
											<td>是否应支持switch case, while等?</td>
											<td>疑问</td>
											<td>小</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>是否应支持变量别名引用alias?</td>
											<td>疑问</td>
											<td>小</td>
											<td>低</td>
											<td>低</td>
										</tr>
										<tr>
											<td>加强模板测试, 在test/integration/template/目录下放一个xxx.ctl, 写入模板,
											在test/integration/result/目录下放同名的模板结果,
											运行test/integration.TemplateTester可以进行自动测试. 另外,
											模板结果的获取可以通过integration.OutTester得到,
											它将读取模板文件test/integration/out.ctl的内容, 并将解析结果输出到控制台.</td>
											<td>测试</td>
											<td>大</td>
											<td>中</td>
											<td>中</td>
										</tr>
									</table>
									<br/>
!$
<!--$end-->
<!--$end-->