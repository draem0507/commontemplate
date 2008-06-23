package org.commontemplate.util;

import java.util.Map;

import junit.framework.TestCase;

/**
 * BeanUtils测试类
 *
 * @author liangfei0201@163.com
 *
 */
public class BeanUtilsTester extends TestCase {

	public void testNullValue() throws Exception {
		super.assertEquals(null, BeanUtils.getProperty(null, "name"));
	}

	public void testNameVlaue() throws Exception {
		Data data = new DataImpl();
		super.assertEquals("xx", BeanUtils.getProperty(data, "name"));
	}

	public void testPublicProperties() throws Exception {
		Data data = new DataImpl();
		Map properties = BeanUtils.getProperties(data);
		super.assertEquals(1, properties.size());
		super.assertEquals("xx", properties.get("name"));
	}

	/**
	 * 测试静态内部类接口取值
	 */
	public void testStaticProperty() throws Exception {
		Data data = new DataStaticImpl();
		super.assertEquals("aa", BeanUtils.getProperty(data, "name"));
	}

	public void testStaticProperties() throws Exception {
		Data data = new DataStaticImpl();
		Map properties = BeanUtils.getProperties(data);
		super.assertEquals(1, properties.size());
		super.assertEquals("aa", properties.get("name"));
	}

	/**
	 * 测试非静态内部类接口取值
	 */
	public void testInnerImpl() throws Exception {
		Data data = new DataInnerImpl();
		super.assertEquals("bb", BeanUtils.getProperty(data, "name"));
	}

	public void testInnerProperties() throws Exception {
		Data data = new DataInnerImpl();
		Map properties = BeanUtils.getProperties(data);
		super.assertEquals(1, properties.size());
		super.assertEquals("bb", properties.get("name"));
	}

	/**
	 * 测试匿名类接口取值
	 */
	public void testAnonymousProperty() throws Exception {
		Data data = new Data() {
			public String getName() {
				return "cc";
			}
		};
		super.assertEquals("cc", BeanUtils.getProperty(data, "name"));
	}

	public void testAnonymousProperties() throws Exception {
		Data data = new Data() {
			public String getName() {
				return "cc";
			}
		};
		Map properties = BeanUtils.getProperties(data);
		super.assertEquals(1, properties.size());
		super.assertEquals("cc", properties.get("name"));
	}

	/**
	 * 测试包保护级接口取值
	 */
	public void testDefaultProperty() throws Exception {
		Data data = new DataDefaultImpl();
		super.assertEquals("dd", BeanUtils.getProperty(data, "name"));
	}

	public void testDefaultProperties() throws Exception {
		Data data = new DataDefaultImpl();
		Map properties = BeanUtils.getProperties(data);
		super.assertEquals(1, properties.size());
		super.assertEquals("dd", properties.get("name"));
	}

	/**
	 * 测试无接口取值
	 */
	public void testNotProperty() throws Exception {
		DataNotImpl data = new DataNotImpl();
		super.assertEquals("ee", BeanUtils.getProperty(data, "name"));
	}

	public void testNotProperties() throws Exception {
		DataNotImpl data = new DataNotImpl();
		Map properties = BeanUtils.getProperties(data);
		super.assertEquals(0, properties.size());
		super.assertEquals(null, properties.get("name"));
	}

	private static class DataStaticImpl implements Data {
		public String getName() {
			return "aa";
		}
	}

	private static class DataInnerImpl implements Data {
		public String getName() {
			return "bb";
		}
	}

}

class DataNotImpl {
	public String getName() {
		return "ee";
	}
}

class DataDefaultImpl implements Data {
	public String getName() {
		return "dd";
	}
}
