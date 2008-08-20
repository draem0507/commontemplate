<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
								<b>1. XML:</b><br/>
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
								<b>2. JSON:</b><br/>
								{mail: {from: "xxx@xxx.com", to: "yyy@yyy.com"}, users: [{id: 1, name: "james"},{id: 2, name: "kent"}]}<br/>
								<br/>
								<b>3. Properties:</b><br/>
								mail.from=xxx@xxx.com<br/>
								mail.to=yyy@yyy.com<br/>
								users.0.id=1<br/>
								users.0.name=james<br/>
								users.1.id=2<br/>
								users.1.name=kent<br/>
								<br/>
								<b>4. YAML:</b><br/>
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