<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
								<b>1. 功能说明</b><br/>
								功能：双击*.ctl文件，自动读取数据文件，解析模板，生成html，并用IE打开，如果出错，使用Swing界面弹出错误信息。<br/>
								目的：方便于页面开发人员独立于业务开发进行模板测试. <br/>
								<br/>
								<b>2. 安装使用</b><br/>
								(1)下载CommonTemplateSetup.exe <a href="downloads.html">下载...</a><br/>
								(2)双击CommonTemplateSetup.exe，按向导完成安装，安装后，将自动关联*.ctl文件<br/>
								(3)在*.ctl文件上双击打开，或右键菜单中选“CommonTemplate”打开<br/>
								<img src="../images/viewer/open_with.gif" alt="Open With CommonTemplate"/><br/>
								<br/>
								<b>3. 数据查找</b><br/>
								(1)内部数据供给块指令：\$data{"xml"}...\$end，指令内部放数据表示内容，内置支持xml,json,properties,ymal等数据格式。<a href="extension.html">扩展...</a><br/>
								(2)同名数据文件加载：如打开test.ctl，将搜索同目录的test.xml, test.json, test.properties, test.yaml等数据文件并加载<br/>
								<br/>
								<b>4. 数据格式</b><br/>
								<b>(1)XML数据格式:</b><br/>
								语法：&lt;object&gt;表示对象，&lt;array&gt;表示数组，name属性表示其名称。 注：根标签必需为&lt;object&gt;<br/>
								举例：<br/>
<!--$code{"xml"}--><object>
	<object name="mail">
		<object name="from">xxx@xxx.com</object>
		<object name="to">yyy@yyy.com</object>
	</object>
	<array name="users">
		<object>
			<object name="id">1</object>
			<object name="name">james</object>
		</object>
		<object>
			<object name="id">2</object>
			<object name="name">kent</object>
		</object>
	</array>
</object>
<!--$end-->
								<br/>
								<b>(2)JSON数据格式:</b><br/>
								语法：大括号表示对象，冒号表示属性，方括号表示数组，逗号表示项，引号表示字符串(单词引号可省)。<br/>
								举例：<br/>
								{mail: {from: "xxx@xxx.com", to: "yyy@yyy.com"}, users: [{id: 1, name: "james"},{id: 2, name: "kent"}]}<br/>
								<br/>
								<b>(3)Properties数据格式:</b><br/>
								语法：等号表示键值对，其中，键中的点号表示层级关系，数字表示数组索引。<br/>
								举例：<br/>
								mail.from=xxx@xxx.com<br/>
								mail.to=yyy@yyy.com<br/>
								users.0.id=1<br/>
								users.0.name=james<br/>
								users.1.id=2<br/>
								users.1.name=kent<br/>
								<br/>
								<b>(4)YAML数据格式:</b><br/>
								语法：冒号表示对象属性，横线表示数组项，引号表示字符串(单词引号可省)。<br/>
								举例：<br/>
								mail:<br/>
								&nbsp;&nbsp;from: "xxx@xxx.com"<br/>
								&nbsp;&nbsp;to: "yyy@yyy.com"<br/>
								users:<br/>
								&nbsp;&nbsp;- id: 1<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;name: james<br/>
								&nbsp;&nbsp;- id: 2<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;name: kent<br/>
								<br/>
	<!--$end-->
<!--$end-->