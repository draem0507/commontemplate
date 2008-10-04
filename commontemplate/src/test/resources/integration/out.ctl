<html>
    <body>
        <table ct:if="users != null && users.size &gt; 0" border="1">
            <tr ct:for="user : users">
                <td><span ct:output="for.index + 1">1</span></td>
                <td><span ct:output="user.name">james</span></td>
                <td><span ct:output="user.balance">2.00</span></td>
            </tr>
        </table>
    </body>
</html>