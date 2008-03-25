$for{user : users}
	${for.index}.${user.name}
$end

$for{user : users}
	${for.first}/${for.last}
	${for.count}.${user.name}
	$for{user : users}
		${super.for.index}.${for.index}.${user.name}
	$end
$end

$set{user="ok"}
$for{user : users}
	${for.index}.${user.name}
	$for{user : users}
		${super.for.index}.${for.index}.${user.name}
		$for{user : users}
			${super.super.for.index}.${super.for.index}.${for.index}.${user.name} ${super.super.super.user}
		$end
	$end
$end

$cycle{color:("red", "green", "blue")}
$for{7}
	${for.index}.${color.next}
$end

$for{user : users}
	$break{for.index == 1}
	${for.index}.${user.name}
$end

$for{user : users}
	${for.index}.${user.name}
	$for{user : users}
		$break{for.index == 1}
		${super.for.index}.${for.index}.${user.name}
	$end
$end

$for{user : users}
	$continue{for.index == 1}
	${for.index}.${user.name}
$end

$for{user : users}
	${for.index}.${user.name}
	$for{user : users}
		$continue{for.index == 1}
		${super.for.index}.${for.index}.${user.name}
	$end
$end

$for{3}
	aaa
$end

$for{0}
	bbb
$end

$for{-3}
	ccc
$end