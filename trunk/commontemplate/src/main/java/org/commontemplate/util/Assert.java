package org.commontemplate.util;

import java.util.Collection;
import java.util.Map;

/**
 * 前置条件断言
 *
 * @author liangfei0201@163.com
 *
 */
public final class Assert {

	private Assert(){}

	/**
	 * 立即失败
	 */
	public static void fail() {
		fail("Assert.fail");
	}

	/**
	 * 立即失败
	 *
	 * @param messageKey 错误信息key值
	 */
	public static void fail(String messageKey) {
		throw I18nExceptionFactory.createIllegalStateException(messageKey);
	}

	/**
	 * 立即失败
	 *
	 * @param messageKey 错误信息key值
	 * @param args
	 */
	public static void fail(String messageKey, Object[] args) {
		throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言两个对象相等
	 *
	 * @param value1 对象1
	 * @param value2 对象2
	 */
	public static void assertEquals(Object value1, Object value2) {
		assertEquals(value1, value2, "Assert.equals");
	}

	/**
	 * 断言两个对象相等
	 *
	 * @param value1 对象1
	 * @param value2 对象2
	 * @param messageKey 错误信息key值
	 */
	public static void assertEquals(Object value1, Object value2, String messageKey) {
		assertEquals(value1, value2, messageKey, new Object[] {value1, value2});
	}

	/**
	 * 断言两个对象相等
	 *
	 * @param value1 对象1
	 * @param value2 对象2
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertEquals(Object value1, Object value2, String messageKey, Object[] args) {
		if (value1 == null && value2 == null)
			return;
		if (value1 == null || ! value1.equals(value2))
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言两个对象不相等
	 *
	 * @param value1 对象1
	 * @param value2 对象2
	 */
	public static void assertNotEquals(Object value1, Object value2) {
		assertNotEquals(value1, value2, "Assert.not.equals");
	}

	/**
	 * 断言两个对象不相等
	 *
	 * @param value1 对象1
	 * @param value2 对象2
	 * @param messageKey 错误信息key值
	 */
	public static void assertNotEquals(Object value1, Object value2, String messageKey) {
		assertNotEquals(value1, value2, messageKey, new Object[] {value1, value2});
	}

	/**
	 * 断言两个对象不相等
	 *
	 * @param value1 对象1
	 * @param value2 对象2
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNotEquals(Object value1, Object value2, String messageKey, Object[] args) {
		if ((value1 == null && value2 == null)
				|| (value1 != null && value1.equals(value2)))
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言表达式结果为true
	 *
	 * @param value 表达式结果
	 */
	public static void assertTrue(boolean value) {
		assertTrue(value, "Assert.true");
	}

	/**
	 * 断言表达式结果为true
	 *
	 * @param value 表达式结果
	 * @param messageKey 错误信息key值
	 */
	public static void assertTrue(boolean value, String messageKey) {
		if (! value)
			throw I18nExceptionFactory.createIllegalStateException(messageKey);
	}

	/**
	 * 断言表达式结果为true
	 *
	 * @param value 表达式结果
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertTrue(boolean value, String messageKey, Object[] args) {
		if (! value)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言表达式结果为false
	 *
	 * @param value 表达式结果
	 */
	public static void assertFalse(boolean value) {
		assertFalse(value, "Assert.false");
	}

	/**
	 * 断言表达式结果为false
	 *
	 * @param value 表达式结果
	 * @param messageKey 错误信息key值
	 */
	public static void assertFalse(boolean value, String messageKey) {
		if (value)
			throw I18nExceptionFactory.createIllegalStateException(messageKey);
	}

	/**
	 * 断言表达式结果为false
	 *
	 * @param value 表达式结果
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertFalse(boolean value, String messageKey, Object[] args) {
		if (value)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言对象为空
	 *
	 * @param value 对象
	 */
	public static void assertNull(Object value) {
		assertNull(value, "Assert.null");
	}

	/**
	 * 断言对象为空
	 *
	 * @param value 对象
	 * @param messageKey 错误信息key值
	 */
	public static void assertNull(Object value, String messageKey) {
		assertNull(value, messageKey, new Object[]{value});
	}

	/**
	 * 断言对象为空
	 *
	 * @param value 对象
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNull(Object value, String messageKey, Object[] args) {
		if (value != null)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言对象不为空
	 *
	 * @param value 对象
	 */
	public static void assertNotNull(Object value) {
		assertNotNull(value, "Assert.not.null");
	}

	/**
	 * 断言对象不为空
	 *
	 * @param value 对象
	 * @param messageKey 错误信息key值
	 */
	public static void assertNotNull(Object value, String messageKey) {
		if (value == null)
			throw I18nExceptionFactory.createNullPointerException(messageKey);
	}

	/**
	 * 断言对象不为空
	 *
	 * @param value 对象
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNotNull(Object value, String messageKey, Object[] args) {
		if (value == null)
			throw I18nExceptionFactory.createNullPointerException(messageKey, args);
	}

	/**
	 * 断言字符串为空
	 *
	 * @param value 字符串
	 */
	public static void assertEmpty(String value) {
		assertEmpty(value, "Assert.empty");
	}

	/**
	 * 断言字符串为空
	 *
	 * @param value 字符串
	 * @param messageKey 错误信息key值
	 */
	public static void assertEmpty(String value, String messageKey) {
		assertEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言字符串为空
	 *
	 * @param value 字符串
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertEmpty(String value, String messageKey, Object[] args) {
		if (value != null && value.trim().length() != 0)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 */
	public static void assertEmpty(Collection value) {
		assertEmpty(value, "Assert.empty");
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 */
	public static void assertEmpty(Collection value, String messageKey) {
		assertEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertEmpty(Collection value, String messageKey, Object[] args) {
		if (value != null && value.size() != 0)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 */
	public static void assertEmpty(Map value) {
		assertEmpty(value, "Assert.empty");
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 */
	public static void assertEmpty(Map value, String messageKey) {
		assertEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertEmpty(Map value, String messageKey, Object[] args) {
		if (value != null && value.size() != 0)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 */
	public static void assertEmpty(Object[] value) {
		assertEmpty(value, "Assert.empty");
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 */
	public static void assertEmpty(Object[] value, String messageKey) {
		assertEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言集合为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertEmpty(Object[] value, String messageKey, Object[] args) {
		if (value != null && value.length != 0)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言字符串不为空
	 *
	 * @param value 字符串
	 */
	public static void assertNotEmpty(String value) {
		assertNotEmpty(value, "Assert.not.empty");
	}

	/**
	 * 断言字符串不为空
	 *
	 * @param value 字符串
	 * @param messageKey 错误信息key值
	 */
	public static void assertNotEmpty(String value, String messageKey) {
		assertNotEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言字符串不为空
	 *
	 * @param value 字符串
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNotEmpty(String value, String messageKey, Object[] args) {
		if (value == null || value.trim().length() == 0)
			throw I18nExceptionFactory.createIllegalArgumentException(messageKey, args);
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 */
	public static void assertNotEmpty(Collection value) {
		assertNotEmpty(value, "Assert.not.empty");
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 */
	public static void assertNotEmpty(Collection value, String messageKey) {
		assertNotEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNotEmpty(Collection value, String messageKey, Object[] args) {
		if (value == null || value.size() == 0)
			throw I18nExceptionFactory.createIllegalArgumentException(messageKey, args);
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 */
	public static void assertNotEmpty(Map value) {
		assertNotEmpty(value, "Assert.not.empty");
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 */
	public static void assertNotEmpty(Map value, String messageKey) {
		assertNotEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNotEmpty(Map value, String messageKey, Object[] args) {
		if (value == null || value.size() == 0)
			throw I18nExceptionFactory.createIllegalArgumentException(messageKey, args);
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 */
	public static void assertNotEmpty(Object[] value) {
		assertNotEmpty(value, "Assert.not.empty");
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 */
	public static void assertNotEmpty(Object[] value, String messageKey) {
		assertNotEmpty(value, messageKey, new Object[] {value});
	}

	/**
	 * 断言集合不为空
	 *
	 * @param value 集合
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertNotEmpty(Object[] value, String messageKey, Object[] args) {
		if (value == null || value.length == 0)
			throw I18nExceptionFactory.createIllegalArgumentException(messageKey, args);
	}

	/**
	 * 断言字符串包含子串
	 *
	 * @param value 字符串
	 * @param sub 子串
	 */
	public static void assertContain(String value, String sub) {
		assertContain(value, sub, "Assert.contain");
	}

	/**
	 * 断言字符串包含子串
	 *
	 * @param value 字符串
	 * @param sub 子串
	 * @param messageKey 错误信息key值
	 */
	public static void assertContain(String value, String sub, String messageKey) {
		assertContain(value, sub, messageKey, new Object[]{value, sub});
	}

	/**
	 * 断言字符串包含子串
	 *
	 * @param value 字符串
	 * @param sub 子串
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertContain(String value, String sub, String messageKey, Object[] args) {
		if (value == null || sub == null || value.indexOf(sub) == -1)
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言字符串匹配正则表达式
	 *
	 * @param value 字符串
	 * @param regex 正则表达式
	 */
	public static void assertMatches(String value, String regex) {
		assertMatches(value, regex, "Assert.matches");
	}

	/**
	 * 断言字符串匹配正则表达式
	 *
	 * @param value 字符串
	 * @param regex 正则表达式
	 * @param messageKey 错误信息key值
	 */
	public static void assertMatches(String value, String regex, String messageKey) {
		assertMatches(value, regex, messageKey, new Object[] {value, regex});
	}

	/**
	 * 断言字符串匹配正则表达式
	 *
	 * @param value 字符串
	 * @param regex 正则表达式
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertMatches(String value, String regex, String messageKey, Object[] args) {
		if (value == null || ! value.matches(regex))
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言可分派父子类关系
	 *
	 * @param superClass 父类
	 * @param subClass 子类
	 */
	public static void assertAssignable(Class superClass, Class subClass) {
		assertAssignable(superClass, subClass, "Assert.assignable");
	}

	/**
	 * 断言可分派父子类关系
	 *
	 * @param superClass 父类
	 * @param subClass 子类
	 * @param messageKey 错误信息key值
	 */
	public static void assertAssignable(Class superClass, Class subClass, String messageKey) {
		assertAssignable(superClass, subClass, messageKey, new Object[]{superClass, subClass});
	}

	/**
	 * 断言可分派父子类关系
	 *
	 * @param superClass 父类
	 * @param subClass 子类
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertAssignable(Class superClass, Class subClass, String messageKey, Object[] args) {
		if (superClass == null || ! superClass.isAssignableFrom(subClass))
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

	/**
	 * 断言类实例关系
	 *
	 * @param instance 实例
	 * @param type 类型
	 */
	public static void assertInstance(Object instance, Class type) {
		assertInstance(instance, type, "Assert.instance");
	}

	/**
	 * 断言类实例关系
	 *
	 * @param instance 实例
	 * @param type 类型
	 * @param messageKey 错误信息key值
	 */
	public static void assertInstance(Object instance, Class type, String messageKey) {
		assertInstance(instance, type, messageKey, new Object[]{type, instance});
	}

	/**
	 * 断言类实例关系
	 *
	 * @param instance 实例
	 * @param type 类型
	 * @param messageKey 错误信息key值
	 * @param args 错误信息参数
	 */
	public static void assertInstance(Object instance, Class type, String messageKey, Object[] args) {
		if (type == null || ! type.isInstance(instance))
			throw I18nExceptionFactory.createIllegalStateException(messageKey, args);
	}

}
