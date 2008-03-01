package org.commontemplate.standard.function;

import java.util.List;

public interface FunctionHandler {
	
	public Object handleFunction(Object bean, List args) throws Exception;

}
