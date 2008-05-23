$for{user: users[-10..10]}
${user.name}
$end
----
$for{user: users[-10..*]}
${user.name}
$end
----
$for{user: users[2..*]}
${user.name}
$end
----
$for{user: users[0..4]}
${user.name}
$end
----
$for{user: users[2..3]}
${user.name}
$end
========================
$for{user: users.toArray[-10..10]}
${user.name}
$end
----
$for{user: users.toArray[-10..*]}
${user.name}
$end
----
$for{user: users.toArray[2..*]}
${user.name}
$end
----
$for{user: users.toArray[0..4]}
${user.name}
$end
----
$for{user: users.toArray[2..3]}
${user.name}
$end
========================
${"abcdefg"[-10..10]}
----
${"abcdefg"[-10..*]}
----
${"abcdefg"[2..*]}
----
${"abcdefg"[0..6]}
----
${"abcdefg"[2..5]}
========================
${users[0].name[-10..10]}
----
${users[0].name[-10..*]}
----
${users[0].name[2..*]}
----
${users[0].name[0..6]}
----
${users[0].name[2..5]}
