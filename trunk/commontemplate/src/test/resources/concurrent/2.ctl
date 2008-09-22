$for{i : (1..3)}
	$if{! for.first},$end
	${i}
$end
<br/>
$for{user : users}
	$if{! for.first},$end
	${user.id}.${user.name}
$end