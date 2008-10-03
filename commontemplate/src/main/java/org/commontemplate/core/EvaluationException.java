package org.commontemplate.core;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.commontemplate.util.I18nRuntimeException;

/**
 * 表达式异常，表达式中的异常均被封装成此异常抛出
 *
 * @author liangfei0201@163.com
 *
 */
public class EvaluationException extends I18nRuntimeException {

	private static final long serialVersionUID = 1L;

	private final Expression expression;

	public EvaluationException(Expression expression) {
		super();
		this.expression = expression;
		this.rootExpression = expression;
	}

	public EvaluationException(Expression expression, String message) {
		super(message);
		this.expression = expression;
		this.rootExpression = expression;
	}

	public EvaluationException(Expression expression, String message, Object[] args) {
		super(message, args);
		this.expression = expression;
		this.rootExpression = expression;
	}

	public EvaluationException(Expression expression, Throwable cause) {
		super(cause.getMessage(), cause);
		this.expression = expression;
		this.rootExpression = expression;
	}

	public EvaluationException(Expression expression, String message,
			Throwable cause) {
		super(message, cause);
		this.expression = expression;
		this.rootExpression = expression;
	}

	public EvaluationException(Expression expression, String message, Object[] args,
			Throwable cause) {
		super(message, args, cause);
		this.expression = expression;
		this.rootExpression = expression;
	}

	private Expression rootExpression;

	public Expression getRootExpression() {
		return rootExpression;
	}

	public void setRootExpression(Expression rootExpression) {
		this.rootExpression = rootExpression;
	}

	public Expression getExpression() {
		return expression;
	}

	public void printStackTrace(PrintStream s) {
		// printStackTrace(new PrintWriter(new OutputStreamWriter(s)));
		s.println();
		s.println("[commontemplate] Error occur to Expression: " + (rootExpression == null ? null : rootExpression.getSource())); // TODO 未国际化
		s.println("[commontemplate] Error occur to Location: " + (expression == null ? null : expression.getLocation())); // TODO 未国际化
		s.println("[commontemplate] Error occur to Operator: " + (expression == null ? null : expression.getName())); // TODO 未国际化
		s.println("[commontemplate] Error Message: " + getMessage()); // TODO 未国际化
		s.println("[commontemplate] Error Stack: ");
		super.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		s.println();
		s.println();
		s.println("[commontemplate] Error occur to Expression: " + (expression == null ? null : expression.getSource())); // TODO 未国际化
		s.println("[commontemplate] Error occur to Location: " + (expression == null ? null : expression.getLocation())); // TODO 未国际化
		s.println("[commontemplate] Error occur to Operator: " + (expression == null ? null : expression.getName())); // TODO 未国际化
		s.println("[commontemplate] Error Message: " + getMessage()); // TODO 未国际化
		s.println("[commontemplate] Error Stack: ");
		super.printStackTrace(s);
	}

}
