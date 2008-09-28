package org.commontemplate.standard.operator.filter;

import java.io.Serializable;
import java.util.Map;

import org.commontemplate.core.Expression;
import org.commontemplate.engine.expression.ExpressionContext;

/**
 * 过滤器条件封装类<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class Filter implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Expression expression;

	private final String name;

	public Filter(Expression expression) {
		this(null, expression);
	}

	public Filter(String name, Expression expression) {
		super();
		this.name = name;
		this.expression = expression;
	}

	public final String getName() {
		return name;
	}

	public final Expression getExpression() {
		return expression;
	}

	public Object filter(Map variables) {
		ExpressionContext context = new ExpressionContext();
		context.putAll(variables);
		return expression.evaluate(context);
	}

}
