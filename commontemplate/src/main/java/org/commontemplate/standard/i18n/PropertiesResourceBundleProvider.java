package org.commontemplate.standard.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import org.commontemplate.config.ResourceBundleProvider;

/**
 * 基于属性文件的国际化信息供给器
 * 
 * @author liangfei0201@163.com
 *
 */
public class PropertiesResourceBundleProvider implements ResourceBundleProvider {
	
	private String basename;

	public void setBasename(String basename) {
		this.basename = basename;
	}

	private ClassLoader classLoader;

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public ResourceBundle getResourceBundle(Locale locale) {
		if (classLoader == null)
			return ResourceBundle.getBundle(basename, locale);
		return ResourceBundle.getBundle(basename, locale, classLoader);
	}

}
