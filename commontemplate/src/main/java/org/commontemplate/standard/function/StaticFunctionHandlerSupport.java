package org.commontemplate.standard.function;

import java.io.Serializable;
import java.util.List;

import org.commontemplate.util.ArgumentUtils;

public abstract class StaticFunctionHandlerSupport implements StaticFunctionHandler, Serializable {

	private static final long serialVersionUID = 1L;
	
	public Object doFunction(Object argument) throws Exception {
		return doFunction(ArgumentUtils.getArgumentList(argument));
	}

	public abstract Object doFunction(List args) throws Exception;
}
