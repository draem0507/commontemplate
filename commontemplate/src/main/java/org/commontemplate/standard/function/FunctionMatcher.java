package org.commontemplate.standard.function;

import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;

public class FunctionMatcher implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Class bean;

	private String function;

	public FunctionMatcher(String beanAndFunction) {
		int i = beanAndFunction.lastIndexOf('.');
		Assert.assertTrue(i > -1 && i < beanAndFunction.length(), "FunctionMatcher.format.error");
		function = beanAndFunction.substring(i + 1);
		String beanName = beanAndFunction.substring(0, i);
		try {
			bean = ClassUtils.forName(beanName);
		} catch (ClassNotFoundException e) {
			Assert.fail("FunctionMatcher.type.error", new Object[]{beanName, e.getMessage()});
		}
	}

	public FunctionMatcher(Class bean, String function) {
		Assert.assertNotNull(bean);
		Assert.assertNotNull(function);

		this.bean = bean;
		this.function = function;
	}

	public boolean isMatch(Class bean, String property) {
		return this.bean.isAssignableFrom(bean) && this.function.equals(property);
	}

}
