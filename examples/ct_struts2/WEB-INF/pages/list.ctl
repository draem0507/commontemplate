<!--$extends{"common.ctl"}-->
	<!--$overzone{"content"}-->
	<!--$message{"user.list"}-->User List<!--$end--><br/>
	<table border="1">
		<tr>
			<td><!--$message{"user.id.title"}-->User ID<!--$end--></td>
			<td><!--$message{"user.name.title"}-->User Name<!--$end--></td>
			<td><!--$message{"user.email.title"}-->E-Mail<!--$end--></td>
			<td><!--$message{"user.balance.title"}-->Balance<!--$end--></td>
			<td><!--$message{"user.creationDate.title"}-->Registration Time<!--$end--></td>
			<td><!--$message{"edit"}-->Edit<!--$end--></td>
		</tr>
		<!--$for{user : users}-->
		<tr>
			<td><!--$out{user.id}-->1<!--$end--></td>
			<td><a href="view.action?user.id=${user.id}"><!--$out{user.name}-->James<!--$end--></a></td>
			<td><!--$out{user.email}-->james@xxx.com<!--$end--></td>
			<td><!--$out{user.balance # "##0.00"}-->132.00<!--$end--><!--$message{"user.balance.unit"}-->Dollar<!--$end--></td>
			<td><!--$out{user.creationDate}-->2007-12-18 10:31:24<!--$end--></td>
			<td><a href="edit.action?user.id=${user.id}"><!--$message{"edit"}-->Edit<!--$end--></a></td>
		</tr>
		<!--$end-->
	</table>
	<!--$end-->
<!--$end-->