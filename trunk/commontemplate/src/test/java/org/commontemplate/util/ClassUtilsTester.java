package org.commontemplate.util;

import junit.framework.TestCase;

public class ClassUtilsTester extends TestCase {

	TestObject obj;

	public void setUp() throws Exception {
		obj  = new TestObject();
	}

	public void testObjectFunctionInvoke() throws Exception {
		super.assertEquals("OK", ClassUtils.invokeMethod(obj, "objectFunction", new Object[]{null, null}));
		super.assertEquals("OK", ClassUtils.invokeMethod(obj, "objectFunction", new Object[]{"XX", null}));
		super.assertEquals("OK", ClassUtils.invokeMethod(obj, "objectFunction", new Object[]{null, "XX"}));
		super.assertEquals("OK", ClassUtils.invokeMethod(obj, "objectFunction", new Object[]{new Integer(0), "XX"}));
	}

	public void testPrimitiveFunctionInvoke() throws Exception {
		super.assertEquals(new Integer(10), ClassUtils.invokeMethod(obj, "primitiveFunction", new Object[]{new Integer(1), new Long(2)}));
	}

	public void testPrimitiveFunctionError() throws Exception {
		try {
			super.assertEquals(new Integer(10), ClassUtils.invokeMethod(obj, "primitiveFunction", new Object[]{null, null}));
			super.fail("null不能转换成primitive类型，应抛出异常！");
		} catch (NoSuchMethodException e) {
			// right
		}
	}

}
