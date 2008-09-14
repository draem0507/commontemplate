package org.commontemplate.standard.property.object;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.commontemplate.standard.property.PropertyHandler;
import org.commontemplate.standard.property.object.ObjectToJsonPropertyHandler;

public class ObjectToJsonPropertyHandlerTester extends TestCase {

	private PropertyHandler propertyHandler;

	public void setUp() {
		propertyHandler = new ObjectToJsonPropertyHandler();
	}

	public void testPrimitive() throws Exception {
		String json = (String)propertyHandler.doProperty(new Integer(3));
		super.assertEquals("3", json);
	}

	public void testBean() throws Exception {
		String json = (String)propertyHandler.doProperty(new Bean("liangfei", 4, true));
		List args = Arrays.asList(json.substring(1, json.length() - 1).split("\\,"));
		super.assertTrue(args.contains("ok:true"));
		super.assertTrue(args.contains("name:\"liangfei\""));
		super.assertTrue(args.contains("count:4"));
	}

}
