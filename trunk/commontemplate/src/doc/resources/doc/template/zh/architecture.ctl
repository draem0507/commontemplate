<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. 包结构设计说明</b><br/>
								<b>包设计原则：</b><br/>
								高内聚：<br/>
								重用发布等价原则(REP)： 包的重用粒度应该与可发布粒度相同，可发布是指可单独打成组件包(jar)，并且组件包能符合开闭原则(OCP)。<br/>
								共同重用原则(CRP)： 包中的类应该有同样的重用可能性，也就是紧密协作的类应该放在一个包。<br/>
								共同封闭原则(CCP)： 包中所有类应该对同一类性质的改变作出相同反应(全改或全不改)，若改动则变化应在包内终止而不传播到其它包。<br/>
								低耦合：<br/>
								无环依赖原则(ADP)： 包之间不出现相互依赖和循环依赖，这是最基本应做到的。<br/>
								稳定依赖原则(SDP)： 被依赖的包应该总是比依赖者更稳定，也就是不要让一个稳定的包依赖于不稳定包。<br/>
								稳定抽象原则(SAP)： 越稳定的包应该越抽象，稳定的包不抽象将导致扩展性极差，抽象的包不稳定将导致其依赖包跟随变化。 <br/>
								<font color="green">(注：稳定性和抽象性，请参见此节后面的计算方式)</font><br/>
								<b>CommonTemplate总体包结构：</b><br/>
								<b>(1) util包 (通用工具包)</b><br/>
								<font color="gray">位置：</font>org.commontemplate.util及其子包<br/>
								<font color="gray">职责定义：</font>相当于java.util的扩展，实现一些其它通用功能，它只依赖于JDK，不依赖于CommonTemplate引擎。<br/>
								<font color="green">(注：以下包都依赖于JDK相关包及上面的util包，不再说明)</font><br/>
								<b>(2) core包 (核心模型包)</b> <font color="green">(Application Programming Interface(API))</font><br/>
								<font color="gray">位置：</font>org.commontemplate.core及其子包<br/>
								<font color="gray">职责定义：</font>模板语言的API包，定义模板各元素间的关系，资源管理接口等，全部为接口或抽象类，围绕Template和Context展开。<br/>
								<font color="gray">中心类：</font>org.commontemplate.core.Factory<br/>
								<b>(3) engine包 (引擎实现包)</b><br/>
								<font color="gray">位置：</font>org.commontemplate.engine及其子包<br/>
								<font color="gray">职责定义：</font>模板引擎的实现，处理模板加载解析等，实现core包的所有接口，此包隐藏实现细节，只暴露Engine类，便于后期优化，Engine实现了core包的Factory，core包的其它相关接口都可以通过接口间导航获取。<br/>
								<font color="gray">中心类：</font>org.commontemplate.engine.Engine<br/>
								<font color="gray">依赖于：</font>core包, config包<br/>
								<b>(4) config包 (配置接口包)</b> <font color="green">(Service Provide Interface(SPI))</font><br/>
								<font color="gray">位置：</font>org.commontemplate.config及其子包<br/>
								<font color="gray">职责定义：</font>留给第三方的扩展接口，以Configuration接口为中心，Configuration定义了engine包所需配置的getXXX()方法，但不包括setXXX()等，因为SPI不应限制实现类的注册方式。<br/>
								<font color="gray">中心类：</font>org.commontemplate.config.Configuration<br/>
								<font color="gray">依赖于：</font>core包<br/>
								<b>(5) standard包 (标准参考配置实现包)</b><br/>
								<font color="gray">位置：</font>org.commontemplate.standard及其子包<br/>
								<font color="gray">职责定义：</font>config包的标准参考实现，包括指令及操作符等语法的定义相关配置，并提供Configuration相应的setXXX, addXXX等注册方法类。<br/>
								<font color="gray">中心类：</font>org.commontemplate.standard.ConfigurationSettings<br/>
								<font color="gray">依赖于：</font>core包, config包<br/>
								<b>(6) tools包 (简化使用工具包)</b> <font color="green">(Client Facade)</font><br/>
								<font color="gray">位置：</font>org.commontemplate.tools及其子包<br/>
								<font color="gray">职责定义：</font>一些常用的客户端工具以及与其它框架集成相关类，这些已不属于CommonTemplate引擎的范围内，只是为了方便用户使用而提供。<br/>
								<font color="gray">中心类：</font>org.commontemplate.standard.PropertiesConfigurationLoader<br/>
								<font color="gray">依赖于：</font>core包, engine包, config包, standard包<br/>
								<b>总体包结构图如下：</b> <font color="green">(注：省略了继承包相同于被继承包的依赖关系)</font><br/>
								<img src="../images/uml/package.gif"/><br/>
								<b>不稳定度与抽象度的计算与关系：</b><br/>
								<b>(1) I = Ce / (Ca + Ce)</b><br/>
								I: Instability (不稳定度)<br/>
								Ca: Afferent Coupling (传入依赖，也就是被其它包依赖的个数)<br/>
								Ce: Efferent Coupling (输出依赖，也就是依赖其它包的个数)<br/>
								<b>(2) A = Na / Nc</b><br/>
								A: Abstractness (抽象度)<br/>
								Na: Number of abstract classes (抽象类的个数)<br/>
								Nc: Number of classes (类的个数，包括抽象类)<br/>
								<b>(3) D = abs(1 - I - A) * sin(45)</b><br/>
								D: Distance (偏差)<br/>
								I: Instability (不稳定度)<br/>
								A: Abstractness (抽象度)<br/>
								<img src="../images/frame/iad.gif" /><br/>
								设计最理想状态是D为0，也就是I-A交点分布在绿线上<br/>
								比较合理的设计度量为：D &lt; 0.2<br/>
								<b>CommonTemplate所有包(包括子包)的I-A交点分布如下：</b> <font color="green">(注：D &lt; 0.2 的为绿色，否则为黑色)</font><br/>
								<img src="../images/frame/metrics.gif" /><br/>
								黑色(不合格)的是：<br/>
								util包 (这个包必然如此，因为作为工具包，它被依赖过多，但抽象度却不够)<br/>
								standard包的一些子包 (待调整)<br/>
								<br/>
								<b>二. 类结构设计说明</b><br/>
								<b>类设计原则：</b><br/>
								开闭原则(OCP)：对扩展开放，对修改关闭。<br/>
								里氏代换原则(LSP)：子类应该在任何情况都能替换父类（包括逻辑上的）。<br/>
								单一职责原则(SRP)：每个类只封装一个变化因子。<br/>
								接口隔离原则(ISP)：依赖抽象（接口），隐藏具体（实现）。<br/>
								依赖倒置原则(IoC/DI)：不主动依赖实现类或工厂，只声明依赖接口，由框架容器等外部服务注入依赖实例以及管理生命周期。<br/>
								合成/聚合复用原则(CARP)：使用合成/聚合，而不是继承关系达到复用目的。<br/>
								迪米特法则(LoD)：尽可能少的与其它类关联，只与触手可及的类打交道，尽量保持单向单一依赖。<br/>
								<b>1. CommonTemplate核心包类结构：</b><br/>
								核心包采用接口驱动设计，全部为接口或抽象类，只考虑需求与关系，忽略实现。<br/>
								并按领域驱动设计将其分为三个域：<br/>
								<b>(1) Template域</b> (实体域)<br/>
								<font color="gray">范围：</font>包括Resource, Template, Element, Directive, Expression, Operator等。<br/>
								<font color="gray">职责定义：</font>用于表示一个具体的模板，此域是整个引擎的中心，所有功能围绕其展开。<br/>
								<font color="gray">线程安全性：</font>Template域的领域模型被设计成线程安全的(不变类)，保证在多线程中单实例重用。<br/>
								<b>(2) Context域</b> (会话域)<br/>
								<font color="gray">范围：</font>包括Context, GlobalContext, LocalContext等。<br/>
								<font color="gray">职责定义：</font>Context域负责状态及外部资源的管理。<br/>
								<font color="gray">线程安全性：</font>Context域是非线程安全的，应该为每次执行创建新的实例(即在线程栈内使用)。<br/>
								<b>(3) Factory域</b> (服务域)<br/>
								<font color="gray">范围：</font>包括Factory, ContextFactory, TemplateFactory等。<br/>
								<font color="gray">职责定义：</font>Factory域负责管理Template域和Context域的生命周期。<br/>
								<font color="gray">线程安全性：</font>Factory域是线程安全的(内部同步)，可单实例重用。<br/>
								<b>核心包设计图如下：</b> <font color="green">(注：省略了部分Factory域对Template域，Template域对Context域的依赖关系)</font><br/>
								<img src="../images/uml/core.gif" /><br/>
								<b>UML四色原型：</b>[Coad95-97]<br/>
								红色：moment-interval (瞬时状态，会话等)<br/>
								黄色：role (主动域，角色，操作者，服务等)<br/>
								绿色：party, place or thing (被动域，成分，事物，实体，值对象等)<br/>
								蓝色：catalog-entry-like description (分类或入口标识)<br/>
								<b>2. CommonTemplate引擎包类结构：</b><br/>
								引擎包使用配置包SPI实现核心包API。<br/>
								引擎包隐藏所有实现细节，只暴露Engine, TemplateEngine, ExpressionEngine，便于后期优化。<br/>
								引擎按重用粒度分级控制：<br/>
								(1) 通常都直接用Engine，它提供所有功能，包括解析器和Context资源管理等。<br/>
								(2) 如果使用第三方实现的Context(通常用在与其它模板工具适配时)，则可以只用TemplateEngine，提供模板解析功能。<br/>
								(3) 如果只用表达式功能(通常是作为动态表达式求值工具时)，则可以只用ExpressionEnine，提供表达式解析功能。<br/>
								<b>引擎包设计图如下：</b> <font color="green">(注：同时列出了engine与core及config的关系)</font><br/>
								<img src="../images/uml/engine.gif" border="0"/><br/>
								<b>3. CommonTemplate配置包类结构：</b><br/>
								配置包用于向引擎供给数据，按引擎包的格局分三个层次：<br/>
								(1) 表达式解析配置<br/>
								(2) 模板解析配置<br/>
								(3) 资源管理配置<br/>
								<b>配置包设计图如下：</b><br/>
								<img src="../images/uml/config.gif"/><br/>
!$
	<!--$end-->
<!--$end-->