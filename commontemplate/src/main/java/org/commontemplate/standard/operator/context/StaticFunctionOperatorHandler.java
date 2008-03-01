package org.commontemplate.standard.operator.context;

import java.util.Map;

import org.commontemplate.standard.function.StaticFunctionHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.Function;

public class StaticFunctionOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StaticFunctionOperatorHandler() {
		super(Function.class);
	}

	private Map functionHandlers;
	
	/**
	 * 设置全局属性
	 * 
	 * @param functionHandlers Map<String, StaticFunctionHandler>
	 */
	public void setStaticFunctionHandlers(Map functionHandlers) {
		this.functionHandlers = functionHandlers;
	}

	public Object doEvaluate(Object operand) throws Exception {
		Function function = (Function)operand;
		if (functionHandlers != null) {
			StaticFunctionHandler handler = (StaticFunctionHandler)functionHandlers.get(function.getName());
			if (handler != null)
				return handler.handleFunction(function.getArguments());
		}
		throw new RuntimeException("未找到静态方法:" + function.getName());
	}


}