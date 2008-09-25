package org.commontemplate.util;

/**
 * 系统信息工具类
 *
 * @author liangfei0201@163.com
 *
 */
public final class SystemUtils {

	private SystemUtils(){}

	/**
	 * 获取操作系统名称
	 *
	 * @return 操作系统名称
	 */
	public static String getOsName() {
		return System.getProperties().getProperty("os.name");
	}

	/**
	 * 获取操作系统版本
	 *
	 * @return 操作系统版本
	 */
	public static String getOsVersion() {
		return System.getProperties().getProperty("os.version");
	}

	/**
	 * 获取操作系统架构
	 *
	 * @return 操作系统架构
	 */
	public static String getOsArch() {
		return System.getProperties().getProperty("os.arch");
	}

	/**
	 * 获取系统默认编码
	 *
	 * @return 系统默认编码
	 */
	public static String getFileEncoding() {
		return System.getProperties().getProperty("file.encoding");
	}

	/**
	 * 获取系统临时目录
	 *
	 * @return 系统临时目录
	 */
	public static String getTempDir() {
		return System.getProperties().getProperty("java.io.tmpdir");
	}

	/**
	 * 获取系统用户名
	 *
	 * @return 系统用户名
	 */
	public static String getUserName() {
		return System.getProperties().getProperty("user.name");
	}

	/**
	 * 获取系统用户虚拟根目录
	 *
	 * @return 系统用户虚拟根目录
	 */
	public static String getUserHome() {
		return System.getProperties().getProperty("user.home");
	}

	/**
	 * 获取系统用户当前目录 ("./"所指目录)
	 *
	 * @return 系统用户当前目录
	 */
	public static String getUserDir() {
		return System.getProperties().getProperty("user.dir");
	}

	/**
	 * 获取系统用户环境变量
	 *
	 * @return 系统用户环境变量
	 */
	public static String getUserVariant() {
		return System.getProperties().getProperty("user.variant");
	}

	/**
	 * 获取系统用户语言代号, 如: en, zh
	 *
	 * @return 系统用户所在语言代号
	 */
	public static String getUserLanguage() {
		return System.getProperties().getProperty("user.language");
	}

	/**
	 * 获取系统用户所在国家或地区代号, 如: US, CN, TW,
	 *
	 * @return 系统用户所在国家或地区代号
	 */
	public static String getUserCountry() {
		return System.getProperties().getProperty("user.country");
	}

	/**
	 * 获取系统所在时区
	 *
	 * @return 系统用户所在时区
	 */
	public static String getUserTimezone() {
		return System.getProperties().getProperty("user.timezone");
	}

	/**
	 * 获取当前环境ClassPath
	 *
	 * @return 当前环境ClassPath, 以"\n"分割.
	 */
	public static String getClassPath() {
		String pathSeparator = System.getProperties().getProperty("path.separator");
		return System.getProperties().getProperty("java.class.path")
				.replaceAll(pathSeparator, "\n")
				+ System.getProperties().getProperty("sun.boot.class.path")
				.replaceAll(pathSeparator, "\n");
	}

	/**
	 * 获取当前环境LibraryPath
	 *
	 * @return 当前环境LibraryPath, 以"\n"分割.
	 */
	public static String getLibraryPath() {
		String pathSeparator = System.getProperties().getProperty("path.separator");
		return System.getProperties().getProperty("java.library.path")
				.replaceAll(pathSeparator, "\n")
				+ System.getProperties().getProperty("sun.boot.library.path")
				.replaceAll(pathSeparator, "\n");
	}

	/**
	 * 获取Java安装主目录
	 *
	 * @return Java安装主目录
	 */
	public static String getJavaHome() {
		return System.getProperties().getProperty("java.home");
	}

	/**
	 * 获取Java版本
	 *
	 * @return Java版本
	 */
	public static String getJavaVersion() {
		return System.getProperties().getProperty("java.version");
	}

	/**
	 * 获取Class版本
	 *
	 * @return Class版本
	 */
	public static String getClassVersion() {
		return System.getProperties().getProperty("java.class.version");
	}

	/**
	 * 获取JRE名称
	 *
	 * @return JRE名称
	 */
	public static String getJreName() {
		return System.getProperties().getProperty("java.runtime.name");
	}

	/**
	 * 获取JRE版本
	 *
	 * @return JRE版本
	 */
	public static String getJreVersion() {
		return System.getProperties().getProperty("java.runtime.version");
	}

	/**
	 * 获取JVM产品名称
	 *
	 * @return JVM产品名称
	 */
	public static String getJvmName() {
		return System.getProperties().getProperty("java.vm.name");
	}

	/**
	 * 获取JVM产品版本
	 *
	 * @return JVM产品版本
	 */
	public static String getJvmVersion() {
		return System.getProperties().getProperty("java.vm.version");
	}

	/**
	 * 获取JVM产品描述信息
	 *
	 * @return JVM产品描述信息
	 */
	public static String getJvmInfo() {
		return System.getProperties().getProperty("java.vm.info");
	}

	/**
	 * 获取JVM产品生产商名称
	 *
	 * @return JVM产品生产商名称
	 */
	public static String getJvmVendor() {
		return System.getProperties().getProperty("java.vm.vendor");
	}

	/**
	 * 获取JVM产品生产商站点URL
	 *
	 * @return JVM产品生产商站点URL
	 */
	public static String getJvmVendorUrl() {
		return System.getProperties().getProperty("java.vm.vendor.url");
	}

	/**
	 * 获取JVM产品缺陷列表URL
	 *
	 * @return JVM产品缺陷列表URL
	 */
	public static String getJvmBugUrl() {
		return System.getProperties().getProperty("java.vm.vendor.url.bug");
	}

}
