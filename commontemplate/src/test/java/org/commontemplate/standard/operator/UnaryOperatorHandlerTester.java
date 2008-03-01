package org.commontemplate.standard.operator;

import junit.framework.TestCase;

import org.commontemplate.config.UnaryOperatorHandler;

public abstract class UnaryOperatorHandlerTester extends TestCase {

	protected UnaryOperatorHandler unaryOperatorHandler;

	public abstract void setUp() throws Exception;

	public void tearDown() throws Exception {
		super.tearDown();
		unaryOperatorHandler = null;
	}

	protected void assertEvaluation(Object operand, Object result) throws Exception {
		assertEvaluation("表达式求值结果错误！", operand, result);
	}

	protected void assertEvaluation(String message, Object operand, Object result) throws Exception {
		if (unaryOperatorHandler == null)
			throw new NullPointerException("测试用例: " + getClass().getName() + "，未初始化unaryOperatorHandler！请覆写setUp()方法进行初始化！");
		assertEquals(message, result, unaryOperatorHandler.doEvaluate(operand));
	}

	protected void assertUnhandle(Object operand) throws Exception {
		assertUnhandle("传入错误类型的参数，应该抛出异常！", operand);
	}

	protected void assertUnhandle(String message, Object operand) throws Exception {
		try {
			unaryOperatorHandler.doEvaluate(operand);
			fail(message);
		} catch (UnhandleException e) {
			// right
		}
	}

}
