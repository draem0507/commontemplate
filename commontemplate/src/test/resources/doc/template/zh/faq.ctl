<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>1. 编码问题</b><br/>
								<b>加载：</b><br/>
								resourceLoader在加载模板文件时，需得知以何种编码读取文件。<br/>
								如果各模板文件的编码一致，可以设置加载器的默认编码：<br/>
								defaultEncoding=UTF-8<br/>
								注：如果defaultEncoding=null将使用System.getProperty("file.encoding")作为默认编码。<br/>
								以下调用方式会用到默认编码：<br/>
								engine.getTemplate(name);<br/>
								engine.loadResource(name);<br/>
								如果各模板文件的编码不一致，需单独指定编码，则应该使用：<br/>
								engine.getTemplate(name, encoding);<br/>
								engine.loadResource(name, encoding);<br/>
								<b>输出：</b><br/>
								输出尽可能与模板保持编码一致：<br/>
								response.setCharacterEncoding("UTF-8");<br/>
								当然也可以做转码处理：(response已内部处理，只需指定目标编码，并且目标编码中包含相应字符)<br/>
								response.setCharacterEncoding("GBK");<br/>
								注：输出编码必需指定，否则出现"?"不识别码。<br/>
								<br/>
								<b>2. 缓存问题</b><br/>
								...<br/>
								<br/>
								<b>3. 模板路径问题</b><br/>
								...<br/>
!$
	<!--$end-->
<!--$end-->