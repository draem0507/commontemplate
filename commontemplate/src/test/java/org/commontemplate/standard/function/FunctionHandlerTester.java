package org.commontemplate.standard.function;

import java.util.List;

import junit.framework.TestCase;

public abstract class FunctionHandlerTester extends TestCase {

	private FunctionHandlerSupport functionHandler;
	
	protected abstract FunctionHandlerSupport newFunctionHandlerSupport();
	
	public void setUp() throws Exception {
		functionHandler = newFunctionHandlerSupport();
	}
	
	public void tearDown() throws Exception {
		super.tearDown();
		functionHandler = null;
	}

	public FunctionHandlerSupport getFunctionHandler() {
		return functionHandler;
	}
	
	/**
	 * 断言运算结果是否正确
	 *
	 * @param bean Object
	 * @param args 参数
	 * @param result 断言结果
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertEvaluation(Object bean, List args, Object result) throws Exception {
		assertEvaluation(bean, args, result, "表达式求值结果错误！");
	}

	/**
	 * 断言运算结果是否正确, 并指定出错信息
	 *
	 * @param bean Object
	 * @param args 参数
	 * @param result 断言结果
	 * @param message 出错信息
	 * @throws Exception 任意异常, 不应捕获, 由JUnit处理.
	 */
	protected void assertEvaluation(Object bean, List args, Object result, String message) throws Exception {
		if (functionHandler == null)
			throw new NullPointerException("测试用例: " + getClass().getName() + "，未初始化functionHandler！请覆写newFunctionHandlerSupport()方法进行初始化！");
		
		assertEquals(message, result, functionHandler.doFunction(bean, args));
	}
}
