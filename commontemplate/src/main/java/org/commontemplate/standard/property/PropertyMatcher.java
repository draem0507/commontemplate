package org.commontemplate.standard.property;

import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;

public class PropertyMatcher implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Class bean;

	private String property;

	public PropertyMatcher(String beanAndProperty) {
		int i = beanAndProperty.lastIndexOf('.');
		Assert.assertTrue(i > -1 && i < beanAndProperty.length(), "PropertyMatcher.format.error");
		property = beanAndProperty.substring(i + 1);
		String beanName = beanAndProperty.substring(0, i);
		try {
			bean = ClassUtils.forName(beanName);
		} catch (ClassNotFoundException e) {
			Assert.fail("PropertyMatcher.type.error", new Object[]{beanName, e.getMessage()});
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
