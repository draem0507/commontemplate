package org.commontemplate.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * 类型测试
 *
 * @author Yan Rong (yananay@126.com)
 *
 */
public class TypeUtilsTester extends TestCase {

	public void testRightNumber() {
		assertTrue(TypeUtils.isNumber("1"));
		assertTrue(TypeUtils.isNumber("1L"));
		assertTrue(TypeUtils.isNumber("1.2"));
		assertTrue(TypeUtils.isNumber("1.2F"));
		assertTrue(TypeUtils.isNumber("1.2D"));
	}

	public void testNotNumber() {
		assertFalse(TypeUtils.isNumber("a"));
		assertFalse(TypeUtils.isNumber("a1"));
		assertFalse(TypeUtils.isNumber("a1.2"));
		assertFalse(TypeUtils.isNumber("a1.2F"));
		assertFalse(TypeUtils.isNumber("a1.2D"));
		assertFalse(TypeUtils.isNumber("_1.2F"));
		assertFalse(TypeUtils.isNumber("+1.2D"));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 null 对象。
	 * @result
	 * 结果<br>
	 * 返回 false。
	 * @throws Exception
	 */
	public void testIsTrueForNullObj() {
		assertFalse(TypeUtils.isTrue(null));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 boolean 对象。
	 * @result
	 * 结果<br>
	 * 返回 boolean 所标示的bool值。
	 * @throws Exception
	 */
	public void testIsTrueForBoolean() {
		assertFalse(TypeUtils.isTrue(Boolean.FALSE));
		assertTrue(TypeUtils.isTrue(Boolean.TRUE));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 Number 对象。
	 * @result
	 * 结果<br>
	 * Number 的 value> 0 : 返回 true,<br>
	 * 否则返回 false
	 * @throws Exception
	 */
	public void testIsTrueForNumber() {
		assertTrue(TypeUtils.isTrue(Float.valueOf("0.9")));
		assertTrue(TypeUtils.isTrue(Float.valueOf("1.1")));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 String 对象。
	 * @result
	 * 结果<br>
	 * String 的 长度 > 0 : 返回 true,<br>
	 * 否则返回 false
	 * @throws Exception
	 */
	public void testIsTrueForString() {
		assertFalse(TypeUtils.isTrue(""));
		assertTrue(TypeUtils.isTrue(" "));
		assertTrue(TypeUtils.isTrue("abc"));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 Collection 对象。
	 * @result
	 * 结果<br>
	 * String 的 size > 0 : 返回 true,<br>
	 * 否则返回 false
	 * @throws Exception
	 */
	public void testIsTrueForCollection() {

		Collection c = new ArrayList();
		assertFalse(TypeUtils.isTrue(c));
		c.add("a");
		assertTrue(TypeUtils.isTrue(c));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 Map 对象。
	 * @result
	 * 结果<br>
	 * String 的 size > 0 : 返回 true,<br>
	 * 否则返回 false
	 * @throws Exception
	 */
	public void testIsTrueForMap() {

		Map map = new HashMap();
		assertFalse(TypeUtils.isTrue(map));
		map.put("a", "a");
		assertTrue(TypeUtils.isTrue(map));
	}

	/**
	 * 对TypeUtils.isTrue的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数 普通 对象。
	 * @result
	 * 结果<br>
	 * 对象不等于空，返回 true,<br>
	 * 否则返回 false
	 * @throws Exception
	 */
	public void testIsTrueForOtherObject() {

		assertTrue(TypeUtils.isTrue(this));
	}

}
