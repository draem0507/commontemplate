${new integration.User}
${new integration.User()}
${new integration.User(name:"oooo")}
${new integration.User(name:"oooo",email:"oooo@oooo.com")}
${&integration.User.test}
${&integration.User.test.toUpperCase}
${&integration.User.test.toUpperCase()}
${&integration.User.getTest()}
${&integration.User.getTest2("xxx")}
${&integration.User.rootUser.getEmail()}
${&org.commontemplate.util.StringUtils.isEmpty("")}
${&org.commontemplate.util.StringUtils.isEmpty("aa")}