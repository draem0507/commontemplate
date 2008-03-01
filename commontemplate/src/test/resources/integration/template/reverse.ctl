$for{user : users}
	${user.name}
$end

$for{user : -users}
	${user.name}
$end

$for{user : -users.toArray}
	${user.name}
$end