package org.commontemplate.standard.operator;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;

public abstract class BinaryOperatorHandlerTester extends TestCase {

	/**
	 * 操作符供给模板方法
	 * 
	 * @return 待测试的操作符
	 */
	protected abstract BinaryOperatorHandler newBinaryOperatorHandler();

	public void setUp() throws Exception {
		binaryOperatorHandler = newBinaryOperatorHandler();
	}

	public void tearDown() throws Exception {
		super.tearDown();
		binaryOperatorHandler = null;
	}

	private BinaryOperatorHandler binaryOperatorHandler;

	/**
	 * 获取当前待测试的操作符
	 * 
	 * @return 当前待测试的操作符
	 */
	protected BinaryOperatorHandler getBinaryOperatorHandler() {
		return binaryOperatorHandler;
	}

	/**
	 * 断言运算结果是否正确
	 * 
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @param result 断言结果
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertEvaluation(Object leftOperand, Object rightOperand, Object result) throws Exception {
		assertEvaluation(leftOperand, rightOperand, result, "表达式求值结果错误！");
	}

	/**
	 * 断言运算结果是否正确, 并指定出错信息
	 * 
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @param result 断言结果
	 * @param message 出错信息
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertEvaluation(Object leftOperand, Object rightOperand, Object result, String message) throws Exception {
		if (binaryOperatorHandler == null)
			throw new NullPointerException("测试用例: " + getClass().getName() + "，未初始化binaryOperatorHandler！请覆写setUp()方法进行初始化！");
		assertEquals(message, result, binaryOperatorHandler.doEvaluate(leftOperand, rightOperand));
	}

	/**
	 * 传入非法类型，断言运算操作符未处理
	 * 
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertUnhandle(Object leftOperand, Object rightOperand) throws Exception {
		assertUnhandle(leftOperand, rightOperand, "传入错误类型的参数，应该抛出异常！");
	}

	/**
	 * 传入非法类型，断言运算操作符未处理, 并指定出错信息
	 * 
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 * @param message 出错信息
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertUnhandle(Object leftOperand, Object rightOperand, String message) throws Exception {
		try {
			binaryOperatorHandler.doEvaluate(leftOperand, rightOperand);
			fail(message);
		} catch (UnhandleException e) {
			// right
		}
	}

}
