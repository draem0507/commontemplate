package org.commontemplate.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class ExceptionMessageSource {

	private ExceptionMessageSource() {}
	
	// 可以通过修改系统属性org.commontemplate.util.ExceptionMessageSource.baseName改变异常信息位置
	private static ResourceBundle resourceBundle = ResourceBundle
			.getBundle(System.getProperty(
					"org.commontemplate.util.ExceptionMessageSource.baseName",
					"org/commontemplate/util/exception_messages"));
	
	public static synchronized void setResourceBundle(ResourceBundle rb) {
		resourceBundle = rb;
	}

	public static String getMessage(String key) {
		return resourceBundle.getString(key);
	}

	public static String getMessage(String key, String defaultValue) {
		String msg = getMessage(key);
		if (msg == null)
			return defaultValue;
		return msg;
	}

	public static String getMessage(String key, Object[] args) {
		String msg = getMessage(key);
		if (args == null || args.length == 0)
			return msg;
		return formatMessage(msg, args);
	}

	public static String getMessage(String key, Object[] args,
			String defaultValue) {
		String msg = getMessage(key, defaultValue);
		if (args == null || args.length == 0)
			return msg;
		return formatMessage(msg, args);
	}

	private static String formatMessage(String msg, Object[] args) {
		if (msg != null && msg.length() > 0 
				&& args != null && args.length > 0)
			return new MessageFormat(msg, resourceBundle.getLocale()).format(args);
		return msg;
	}

}
