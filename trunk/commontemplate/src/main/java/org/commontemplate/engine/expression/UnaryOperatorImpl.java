package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.StopVisitException;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Assert;
import org.commontemplate.util.Location;

/**
 * 一元操作符实现
 *
 * @author liangfei0201@163.com
 *
 */
final class UnaryOperatorImpl extends UnaryOperator {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Location location;

	private final int priority;

	private final UnaryOperatorHandler handler;

	private final boolean operandLazy;

	private final boolean operandNamed;

	private final boolean operandDotNamed;

	private final boolean operandFunctioned;

	private final List evaluateInterceptors;

	private final UnaryOperator proxy;

	UnaryOperatorImpl(String name, Location location, int priority, UnaryOperatorHandler handler, List evaluateInterceptors) {
		Assert.assertNotNull(handler);
		this.name = name;
		this.location = location;
		this.priority = priority;
		this.handler  = handler;
		this.operandLazy  = handler.isOperandLazy();
		this.operandNamed  = handler.isOperandNamed();
		this.operandDotNamed  = handler.isOperandDotNamed();
		this.operandFunctioned = handler.isOperandFunctioned();
		this.evaluateInterceptors = evaluateInterceptors;
		this.proxy = new UnaryOperatorProxy(this);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		if (evaluateInterceptors != null && evaluateInterceptors.size() > 0)
			return new EvaluationImpl(proxy, context, evaluateInterceptors).doEvaluate();
		else
			return doEvaluate(context);
	}

	Object doEvaluate(VariableResolver context) throws EvaluationException {
		try {
			Object operand;
			if (operandLazy)
				operand = new LazyOperandImpl(getOperand(), context);
			else
				operand = getOperand().evaluate(context);
			return handler.doEvaluate(operand);
		} catch (EvaluationException e) {
			throw e;
		} catch (Exception e) {
			throw new EvaluationException(this, e);
		}
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public int getPriority() {
		return priority;
	}

	boolean isOperandNamed() {
		return operandNamed;
	}

	boolean isOperandDotNamed() {
		return operandDotNamed;
	}

	public boolean isOperandFunctioned() {
		return operandFunctioned;
	}

	private Expression operand;

	public Expression getOperand() {
		return operand;
	}

	private List operands;

	public List getOperands() {
		return operands;
	}

	public String getSource() {
		return getName() + " " + getOperand().getSource();
	}

	/**
	 * 设置一元操作符的操作数
	 *
	 * @param operand 操作数
	 */
	void setOperand(Expression operand) {
		this.operand = operand;
		List list = new ArrayList(1);
		list.add(operand);
		operands = java.util.Collections.unmodifiableList(list);
	}

	public void accept(ExpressionVisitor visitor, boolean isEnter) {
		try {
			visitor.visitUnaryOperator(this);
			this.getOperand().accept(visitor, false);
		} catch (StopVisitException e) {
			if (! isEnter)
				throw e;
		}
	}

}