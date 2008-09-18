package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.StopVisitException;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.util.Assert;
import org.commontemplate.util.Location;

/**
 * 二元操作符实现类
 *
 * @author liangfei0201@163.com
 *
 */
final class BinaryOperatorImpl extends BinaryOperator {

	private static final long serialVersionUID = 1L;

	private final String name;

	private final Location location;

	private final int priority;

	private final BinaryOperatorHandler handler;

	private final boolean rightToLeft;

	private final boolean leftOperandLazy;

	private final boolean rightOperandLazy;

	private final boolean leftOperandNamed;

	private final boolean rightOperandNamed;

	private final boolean rightOperandDotNamed;

	private final boolean rightOperandFunctioned;

	private final boolean associative;

	private final boolean commutative;

	private final List evaluateInterceptors;

	private final BinaryOperator proxy;

	BinaryOperatorImpl(String name, Location location, int priority, BinaryOperatorHandler handler, List evaluateInterceptors) {
		Assert.assertNotNull(handler);
		this.name = name;
		this.location = location;
		this.priority = priority;
		this.handler = handler;
		this.rightToLeft = handler.isRightToLeft();
		this.leftOperandLazy = handler.isLeftOperandLazy();
		this.rightOperandLazy = handler.isRightOperandLazy();
		this.leftOperandNamed = handler.isLeftOperandNamed();
		this.rightOperandNamed = handler.isRightOperandNamed();
		this.rightOperandDotNamed = handler.isRightOperandDotNamed();
		this.rightOperandFunctioned = handler.isRightOperandFunctioned();
		this.associative = handler.isAssociative();
		this.commutative = handler.isCommutative();
		this.evaluateInterceptors = evaluateInterceptors;
		this.proxy = new BinaryOperatorProxy(this);
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		if (evaluateInterceptors != null && evaluateInterceptors.size() > 0)
			return new EvaluationImpl(proxy, context, evaluateInterceptors).doEvaluate();
		else
			return doEvaluate(context);
	}

	Object doEvaluate(VariableResolver context) throws EvaluationException {
		try {
			Object left;
			if (leftOperandLazy)
				left = new LazyOperandImpl(getLeftOperand(), context);
			else
				left = getLeftOperand().evaluate(context);
			Object right;
			if (rightOperandLazy)
				right = new LazyOperandImpl(getRightOperand(), context);
			else
				right = getRightOperand().evaluate(context);
			return handler.doEvaluate(left, right);
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

	boolean isOptimize(Object leftOperand, Object rightOperand) {
		return handler.isOptimize(leftOperand, rightOperand);
	}

	boolean isAssociative() {
		return associative;
	}

	boolean isCommutative() {
		return commutative;
	}

	boolean isRightToLeft() {
		return rightToLeft;
	}

	boolean isLeftOperandNamed() {
		return leftOperandNamed;
	}

	boolean isRightOperandNamed() {
		return rightOperandNamed;
	}

	boolean isRightOperandDotNamed() {
		return rightOperandDotNamed;
	}

	boolean isRightOperandFunctioned() {
		return rightOperandFunctioned;
	}

	public String getSource() {
		Expression left = getLeftOperand();
		String leftPrototype = left.getSource();
		if (left instanceof BinaryOperator && ((BinaryOperator)left).getPriority() < getPriority()) {
			leftPrototype = "(" + leftPrototype + ")";
		}
		Expression right = getRightOperand();
		String rightPrototype = right.getSource();
		if (right instanceof BinaryOperator && ((BinaryOperator)right).getPriority() < getPriority()) {
			rightPrototype = "(" + rightPrototype + ")";
		}
		return leftPrototype + " " + getName() + " " + rightPrototype;
	}

	private List operands;

	public List getOperands() {
		return operands;
	}

	private Expression leftOperand;

	public Expression getLeftOperand() {
		return leftOperand;
	}

	private Expression rightOperand;

	public Expression getRightOperand() {
		return rightOperand;
	}

	/**
	 * 设置二元操作符的操作数
	 *
	 * @param leftOperand 左操作数
	 * @param rightOperand 右操作数
	 */
	void setOperands(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		List l = new ArrayList(2);
		l.add(leftOperand);
		l.add(rightOperand);
		operands = java.util.Collections.unmodifiableList(l);
	}

	public void accept(ExpressionVisitor visitor, boolean isEnter) {
		try {
			getLeftOperand().accept(visitor, false);
			visitor.visitBinaryOperator(this);
			getRightOperand().accept(visitor, false);
		} catch (StopVisitException e) {
			if (! isEnter)
				throw e;
		}
	}

}