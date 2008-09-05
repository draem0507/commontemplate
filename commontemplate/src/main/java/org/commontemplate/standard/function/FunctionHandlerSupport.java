package org.commontemplate.standard.function;

import java.io.Serializable;
import java.util.List;

import org.commontemplate.util.ArgumentUtils;

public abstract class FunctionHandlerSupport implements FunctionHandler, Serializable {

	public Object doFunction(Object bean, Object argument) throws Exception {
		return doFunction(bean, ArgumentUtils.getArgumentList(argument));
	}

	public abstract Object doFunction(Object bean, List args) throws Exception;
}
