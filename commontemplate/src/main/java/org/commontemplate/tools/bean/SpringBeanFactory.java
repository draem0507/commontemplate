package org.commontemplate.tools.bean;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Spring的BeanFactory接口适配
 *
 * @author liangfei0201@163.com
 *
 */
public class SpringBeanFactory implements BeanFactory {

	private final org.springframework.beans.factory.BeanFactory factory;

	public SpringBeanFactory(String resource) {
		factory = new XmlBeanFactory(new ClassPathResource(resource));
	}

	public SpringBeanFactory(Resource resource) {
		factory = new XmlBeanFactory(resource);
	}

	public Object getBean(String name) throws BeanException {
		return factory.getBean(name);
	}

}
