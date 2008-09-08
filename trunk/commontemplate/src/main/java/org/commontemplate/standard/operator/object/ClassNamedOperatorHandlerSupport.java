package org.commontemplate.standard.operator.object;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;

public abstract class ClassNamedOperatorHandlerSupport extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassNamedOperatorHandlerSupport(Class operandClass) {
		super(operandClass);
	}

	private BinaryOperatorHandler dotBinaryOperatorHandler;

	public BinaryOperatorHandler getDotBinaryOperatorHandler() {
		return dotBinaryOperatorHandler;
	}

	public void setDotBinaryOperatorHandler(
			BinaryOperatorHandler dotBinaryOperatorHandler) {
		this.dotBinaryOperatorHandler = dotBinaryOperatorHandler;
	}

	/**
	 * 处理属性
	 * @param propertyName 属性或类名
	 * @return 类元或类静态属性值
	 * @throws Exception 类或属性不存在时抛出
	 */
	public Object doEvaluateProperties(String propertyName) throws Exception {
		String className = propertyName;
		Class clazz = ClassUtils.safeForName(className);
		Stack properties = new LinkedStack();
		int index;
		while (clazz == null
				&& (index = className.lastIndexOf('.')) != -1) {
			properties.push(className.substring(index + 1));
			className = className.substring(0, index);
			clazz = ClassUtils.safeForName(className);
		}
		Assert.assertNotNull(clazz, "不存在类:{0}", new Object[]{propertyName});
		Object result = convertResult(clazz);
		while (! properties.isEmpty()) {
			result = dotBinaryOperatorHandler.doEvaluate(result, properties.pop());
		}
		return result;
	}

	/**
	 * 处理函数
	 * @param function 函数
	 * @return 函数值
	 * @throws Exception 类或属性不存在时抛出
	 */
	public Object doEvaluateFunction(Function function) throws Exception {
		String name = function.getName();
		int index = name.lastIndexOf('.');
		Assert.assertTrue(index != -1, "不存在类:{0}", new Object[]{name});
		String propertyName = name.substring(0, index); // 最点号前的作为类名或静态属性名
		String className = propertyName;
		Function callFunction = new Function(name.substring(index + 1), function.getArgument()); // 将点号最后一截作为静态函数名
		Class clazz = ClassUtils.safeForName(className);
		Stack properties = new LinkedStack();
		while (clazz == null
				&& (index = className.lastIndexOf('.')) != -1) {
			properties.push(className.substring(index + 1));
			className = className.substring(0, index);
			clazz = ClassUtils.safeForName(className);
		}
		Assert.assertNotNull(clazz, "不存在类:{0}", new Object[]{propertyName});
		Object result = convertResult(clazz);
		while (! properties.isEmpty()) {
			result = dotBinaryOperatorHandler.doEvaluate(result, properties.pop());
		}
		return dotBinaryOperatorHandler.doEvaluate(result, callFunction);
	}

	/**
	 * 模板方法, 转换类元处理.
	 * @param clazz 类元
	 * @return 转换后的结果
	 */
	protected Object convertResult(Class clazz) throws Exception {
		return clazz;
	}

}