package org.commontemplate.standard.i18n;

import java.util.Locale;


/**
 * 可以重载的国际化资源提供器的接口。
 * 
 * @author yananay@126.com
 * 
 */
public interface ReloadableResourceProvider {
	/**
	 * 根据 key 得到一个字符串。
	 * @param key
	 * @return
	 */
	public String getString(String key);
	/**
	 * 根据 key 得到一个对象。
	 * @param key
	 * @return
	 */
	public Object getObject(String key);
	/**
	 * 设定 Locacle。
	 * @param locale
	 */
	public void setResourceLocale(Locale locale);
	/**
	 * 设定 ClassLoader。
	 * @param resourceClassLoader
	 */
	public void setResourceClassLoader(ClassLoader resourceClassLoader);
	/**
	 * 设置资源文件的名称。如 country 或 country_zh。
	 * @param resourceBaseName
	 */
	public void setResourceBaseName(String resourceBaseName);
	/**
	 * 设置想要处理的文件的扩展名。
	 * @param fileExtName
	 */
	public void setFileExtName(String fileExtName);
	/**
	 * 设置处理资源的类的名字。这个类必须实现 ReloadProviderResource 接口。
	 * @param resolveReloadResource
	 * <br>处理资源的类的名字。例如 org.commontemplate.standard.i18n.ReloadablePropertyResource
	 * @see ReloadableResource
	 */
	public void setResolveReloadResource(String resolveReloadResource);
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
	
	/**
	 * 指定编码的字符集。
	 * @param encoding
	 */
	public void setEncoding(String encoding);

}
