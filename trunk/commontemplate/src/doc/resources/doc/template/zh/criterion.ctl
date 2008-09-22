<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
								<b>1. 编码规范</b><br/>
								(1) 遵守JAVA所有命名约定<br/>
								(2) 采用Eclipse默认的缩进风格<br/>
								(3) 数据抽象耦合度 (DAC) &lt;=7<br/>
								(4) 类扇出(CFO) &lt;=20<br/>
								(5) 参数个数 &lt;=5<br/>
								(6) 代码行数 &lt;=150<br/>
								(7) 圈复杂度(CC) &lt;=10<br/>
								(8) 可能的执行路径(NPath) &lt;=200<br/>
								待补充...<br/>
								<br/>
								<b>2. 设计规范</b><br/>
								(1) 遵守最基本的包设计原则和类设计原则。<a href="architecture.html">架构设计...</a><br/>
								(2) 尽可能使具体类不继承具体类，被继承的类永远是抽象的。<br/>
								(3) 区分命令与查询函数，也就是有副作用(修改状态)的函数均不返回值(返回类型为void)，有返回值的函数均不修改状态。<br/>
								(4) 公开的函数均明确例出先验条件与后验条件。<br/>
								(5) 所有类都应声明是否线程安全，并且尽可能保证服务域的类线程安全。<br/>
								(6) 有状态切换或值改变时均发布事件，重要的处理过程均留出截面，注册拦截器，便于AOP处理。<br/>
								(7) 配置项尽可能依赖实例，而不是类元，即：不控制SPI类实例的创建方式和生命周期。<br/>
								(8) 服务域尽可能用接口提供API，明确接口功能范围，隐藏内部细节。<br/>
								(9) 实体域尽可能用类或抽象类，便于兼容性扩展。<br/>
								(10) SPI策略接口尽量功能单一，便于策略组合。<br/>
								待补充...<br/>
								<br/>
								<b>3. 类设计自省</b><br/>
								(1) 是否与类名的语义相符? (重构类名或实现)<br/>
								(2) 是否有可变因子? (封装稳定部分, 拆分可变部分为另一接口)<br/>
								(3) 是否可置换实现中的某一部分? (留出模板方法, 可由子类覆写)<br/>
								(4) 是否有多种不同实现策略? (留出SPI接口, 可扩展策略)<br/>
								(5) 是否会被功能性改动? (类名和实现不一致或职责太多,类应该被整体置换,而不是修改)<br/>
								(6) 是否因为代码复用继承了某个类? (改为组合类方式, 或工具类)<br/>
								(7) 是否职责单一? (组合式拆分为多个类或接口)<br/>
								(8) 是否线程安全? (将状态改为接口参数, 或同步)<br/>
								(9) 是否需要序列化? (实现序列化接口)<br/>
								(10) 是否考虑向前兼容性? (将接口改为抽象类)<br/>
								(11) 是否依赖于具体? (所依赖的具体类可能有改动, 防止连锁改动, 并考虑兼容问题)<br/>
								(12) 是否控制了其它类的生命周期? (改为setXXX()注入实例, 由IoC容器注入)<br/>
								(13) 是否暴露了调用者不关心的接口函数? (明确调用者，私有化不应暴露的函数)<br/>
								待补充...<br/>
								<br/>
								<b>4. 单元测试原则</b><br/>
								(1) 自动性：不需要人工参与结果的检验，通常使用断言机制自动验证，不要向控制台输出人为观测的信息<br/>
								(2) 重复性：测试用例可重复运行，如果有状态修改，应进行回滚<br/>
								(3) 无序性：测试用例可不分先后的运行，不依赖于其它测试用例<br/>
								(4) 隔离性：不会有任何其它类的错误引起该用例连锁失败，通过Mock,Stub等方式进行隔离<br/>
								(5) 轻便性：测试用例应尽可能快的运行完成，不要进行大数据量的测试，那是性能测试的职责<br/>
								(6) 单一性：每个测试用例尽可能只对单一类单一方法进行测试<br/>
								(7) 完备性：测试条件应尽可能多的包含边界值，以及异常情况的测试<br/>
								(8) 非偶然性：测试条件应防止偶然性的发生，采用测试驱动开发(TDD)的思维模式，假定被测函数都是基于KISS(Keep it Simple, Stupid)法则实现的，需进行多重多区间测试，如：测试函数：sum(1, 2)，断言结果为：3，但也有可能sum()函数内直接为：return 3; (因为直接返回3是通过该测试的最简单实现(KISS法则))<br/>
								待补充...<br/>
								<br/>
								<b>5. 重构原则</b><br/>
								(1) 不同时戴“两顶帽子”，重构功能的时候不重构设计结构，重构设计结构的时候不重构功能<br/>
								(2) 全面的TDD伴随进行，所有改动都应有单元测试跟进<br/>
								(3) 小步前进，每次只改一小部分功能，便进行测试<br/>
								(4) 代码即文档，函数名，类名，参数名看起来像文档<br/>
								(5) 行为与状态统一，防止贫血类<br/>
								待补充...<br/>
								<br/>
								<font color="green">(注: 部分说明只适用本项目)</font>
								<br/>
	<!--$end-->
<!--$end-->