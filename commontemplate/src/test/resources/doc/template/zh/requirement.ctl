<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<table border="1" align="center" style="border-style: solid; border-width: 1px; border-color: #gray;">
								<tr><td style="background-color: #DBEEF3;">使用和扩展场景</td><td>分类</td><td>可能性</td><td>支持度</td><td>支持方式</td><td>待改进</td></tr>
								<tr><td>与Web框架集成</td><td>使用</td><td>高</td><td>良好</td><td>EngineHolder统一接口集成,已内置支持与Struts, Struts2, WebWork, SpringMVC的集成</td><td>简化配置</td></tr>
								<tr><td>独立使用CTL快速构建小型应用, 不与MVC集成和调用JavaBean.	</td><td>使用</td><td>中</td><td>较差</td><td></td><td>"提供Servlet直接映射ctl提供模板脚本支持"</td></tr>
								<tr><td>在代码生成器中使用</td><td>使用</td><td>高</td><td>较差</td><td></td><td>"提供指令空白行自动去除支持与Ant集成, 实现相关Task类, 便于通过build生成代码."</td></tr>
								<tr><td>只使用EL表达式	使用</td><td>高</td><td>一般</td><td>调用ExpressionEngine</td><td>EL表达式未独立成jar包</td></tr>
								<tr><td>增加一个操作符</td><td>扩展</td><td>中</td><td>良好</td><td>在配置中注册OperatorHandler</td><td></td></tr>
								<tr><td>增加一个指令</td><td>扩展</td><td>高</td><td>良好</td><td>在配置中注册DirectiveHandler</td><td></td></tr>
								<tr><td>使用其它方式存储及还原模板树结构</td><td>扩展</td><td>高</td><td>较差</td><td>通过Visitor遍历整个树结构进行导出</td><td>engine包封死了模板元素的实现类, 用户无法从其它方式还原模块</td></tr>
								<tr><td>使用第三方缓存</td><td>扩展</td><td>中</td><td>良好</td><td>适配Cache接口, 已内置提供OSCache和EHCache的适配器</td><td></td></tr>
								<tr><td>使用页面缓存</td><td>扩展</td><td>中</td><td>较差</td><td></td><td>提供内置的页面或页面块的缓存机制</td></tr>
								<tr><td>在与其它框架使用$符冲突时(如JS中常用$作为取页面元素符), 改变指令引导符.</td><td>使用</td><td>中</td><td>良好</td><td>语法中特殊符号均可配置,包括:'$','{','}','#','*','!'</td><td></td></tr>
								</table>
								<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->