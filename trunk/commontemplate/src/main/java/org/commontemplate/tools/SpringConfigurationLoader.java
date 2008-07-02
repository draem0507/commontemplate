package org.commontemplate.tools;

import org.commontemplate.standard.ConfigurationSettings;
import org.commontemplate.tools.bean.BeanFactory;
import org.commontemplate.tools.bean.SpringBeanFactory;

public final class SpringConfigurationLoader {

	private SpringConfigurationLoader(){}

	/**
	 * 配置Bean的名称
	 */
	public static final String CONFIG_BEAN_NAME = "commontemplate";

	/**
	 * 标准配置路径
	 */
	public static final String STANDARD_CONFIG_PATH = "org/commontemplate/tools/commontemplate-spring.xml";

	/**
	 * 载入标准配置
	 *
	 * @return 配置
	 */
	public static ConfigurationSettings loadStandardConfiguration() {
		return loadConfiguration(STANDARD_CONFIG_PATH);
	}

	/**
	 * 载入Spring配置
	 * @param propertiesPath 配置文件的路径，在未设置ResourceLoader时默认在Classpath中查找
	 * @return 配置
	 * @see org.commontemplate.tools.bean.ResourceLoader
	 * @see org.commontemplate.tools.bean.SpringBeanFactory
	 */
	public static ConfigurationSettings loadConfiguration(String propertiesPath) {
		return loadConfiguration(new SpringBeanFactory(propertiesPath));
	}

	/**
	 * 载入Spring配置
	 *
	 * @param beanFactory Bean工厂
	 * @return 配置
	 */
	public static ConfigurationSettings loadConfiguration(BeanFactory beanFactory) {
		return (ConfigurationSettings) beanFactory.getBean(CONFIG_BEAN_NAME);
	}
}
