${users[0].name}
${users[0].name[0]}
${users[0].name[0].class.name}
${users[0].name[0] == "l"}
${users[0].name[0] == "lf"}
${users[0].name[0] == "L"}
${users[0].name[0] ~= "L"}

${"l" == users[0].name[0]}
${"lf" == users[0].name[0]}
${"L" == users[0].name[0]}
${"L" ~= users[0].name[0]}