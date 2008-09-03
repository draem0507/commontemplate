package org.commontemplate.standard.i18n;

import java.util.Locale;
import java.util.Map;


/**
 * 可以重载的国际化资源提供器的接口。
 * 
 * @author yananay@126.com
 * 
 */
public interface ReloadableResourceProvider {
	
	/**
	 * 指定 resourceBaseName, Locale, Key 来得到一个对象。<br>
	 * @param resourceBaseName
	 * 资源的名称，如 country, 或 country_zh 之类。
	 * @param locale
	 * Locale 对象。
	 * @param key
	 * 关键字。
	 * @param extInfo
	 * 扩展参数的信息。<br>
	 * 为了扩展接口而使用。在 extInfo 中，可以放入需要的信息，如<br>
	 * ClassLoader, 字符集，处理的文件扩展名等等。
	 * @return
	 * 根据key得到的对象。
	 * @see StandardReloadableResourceProvider
	 */
	public Object getObject(String resourceBaseName, Locale locale, String key, Map extInfo);
	
	/**
	 * 设置刷新的间隔时间
	 * @param refreshInterval
	 */
	public void setRefreshInterval(long refreshInterval);
	/**
	 * 清空缓存。
	 *
	 */
	public void clearCache();
	
}
