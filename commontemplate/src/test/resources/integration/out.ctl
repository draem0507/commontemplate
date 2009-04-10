$var{v:[['a1','a2'],['b1','b2'],['c1','c2']]}
${v}
$for{x : v}
	$for{y : x}
		${y}
	$end
$end