<!--$extends{"/doc/template/frame.ctl"}-->
	<!--$zone{"content"}-->
$!
<b>(1)与OSCache集成</b><br/>
OSCache项目地址：<a href="http://www.opensymphony.com/oscache" target="_blank">http://www.opensymphony.com/oscache</a><br/>
若需使用OSCache作为缓存方案，可以配置commontemplate.properties：<br/>
templateCache=<font color="#2a00ff">org.commontemplate.standard.cache.OSCache()</font><br/>
当然，你可能还需要配置oscache.properties:<br/>
cache.algorithm=<font color="#2a00ff">com.opensymphony.oscache.base.algorithm.LRUCache</font><br/>
cache.capacity=<font color="#2a00ff">1000</font><br/>
<br/>
<b>(2)与EHCache集成</b><br/>
EHCache项目地址：<a href="http://ehcache.sourceforge.net" target="_blank">http://ehcache.sourceforge.net</a><br/>
若需使用EHCache作为缓存方案，可以配置commontemplate.properties：<br/>
templateCache=<font color="#2a00ff">org.commontemplate.standard.cache.EHCache()</font><br/>
当然，你可能还需要配置ehcache.xml:<br/>
<font color="#3f7f5f">&lt;ehcache&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;diskStore&nbsp;<font color="#7f0055">path</font><font color="#000000">=</font><font color="#2a00ff">"java.io.tmpdir"</font>&nbsp;/&gt;</font><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<font color="#3f7f5f">&lt;defaultCache&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">maxElementsInMemory</font><font color="#000000">=</font><font color="#2a00ff">"10000"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">eternal</font><font color="#000000">=</font><font color="#2a00ff">"false"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">timeToIdleSeconds</font><font color="#000000">=</font><font color="#2a00ff">"120"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">timeToLiveSeconds</font><font color="#000000">=</font><font color="#2a00ff">"120"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#7f0055">overflowToDisk</font><font color="#000000">=</font><font color="#2a00ff">"true"</font>&nbsp;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;/&gt;</font><br/>
<font color="#3f7f5f">&lt;/ehcache&gt;</font><br/>
<br/>
<b>(3)与JCache(jsr107)集成</b><br/>
JCache(jsr107)项目地址：<a href="http://www.jcp.org/en/jsr/detail?id=107" target="_blank">http://www.jcp.org/en/jsr/detail?id=107</a><br/>
javax.cache.CacheFactory<br/>
......<br/>
<br/>
!$
	<!--$end-->
<!--$end-->