<!--$extends{"common.ctl"}-->
	<!--$overzone{"content"}-->
		<!--$message{"user.view"}-->User View<!--$end--><br/>
		<table border="1">
			<tr>
				<td><!--$message{"user.id.title"}-->User ID<!--$end--></td>
				<td><!--$out{user.id}-->1<!--$end--></td>
			</tr>
			<tr>
				<td><!--$message{"user.name.title"}-->User Name<!--$end--></td>
				<td><!--$out{user.name}-->James<!--$end--></td>
			</tr>
			<tr>
				<td><!--$message{"user.email.title"}-->E-Mail<!--$end--></td>
				<td><!--$out{user.email}-->james@xxx.com<!--$end--></td>
			</tr>
			<tr>
				<td><!--$message{"user.balance.title"}-->Balance<!--$end--></td>
				<td><!--$out{user.balance # "##0.00"}-->132.00<!--$end--><!--$message{"user.balance.unit"}-->Dollar<!--$end--></td>
			</tr>
			<tr>
				<td><!--$message{"user.creationDate.title"}-->Registration Time<!--$end--></td>
				<td><!--$out{user.creationDate}-->2007-12-18 10:31:24<!--$end--></td>
			</tr>
		</table>
		<a href="list.action"><!--$message{"back"}-->Back<!--$end--></a>
		<a href="edit.action?user.id=${user.id}"><!--$message{"edit"}-->Edit<!--$end--></a>
	<!--$end-->
<!--$end-->