package org.commontemplate.standard.operator;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;

public abstract class BinaryOperatorHandlerTester extends TestCase {

	protected BinaryOperatorHandler binaryOperatorHandler;

	public abstract void setUp() throws Exception;

	public void tearDown() throws Exception {
		super.tearDown();
		binaryOperatorHandler = null;
	}

	protected void assertEvaluation(Object leftOperand, Object rightOperand, Object result) throws Exception {
		assertEvaluation("表达式求值结果错误！", leftOperand, rightOperand, result);
	}

	protected void assertEvaluation(String message, Object leftOperand, Object rightOperand, Object result) throws Exception {
		if (binaryOperatorHandler == null)
			throw new NullPointerException("测试用例: " + getClass().getName() + "，未初始化binaryOperatorHandler！请覆写setUp()方法进行初始化！");
		assertEquals(message, result, binaryOperatorHandler.doEvaluate(leftOperand, rightOperand));
	}

	protected void assertUnhandle(Object leftOperand, Object rightOperand) throws Exception {
		assertUnhandle("传入错误类型的参数，应该抛出异常！", leftOperand, rightOperand);
	}

	protected void assertUnhandle(String message, Object leftOperand, Object rightOperand) throws Exception {
		try {
			binaryOperatorHandler.doEvaluate(leftOperand, rightOperand);
			fail(message);
		} catch (UnhandleException e) {
			// right
		}
	}

}
