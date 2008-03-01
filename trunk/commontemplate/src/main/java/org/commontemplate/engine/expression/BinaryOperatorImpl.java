package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.config.SpecialBinaryOperatorHandler;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Expression;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.core.Visitor;
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

	private final boolean associativeLaw;
	
	private final boolean commutativeLaw;
	
	private final boolean rightToLeft;

	private final boolean leftOperandLazy;
	
	private final boolean rightOperandLazy;
	
	private final boolean leftOperandNamed;
	
	private final boolean rightOperandNamed;
	
	private final boolean rightOperandFunctioned;

	BinaryOperatorImpl(String name, Location location, int priority, BinaryOperatorHandler handler) {
		Assert.assertNotNull(handler);
		this.name = name;
		this.location = location;
		this.priority = priority;
		this.handler = handler;
		if (handler instanceof SpecialBinaryOperatorHandler) {
			SpecialBinaryOperatorHandler shandler = (SpecialBinaryOperatorHandler) handler;
			this.rightToLeft = shandler.isRightToLeft();
			this.leftOperandLazy = shandler.isLeftOperandLazy();
			this.rightOperandLazy = shandler.isRightOperandLazy();
			this.leftOperandNamed = shandler.isLeftOperandNamed();
			this.rightOperandNamed = shandler.isRightOperandNamed();
			this.rightOperandFunctioned = shandler.isRightOperandFunctioned();
			this.associativeLaw = shandler.isAssociativeLaw();
			this.commutativeLaw = shandler.isCommutativeLaw();
		} else {
			this.rightToLeft = false;
			this.leftOperandLazy = false;
			this.rightOperandLazy = false;
			this.leftOperandNamed = false;
			this.rightOperandNamed = false;
			this.rightOperandFunctioned = false;
			this.associativeLaw = false;
			this.commutativeLaw = false;
		}
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
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
	
	boolean isAssociativeLaw() {
		return associativeLaw;
	}
	
	boolean isCommutativeLaw() {
		return commutativeLaw;
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
	
	public boolean isRightOperandFunctioned() {
		return rightOperandFunctioned;
	}
	
	public String getCanonicalForm() {
		Expression left = getLeftOperand();
		String leftPrototype = left.getCanonicalForm();
		if (left instanceof BinaryOperator && ((BinaryOperator)left).getPriority() < getPriority()) {
			leftPrototype = "(" + leftPrototype + ")";
		}
		Expression right = getRightOperand();
		String rightPrototype = right.getCanonicalForm();
		if (right instanceof BinaryOperator && ((BinaryOperator)right).getPriority() < getPriority()) {
			rightPrototype = "(" + rightPrototype + ")";
		}
		return leftPrototype + " " + getName() + " " + rightPrototype;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		getLeftOperand().accept(visitor);
		getRightOperand().accept(visitor);
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

}