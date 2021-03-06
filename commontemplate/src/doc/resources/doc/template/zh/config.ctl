<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								整个配置体系，从ConfigurationSettings开始，所有配置类全部留有setter注入接口，任何支持setter注入方式的IoC容器均可以完成配置。<br/>
								<br/>
								<b>一. Properties 配置</b><br/>
								默认采用内置的IoC容器初始化配置，详细请参考：org.commontemplate.tools.bean.PropertiesBeanFactory<br/>
								因为使用properties文件作为配置，所以需遵循java.util.Properties的所有规则，如：# ! = :等符号需转义等。<br/>
								使用properties文件的优势：各属性项按行展开，便于上下级配置间层级覆盖。<br/>
								<b>配置规则：</b><br/>
								<b>(1) 基本类型：</b>(与Java相似)<br/>
								(a) null为空值，true, false为布尔值<br/>
								如：<br/>
								xxx=true<br/>
								(b) 以数字开头的为Number，识别后缀L,F,D,S<br/>
								如：<br/>
								xxx=10<br/>
								(c) 以单引号括起的为Character<br/>
								如：<br/>
								xxx='A'<br/>
								(d) 以双引号括起的为String，如果非特殊串，双引号可省<br/>
								如：<br/>
								xxx="true"<br/>
								xxx=abc<br/>
								<b>(2) 类和对象：</b><br/>
								(a) 以.class结尾表示相应Class类元<br/>
								如：<br/>
								xxx=com.xxx.XXX.class<br/>
								(b) 以()结尾表示创建实例，并以实例的Key加点号作为前缀，查找并注入其属性<br/>
								如：<br/>
								xxx=com.xxx.XXX()<br/>
								xxx.yyy=yyy<br/>
								(c) 以.static结尾表示通过静态字段获取实例，并以实例的Key加点号作为前缀，查找并注入其属性<br/>
								如：<br/>
								xxx=com.xxx.XXX.xxx.static<br/>
								xxx.yyy=yyy<br/>
								(d) 以().static结尾表示通过静态工厂方法获取实例，并以实例的Key加点号作为前缀，查找并注入其属性<br/>
								如：<br/>
								xxx=com.xxx.XXX.getXXX().static<br/>
								xxx.yyy=yyy<br/>
								<b>(3) 集合：</b><br/>
								(a) 以[]结尾表示List，并以[]前的名称查找List的项<br/>
								如：<br/>
								xxx=providers[]<br/>
								providers[100]=com.xxx.XXXProvider()<br/>
								providers[200]=com.yyy.YYYProvider()<br/>
								<font color="green">(注：下标大小可为任意数字，只用于排序，通常都留有一定间隔，使子配置继承时可在中间添加项)</font><br/>
								(b) 以{}结尾表示Map，并以{}前的名称查找Map的项<br/>
								如：<br/>
								xxx=providers{}<br/>
								providers{xxx}=com.xxx.XXXProvider()<br/>
								providers{yyy}=com.yyy.YYYProvider()<br/>
								(c) 以&lt;&gt;结尾表示Set，并以&lt;&gt;前的名称查找Set的项，类似Map，但忽略Key<br/>
								如：<br/>
								xxx=providers&lt;&gt;<br/>
								providers&lt;xxx&gt;=com.xxx.XXXProvider()<br/>
								providers&lt;yyy&gt;=com.yyy.YYYProvider()<br/>
								<font color="green">(注：尖括号内的Key只用于唯一标识，组装Set时将忽略)</font><br/>
								<b>(4) 引用：</b><br/>
								以$开头表示引用其它配置项<br/>
								如：<br/>
								xxx=$yyy<br/>
								yyy=com.xxx.XXXProvider()<br/>
								<b>(5) 配置继承：</b><br/>
								特殊的Key：@extends表示继承其它配置内容，并用当前配置内容中的相同项覆盖被继承和内容，多个配置用逗号分隔<br/>
								如：<br/>
								@extends=parent1.properties,parent2.properties<br/>
								<br/>
								<b>使用方法：</b><br/>
								PropertiesConfigurationLoader.loadConfiguration("xxx.properties"); // 在Classpath中查找<br/>
								PropertiesConfigurationLoader.loadConfiguration("xxx.properties", resourceLoader); // 指定资源加载器<br/>
								<font color="green">(注：如果@extends项未配置，则缺省为：@extends=org/commontemplate/tools/commontemplate.properties)</font><br/>
								<br/>
								<b>参考配置：</b><br/>
								org/commontemplate/tools/commontemplate.properties<br/>
								org/commontemplate/tools/context.properties<br/>
								org/commontemplate/tools/template.properties<br/>
								org/commontemplate/tools/directive.properties<br/>
								org/commontemplate/tools/expression.properties<br/>
								<br/>
								<b>配置示例：</b><br/>
!$
$code{properties}
# 继承标准web配置，可不配，将根据应用环境缺省继承，如果在web环境下缺省继承以下配置：
@extends=org/commontemplate/tools/web/commontemplate.properties

# 调试模式在遇到断点时将挂起线程单步运行，非调试模式将隐藏部分不重要的异常信息
debug=false

# 国际化信息基名，规则同java.util.ResourceBundle，
# 如：假设基名为xxx/yyy，用户地区为zh_CN，
# 则在ClassPath中先查找xxx/yyy_zh_CN.properties，
# 不存在则查找xxx/yyy_zh.properties, 否则查找xxx/yyy.properties
messagesBasename=xxx/yyy

# null值输出时的默认替换字符串
defaultNullValue=

# boolean值输出时的默认替换字符串
defaultBooleanValue=true|false

# 日期输出时的默认格式
defaultDateFormat=yyyy-MM-dd HH:mm:ss

# 数字输出时的默认格式
defaultNumberFormat=###,##0.###

# 加载模板文件时的默认字符集编码，默认为UTF-8
# 若设置defaultEncoding=null将取当前系统的默认文件编码
defaultEncoding=UTF-8

# 模板文件根目录，模板加载时将以此目录作为根目录
rootDirectory=WEB-INF/views/

# 文件修改检测时间间隔，用于热加载，单位：毫秒(mm)
# 默认为30秒(30000mm)，调试时可设成1秒(1000mm)
modificationCheckInterval=30000

# 模板运行日志输出端(可扩展)
loggerProvider=org.commontemplate.util.log.CommonsLoggerProvider()
#loggerProvider=org.commontemplate.util.log.Log4jLoggerProvider()
#loggerProvider=org.commontemplate.util.log.AvalonLoggerProvider()
#loggerProvider=org.commontemplate.util.log.Slf4jLoggerProvider()
#loggerProvider=org.commontemplate.util.log.JdkLoggerProvider()
#loggerProvider=org.commontemplate.util.log.SimpleLoggerProvider()
#loggerProvider=org.commontemplate.util.log.NoneLoggerProvider()

# 磁盘缓存目录，用于缓存模板编译结果
diskCache.directory=WEB-INF/cache/

# 缓存文件前缀
diskCache.filePrefix=

# 缓存文件后缀
diskCache.fileSuffix=.template

# 内存缓存策略，OSCache, EHCache需要另外的配置(可扩展)
memoryCache=org.commontemplate.standard.cache.LruCache()
#memoryCache=org.commontemplate.standard.cache.MruCache()
#memoryCache=org.commontemplate.standard.cache.FifoCache()
#memoryCache=org.commontemplate.standard.cache.WeakCache()
#memoryCache=org.commontemplate.standard.cache.SoftCache()
#memoryCache=org.commontemplate.standard.cache.StrongCache()
#memoryCache=org.commontemplate.standard.cache.OSCache()
#memoryCache=org.commontemplate.standard.cache.EHCache()
#memoryCache=org.commontemplate.standard.cache.JCache()
#memoryCache=org.commontemplate.standard.cache.NoneCache()

# 内存缓存容量，用于：LruCache, MruCache, FifoCache
memoryCache.maxSize=1000

# 自动导入宏模板，以xx为名称空间导入xxx.ctl和yyy.ctl的配置如下：
autoImport{my}=xxx.ctl,yyy.ctl

# \$embed, \$include, \$display等指令查找模板时，是否本地化查找，不设置默认为true
# 如：\$include{"xxx.ctl"}，假设本地区域信息为zh_CN，首先查找"xxx_zh_CN.ctl"，不存在则查找"xxx_zh.ctl"，否则查找"xxx.ctl"
localizedLookup=true

# 响应内容类型, 不设置默认为text/html
response.contentType=text/html
$end{code}
$!
								<br/><br/>
								<b>配置说明：</b><br/>
								<b>(1)</b> 非web应用，如代码生成器等，可以设置：<br/>
								@extends=<font color="#2a00ff">org/commontemplate/tools/commontemplate.properties</font><br/>
								<b>(2)</b> 如果需去掉磁盘缓存，可以设置：<br/>
								diskCache=<font color="#2a00ff">null</font><br/>
								<b>(3)</b> 如果需将缓存放在非web应用目录，可以设置：<br/>
								diskCache.rootDirectory=<font color="#2a00ff">C:/xxx/</font><br/>
								<b>(4)</b> 默认是开启(非void函数)函数调用的，若需要禁止所有函数调用，可以设置：<br/>
								functionAvailable=<font color="#2a00ff">false</font><br/>
								<br/>
								<b>二. Spring 配置</b><br/>
								使用SpringIoC容器组装配置，需spring.jar支持。<br/>
								<b>配置规则：</b><br/>
								Spring配置的使用请参见：<br/>
								<a href="http://www.springframework.org">http://www.springframework.org</a><br/>
								<br/>
								<b>使用方法：</b><br/>
								SpringConfigurationLoader.loadConfiguration("xxx.xml"); // 在Classpath中查找<br/>
								<br/>
								<b>参考配置：</b><br/>
								org/commontemplate/tools/commontemplate-spring.xml<br/>
								<br/>
								<b>三. 遗留</b> <font color="green">(注: 将在1.0版本统一删除)</font><br/>
								(1) virtualDirectory<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">起止版本：</font>0.7.8加入，0.8.6废弃<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">废弃原因：</font>名称歧义<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray">替代方案：</font>rootDirectory<br/>
								<br/>
!$
	<!--$end-->
<!--$end-->