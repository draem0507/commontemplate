package org.commontemplate;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

/**
 * 当前模板引擎的版本
 * 
 * @author liangfei0201@163.com
 * 
 */
public final class Version {
	
	private Version(){}
	
	private static void assertVersionNumber(String version) {
		if (version == null)
			throw new NullPointerException("版本号为空");
		if (! version.matches("^\\d+(\\.\\d+)*$"))
			throw new IllegalArgumentException("不合法版本号：" + version + "，版本号应为\"数字.数字.数字\"");
	}

	/**
	 * 版本比较
	 * 
	 * @param v1 版本1
	 * @param v2 版本2
	 * @return 版本间差值. 等于0表示版本相同, 大于0表示v1比v2新, 大于0表示v1比v2旧
	 */
	public static int compare(String v1, String v2) {
		assertVersionNumber(v1);
		assertVersionNumber(v2);
		String[] vs1 = v1.split("\\.");
		String[] vs2 = v2.split("\\.");
		int max = Math.max(vs1.length, vs2.length);
		for (int i = 0; i < max; i++) {
			int s1 = 0;
			if ((vs1.length > i && vs1[i] != null && vs1[i].length() > 0))
				s1 = Integer.parseInt(vs1[i]);
			int s2 = 0;
			if((vs2.length > i && vs2[i] != null && vs2[i].length() > 0))
				s2 = Integer.parseInt(vs2[i]);
			if (s1 > s2)
				return 1;
			else if (s1 < s2) 
				return -1;
		}
		return 0;
	}
	
	/**
	 * 将当前版本与其它版本比较
	 * 
	 * @param otherVersion 其它版本
	 * @return 版本间差值. 等于0表示版本相同, 大于0表示当前版本新, 大于0表示当前版本旧
	 */
	public static int compareTo(String otherVersion) {
		return compare(getVersionNumber(), otherVersion);
	}

	/**
	 * 获取当前模板引擎的版本号
	 * 
	 * @return 版本号, 未知时返回null
	 */
	public static String getVersionNumber() {
		Package pkg = Version.class.getPackage();
		if (pkg != null) {
			String v = pkg.getImplementationVersion();
			if (v != null)
				return v;
		}
		try {
			Properties prop = new Properties();
			prop.load(Version.class.getClassLoader().getResourceAsStream("org/commontemplate/version.properties"));
			return prop.getProperty("versionNumber");
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * 获取当前版本发行日期
	 * 
	 * @return 发行日期, 未知时返回null
	 */
	public static Date getReleaseDate() {
		try {
			Properties prop = new Properties();
			prop.load(Version.class.getClassLoader().getResourceAsStream("org/commontemplate/version.properties"));
			return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(prop.getProperty("releaseDate"));
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取发行者信息
	 * 
	 * @return 发行者信息, 未知时返回null
	 */
	public static String getVendor() {
		Package pkg = Version.class.getPackage();
		if (pkg != null) {
			String v = pkg.getImplementationVendor();
			if (v != null)
				return v;
		}
		try {
			Properties prop = new Properties();
			prop.load(Version.class.getClassLoader().getResourceAsStream("org/commontemplate/version.properties"));
			return prop.getProperty("vendor");
		} catch (IOException e) {
			return "http://www.commontemplate.org/";
		}
	}
}
