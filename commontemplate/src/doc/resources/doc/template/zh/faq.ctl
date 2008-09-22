<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>1. 模板编码问题</b><br/>
								<b>加载：</b><br/>
								resourceLoader在加载模板文件时，需得知以何种编码读取文件。<br/>
								如果各模板文件的编码一致，可以设置加载器的默认编码：<br/>
								defaultEncoding=UTF-8<br/>
								如果希望使用操作系统编码，可以将默认编码设为空：<br/>
								defaultEncoding=null<br/>
								以下调用方式会用到默认编码：<br/>
								engine.getTemplate(name);<br/>
								engine.getResource(name);<br/>
								如果各模板文件的编码不一致，需单独指定编码，可以使用：<br/>
								engine.getTemplate(name, encoding);<br/>
								engine.getResource(name, encoding);<br/>
								<b>输出：</b><br/>
								输出尽可能与模板保持编码一致：<br/>
								response.setCharacterEncoding("UTF-8");<br/>
								当然也可以做转码处理：(response已内部处理，只需指定目标编码，并且目标编码中包含相应字符)<br/>
								response.setCharacterEncoding("GBK");<br/>
								注：输出编码必需指定，否则出现"?"不识别码。<br/>
								<br/>
								<b>2. 模板路径问题</b><br/>
								所有路径均以"/"开头表示相对于根目录(根目录可设置)。<br/>
								所有未以"/"开头的路径都将转换成以"/"开头的绝对路径使用，保证路径名称唯一性。<br/>
								支持"../"和"./"等方式的相对路径，模板间包含时，以当前模板所在目录计算绝对路径，"../"向上访问不能超出根目录。<br/>
								<br/>
								<b>3. 模板缓存问题</b><br/>
								...<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->