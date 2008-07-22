<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一、tools包依赖第三方包情况：</b><br/>
								(1). org.commontemplate.tools.web.servlet.TemplateServlet依赖于servlet-api.jar包<br/>
								(2). org.commontemplate.tools.web.jsp.TemplateTag依赖于jsp-api.jar包<br/>
								(3). org.commontemplate.tools.web.webwork.TemplateResult依赖于webwork.jar和xwork.jar包<br/>
								(4). org.commontemplate.tools.web.struts2.TemplateResult依赖于struts2.jar和xwork2.jar包<br/>
								<br/>
								<b>二、standard包依赖第三方包情况：</b><br/>
								(1). org.commontemplate.standard.log.CommonsLogging依赖于commons-logging.jar包<br/>
								(3). org.commontemplate.standard.cache.EHCache依赖于ehcache.jar包<br/>
								(4). org.commontemplate.standard.cache.OSCache依赖于oscache.jar包<br/>
								(5). org.commontemplate.standard.coat.attribute.jericho.JerichoAttributeCoatFilter依赖于jericho.jar包<br/>
								(6). org.commontemplate.standard.directive.code.JavaCodeFilter依赖于java2html.jar包<br/>
								(7). org.commontemplate.standard.directive.taglib.TagLineDirectiveAdapter/TagBlockDirectiveAdapter依赖于servlet-api.jar包和jsp-api.jar包<br/>
								<br/>
								<font color="green">(注：这里列出的是build依赖，运行时依赖需看具体应用，所依赖的包可能会再依赖其它包，以上列出的包可以在项目的lib目录下找到)</font><br/>
!$
	<!--$end-->
<!--$end-->