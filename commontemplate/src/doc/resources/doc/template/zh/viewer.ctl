<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
								<b>1. 功能说明</b><br/>
								功能：双击*.ctl文件，自动读取数据文件，解析模板，生成html，并用IE打开，如果出错，使用Swing界面弹出错误信息。<br/>
								目的：方便于页面开发人员独立于业务开发进行模板测试. <br/>
								<br/>
								<b>2. 安装使用</b><br/>
								(1)安装JDK1.4.2以上版本 <a href="http://java.sun.com/j2se/1.4.2" target="_blank">下载...</a><br/>
								(2)下载CommonTemplateSetup.exe <a href="downloads.html">下载...</a><br/>
								(3)双击CommonTemplateSetup.exe，按向导完成安装，安装后，将自动关联*.ctl文件<br/>
								(4)在*.ctl文件上双击打开，或右键菜单中选“CommonTemplate”打开<br/>
								<img src="../images/viewer/open_with.gif" alt="Open With CommonTemplate"/><br/>
								CommonTemplate(view) 编译运行，并用浏览器打开<br/>
								CommonTemplate(debug) 以调试模式打开，并在模板的第一行设置断点<br/>
								CommonTemplate(generate) 生成模板到指定文件<br/>
								<br/>
								<b>3. 类加载</b><br/>
								(1) 加载JRE和引擎内置的jar包<br/>
								(2) 加载安装目录中的jar包<br/>
								(3) 加载模板所在目录中的jar包<br/>
								<br/>
								<b>4. 配置加载</b><br/>
								(1) 首先，查找模板所在目录的commontemplate.properties<br/>
								(2) 其次，查找安装目录的commontemplate.properties<br/>
								(3) 否则，使用默认的org/commontemplate/tools/viewer/commontemplate.properties<br/>
								<font color="green">(注：如果自定义配置文件中未配置@extends项，则缺省为：@extends=org/commontemplate/tools/viewer/commontemplate.properties)</font><br/>
								<br/>
								<b>5. 数据加载</b> <a href="data.html">数据格式...</a><br/>
								(1)同名数据文件加载：如打开test.ctl，将搜索同目录的test.xml, test.json, test.properties, test.yaml等数据文件并加载<br/>
								(2)内部数据供给块指令：\$data{"xml"}...\$end，指令内部放数据表示内容<br/>
								(3)外部数据加载指令：\$load{xml: "test.xml"} 或者 \$load{"test.xml"} 通过文件扩展名识别类型<br/>
								内置支持xml,json,properties,ymal等数据格式。<a href="extension.html">扩展...</a><br/>
								<br/>
	<!--$end-->
<!--$end-->