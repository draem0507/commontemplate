非标准起始文字
some text
<!-- 注释块 -->
<html>
	<input type="text" ct:if="yy">
	<something>
	asdfasdf;<"&<,>&lt;&gt;
	<!-- 注释块 -->
    <body>
        <table ct:if="users != null && users.size> 0" ct:for="xx" border="1">
            <tr ct:for="user : users">
                <td><span ct:if="for.index > 0" ct:out="for.index + 1">1</span></td>
                <td><span ct:out="user.name">james</span></td>
                <td><span ct:out="user.coins">2.00</span></td>
            </tr>
        </table>
    </body>
</html>
<tag ct:if="xx">
	<tag ct:if="yy"/>
</tag>
some text
非标准结束
