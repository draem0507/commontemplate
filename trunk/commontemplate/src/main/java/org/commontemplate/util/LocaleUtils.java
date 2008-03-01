package org.commontemplate.util;

import java.io.IOException;
import java.text.Collator;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

/**
 * 地区信息工具类
 * 
 * @author liangfei0201@163.com
 *
 */
public final class LocaleUtils {
	
	private LocaleUtils() {}
	
	/**
	 * 获取本地区域
	 * 
	 * @return 本地区域
	 */
	public static Locale getNativeLocale() {
		return Locale.getDefault();
	}

	/**
	 * 获取本地编码信息
	 * 
	 * @return 本地编码信息
	 */
	public static String getNativeEncoding() {
		String nativeEncoding = System.getProperty("file.encoding");
		if (nativeEncoding != null && nativeEncoding.length() > 0)
			return nativeEncoding;
		return getDefaultEncoding(getNativeLocale());
	}

	private static final String FINAL_DEFAULT_ENCODING = "UTF-8";

	/**
	 * 获取指定地区的默认编码
	 * 
	 * @param locale 地区
	 * @return 指定地区的默认编码
	 */
	public static String getDefaultEncoding(Locale locale) {
		Properties encodingMap = new Properties();
		try {
			encodingMap.load(LocaleUtils.class.getClassLoader().getResourceAsStream("org/commontemplate/util/default_encodings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String defaultEncoding = searchValue(encodingMap, locale);
		if (defaultEncoding != null)
			return defaultEncoding;
		return FINAL_DEFAULT_ENCODING;
	}
	
	/**
	 * 获取本地时区信息
	 * 
	 * @return 本地时区信息
	 */
	public static TimeZone getNativeTimeZone() {
		String nativeTimeZoneId = System.getProperty("user.timezone");
		if (nativeTimeZoneId != null && nativeTimeZoneId.length() > 0)
			return TimeZone.getTimeZone(nativeTimeZoneId);
		return getDefaultTimeZone(getNativeLocale());
	}

	/**
	 * 获取指定地区的默认时区(以首都为准)
	 * 
	 * @param locale 地区
	 * @return 指定地区的默认时区(以首都为准)
	 */
	public static TimeZone getDefaultTimeZone(Locale locale) {
		Properties timeZoneMap = new Properties();
		try {
			timeZoneMap.load(LocaleUtils.class.getClassLoader().getResourceAsStream("org/commontemplate/util/default_timezone.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String defaultTimeZoneId = searchValue(timeZoneMap, locale);
		if (defaultTimeZoneId == null)
			return TimeZone.getDefault();
		return TimeZone.getTimeZone(defaultTimeZoneId);
	}
	
	// 从配置中搜取相应地区值
	private static String searchValue(Properties prop, Locale locale) {
		String value = (String) prop.get(locale.getLanguage());
		if (value == null)
			value = (String) prop.get(locale.getLanguage() + "_" + locale.getCountry());
		return value;
	}
	
	/**
	 * 获取本地流通货币信息
	 * 
	 * @return 本地流通货币信息
	 */
	public static Currency getNativeCurrency() {
		return getDefaultCurrency(getNativeLocale());
	}
	
	/**
	 * 获取指定地区的默认流通货币信息
	 * 
	 * @param locale 地区
	 * @return 流通货币信息
	 */
	public static Currency getDefaultCurrency(Locale locale) {
		return Currency.getInstance(locale);
	}
	
	/**
	 * 获取本地校对机信息
	 * 
	 * @return 本地校对机信息
	 */
	public static Collator getNativeCollator() {
		return getDefaultCollator(getNativeLocale());
	}
	
	/**
	 * 获取指定地区的默认校对机信息
	 * 
	 * @param locale 地区
	 * @return 校对机信息
	 */
	public static Collator getDefaultCollator(Locale locale) {
		return Collator.getInstance(locale);
	}

}
