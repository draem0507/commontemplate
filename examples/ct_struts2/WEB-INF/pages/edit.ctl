<!--$extends{"common.ctl"}-->
	<!--$overzone{"content"}-->
	<!--$message{"user.view"}-->User View<!--$end--><br/>
	<table border="1">
		<tr>
			<td><!--$message{"user.id.title"}-->User ID<!--$end--></td>
			<td><!--$out{user.id}-->1<!--$end--></td>
		</tr>
		$textfield{label: "Name", name: "name", value: user.name}
		$textfield{label: "Email", name: "email", value: user.email}
		$textfield{label: "Balance", name: "balance", value: user.balance}
		$textfield{label: "CreationDate", name: "creationDate", value: user.creationDate # "yyyy-MM-dd"}
	</table>
	<a href="list.action"><!--$message{"back"}-->Back<!--$end--></a>
	<a href="view.action?user.id=${user.id}"><!--$message{"view"}-->View<!--$end--></a>
	<!--$end-->
<!--$end-->