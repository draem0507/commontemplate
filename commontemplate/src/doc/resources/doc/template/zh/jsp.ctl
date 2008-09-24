<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>1. 与JSP集成</b> <font color="green">(注：在JSP中使用CTL)</font><br/>
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
								<b>2. 与TagLib集成</b> <font color="green">(注：在CTL中使用JSP标签)</font><br/>
								配置commontemplate.properties:<br/>
								行指令：<br/>
								directive{textfield}=<font color="#2a00ff">org.commontemplate.standard.directive.taglib.TagLineDirectiveAdapter()</font><br/>
								directive{textfield}.tagClassName=<font color="#2a00ff">org.apache.struts2.views.jsp.ui.TextFieldTag</font><br/>
								块指令：<br/>
								directive{grid}=<font color="#2a00ff">org.commontemplate.standard.directive.taglib.TagBlockDirectiveAdapter()</font><br/>
								directive{grid}.tagClassName=<font color="#2a00ff">org.apache.struts2.views.jsp.ui.GridTag</font><br/>
								<br/>
!$
	<!--$end-->
<!--$end-->