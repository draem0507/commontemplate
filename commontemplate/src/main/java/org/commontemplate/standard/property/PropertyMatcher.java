package org.commontemplate.standard.property;

import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;

public class PropertyMatcher implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Class bean; 
	
	private String property;
	
	public PropertyMatcher(String beanAndProperty) {
		int i = beanAndProperty.lastIndexOf('.');
		Assert.assertTrue(i > -1 && i < beanAndProperty.length(), "格式错误，必需为：org.xxx.Bean.property");
		property = beanAndProperty.substring(i + 1);
		String beanName = beanAndProperty.substring(0, i);
		try {
			bean = ClassUtils.forCanonicalName(beanName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("类型错误：" + beanName , e);
		}
	}
	
	public PropertyMatcher(Class bean, String property) {
		Assert.assertNotNull(bean);
		Assert.assertNotNull(property);
		
		this.bean = bean;
		this.property = property;
	}
	
	public boolean isMatch(Class bean, String property) {
		return this.bean.isAssignableFrom(bean) && this.property.equals(property);
	}

}
