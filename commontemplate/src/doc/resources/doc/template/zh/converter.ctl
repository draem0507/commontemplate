<!--$extends{"/doc/template/frame.ctl"}-->
<!--$zone{"content"}-->
<b>1. 功能说明:</b><br/>
将Velocity或FreeMarker等其它模板转换成CommonTemplate模板.<br/>
<br/>
<b>2. ANT任务转换:</b><br/>
(1) 模板任务定义<br/>
$code{"xml"}<taskdef name="ctlconvert" classname="org.commontemplate.tools.converter.TemplateConvertTask" classpath="commontemplate.jar"/>
$end
(2) 模板任务调用<br/>
$code{"xml"}<ctlconvert srctype="velocity" srcdir="F:/velocity" destdir="F:/commontemplate" />
$end
<br/>
<b>3. 命令行转换:</b><br/>
ctlconvert velocity "F:/velocity" "F:/commontemplate"<br/>
<br/>
<b>4. 图形工具转换:</b><br/>
<img src="../images/converter/converter.gif"/><br/>
<br/>
<!--$end-->
<!--$end-->