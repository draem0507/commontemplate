$macro{a}
	this is a
	aaa: $inner
$end{macro}

$macro{b}
	this is b
	bbb: $inner
$end{macro}

$a.block
	$b
$end{a.block}