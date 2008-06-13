<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
								<b>1. 安装使用</b><br/>
								(1)下载CommonTemplateViewerSetup.exe <a href="download.html">下载...</a><br/>
								...<br/>
								<b>2. 数据查找</b><br/>
								...<br/>
								<b>3. 数据格式</b><br/>
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
								<b>(2)JSON数据格式:</b><br/>
								{mail: {from:"xxx@xxx.com", to:"yyy@yyy.com"}, users:[{id:1,name:"james"},{id:2,name:"kent"}]}<br/>
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