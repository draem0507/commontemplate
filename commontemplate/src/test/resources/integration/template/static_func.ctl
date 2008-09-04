${new integration.User}
${new integration.User()}
${new integration.User(name:"oooo")}
${new integration.User(name:"oooo",email:"oooo@oooo.com")}
${&integration.User.test}
${&integration.User.getTest()}
${&integration.User.getTest2("xxx")}
${&org.commontemplate.util.StringUtils.isEmpty("")}
${&org.commontemplate.util.StringUtils.isEmpty("aa")}