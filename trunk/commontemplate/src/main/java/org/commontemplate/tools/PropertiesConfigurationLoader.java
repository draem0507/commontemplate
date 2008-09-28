package org.commontemplate.tools;

import java.util.Map;
import java.util.Properties;

import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.standard.ExpressionConfigurationSettings;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.ClassLoaderResourceLoader;
import org.commontemplate.tools.bean.PropertiesBeanFactory;
import org.commontemplate.tools.bean.ResourceLoader;

/**
 * Properties配置加载器
 *
 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
 * @author liangfei0201@163.com
 *
 */
public final class PropertiesConfigurationLoader {

	private PropertiesConfigurationLoader() {}

	/**
	 * 配置Bean的名称
	 */
	public static final String CONFIG_BEAN_NAME = "commontemplate";

	/**
	 * 标准配置路径
	 */
	public static final String STANDARD_CONFIG_PATH = "org/commontemplate/tools/commontemplate.properties";

	/**
	 * 载入标准配置
	 *
	 * @return 配置
	 */
	public static ConfigurationSettings loadStandardConfiguration() {
		return loadConfiguration(STANDARD_CONFIG_PATH, new ClassLoaderResourceLoader(ConfigurationSettings.class.getClassLoader()));
	}

	/**
	 * 载入Properties配置
	 * @param propertiesPath 配置文件的路径，在未设置ResourceLoader时默认在Classpath中查找
	 * @return 配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ConfigurationSettings loadConfiguration(String propertiesPath) {
		return loadConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_CONFIG_PATH));
	}

	/**
	 * 载入Properties配置
	 * @param propertiesPath 配置文件的路径
	 * @param resourceLoader 配置加载器
	 * @return 配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ConfigurationSettings loadConfiguration(String propertiesPath, ResourceLoader resourceLoader) {
		return loadConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_CONFIG_PATH, resourceLoader));
	}

	/**
	 * 载入Properties配置
	 * @param propertiesPath 配置文件的路径
	 * @param variables 配置变量
	 * @return 配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ConfigurationSettings loadConfiguration(String propertiesPath, Map variables) {
		return loadConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_CONFIG_PATH, variables));
	}

	/**
	 * 载入Properties配置
	 * @param propertiesPath 配置文件的路径
	 * @param resourceLoader 配置加载器
	 * @param variables 配置变量
	 * @return 配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ConfigurationSettings loadConfiguration(String propertiesPath, ResourceLoader resourceLoader, Map variables) {
		return loadConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_CONFIG_PATH, resourceLoader, variables));
	}

	/**
	 * 载入Properties配置
	 * @param properties 配置
	 * @param resourceLoader 配置加载器
	 * @param variables 配置变量
	 * @return 配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ConfigurationSettings loadConfiguration(Properties properties, ResourceLoader resourceLoader, Map variables) {
		return loadConfiguration(new PropertiesBeanFactory(properties, STANDARD_CONFIG_PATH, resourceLoader, variables));
	}

	/**
	 * 载入Properties配置
	 *
	 * @param beanFactory Bean工厂
	 * @return 配置
	 */
	public static ConfigurationSettings loadConfiguration(BeanFactory beanFactory) {
		return (ConfigurationSettings) beanFactory.getBean(CONFIG_BEAN_NAME);
	}

	/**
	 * 标准表达式配置路径
	 */
	public static final String STANDARD_EXPRESSION_CONFIG_PATH = "org/commontemplate/tools/commonexpression.properties";

	/**
	 * 载入标准表达式配置
	 *
	 * @return 表达式配置
	 */
	public static ExpressionConfigurationSettings loadStandardExpressionConfiguration() {
		return loadExpressionConfiguration(STANDARD_EXPRESSION_CONFIG_PATH, new ClassLoaderResourceLoader(ExpressionConfigurationSettings.class.getClassLoader()));
	}

	/**
	 * 载入Properties表达式配置
	 * @param propertiesPath 配置文件的路径，在未设置ResourceLoader时默认在Classpath中查找
	 * @return 表达式配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ExpressionConfigurationSettings loadExpressionConfiguration(String propertiesPath) {
		return loadExpressionConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_EXPRESSION_CONFIG_PATH));
	}

	/**
	 * 载入Properties表达式配置
	 * @param propertiesPath 配置文件的路径
	 * @param resourceLoader 配置加载器
	 * @return 表达式配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ExpressionConfigurationSettings loadExpressionConfiguration(String propertiesPath, ResourceLoader resourceLoader) {
		return loadExpressionConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_EXPRESSION_CONFIG_PATH, resourceLoader));
	}

	/**
	 * 载入Properties表达式配置
	 * @param propertiesPath 配置文件的路径，在未设置ResourceLoader时默认在Classpath中查找
	 * @param variables 配置变量
	 * @return 表达式配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ExpressionConfigurationSettings loadExpressionConfiguration(String propertiesPath, Map variables) {
		return loadExpressionConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_EXPRESSION_CONFIG_PATH, variables));
	}

	/**
	 * 载入Properties表达式配置
	 * @param propertiesPath 配置文件的路径
	 * @param resourceLoader 配置加载器
	 * @param variables 配置变量
	 * @return 表达式配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ExpressionConfigurationSettings loadExpressionConfiguration(String propertiesPath, ResourceLoader resourceLoader, Map variables) {
		return loadExpressionConfiguration(new PropertiesBeanFactory(propertiesPath, STANDARD_EXPRESSION_CONFIG_PATH, resourceLoader, variables));
	}

	/**
	 * 载入Properties表达式配置
	 * @param properties 配置
	 * @param resourceLoader 配置加载器
	 * @param variables 配置变量
	 * @return 表达式配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.PropertiesBeanFactory
	 */
	public static ExpressionConfigurationSettings loadExpressionConfiguration(Properties properties, ResourceLoader resourceLoader, Map variables) {
		return loadExpressionConfiguration(new PropertiesBeanFactory(properties, STANDARD_EXPRESSION_CONFIG_PATH, resourceLoader, variables));
	}

	/**
	 * 载入Properties表达式配置
	 *
	 * @param beanFactory Bean工厂
	 * @return 表达式配置
	 */
	public static ExpressionConfigurationSettings loadExpressionConfiguration(BeanFactory beanFactory) {
		return (ExpressionConfigurationSettings) beanFactory.getBean(CONFIG_BEAN_NAME);
	}

}
