package org.commontemplate.standard.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * 可热加载的国际化信息供给器
 * 
 * @author yananay@126.com
 *
 */
public class ReloadablePropertiesResourceBundleProvider implements ResourceBundleProvider {

	private String basename;
	
	private ClassLoader classLoader;
	
	private long refreshInterval;
	
	public void setBasename(String basename) {
		this.basename = basename;
	}	

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	public ResourceBundle getResourceBundle(Locale locale) {
		
		if(classLoader != null) {
			return ReloadResourceBundleProxy.getBundle(basename, locale, classLoader);
		} else {
			return ReloadResourceBundleProxy.getBundle(basename, locale, Thread.currentThread().getContextClassLoader());
		}
	}
	
	/**
	 * 参照 ReloadResourceBundleProxy.clearCache
	 *
	 */
	public void clearCache() {
		
		ReloadResourceBundleProxy._clearCache();
	}
		
	public long getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(long refreshInterval) {
		this.refreshInterval = refreshInterval;
		// 同时给 ReloadResourceBundleProxy 设置刷新时间
		ReloadResourceBundleProxy.setRefreshInterval(refreshInterval);
	}
	
}
