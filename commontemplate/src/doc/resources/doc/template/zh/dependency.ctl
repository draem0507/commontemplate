<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>一、tools包依赖第三方包情况：</b><br/>
								(1) org.commontemplate.tools.web.servlet.TemplateServlet依赖于servlet-api.jar包<br/>
								(2) org.commontemplate.tools.web.jsp.TemplateTag依赖于jsp-api.jar包<br/>
								(3) org.commontemplate.tools.web.webwork.TemplateResult依赖于webwork.jar和xwork.jar包<br/>
								(4) org.commontemplate.tools.web.struts2.TemplateResult依赖于struts2.jar和xwork2.jar包<br/>
								(5) org.commontemplate.tools.web.spring.CommonTemplateViewResolver/CommonTemplateView依赖于spring.jar包<br/>
								(6) org.commontemplate.tools.ant.TemplateTask依赖于ant.jar包<br/>
								(7) org.commontemplate.tools.javaxscript相关类依赖于script-api.jar包 (JDK1.6以下版本)<br/>
								<br/>
								<b>二、standard包依赖第三方包情况：</b><br/>
								(1) org.commontemplate.util.log.CommonsLogger和CommonsLoggerProvider依赖于commons-logging.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：配置项：logger=org.commontemplate.standard.log.CommonsLogging()<br/>
								(2) org.commontemplate.standard.cache.EHCache依赖于ehcache.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：配置项：memoryCache=org.commontemplate.standard.cache.EHCache()<br/>
								(3) org.commontemplate.standard.cache.OSCache依赖于oscache.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：配置项：memoryCache=org.commontemplate.standard.cache.OSCache()<br/>
								(4) org.commontemplate.standard.cache.JCache依赖于jcache-api.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：配置项：memoryCache=org.commontemplate.standard.cache.JCache()<br/>
								(5) org.commontemplate.standard.coat.attribute.jericho.JerichoAttributeCoatFilter依赖于jericho.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：属性语法外套<br/>
								(6) org.commontemplate.standard.directive.filter.code.JavaCodeFilter依赖于java2html.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：指令：$code{java}<br/>
								(7) org.commontemplate.standard.directive.taglib.TagLineDirectiveAdapter/TagBlockDirectiveAdapter依赖于servlet-api.jar包和jsp-api.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;org.commontemplate.standard.directive.taglib.PageContextImpl依赖于commons-el.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：JspTagLib指令适配<br/>
								(8) org.commontemplate.util.JSONUtils依赖于json.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：指令：$data{json} 扩展属性：${obj.toJson} ${"{name:\"kent\"}".fromJson}<br/>
								(9) org.commontemplate.standard.data.YamlDataProvider依赖于jyaml.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：指令：$data{yaml}<br/>
								(10) org.commontemplate.standard.property.object.ObjectToXstreamPropertyHandler和org.commontemplate.standard.property.string.StringFromXstreamPropertyHandler依赖于xstream.jar包<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;影响：扩展属性：${obj.toXstream} ${"&lt;xxx&gt;&lt;/xxx&gt;".fromXstream}<br/>
								<br/>
								<font color="green">(注：以上jar包只有用到相应功能时才需要加入，必然依赖的第三方包均已通过源码引入util包中)</font><br/>
								<font color="green">(注：这里列出的是build依赖，运行时依赖需看具体应用，所依赖的包可能会再依赖其它包，以上列出的包可以在项目的lib目录下找到)</font><br/>
								<a href="http://commontemplate.googlecode.com/svn/trunk/commontemplate/lib" target="_blank">依赖包下载...</a><br/>
!$
	<!--$end-->
<!--$end-->