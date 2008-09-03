package org.commontemplate.core;

/**
 * 停止访问异常
 *
 * @see org.commontemplate.core.ExpressionVisitor
 * @see org.commontemplate.core.TemplateVisitor
 * @author liangfei0201@163.com
 *
 */
public class StopVisitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StopVisitException() {
		super();
	}

}
