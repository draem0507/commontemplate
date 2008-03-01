package org.commontemplate.core;

/**
 * 表达式异常，表达式中的异常均被封装成此异常抛出
 * 
 * @author liangfei0201@163.com
 * 
 */
public class EvaluationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Expression expression;

	public EvaluationException(Expression expression) {
		super();
		this.expression = expression;
	}

	public EvaluationException(Expression expression, String message) {
		super(message);
		this.expression = expression;
	}

	public EvaluationException(Expression expression, Throwable cause) {
		super(cause);
		this.expression = expression;
	}

	public EvaluationException(Expression expression, String message,
			Throwable cause) {
		super(message, cause);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

}
