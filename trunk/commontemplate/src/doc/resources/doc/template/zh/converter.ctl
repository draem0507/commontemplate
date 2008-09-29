<!--$extends{"/doc/template/frame.ctl"}-->
<!--$zone{"content"}-->
<b>1. 功能说明:</b><br/>
将Velocity或FreeMarker等其它模板转换成CommonTemplate模板.<br/>
<br/>
<b>2. 命令行转换:</b><br/>
ctlconvert velocity "F:/velocity" "F:/commontemplate"<br/>
<br/>
<b>3. ANT任务转换:</b><br/>
(1) 模板任务定义<br/>
$code{"xml"}<taskdef name="ctlconvert" classname="org.commontemplate.tools.converter.TemplateConvertTask" classpath="commontemplate.jar"/>
$end
(2) 模板任务调用<br/>
$code{"xml"}<ctlconvert srctype="velocity" srcdir="F:/velocity" destdir="F:/commontemplate" />
$end
<br/>
<b>4. 转换工具安装:</b><br/>
(1) 需JDK1.4.2以上版本支持 <a href="http://java.sun.com/j2se/1.4.2" target="_blank">下载jre...</a><br/>
(2) 下载TemplateConverterSetup.exe <a href="downloads.html">下载...</a><br/>
(3) 双击TemplateConverterSetup.exe，按向导完成安装，安装后，将在桌面添加快捷方式<br/>
(4) 双击桌面快捷方式启动模板转换器<br/>
<br/>
<b>5. 转换工具界面截图:</b><br/>
<img src="../images/converter/converter.gif"/><br/>
<br/>
<!--$end-->
<!--$end-->