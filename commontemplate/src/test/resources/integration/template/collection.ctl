${users.size}
${users[0].name}
${users[0]["name"]}
${"ok" + users[0]["name"]}
${users[0]["name"] + "," + users[1]["name"]}
${users[0].creationDate.year}
${users.toArray.size}
${users.toArray.length}
${users.toArray[0].name}
${users.toArray[0]["name"]}
${users.toArray[0].creationDate.year}

${users[0,2][1].name}
${users[0..2][1].name}
${users[1..2][0].name}