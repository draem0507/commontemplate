${users[0].creationDate}
${users[0].creationDate # "dd/MM/yyyy"}
${"2007-08-09".toDate#"dd/MM/yyyy"}
----
${"2007-08-09 10:11:12".toDateTime - 2.second}
${"2007-08-09 10:11:12".toDateTime - 2.minute}
${"2007-08-09 10:11:12".toDateTime - 2.hour}
${"2007-08-09".toDate - 2.day}
${"2007-08-09".toDate - 2.month}
${"2007-08-09".toDate - 2.week}
${"2007-08-09".toDate - 2.year}