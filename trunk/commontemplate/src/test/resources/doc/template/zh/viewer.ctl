<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
								<b>1. 功能说明</b><br/>
								场景：双击*.ctl文件，自动读取数据文件，解析模板，生成html，并用IE打开，如果出错，使用Swing界面弹出错误信息。<br/>
								目的：方便于页面开发人员独立于业务开发进行模板测试. <br/>
								<br/>
								<b>2. 安装使用</b><br/>
								(1)下载CommonTemplateSetup.exe <a href="downloads.html">下载...</a><br/>
								(2)双击CommonTemplateSetup.exe，按向导完成安装，安装后，将自动关联*.ctl文件<br/>
								(3)在*.ctl文件上双击打开，或右键菜单中选“CommonTemplate”打开<br/>
								<br/>
								<b>3. 数据查找</b><br/>
								(1)内部数据供给块指令：\$data{"xml"}...\$end，指令内部放数据表示内容，支持xml,json,properties三种数据格式<br/>
								(2)同名数据文件加载：如打开test.ctl，将搜索同目录的test.xml, test.json, test.properties数据文件并加载<br/>
								<br/>
								<b>4. 数据格式</b><br/>
								<b>(1)XML数据格式:</b>
<!--$code{"xml"}-->
<object>
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
								{mail: {from:"xxx@xxx.com", to:"yyy@yyy.com"}, users:[{id:1,name:"james"},{id:2,name:"kent"}]}<br/>
								<br/>
								<b>(3)Properties数据格式:</b><br/>
								mail.from=xxx@xxx.com<br/>
								mail.to=yyy@yyy.com<br/>
								users.0.id=1<br/>
								users.0.name=james<br/>
								users.1.id=2<br/>
								users.1.name=kent<br/>
								<br/>
	<!--$end-->
<!--$end-->