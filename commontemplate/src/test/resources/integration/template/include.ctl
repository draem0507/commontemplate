xxx
$set{name:"james"}
$include{"/integration/included/welcome.mm", name : name}

$include{"../included/welcome.mm", [name : name]}

$include{"../included/welcome.mm", name : "liangfei"}

$display{"../included/welcome.mm"}

$display{"/integration/included/welcome.mm", "UTF-8"}

yyy