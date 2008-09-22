<!--中文-->
<html>
    <body>
        <table ct:if="users != null && users.size > 0" border="1">
            <tr ct:for="user : users">
                <td><span ct:out="for.index + 1">1</span></td>
                <td><span ct:out="user.name">james</span></td>
                <td><span ct:out="user.coins">2.00</span></td>
            </tr>
        </table>
    </body>
</html>