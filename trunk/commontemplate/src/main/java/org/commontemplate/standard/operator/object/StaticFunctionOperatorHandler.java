package org.commontemplate.standard.operator.object;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.function.FunctionMatcher;
import org.commontemplate.standard.function.StaticFunctionHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.UnhandleException;
import org.commontemplate.util.ArgumentUtils;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.Function;

public class StaticFunctionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StaticFunctionOperatorHandler() {
		super(Class.class, Function.class);
	}

	private Map functionHandlers;

	/**
	 * 设置全局属性
	 *
	 * @param handlers Map<String, StaticFunctionHandler>
	 */
	public void setStaticFunctionHandlers(Map handlers) {
		this.functionHandlers = new HashMap(handlers.size());
		for (Iterator iterator = handlers.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry)iterator.next();
			functionHandlers.put(new FunctionMatcher((String)entry.getKey()), entry.getValue());
		}
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Class clazz = (Class)leftOperand;
		Function function = (Function)rightOperand;
		String name = function.getName();
		Object argument = function.getArgument();
		if (functionHandlers != null) {
			for (Iterator iterator = functionHandlers.entrySet().iterator(); iterator.hasNext();) {
				Entry entry = (Entry)iterator.next();
				if (((FunctionMatcher)entry.getKey()).isMatch(leftOperand.getClass(), name)) {
					return ((StaticFunctionHandler)entry.getValue()).doFunction(clazz, argument);
				}
			}
		}
		try {
			return ClassUtils.invokeStaticMethod(clazz, name, ArgumentUtils.getArgumentArray(argument));
		} catch (NoSuchMethodException e) {
			throw new UnhandleException("ObjectFunctionOperatorHandler.function.no.such", new Object[]{leftOperand.getClass().getName(), name});
		}
	}


}
