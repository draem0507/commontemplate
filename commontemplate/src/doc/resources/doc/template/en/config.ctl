<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
								<b>1. Properties Configuration</b><br/>
@extends=<font color="#2a00ff">org/commontemplate/tools/web/commontemplate.properties</font><br/>
<br/>
debugMode=<font color="#2a00ff">false</font><br/>
<br/>
messagesBasename=<font color="#2a00ff">xxx/yyy</font><br/>
<br/>
defaultNullValue=<font color="#2a00ff"></font><br/>
<br/>
defaultDateFormat=<font color="#2a00ff">yyyy-MM-dd HH:mm:ss</font><br/>
<br/>
defaultNumberFormat=<font color="#2a00ff">###,##0.###</font><br/>
<br/>
defaultEncoding=<font color="#2a00ff">UTF-8</font><br/>
<br/>
virtualDirectory=<font color="#2a00ff">WEB-INF/views/</font><br/>
<br/>
modificationCheckInterval=<font color="#2a00ff">30000</font><br/>
<br/>
loggerProvider=<font color="#2a00ff">org.commontemplate.util.log.SimpleLoggerProvider()</font><br/>
<font color="#3f7f5f">#loggerProvider=org.commontemplate.util.log.CommonsLoggerProvider()</font><br/>
<br/>
diskCache.directory=<font color="#2a00ff">WEB-INF/cache/</font><br/>
<br/>
diskCache.filePrefix=<font color="#2a00ff"></font><br/>
<br/>
diskCache.fileSuffix=<font color="#2a00ff">.template</font><br/>
<br/>
diskCache.pathSeparatorReplacer=<font color="#2a00ff">_-</font><br/>
<br/>
memoryCache=<font color="#2a00ff">org.commontemplate.standard.cache.LruCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.MruCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.FifoCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.NoneCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.WeakCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.SoftCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.StrongCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.OSCache()</font><br/>
<font color="#3f7f5f">#memoryCache=org.commontemplate.standard.cache.EHCache()</font><br/>
<br/>
memoryCache.maxSize=<font color="#2a00ff">1000</font><br/>
!$
	<!--$end-->
<!--$end-->