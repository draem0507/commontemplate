package org.commontemplate.standard.operator.context;

import java.util.Map;

import org.commontemplate.standard.function.SystemFunctionHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.Function;

/**
 * 系统方法一元操作符: "."<br/>
 * 如: ${.xxx()}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class SystemFunctionOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public SystemFunctionOperatorHandler() {
		super(Function.class);
	}

	private Map functionHandlers;

	/**
	 * 设置全局属性
	 *
	 * @param functionHandlers Map<String, SystemFunctionHandler>
	 */
	public void setSystemFunctionHandlers(Map functionHandlers) {
		this.functionHandlers = functionHandlers;
	}

	public Object doEvaluate(Object operand) throws Exception {
		Function function = (Function)operand;
		if (functionHandlers != null) {
			SystemFunctionHandler handler = (SystemFunctionHandler)functionHandlers.get(function.getName());
			if (handler != null)
				return handler.doFunction(function.getArgument());
		}
		return null;
	}

}