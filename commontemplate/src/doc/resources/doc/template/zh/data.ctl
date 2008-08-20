<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
								<b>1. XML数据格式:</b><br/>
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
								<b>2. JSON数据格式:</b><br/>
								语法：大括号表示对象，冒号表示属性，方括号表示数组，逗号表示项，引号表示字符串(单词引号可省)。<br/>
								举例：<br/>
								{mail: {from: "xxx@xxx.com", to: "yyy@yyy.com"}, users: [{id: 1, name: "james"},{id: 2, name: "kent"}]}<br/>
								<br/>
								<b>3. Properties数据格式:</b><br/>
								语法：等号表示键值对，其中，键中的点号表示层级关系，数字表示数组索引。<br/>
								举例：<br/>
								mail.from=xxx@xxx.com<br/>
								mail.to=yyy@yyy.com<br/>
								users.0.id=1<br/>
								users.0.name=james<br/>
								users.1.id=2<br/>
								users.1.name=kent<br/>
								<br/>
								<b>4. YAML数据格式:</b><br/>
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