$macro{b}
$inner
$end{macro}
 
$macro{a}
$b.block
$inner
$end{b.block}
$end{macro}
 
$macro{c}
test   $inner
$end{macro}
 
$a.block
$c
$end{a.block}
 
$a.block
$c.block
$end{c.block}
$end{a.block}