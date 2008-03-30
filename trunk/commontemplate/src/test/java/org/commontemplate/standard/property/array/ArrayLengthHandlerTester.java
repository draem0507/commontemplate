package org.commontemplate.standard.property.array;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.property.system.EngineBean;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.standard.property.PropertyHandler;

import junit.framework.TestCase;

public class ArrayLengthHandlerTester extends TestCase {

	private PropertyHandler propertyHandler;

	public void setUp() {
		propertyHandler = new ArrayLengthHandler();
	}
	/**
	 * 对EnginePropertyHanlder的测试。<br>
	 * @condition
	 * 条件<br>
	 * 得到 EnginePropertyHanlder。
	 * @result
	 * 结果<br>
	 * 得到EngineBean这个对象。
	 * @throws Exception
	 */
	public void testGetProperty() throws Exception{
		Object[] objs = new Object[3];
		Integer length = (Integer)propertyHandler.getProperty(objs);
		super.assertEquals(3, length.intValue());
	}
}
