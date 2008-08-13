$macro{mymacro}
    define...
    param1 value: ${param1}
    okok
    param2 value: ${param2}
$end{macro}
$mymacro{param1: "value1", param2: "value2"}
-----------
$macro{"mymacro2"}
    define...
    param1 value: ${param1}
    $inner{back1: "myback1"}
    okok
    param2 value: ${param2}
$end
$mymacro2.block{param1: "value1", param2: "value2"}
	run...
    back1 value: ${back1}
    param1 value: ${param1}
$end