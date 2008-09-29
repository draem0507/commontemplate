$for{user : users}
	${user.name} ${user.balance}
$end
---
$for{user : users orderby "balance"}
	${user.name} ${user.balance}
$end
---
$for{user : users orderby "+balance"}
	${user.name} ${user.balance}
$end
---
$for{user : users orderby "-balance"}
	${user.name} ${user.balance}
$end
---
$for{user : users orderby "name"}
	${user.name} ${user.balance}
$end
---
$for{user : users orderby ("+balance", "name")}
	${user.name} ${user.balance}
$end