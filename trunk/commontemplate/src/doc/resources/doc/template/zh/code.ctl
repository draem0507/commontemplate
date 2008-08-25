<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
								<b>1. 功能说明</b><br/>
								代码生器用于设计领域实体属性及关系，并通过<a href="template.html">CTL模板</a>生成结构性代码。<br/>
								通过配置在代码生器的工具栏上添加模板方案按钮(点击该按钮将调用相应方案的模板进行代码生成)。<br/>
								内置提供生成Struts/WebWork/Struts2 + Spring2 + JPA/Hibernate3/Ibatis2等常规方案的代码模板。<br/>
								支持独立exe运行，Eclipse插件，NetBeans插件，三种方式使用。<br/>
								<br/>
								<b>2. 安装使用</b> <font color="green">(需JRE1.6以上版本支持)</font><br/>
								(1)下载CodeGeneratorSetup.exe <a href="downloads.html">下载...</a><br/>
								(2)双击CodeGeneratorSetup.exe，按向导完成安装，安装后，将在桌面添加快捷方式<br/>
								(3)双击桌面快捷方式启动代码生成器<br/>
								<br/>
								<b>3. 模板方案配置</b><br/>
$code{"xml"}<case name="方案名称" desc="方案描述" icon="显示在工具栏上的图标">
	<template name="模板名称" encoding="模板编码"  type="模板类型，支持commontemplate/velocity/freemarker，可扩展，默认为commontemplate" run="执行类型：project或者entity，分别表示每项目执行模板和每实体执行模板" targetName="目标文件名称，可使用CTL模板语言，如：\${entity.name}Dao.java" targetEncoding="目标文件编码"/>
</case>
$end
								<br/>
								<b>3. 界面截图</b><br/>
								(1) 主界面：(包括菜单，工具栏，模型树，关系视图，缩略视图，属性面板)<br/>
								<img src="../images/code/code_main.gif" border="0" /><br/>
								<br/>
								(2) 数据库设置：<br/>
								<img src="../images/code/code_database.gif" border="0" /><br/>
								<br/>
								(3) 视图菜单：(可建立多个视图，分别显示相关联的实体，以防止实体太多而变成蜘蛛网)<br/>
								<img src="../images/code/code_view.gif" border="0" /><br/>
								<br/>
								(4) 实体树上下文菜单：(用于对Project,Entity,Field进行操作)<br/>
								<img src="../images/code/code_menu.gif" border="0" /><br/>
								<br/>
	<!--$end-->
<!--$end-->