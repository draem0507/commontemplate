package org.commontemplate.standard.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * 可热加载的国际化信息供给器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ReloadablePropertiesResourceBundleProvider implements ResourceBundleProvider {

	public ResourceBundle getResourceBundle(Locale locale) {
		// FIXME 热加载国际化信息未实现
		return null;
	}

}
