package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.VariableException;
import org.commontemplate.core.VariableResolver;

/**
 * 简单表达式变量上下文实现
 *
 * @author liangfei0201@163.com
 *
 */
public class SimpleExpressionContext implements VariableResolver {

	public SimpleExpressionContext() {

	}

	public Object getVariable(String name) throws VariableException {
		return variables.get(name);
	}

	private final Map variables = new HashMap();

	public void putVariable(String name, Object value) {
		variables.put(name, value);
	}

	public void putAllVariables(Map map) {
		variables.putAll(map);
	}

	public void removeVariable(String name) {
		variables.remove(name);
	}

	public void clearVariables() {
		variables.clear();
	}

}
