${allow}
${true ? "a" : "b"}
${allow ? "a" : "b"}
${allow ? a : "b"}
${! allow ? "a" : b}
${true ? users[0].name : users[1].name}
${false ? users[0].name : users[1].name}
${allow ? users[0].name : users[1].name}
${allow ? users[0].name + "1" : users[1].name + "2"}
${allow ? users[0].name + "1" : users[1].name + "2", "a", "b"}
${allow&& true ? users[0].name : users[1].name}
${allow ||true ? users[0].name : users[1].name}
$set{a : "xx"}
$set{b : "yy"}
$set{c : true}
${c ? a : b}
${! c ? a : b}
${null ? null : null}
${null ? "a" : "b"}
${null ? null : "b"}