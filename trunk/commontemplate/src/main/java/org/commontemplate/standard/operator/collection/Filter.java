package org.commontemplate.standard.operator.collection;

import java.util.Map;

import org.commontemplate.core.Expression;

/**
 * 过滤器条件封装类<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class Filter {

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
		SimpleExpressionContext context = new SimpleExpressionContext();
		context.putAllVariables(variables);
		return expression.evaluate(context);
	}

}
