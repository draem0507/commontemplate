package org.commontemplate.standard.operator;

import junit.framework.TestCase;

import org.commontemplate.config.UnaryOperatorHandler;

/**
 * 一元操作符测试用例基类
 * 所有的一元操作符测试用例均继承于此类.
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class UnaryOperatorHandlerTester extends TestCase {

	/**
	 * 操作符供给模板方法
	 *
	 * @return 待测试的操作符
	 */
	protected abstract UnaryOperatorHandler newUnaryOperatorHandler();

	public void setUp() throws Exception {
		unaryOperatorHandler = newUnaryOperatorHandler();
	}

	public void tearDown() throws Exception {
		super.tearDown();
		unaryOperatorHandler = null;
	}

	private UnaryOperatorHandler unaryOperatorHandler;

	/**
	 * 获取待测试的一元操作符
	 *
	 * @return 一元操作符
	 */
	protected UnaryOperatorHandler getUnaryOperatorHandler() {
		return unaryOperatorHandler;
	}

	/**
	 * 断言运算结果是否正确
	 *
	 * @param operand 操作数
	 * @param result 断言结果
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertEvaluation(Object operand, Object result) throws Exception {
		assertEvaluation(operand, result, "表达式求值结果错误！");
	}

	/**
	 * 断言运算结果是否正确, 并指定出错信息
	 *
	 * @param operand 操作数
	 * @param result 断言结果
	 * @param message 出错信息
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertEvaluation(Object operand, Object result, String message) throws Exception {
		if (unaryOperatorHandler == null)
			throw new NullPointerException("测试用例: " + getClass().getName() + "，未初始化unaryOperatorHandler！请覆写newUnaryOperatorHandler()方法进行初始化！");
		if (unaryOperatorHandler instanceof UnaryOperatorHandlerMatcher)
			assertTrue(((UnaryOperatorHandlerMatcher)unaryOperatorHandler).isMatch(operand));
		assertEquals(message, result, unaryOperatorHandler.doEvaluate(operand));
	}

}
