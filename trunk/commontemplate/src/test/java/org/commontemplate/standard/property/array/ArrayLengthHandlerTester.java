package org.commontemplate.standard.property.array;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;

/**
 * ArrayLengthHandler测试类
 *
 * @author liangfei0201@163.com
 *
 */
public class ArrayLengthHandlerTester extends TestCase {

	private PropertyHandler propertyHandler;

	public void setUp() {
		propertyHandler = new ArrayLengthHandler();
	}

	public void testGetProperty() throws Exception{
		Object[] objs = new Object[3];
		Integer length = (Integer)propertyHandler.getProperty(objs);
		super.assertEquals(3, length.intValue());
	}
}
