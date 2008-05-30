package org.commontemplate.standard.function;

import java.util.List;

public interface FunctionHandler {
	
	public Object doFunction(Object bean, List args) throws Exception;

}
