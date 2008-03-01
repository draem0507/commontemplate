package org.commontemplate.tools.bean;

/**
 * Bean工厂
 * 
 * @author liangfei0201@163.com
 *
 */
public interface BeanFactory {

	/**
	 * 获取容器中的Bean对象
	 * 
	 * @param name 待获取Bean的引用名
	 * @return Bean对象
	 */
	public abstract Object getBean(String name) throws BeanException;

}
