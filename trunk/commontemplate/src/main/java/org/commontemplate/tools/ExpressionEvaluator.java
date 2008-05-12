package org.commontemplate.tools;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.ExpressionConfiguration;
import org.commontemplate.core.VariableException;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.engine.expression.ExpressionEngine;

/**
 * 表达式求值工具
 * 
 * <p/>
 * 使用示例:
 * <pre>
 * Object result = new ExpressionEvaluator("book.price * discount + 1").put("book", new Book()).put("discount", 0.8).evaluate();
 * </pre>
 * 
 * <p/>
 * 配置初始化:
 * <pre>
 * ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("expression", PropertiesConfigurationLoader.loadExpressionConfiguration("xxx.properties"));
 * </pre>
 * 
 * @see org.commontemplate.tools.PropertiesConfigurationLoader#loadExpressionConfiguration
 * @author liangfei0201@163.com
 *
 */
public class ExpressionEvaluator implements VariableResolver {
	
	private final ExpressionEngine expressionEngine;
	
	private final String expr;
	
	public ExpressionEvaluator(String expr) {
		this(expr, PropertiesConfigurationLoader.loadStandardExpressionConfiguration());
	}

	public ExpressionEvaluator(String expr, ExpressionConfiguration config) {
		this.expr = expr;
		this.expressionEngine = new ExpressionEngine(config);
	}

	/**
	 * 表达式求值
	 * 
	 * @return 求值结果
	 */
	public Object evaluate() {
		return expressionEngine.parseExpression(expr).evaluate(this);
	}
	
	// 适配VariableResolver ---------

	public Object getVariable(String name) throws VariableException {
		return variables.get(name);
	}
	
	// 变量设置管理接口 -----------
	
	private final Map variables = new HashMap();

	public Object get(String key) {
		return variables.get(key);
	}
	
	public void remove(String key) {
		variables.remove(key);
	}
	
	public void clear() {
		variables.clear();
	}
	
	// ==== 注：以下函数为了调用者方便，采用链式调用风格，违背“契约式设计原则”，按原则应为void返回类型 ====

	public ExpressionEvaluator putAll(Map values) {
		variables.putAll(values);
		return this;
	}
	
	public ExpressionEvaluator put(String key, Object value) {
		variables.put(key, value);
		return this;
	}
	
	public ExpressionEvaluator put(String key, char value) {
		variables.put(key, new Character(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, boolean value) {
		variables.put(key, Boolean.valueOf(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, byte value) {
		variables.put(key, new Byte(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, short value) {
		variables.put(key, new Short(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, int value) {
		variables.put(key, new Integer(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, long value) {
		variables.put(key, new Long(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, float value) {
		variables.put(key, new Float(value));
		return this;
	}
	
	public ExpressionEvaluator put(String key, double value) {
		variables.put(key, new Double(value));
		return this;
	}
}
