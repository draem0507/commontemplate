<table ct:if="users != null && users.size > 0">
	<tr ct:for="user: users">
		<td><span ct:out="user.name"></span></td>
	</tr>
</table>