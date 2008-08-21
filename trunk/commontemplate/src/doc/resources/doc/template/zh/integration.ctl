<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>1. 与Servlet集成</b> <a href="config.html">配置&gt;&gt;</a><br/>
								配置web.xml:<br/>
<font color="#3f5fbf">&lt;!--&nbsp;模板引擎初始化配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>

<font color="#3f5fbf">&lt;!--&nbsp;字符编码过滤器(也可以用其它工具提供的过滤器)&nbsp;--&gt;</font><br/>
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
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.ctl<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>

<font color="#3f5fbf">&lt;!--&nbsp;模板Servlet配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>commontemplate<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-class&gt;</font>org.commontemplate.tools.web.servlet.TemplateServlet<font color="#3f7f5f">&lt;/servlet-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;load-on-startup&gt;</font>1<font color="#3f7f5f">&lt;/load-on-startup&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet&gt;</font><br/>
<font color="#3f7f5f">&lt;servlet-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>commontemplate<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.ctl<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet-mapping&gt;</font><br/>
								此Servlet只用于模板显现，使用时需要在其它Servlet中填充数据后forward到此Servlet。<br/>
								通常用于与没有提供页面handler的MVC框架集成，如：Struts1, SpringMVC等，<br/>
								在Web框架的Action/Controller中填充数据后forward到xxx.ctl。<br/>
								<b>Web配置查找顺序：</b><br/>
								1.首先查找web.xml中的context-param配置：commontemplate-config参数所指的路径。如：<br/>
<font color="#3f7f5f">&lt;context-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>commontemplate-config<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>/WEB-INF/commontemplate.properties<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
<font color="#3f7f5f">&lt;/context-param&gt;</font><br/>
								(注：如果配置路径以 / 开头则表示在web应用目录下，否则在ClassPath下查找)<br/>
								2.如果未配置，则查找默认WEB-INF路径：/WEB-INF/commontemplate.properties<br/>
								3.如果WEB-INF中没有，则查找ClassPath根目录：commontemplate.properties<br/>
								4.如果ClassPath根目录也没有，则使用标准配置：org/commontemplate/tools/web/commontemplate.properties<br/>
								(注：如果自定义配置文件中未配置@extends项，则缺省为：@extends=org/commontemplate/tools/web/commontemplate.properties)<br/>
								<br/>
								<b>2. 与Struts集成</b><br/>
								同上面配置好Servlet，在Struts中直接forward到相应.ctl页面<br/>
								(注：与Struts的集成方案请参见下载列表中：ct_struts.war) <a href="downloads.html">下载&gt;&gt;</a><br/>
								配置web.xml:<br/>
								<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>?&gt;</font><br/>
<font color="#3f7f5f">&lt;web-app&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"2.4"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">xmlns</font><font color="#000000">=</font><font color="#2a00ff">"http://java.sun.com/xml/ns/j2ee"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">xmlns:xsi</font><font color="#000000">=</font><font color="#2a00ff">"http://www.w3.org/2001/XMLSchema-instance"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">xsi:schemaLocation</font><font color="#000000">=</font><font color="#2a00ff">"http://java.sun.com/xml/ns/j2ee&nbsp;http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"</font>&gt;</font><br/>

&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--&nbsp;模板引擎初始化配置&nbsp;--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/listener&gt;</font><br/>

&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--&nbsp;字符编码过滤器(也可以用其它工具提供的过滤器)&nbsp;--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.commontemplate.tools.web.EncodingFilter<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>encoding<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>UTF-8<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.do<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.ctl<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>

&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--&nbsp;模板Servlet配置&nbsp;--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>commontemplate<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-class&gt;</font>org.commontemplate.tools.web.servlet.TemplateServlet<font color="#3f7f5f">&lt;/servlet-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;load-on-startup&gt;</font>1<font color="#3f7f5f">&lt;/load-on-startup&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>commontemplate<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.ctl<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/servlet-mapping&gt;</font><br/>

&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f5fbf">&lt;!--&nbsp;Struts配置&nbsp;--&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>struts<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-class&gt;</font>org.apache.struts.action.ActionServlet<font color="#3f7f5f">&lt;/servlet-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>config<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>/WEB-INF/struts.xml<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;load-on-startup&gt;</font>2<font color="#3f7f5f">&lt;/load-on-startup&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>struts<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.do<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/servlet-mapping&gt;</font><br/>
<font color="#3f7f5f">&lt;/web-app&gt;</font><br/>
<br/>
								配置struts.xml:<br/>
<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>&nbsp;?&gt;</font><br/>
<font color="#3f7f5f">&lt;!DOCTYPE&nbsp;struts-config&nbsp;<font color="#666666">PUBLIC</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"-//Apache&nbsp;Software&nbsp;Foundation//DTD&nbsp;Struts&nbsp;Configuration&nbsp;1.2//EN"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"http://jakarta.apache.org/struts/dtds/struts-config_1_2.dtd"</font>&gt;</font><br/>
<font color="#3f7f5f">&lt;struts-config&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;form-beans&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;form-bean&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"userForm"</font>&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"com.xxx.UserForm"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/form-beans&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;action-mappings&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;action&nbsp;<font color="#7f0055">path</font><font color="#000000">=</font><font color="#2a00ff">"/user/list"</font>&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"userForm"</font>&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"com.xxx.UserAction"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;forward&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"success"</font>&nbsp;<font color="#7f0055">path</font><font color="#000000">=</font><font color="#2a00ff">"/user/list.ctl"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/action&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/action-mappings&gt;</font><br/>
<font color="#3f7f5f">&lt;/struts-config&gt;</font><br/>
								<br/>
								<b>CommonTemplate在MVC中的位置：</b><br/>
								<img src="../images/frame/mvc.gif" alt="CommonTemplate In MVC" /><br/>
								<br/>
								<b>3. 与SpringMVC集成</b><br/>
								(注：与SpringMVC的集成方案请参见下载列表中：ct_springmvc.war)  <a href="downloads.html">下载&gt;&gt;</a><br/>
								配置web.xml:<br/>
<font color="#3f5fbf">&lt;!--&nbsp;Spring容器初始化配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;context-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>contextConfigLocation<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>/WEB-INF/applicationContext.xml<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
<font color="#3f7f5f">&lt;/context-param&gt;</font><br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.springframework.web.context.ContextLoaderListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>
<font color="#3f5fbf">&lt;!--&nbsp;模板引擎初始化配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>
<font color="#3f5fbf">&lt;!--&nbsp;字符编码过滤器(也可以用其它工具提供的过滤器)&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.commontemplate.tools.web.EncodingFilter<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;init-param&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-name&gt;</font>encoding<font color="#3f7f5f">&lt;/param-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;param-value&gt;</font>UTF-8<font color="#3f7f5f">&lt;/param-value&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/init-param&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>encoding<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.do<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
<font color="#3f5fbf">&lt;!--&nbsp;SpringMVC配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;servlet&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>springmvc<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-class&gt;</font>org.springframework.web.servlet.DispatcherServlet<font color="#3f7f5f">&lt;/servlet-class&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;load-on-startup&gt;</font>2<font color="#3f7f5f">&lt;/load-on-startup&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet&gt;</font><br/>
<font color="#3f7f5f">&lt;servlet-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;servlet-name&gt;</font>springmvc<font color="#3f7f5f">&lt;/servlet-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>*.do<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/servlet-mapping&gt;</font><br/>
								<br/>
								配置springmvc-servlet.xml:<br/>
								<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>&nbsp;?&gt;</font><br/>
<font color="#3f7f5f">&lt;!DOCTYPE&nbsp;beans&nbsp;PUBLIC&nbsp;"-//SPRING//DTD&nbsp;BEAN&nbsp;2.0//EN"&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;"http://www.springframework.org/dtd/spring-beans-2.0.dtd"&gt;</font><br/>
<font color="#3f7f5f">&lt;beans&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;bean&nbsp;<font color="#7f0055">id</font><font color="#000000">=</font><font color="#2a00ff">"viewResolver"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"org.commontemplate.tools.web.spring.CommonTemplateViewResolver"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;property&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"suffix"</font>&nbsp;<font color="#7f0055">value</font><font color="#000000">=</font><font color="#2a00ff">".ctl"</font>/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;property&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"contentType"</font>&nbsp;<font color="#7f0055">value</font><font color="#000000">=</font><font color="#2a00ff">"text/html;&nbsp;charset=UTF-8"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/bean&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;bean&nbsp;<font color="#7f0055">id</font><font color="#000000">=</font><font color="#2a00ff">"defaultHandlerMapping"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"</font>/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;bean&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"/list.do"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"com.xxx.web.spring.UserListController"</font>&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;property&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"successView"</font>&nbsp;<font color="#7f0055">value</font><font color="#000000">=</font><font color="#2a00ff">"list"</font>/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/bean&gt;</font><br/>
<font color="#3f7f5f">&lt;/beans&gt;</font><br/>
								<br/>
								<b>4. 与WebWork集成</b><br/>
								(注：与WebWork的集成方案请参见下载列表中：ct_webwork.war) <a href="downloads.html">下载&gt;&gt;</a><br/>
								配置web.xml:<br/>
<font color="#3f5fbf">&lt;!--&nbsp;模板引擎初始化配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>

<font color="#3f5fbf">&lt;!--&nbsp;字符编码过滤器(也可以用其它工具提供的过滤器)&nbsp;--&gt;</font><br/>
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

<font color="#3f5fbf">&lt;!--&nbsp;WebWork配置&nbsp;--&gt;</font><br/>
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
								配置xwork.xml:<br/>
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
								<b>5. 与Struts2集成</b><br/>
								(注：与Struts2的集成方案请参见下载列表中：ct_struts2.war)  <a href="downloads.html">下载&gt;&gt;</a><br/>
								配置web.xml:<br/>
<font color="#3f5fbf">&lt;!--&nbsp;模板引擎初始化配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;listener&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;listener-class&gt;</font>org.commontemplate.tools.web.EngineInitializeListener<font color="#3f7f5f">&lt;/listener-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/listener&gt;</font><br/>

<font color="#3f5fbf">&lt;!--&nbsp;字符编码过滤器(也可以用其它工具提供的过滤器)&nbsp;--&gt;</font><br/>
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

<font color="#3f5fbf">&lt;!--&nbsp;Struts2配置&nbsp;--&gt;</font><br/>
<font color="#3f7f5f">&lt;filter&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>struts2<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-class&gt;</font>org.apache.struts2.dispatcher.FilterDispatcher<font color="#3f7f5f">&lt;/filter-class&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter&gt;</font><br/>
<font color="#3f7f5f">&lt;filter-mapping&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;filter-name&gt;</font>struts2<font color="#3f7f5f">&lt;/filter-name&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;url-pattern&gt;</font>/*<font color="#3f7f5f">&lt;/url-pattern&gt;</font><br/>
<font color="#3f7f5f">&lt;/filter-mapping&gt;</font><br/>
								<br/>
								配置struts.xml:<br/>
<font color="#3f7f5f">&lt;?xml&nbsp;<font color="#7f0055">version</font><font color="#000000">=</font><font color="#2a00ff">"1.0"</font>&nbsp;<font color="#7f0055">encoding</font><font color="#000000">=</font><font color="#2a00ff">"UTF-8"</font>&nbsp;?&gt;</font>&nbsp;<br/>
<font color="#3f7f5f">&lt;!DOCTYPE&nbsp;struts&nbsp;<font color="#666666">PUBLIC</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"-//Apache&nbsp;Software&nbsp;Foundation//DTD&nbsp;Struts&nbsp;Configuration&nbsp;2.0//EN"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2a00ff">"http://struts.apache.org/dtds/struts-2.0.dtd"</font>&gt;</font><br/>
<font color="#3f7f5f">&lt;struts&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;package&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"test"</font>&nbsp;<font color="#7f0055">extends</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate-default"</font>&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;action&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"index"</font>&nbsp;<font color="#7f0055">class</font><font color="#000000">=</font><font color="#2a00ff">"com.xxx.IndexAction"</font>&nbsp;<font color="#7f0055">method</font><font color="#000000">=</font><font color="#2a00ff">"index"</font>&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;result&nbsp;<font color="#7f0055">name</font><font color="#000000">=</font><font color="#2a00ff">"success"</font>&nbsp;<font color="#7f0055">type</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate"</font>&gt;</font>/index.ctl<font color="#3f7f5f">&lt;/result&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/action&gt;</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;/package&gt;</font>&nbsp;<br/>
<font color="#3f7f5f">&lt;/struts&gt;</font>&nbsp;<br/>
								<br/>
								<b>6. 与JSP集成</b><br/>
								test.jsp:<br/>
<font color="#3f7f5f">&lt;%@taglib&nbsp;<font color="#7f0055">uri</font><font color="#000000">=</font><font color="#2a00ff">"commontemplate"</font>&nbsp;<font color="#7f0055">prefix</font><font color="#000000">=</font><font color="#2a00ff">"ct"</font>&nbsp;%&gt;</font><br/>
<font color="#3f7f5f">&lt;ct:template&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;CTL...<br/>
<font color="#3f7f5f">&lt;/ct:template&gt;</font><br/>
								<br/>
								如果服务器不支持Servlet2.4或以上版本, 需在web.xml中配置tld的引用:<br/>
<font color="#3f7f5f">&lt;taglib&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;taglib-uri&gt;</font>commontemplate<font color="#3f7f5f">&lt;/taglib-uri&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;taglib-location&gt;</font>/WEB-INF/<a href="../resource/commontemplate.tld">commontemplate.tld</a><font color="#3f7f5f">&lt;/taglib-location&gt;</font><br/>
<font color="#3f7f5f">&lt;/taglib&gt;</font><br/>
								<br/>
								<b>7. 与TagLib集成</b><br/>
								配置commontemplate.properties:<br/>
								directive{textfield}=<font color="#2a00ff">org.commontemplate.standard.directive.taglib.TagLineDirectiveAdapter()</font><br/>
								directive{textfield}.tagClassName=<font color="#2a00ff">org.apache.struts2.views.jsp.ui.TextFieldTag</font><br/>
!$
	<!--$end-->
<!--$end-->