package org.commontemplate.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化信息ResourceBundle提供器
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface ResourceBundleProvider {

	/**
	 * 获取指定区域的本地化信息
	 * 
	 * @param locale
	 *            区域
	 * @return 本地化信息
	 */
	public ResourceBundle getResourceBundle(Locale locale);

}
