<!--$extends{"/doc/template/frame.ctl"}-->
<!--$zone{"content"}-->
<b>1. 功能说明:</b><br/>
执行CommonTemplate模板渲染，并将结果保存到另一目录。<br/>
<br/>
<b>2. 命令行</b><br/>
ctlrender "F:/ctl/" "F:html/"<br/>
<br/>
<b>3. Ant任务</b><br/>
(1) 模板任务定义<br/>
$code{"xml"}<taskdef name="ctlrender" classname="org.commontemplate.tools.renderer.TemplateRenderTask" classpath="commontemplate.jar"/>
$end
(2) 模板任务调用<br/>
$code{"xml"}<target name="xxx">
	<ctlrender srcdir="F:/ctl/" destdir="F:/html/" classpath="commontemplate.jar"/>
</target>
$end
(3) 模板任务属性<br/>
$!
<table border="1">
	<tr bgcolor="#CCF1D5">
		<td>属性名</td>
		<td>类型</td>
		<td>描述</td>
		<td>是否必需</td>
	</tr>
	<tr>
		<td>srcdir</td>
		<td>File</td>
		<td>模板所在目录</td>
		<td><b>必需</b></td>
	</tr>
	<tr>
		<td>inputencoding</td>
		<td>String</td>
		<td>读取模板的输入编码</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>destdir</td>
		<td>File</td>
		<td>生成结果目标目录</td>
		<td><b>必需</b></td>
	</tr>
	<tr>
		<td>outputencoding</td>
		<td>String</td>
		<td>生成结果的输出编码</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>dynamicname</td>
		<td>Boolean</td>
		<td>是否为动态模板名称，缺省为false，如：F:/ctl/${entity.name}Dao.java</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>datafile</td>
		<td>File</td>
		<td>共享数据文件</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>datadir</td>
		<td>File</td>
		<td>数据文件目录，目录中的每一个数据文件都将重新执行所有模板文件，通常需使用动态模板文件名，即：dynamicname="true"，否则生成的结果会出现覆盖。</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>dataencoding</td>
		<td>String</td>
		<td>读取数据文件的输入编码</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>datatype</td>
		<td>String</td>
		<td>数据类型, 如:xml, json, properties, yaml等，不设置将以数据文件扩展名识别</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>configfile</td>
		<td>File</td>
		<td>commontemplate.properties配置文件, 默认采用标准配置</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>classpath</td>
		<td>Path</td>
		<td>类加载位置设置，直接设置</td>
		<td>可选</td>
	</tr>
	<tr>
		<td>classpathref</td>
		<td>Reference</td>
		<td>类加载位置设置，引用类型</td>
		<td>可选</td>
	</tr>
</table>
<br/>
<b>4. 图形工具安装</b><br/>
<br/>
<b>5. 图形工具截图</b><br/>
<br/>
!$
<br/>
<!--$end-->
<!--$end-->