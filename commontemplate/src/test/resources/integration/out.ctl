${new integration.User}
${new integration.User()}
${new integration.User(name:"oooo")}
${new integration.User(name:"oooo",email:"oooo@oooo.com")}
${1 + new integration.User.id}
${1 + new integration.User().id}
${new integration.User(name:"oooo").name}
${&integration.User.test}
${&integration.User.getTest()}
${&integration.User.getTest2("xxx")}
${&org.commontemplate.util.StringUtils.isEmpty("")}
${&org.commontemplate.util.StringUtils.isEmpty("aa")