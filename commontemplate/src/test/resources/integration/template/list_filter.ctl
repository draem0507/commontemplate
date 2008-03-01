${users[=> item.name == "liangfei" && index >= 0][0].email}
${users[x=> x.name == "liangfei" && index >= 0][0].email}
$set{tmap = (aa : "bb", cc : "dd", ee : "dd")}
${tmap[=> entry.value == "dd"].size}
${tmap[x=> x.value == "dd"].size}