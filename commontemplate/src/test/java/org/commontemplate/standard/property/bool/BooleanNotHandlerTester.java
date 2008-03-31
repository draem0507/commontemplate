package org.commontemplate.standard.property.bool;

import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;
/**
 * BooleanNotHandler的测试。
 * @author Yan Rong
 *
 */
public class BooleanNotHandlerTester extends TestCase {

	/**
	 * 测试BooleanNotHandler的方法。
	 * @condition
	 * 条件<br>
	 * 参数为一个bool对象。
	 * @result
	 * 结果<br>
	 * 应该得到bool对象反值。
	 * @throws Exception
	 */
	public void testHandleProperty() throws Exception{
		
		PropertyHandler propertyHandler = new BooleanNotHandler();
		
		assertEquals(Boolean.valueOf(true), 
				(Boolean) propertyHandler.getProperty(new Boolean(false)));
		
		assertEquals(Boolean.valueOf(false), 
				(Boolean) propertyHandler.getProperty(new Boolean(true)));
	}
}
