<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
								<b>1. 编码规范</b><br/>
								(1) 遵守JAVA所有命名约定<br/>
								(2) 采用Eclipse默认的缩进风格<br/>
								(3) 数据抽象耦合度 (DAC) &lt;=7<br/>
								(4) 类扇出(CFO) &lt;=20<br/>
								(5) 参数个数 &lt;=5<br/>
								(6) 代码行数 &lt;=150<br/>
								(7) 圈复杂度(CC) &lt;=10<br/>
								(8) 可能的执行路径(NPath) &lt;=200<br/>
								...<br/>
								<br/>
								<b>2. 设计规范</b><br/>
								(1) 遵守最基本的类设计原则：<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开闭原则(OCP)：对扩展开放，对修改关闭。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;里氏代换原则(LSP)：子类应该在任何情况都能替换父类（包括逻辑上的）。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单一职责原则(SRP)：每个类只封装一个变化因子。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接口隔离原则(ISP)：依赖抽象（接口），隐藏具体（实现）。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;依赖倒置原则(IoC/DI)：不主动依赖实现类或工厂，只声明依赖接口，由框架容器等外部服务注入依赖实例以及管理生命周期。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合成/聚合复用原则(CARP)：使用合成/聚合，而不是继承关系达到复用目的。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;迪米特法则(LoD)：尽可能少的与其它类关联，只与触手可及的类打交道，尽量保持单向单一依赖。<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等等.....<br/>
								(2) 有状态切换或值改变时均发布事件，重要的处理过程均注册拦截器。<br/>
								(3) 区分命令与查询函数，也就是，有副作用(修改状态)的函数均不返回值(返回类型为void)，有返回值的函数均不修改状态。<br/>
								(4) 公开的函数均明确例出先验条件与后验条件。<br/>
								(5) 所有类都应声明是否线程安全，并且属于服务域的类都应该线程安全。<br/>
								...<br/>
								<br/>
	<!--$end-->
<!--$end-->