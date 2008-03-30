package org.commontemplate.standard.property.bean;

import junit.framework.TestCase;

import org.commontemplate.standard.property.PropertyHandler;

public class ObjectToJsonPropertyHandlerTester extends TestCase {

	private PropertyHandler propertyHandler;

	public void setUp() {
		propertyHandler = new ObjectToJsonPropertyHandler();
	}

	public void testPrimitive() throws Exception {
		String json = (String)propertyHandler.getProperty(new Integer(3));
		super.assertEquals("3", json);
	}

	public void testBean() throws Exception {
		String json = (String)propertyHandler.getProperty(new Bean("liangfei", 4, true));
		super.assertEquals("{ok:true,count:4,name:\"liangfei\"}", json);
	}

}
