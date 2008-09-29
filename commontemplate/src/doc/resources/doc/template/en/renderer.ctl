<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
<b>1. Define TemplateTask</b><br/>
$code{"xml"}<taskdef name="commontemplate" classname="org.commontemplate.tools.ant.TemplateTask" classpath="commontemplate.jar"/>
$end
<br/>
<b>2. Call TemplateTask</b><br/>
$code{"xml"}<target name="generate">
	<commontemplate destdir="\${bin}" configfile="\${config}">
		<fileset dir="\${src}">
			<include name="**/*.java.ctl" />
		</fileset>
	</commontemplate>
</target>
$end
<br/>
<b>3. TemplateTask Properties</b><br/>
$!
<table border="1">
	<tr bgcolor="#CCF1D5">
		<td>Property Name</td>
		<td>Type</td>
		<td>Description</td>
		<td>Required</td>
	</tr>
	<tr>
		<td>foreachname</td>
		<td>File</td>
		<td>迭代变量名称, 可在其它属性或子标签中通过${foreachname}进行引用</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>foreachlist</td>
		<td>File</td>
		<td>迭代数据列表,数据项以逗号分隔.</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>basedir</td>
		<td>File</td>
		<td>相对路径基目录, 其它属性或子标签配置的相对路径都基于目录</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>configfile</td>
		<td>File</td>
		<td>commontemplate.properties配置文件, 默认采用标准配置</td>
		<td>Optional, Alternative to "configclass" property</td>
	</tr>
	<tr>
		<td>configclass</td>
		<td>String</td>
		<td>org.commontemplate.config.Configuration实现类类名</td>
		<td>Optional, Alternative to "configfile" property</td>
	</tr>
	<tr>
		<td>classpath</td>
		<td>String</td>
		<td>configfile中配置的类以及configclass所在路径</td>
		<td>Optional, Alternative to "classpathref" property</td>
	</tr>
	<tr>
		<td>classpathref</td>
		<td>Path</td>
		<td>configfile中配置的类以及configclass所在路径引用, <br/>对应标签:&lt;path id="XXX"&gt;的ID值.</td>
		<td>Optional, Alternative to "classpath" property</td>
	</tr>
	<tr>
		<td>dir</td>
		<td>File</td>
		<td>模板所在目录, 等同于子标签&lt;fileset dir="XXX"&gt;</td>
		<td>Required, Alternative to "fileset" sub task</td>
	</tr>
	<tr>
		<td>includes</td>
		<td>String</td>
		<td>逗号分隔的包含文件名(或通配符表达式), <br/>等同于子标签&lt;fileset includes="XXX"&gt;<br/>或者&lt;fileset&gt;&lt;include name="XXX"&gt;&lt;/fileset&gt;</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>excludes</td>
		<td>String</td>
		<td>逗号分隔的排除文件名(或通配符表达式), <br/>等同于子标签&lt;fileset excludes="XXX"&gt;<br/>或者&lt;fileset&gt;&lt;exclude name="XXX"&gt;&lt;/fileset&gt;</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>defaultexcludes</td>
		<td>Boolean</td>
		<td>是否排除Ant默认排除的文件, 如.svn等, <br/>等同于子标签&lt;fileset defaultexcludes="XXX"&gt;</td>
		<td>Optional, Default true</td>
	</tr>
	<tr>
		<td>inputencoding</td>
		<td>String</td>
		<td>读取模板的输入编码</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>data</td>
		<td>String</td>
		<td>直接数据</td>
		<td>Optional, Alternative to "datafile" property</td>
	</tr>
	<tr>
		<td>datafile</td>
		<td>File</td>
		<td>数据文件</td>
		<td>Optional, Alternative to "data" property</td>
	</tr>
	<tr>
		<td>datatype</td>
		<td>String</td>
		<td>数据类型, 如:xml, json, properties, yaml等</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>dataencoding</td>
		<td>String</td>
		<td>读取数据文件的输入编码</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>destdir</td>
		<td>File</td>
		<td>生成结果目标目录</td>
		<td>Required</td>
	</tr>
	<tr>
		<td>destfileprefix</td>
		<td>String</td>
		<td>目标文件名称前缀(将添加在文件名的前面)</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>destfilesuffix</td>
		<td>String</td>
		<td>目标文件名称后缀(将添加在文件名后面,扩展名前面)</td>
		<td>Optional</td>
	</tr>
	<tr>
		<td>deleteextension</td>
		<td>String</td>
		<td>多重扩展名删除, 可用逗号隔开多个扩展名, 如: Dao.java.ctl</td>
		<td>Optional, Default ".ctl"</td>
	</tr>
	<tr>
		<td>outputencoding</td>
		<td>String</td>
		<td>生成结果的输出编码</td>
		<td>Optional</td>
	</tr>
</table>
!$
	<!--$end-->
<!--$end-->