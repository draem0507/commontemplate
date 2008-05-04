<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$overzone{"content"}-->
$!
								<b>一. Properties 配置</b><br/>
								<b>配置示例：</b><br/>
<font color="#3f7f5f"># 继承标准web配置</font><br/>
@extends=<font color="#2a00ff">org/commontemplate/tools/web/commontemplate.properties</font><br/>
<br/>
<font color="#3f7f5f"># 非调试模式将隐藏部分不重要的异常信息</font><br/>
debugMode=<font color="#2a00ff">false</font><br/>
<br/>
<font color="#3f7f5f"># 国际化信息基名，规则同java.util.ResourceBundle，</font><br/>
<font color="#3f7f5f"># 如：假设基名为xxx/yyy，用户地区为zh_CN，</font><br/>
<font color="#3f7f5f"># 则在ClassPath中先查找xxx/yyy_zh_CN.properties，</font><br/>
<font color="#3f7f5f"># 不存在则查找xxx/yyy_zh.properties, 否则查找xxx/yyy.properties</font><br/>
messagesBasename=<font color="#2a00ff">xxx/yyy</font><br/>
<br/>
<font color="#3f7f5f"># null值输出时的默认替换字符串</font><br/>
defaultNullValue=<font color="#2a00ff"></font><br/>
<br/>
<font color="#3f7f5f"># 数组输出时的各元素间的分隔符</font><br/>
defaultArraySeparator=<font color="#2a00ff">,</font><br/>
<br/>
<font color="#3f7f5f"># 日期输出时的默认格式</font><br/>
defaultDateFormat=<font color="#2a00ff">yyyy-MM-dd HH:mm:ss</font><br/>
<br/>
<font color="#3f7f5f"># 数字输出时的默认格式</font><br/>
defaultNumberFormat=<font color="#2a00ff">###,##0.###</font><br/>
<br/>
<font color="#3f7f5f"># 加载模板文件时的默认字符集编码，默认为UTF-8</font><br/>
<font color="#3f7f5f"># 若设置defaultEncoding=null将取当前系统的默认文件编码</font><br/>
defaultEncoding=<font color="#2a00ff">UTF-8</font><br/>
<br/>
<font color="#3f7f5f"># 模板文件虚拟目录，模板加载时将以此目录作为根目录</font><br/>
virtualDirectory=<font color="#2a00ff">WEB-INF/views/</font><br/>
<br/>
<font color="#3f7f5f"># 文件修改检测时间间隔，用于热加载，单位：毫秒(mm)</font><br/>
<font color="#3f7f5f"># 默认为30秒(30000mm)，调试时可设成1秒(1000mm)</font><br/>
modificationCheckInterval=<font color="#2a00ff">30000</font><br/>
<br/>
<font color="#3f7f5f"># 模板运行日志输出端</font><br/>
logger=<font color="#2a00ff">org.commontemplate.standard.log.SimpleLogger()</font><br/>
<font color="#3f7f5f">#logger=org.commontemplate.standard.log.CommonsLogging()</font><br/>
<br/>
<font color="#3f7f5f"># 磁盘缓存目录，用于缓存模板编译结果</font><br/>
diskCache.directory=<font color="#2a00ff">WEB-INF/cache/</font><br/>
<br/>
<font color="#3f7f5f"># 缓存文件前缀</font><br/>
diskCache.filePrefix=<font color="#2a00ff"></font><br/>
<br/>
<font color="#3f7f5f"># 缓存文件后缀</font><br/>
diskCache.fileSuffix=<font color="#2a00ff">.template</font><br/>
<br/>
<font color="#3f7f5f"># 文件路径分割符(也就是"/"和"\")的替换符，用于将模板全路径名转成文件名</font><br/>
diskCache.pathSeparatorReplacer=<font color="#2a00ff">_-</font><br/>
<br/>
<font color="#3f7f5f"># 内存缓存策略，OSCache, EHCache需要另外的配置</font><br/>
memoryCache=<font color="#2a00ff">org.commontemplate.standard.cache.LruCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.MruCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.FifoCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.NoneCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.WeakCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.SoftCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.StrongCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.ext.cache.OSCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.ext.cache.EHCache()</font><br/>
<br/>
<font color="#3f7f5f"># 内存缓存容量，用于：LruCache, MruCache, FifoCache</font><br/>
memoryCache.maxSize=<font color="#2a00ff">1000</font><br/>
<br/>
<font color="#3f7f5f"># 响应内容类型, 不设置默认为text/html</font><br/>
response.contentType=<font color="#2a00ff">text/html</font><br/>
								<br/>
								<b>特殊配置说明：</b><br/>
								(1) 非web应用，如代码生成器等，可以设置：<br/>
								@extends=<font color="#2a00ff">org/commontemplate/tools/commontemplate.properties</font><br/>
								(2) 如果需去掉磁盘缓存，通常用于已在OSCache/EHCache中配置磁盘缓存，可以设置：<br/>
								templateCache=<font color="#2a00ff">$memoryCache</font><br/>
								(3) 如果需将缓存放在非web应用目录，可以设置：<br/>
								diskCache.rootDirectory=<font color="#2a00ff">C:/xxx/</font><br/>
								(3) 默认是禁止函数调用的，若需要开启，可以设置：<br/>
								functionAvailable=<font color="#2a00ff">true</font><br/>
								<br/>
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
								<br/>
								<b>配置规则：</b><br/>
								整个standard包的所有配置类全部留有setter注入接口，任何支持setter方式的IoC容器均可以完成配置。<br/>
								默认采用内置的IoC容器初始化配置，详细请参考：org.commontemplate.util.PropertiesBeanFactory。<br/>
								使用properties文件作为配置，所需遵循java.util.Properties的所有规则，如：# ! = :等符号需转义等。<br/>
								<b>(1) 基本类型：</b>(与Java相似)<br/>
								null, true, false为关键字<br/>
								以数字开头的为Number，识别后缀L,F,D,S<br/>
								以单引号括起的为Character<br/>
								以双引号括起的为String，如果非特殊串，双引号可省<br/>
								<b>(2) 引用：</b><br/>
								以$开头表示引用配置项<br/>
								<b>(3) 类和对象：</b><br/>
								以.class结尾表示相应Class类元<br/>
								以()结尾表示创建Instance，并以Instance的key加点号作为前缀，查找并注入其属性<br/>
								以.static结尾表示通过静态字段获取Instance<br/>
								以().static结尾表示通过静态工厂方法获取Instance，并以Instance的key加点号作为前缀，查找并注入其属性<br/>
								<b>(4) 集合：</b><br/>
								以&lt;&gt;结尾表示Set，并以&lt;&gt;前的名称查找Set的项<br/>
								以[]结尾表示List，并以[]前的名称查找List的项<br/>
								以{}结尾表示Map，并以{}前的名称查找Map的项<br/>
								<b>(5) 配置继承：</b><br/>
								@extends=parent1.properties,parent2.properties<br/>
								<br/>
								<b>使用方法：</b><br/>
								PropertiesConfigurationLoader.loadConfiguration("xxx.properties"); // 在Classpath中查找<br/>
								PropertiesConfigurationLoader.loadConfiguration("xxx.properties", resourceLoader); // 指定资源加载器<br/>
								<br/>
								<b>标准配置参考：</b><br/>
								org/commontemplate/tools/commontemplate.properties<br/>
								org/commontemplate/tools/context.properties<br/>
								org/commontemplate/tools/template.properties<br/>
								org/commontemplate/tools/directive.properties<br/>
								org/commontemplate/tools/expression.properties<br/>
								<br/>
								<b>二. Spring 配置</b><br/>
								Spring的使用请参见：<a href="http://www.springframework.org">http://www.springframework.org</a><br/>
								commontemplate-spring.xml:<br/>
!$
	<!--$end-->
<!--$end-->