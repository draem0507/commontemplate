package org.commontemplate.standard.i18n;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * 基于属性文件的国际化信息供给器
 *
 * @author liangfei0201@163.com
 *
 */
public class PropertiesResourceBundleProvider implements ResourceBundleProvider, Serializable {

	private static final long serialVersionUID = 1L;

	private String basename;

	public void setBasename(String basename) {
		this.basename = basename;
	}

	private transient ClassLoader classLoader;

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public ResourceBundle getResourceBundle(Locale locale) {
		if (classLoader == null)
			return ResourceBundle.getBundle(basename, locale);
		return ResourceBundle.getBundle(basename, locale, classLoader);
	}

}
