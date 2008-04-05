package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.SpecialUnaryOperatorHandler;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Expression;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.core.Visitor;
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
	
	private final boolean operandLiteral;
	
	private final boolean functionLiteral;
	
	UnaryOperatorImpl(String name, Location location, int priority, UnaryOperatorHandler handler) {
		Assert.assertNotNull(handler);
		this.name = name;
		this.location = location;
		this.priority = priority;
		this.handler  = handler;
		if (handler instanceof SpecialUnaryOperatorHandler) {
			SpecialUnaryOperatorHandler shandler = (SpecialUnaryOperatorHandler)handler;
			this.operandLazy  = shandler.isOperandLazy();
			this.operandLiteral  = shandler.isOperandNamed();
			this.functionLiteral = shandler.isOperandFunctioned();
		} else {
			this.operandLazy  = false;
			this.operandLiteral  = false;
			this.functionLiteral = false;
		}
	}
	
	public Object evaluate(VariableResolver context) throws EvaluationException {
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
		return operandLiteral;
	}
	
	public boolean isOperandFunctioned() {
		return functionLiteral;
	}

	private Expression operand;
	
	public Expression getOperand() {
		return operand;
	}

	private List operands;
	
	public List getOperands() {
		return operands;
	}

	public String getCanonicalForm() {
		return getName() + " " + getOperand().getCanonicalForm();
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		getOperand().accept(visitor);
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

}