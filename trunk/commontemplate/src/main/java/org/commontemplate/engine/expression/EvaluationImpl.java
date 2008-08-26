package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.config.EvaluateInterceptor;
import org.commontemplate.config.Evaluation;
import org.commontemplate.core.Expression;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.I18nExceptionFactory;

final class EvaluationImpl extends Evaluation {

	private final Expression expression;

	private final VariableResolver variableResolver;

	private final List evaluateInterceptors;

	private boolean evaluated = false;

	private int index = 0;

	EvaluationImpl(Expression expression, VariableResolver variableResolver, List evaluateInterceptors) {
		this(expression, variableResolver, evaluateInterceptors, 0);
	}

	private EvaluationImpl(Expression expression, VariableResolver variableResolver, List evaluateInterceptors, int index) {
		this.expression = expression;
		this.variableResolver = variableResolver;
		this.evaluateInterceptors = evaluateInterceptors;
		this.index = index;
	}

	public Expression getExpression() {
		return expression;
	}

	public VariableResolver getVariableResolver() {
		return variableResolver;
	}

	public boolean isEvaluated() {
		return evaluated;
	}

	public Object doEvaluate() {
		// 查找下一个不为空的拦截器
		EvaluateInterceptor evaluateInterceptor = null;
		int i = index;
		if (evaluateInterceptors != null) {
			while (evaluateInterceptor == null && i < evaluateInterceptors.size()) {
				evaluateInterceptor = (EvaluateInterceptor)evaluateInterceptors.get(i);
				i ++;
			}
		}
		// 如果存在下一个不为空的拦截器， 则执行它
		if (evaluateInterceptor != null) {
			return evaluateInterceptor.intercept(new EvaluationImpl(expression, variableResolver, evaluateInterceptors, i));
		} else { // 否则运行正常逻辑
			evaluated = true;
			if (expression instanceof BinaryOperatorProxy)
				return ((BinaryOperatorProxy)expression).getTarget().doEvaluate(variableResolver);
			else if (expression instanceof UnaryOperatorProxy)
				return ((UnaryOperatorProxy)expression).getTarget().doEvaluate(variableResolver);
			else if (expression instanceof VariableProxy)
				return ((VariableProxy)expression).getTarget().doEvaluate(variableResolver);
			else if (expression instanceof ConstantProxy)
				return ((ConstantProxy)expression).getTarget().doEvaluate(variableResolver);
			throw I18nExceptionFactory.createIllegalStateException("表达式代理类型出错!"); // TODO 未国际化
		}
	}
}
