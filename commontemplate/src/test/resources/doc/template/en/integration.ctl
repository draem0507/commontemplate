<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>1. Tools</b> <a href="dependency.html">Dependency&gt;&gt;</a><br/>
								<b>(1) Servlet</b><br/>
								web.xml:<br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>
<br/>
<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.commontemplate.tools.web.EncodingFilter<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>encoding<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>UTF-8<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/init-param&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.action<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
<br/>
<font color="#3f7f5f">&lt;servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>commontemplate<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-class&gt;</font>org.commontemplate.tools.web.servlet.TemplateServlet<font color="#3f7f5f">&lt;/servlet-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;load-on-startup&gt;</font>1<font color="#3f7f5f">&lt;/load-on-startup&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet&gt;</font><br/>
<font color="#3f7f5f">&lt;servlet-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>commontemplate<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.ctl<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet-mapping&gt;</font><br/>
								<br/>
								<b>(2) Struts</b><br/>
								...<br/>
								<br/>
								<b>CommonTemplate in MVC: </b><br/>
								<img src="../images/frame/mvc.gif" alt="CommonTemplate In MVC" /><br/>
								<br/>
								<b>(3) WebWork</b><br/>
								web.xml:<br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>
<br/>
<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.commontemplate.tools.web.EncodingFilter<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>encoding<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>UTF-8<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/init-param&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.action<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
<br/>
<font color="#3f7f5f">&lt;servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>webwork<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-class&gt;</font>com.opensymphony.webwork.dispatcher.ServletDispatcher<font color="#3f7f5f">&lt;/servlet-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;load-on-startup&gt;</font>1<font color="#3f7f5f">&lt;/load-on-startup&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet&gt;</font><br/>
<font color="#3f7f5f">&lt;servlet-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>webwork<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.action<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet-mapping&gt;</font><br/>
								<br/>
								xwork.xml:<br/>
<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>?&gt;</font><br/>
<font color="#3f7f5f">&lt;!DOCTYPE&nbsp;xwork&nbsp;<font color="#666666">PUBLIC</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"-//OpenSymphony&nbsp;Group//XWork&nbsp;1.1.1//EN"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"http://www.opensymphony.com/xwork/xwork-1.1.1.dtd"</font>&gt;</font><br/>
<font color="#3f7f5f">&lt;xwork&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;include&nbsp;<font color="#7f0055">file</font><font color="#000000">=</font><font color="#2a00ff">"webwork-default.xml"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;package&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"test"</font>&nbsp;<font color="#7f0055">extends</font><font color="#000000">=</font><font color="#2a00ff">"webwork-default"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result-types&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result-type&nbsp;<font color="#7f0055">default</font><font color="#000000">=</font><font color="#2a00ff">"true"</font>&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"org.commontemplate.tools.web.webwork.TemplateResult"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/result-types&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;action&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"index"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"com.xxx.IndexAction"</font>&nbsp;<font color="#7f0055">method</font><font color="#000000">=</font><font color="#2a00ff">"index"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"success"</font>&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate"</font>&gt;</font>/index.ctl<font color="#3f7f5f">&lt;/result&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/action&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/package&gt;</font><br/>
<font color="#3f7f5f">&lt;/xwork&gt;</font><br/>
								<br/>
								<b>(4) Struts2</b><br/>
								web.xml:<br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>
<br/>
<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.commontemplate.tools.web.EncodingFilter<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>encoding<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>UTF-8<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/init-param&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.action<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
<br/>
<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>struts2<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.apache.struts2.dispatcher.FilterDispatcher<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>struts2<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>/*<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
								<br/>
								struts.xml:<br/>
<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>&nbsp;?&gt;</font>&nbsp;<br/>
<font color="#3f7f5f">&lt;!DOCTYPE&nbsp;struts&nbsp;<font color="#666666">PUBLIC</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"-//Apache&nbsp;Software&nbsp;Foundation//DTD&nbsp;Struts&nbsp;Configuration&nbsp;2.0//EN"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"http://struts.apache.org/dtds/struts-2.0.dtd"</font>&gt;</font><br/>
<font color="#3f7f5f">&lt;struts&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;include&nbsp;<font color="#7f0055">file</font><font color="#000000">=</font><font color="#2a00ff">"struts-default.xml"</font>&nbsp;/&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;package&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"test"</font>&nbsp;<font color="#7f0055">extends</font><font color="#000000">=</font><font color="#2a00ff">"struts-default"</font>&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result-types&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result-type&nbsp;<font color="#7f0055">default</font><font color="#000000">=</font><font color="#2a00ff">"true"</font>&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"org.commontemplate.tools.web.struts2.TemplateResult"</font>&nbsp;/&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/result-types&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;action&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"index"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"com.xxx.IndexAction"</font>&nbsp;<font color="#7f0055">method</font><font color="#000000">=</font><font color="#2a00ff">"index"</font>&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"success"</font>&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate"</font>&gt;</font>/index.ctl<font color="#3f7f5f">&lt;/result&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/action&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/package&gt;</font>&nbsp;<br/>
<font color="#3f7f5f">&lt;/struts&gt;</font>&nbsp;<br/>
								<br/>
								<b>(5) Spring/SpringMVC</b><br/>
								...<br/>
								<br/>
								<b>(6) JSP</b><br/>
								commontemplate.tld:<br/>
<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>&nbsp;?&gt;</font><br/>
<font color="#3f7f5f">&lt;!DOCTYPE&nbsp;taglib&nbsp;<font color="#666666">PUBLIC</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"-//Sun&nbsp;Microsystems,&nbsp;Inc.//DTD&nbsp;JSP&nbsp;Tag&nbsp;Library&nbsp;1.2//EN"</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd"</font>&gt;</font><br/>
<font color="#3f7f5f">&lt;taglib&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tlib-version&gt;</font>1.0<font color="#3f7f5f">&lt;/tlib-version&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;jsp-version&gt;</font>1.2<font color="#3f7f5f">&lt;/jsp-version&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;short-name&gt;</font>ct<font color="#3f7f5f">&lt;/short-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;uri&gt;</font>http://www.commontemplate.org/taglib/template<font color="#3f7f5f">&lt;/uri&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;display-name&gt;</font>Common&nbsp;Template<font color="#3f7f5f">&lt;/display-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;description&gt;</font>Common&nbsp;Template&nbsp;Library<font color="#3f7f5f">&lt;/description&gt;</font><br/>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tag&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;name&gt;</font>template<font color="#3f7f5f">&lt;/name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;tag-class&gt;</font>org.commontemplate.tools.web.jsp.TemplateTag<font color="#3f7f5f">&lt;/tag-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;body-content&gt;</font>JSP<font color="#3f7f5f">&lt;/body-content&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;description&gt;</font>Common&nbsp;Template&nbsp;In&nbsp;JSP<font color="#3f7f5f">&lt;/description&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/tag&gt;</font><br/>
<font color="#3f7f5f">&lt;/taglib&gt;</font><br/>
								<br/>
								test.jsp:<br/>
<font color="#3f7f5f">&lt;%@taglib&nbsp;<font color="#7f0055">uri</font><font color="#000000">=</font><font color="#2a00ff">"http://www.commontemplate.org/taglib/template"</font>&nbsp;<font color="#7f0055">prefix</font><font color="#000000">=</font><font color="#2a00ff">"ct"</font>&nbsp;%&gt;</font><br/>
<font color="#3f7f5f">&lt;ct:template&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;JSP&nbsp;or/and&nbsp;CTL...<br/>
<font color="#3f7f5f">&lt;/ct:template&gt;</font><br/>
								<br/>
								<br/>
								<b>2. Config</b> <a href="dependency .html">��5��&gt;&gt;</a><br/>
								<b>(1) CommonsLogging/Log4J</b><br/>
								commontemplate-my.properties:<br/>
								@extends=<font color="#2a00ff">org/commontemplate/tools/web/commontemplate.properties</font><br/>
								logger=<font color="#2a00ff">org.commontemplate.standard.log.CommonsLogging()</font><br/>
								<br/>
								commons-logging.properties:<br/>
								logger=<font color="#2a00ff">org.apache.commons.logging.impl.Log4JLogger</font><br/>
								<br/>
								log4j.properties:<br/>
								log4j.rootLogger=<font color="#2a00ff">DEBUG,stdout</font><br/>
								log4j.appender.stdout=<font color="#2a00ff">org.apache.log4j.ConsoleAppender</font><br/>
								log4j.appender.stdout.layout=<font color="#2a00ff">org.apache.log4j.PatternLayout</font><br/>
								log4j.appender.stdout.layout.ConversionPattern=<font color="#2a00ff">%-5p [%d] %C - %m\n</font><br/>
								log4j.logger.CommonTemplate=<font color="#2a00ff">DEBUG</font><br/>
								<br/>
								<b>(2) OSCache</b><br/>
								commontemplate-my.properties:<br/>
								@extends=<font color="#2a00ff">org/commontemplate/tools/web/commontemplate.properties</font><br/>
								templateCache=<font color="#2a00ff">org.commontemplate.ext.cache.OSCache()</font><br/>
								<br/>
								oscache.properties:<br/>
								cache.algorithm=<font color="#2a00ff">com.opensymphony.oscache.base.algorithm.LRUCache</font><br/>
								cache.capacity=<font color="#2a00ff">1000</font><br/>
								<br/>
								<b>(3) EHCache</b><br/>
								commontemplate-my.properties:<br/>
								@extends=<font color="#2a00ff">org/commontemplate/tools/web/commontemplate.properties</font><br/>
								templateCache=<font color="#2a00ff">org.commontemplate.ext.cache.EHCache()</font><br/>
								<br/>
								ehcache.xml:<br/>
								<font color="#3f7f5f">&lt;ehcache&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;diskStore&nbsp;<font color="#7f0055">path</font><font color="#000000">=</font><font color="#2a00ff">"java.io.tmpdir"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;defaultCache&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">maxElementsInMemory</font><font color="#000000">=</font><font color="#2a00ff">"10000"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">eternal</font><font color="#000000">=</font><font color="#2a00ff">"false"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">timeToIdleSeconds</font><font color="#000000">=</font><font color="#2a00ff">"120"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">timeToLiveSeconds</font><font color="#000000">=</font><font color="#2a00ff">"120"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">overflowToDisk</font><font color="#000000">=</font><font color="#2a00ff">"true"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;/&gt;</font><br/>
<font color="#3f7f5f">&lt;/ehcache&gt;</font><br/>
								<br/>
								<b>(4) TagLib</b><br/>
								commontemplate-my.properties:<br/>
								directive{textarea}=<font color="#2a00ff">org.commontemplate.standard.web.taglib.JspTagDirectiveAdapter()</font><br/>
								directive{textarea}.tld=<font color="#2a00ff">textarea.tld</font><br/>
!$
	<!--$end-->
<!--$end-->