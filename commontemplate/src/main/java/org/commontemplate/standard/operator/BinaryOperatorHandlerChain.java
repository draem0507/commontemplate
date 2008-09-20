package org.commontemplate.standard.operator;

import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.util.I18nExceptionFactory;

/**
 * 二元操作符链, 将多个重载的二元操作符组装成一个二元操作符, 并负责按类型分派处理.
 *
 * @author liangfei0201@163.com
 *
 */
public class BinaryOperatorHandlerChain extends BinaryOperatorHandler {

	private static final long serialVersionUID = 1L;

	private List binaryOperatorHandlers;

	public void setBinaryOperatorHandlers(List binaryOperatorHandlers) {
		if (binaryOperatorHandlers == null)
			throw I18nExceptionFactory.createIllegalArgumentException("BinaryOperatorHandlerChain.handler.required");
		for (Iterator iterator = binaryOperatorHandlers.iterator(); iterator.hasNext();) {
			Object handler = iterator.next();
			if (handler == null)
				throw I18nExceptionFactory.createIllegalArgumentException("BinaryOperatorHandlerChain.handler.required");
			if (! (handler instanceof BinaryOperatorHandlerMatcher))
				throw I18nExceptionFactory.createIllegalArgumentException("BinaryOperatorHandlerChain.handler.required",
						new Object[]{ handler.getClass().getName()
						, BinaryOperatorHandlerMatcher.class.getName()});
		}
		this.binaryOperatorHandlers = binaryOperatorHandlers;
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		UnhandleException exception = null;
		for (Iterator iterator = binaryOperatorHandlers.iterator(); iterator.hasNext();) {
			BinaryOperatorHandlerMatcher handler = (BinaryOperatorHandlerMatcher)iterator.next();
			if (handler.isMatch(leftOperand, rightOperand)) {
				try {
					return handler.doEvaluate(leftOperand, rightOperand);
				} catch (NullPointerException e) {
					return null;
				} catch (ArrayIndexOutOfBoundsException e) {
					return null;
				} catch (UnhandleException e) {
					exception = e;
					// continue next handler
				}
			}
		}
		if (leftOperand == null || rightOperand == null) // 对null的默认处理
			return null;
		if (exception != null)
			throw exception;
		throw new UnhandleException("BinaryOperatorHandlerChain.unhandle.error",
				new Object[]{ leftOperand.getClass().getName(),
				rightOperand.getClass().getName(), leftOperand,
				rightOperand, binaryOperatorHandlers});
	}

	private boolean leftOperandLazy;

	public boolean isLeftOperandLazy() {
		return leftOperandLazy;
	}

	public void setLeftOperandLazy(boolean leftOperandLazy) {
		this.leftOperandLazy = leftOperandLazy;
	}

	private boolean leftOperandNamed;

	public boolean isLeftOperandNamed() {
		return leftOperandNamed;
	}

	public void setLeftOperandNamed(boolean leftOperandNamed) {
		this.leftOperandNamed = leftOperandNamed;
	}

	private boolean rightOperandLazy;

	public boolean isRightOperandLazy() {
		return rightOperandLazy;
	}

	public void setRightOperandLazy(boolean rightOperandLazy) {
		this.rightOperandLazy = rightOperandLazy;
	}

	private boolean rightOperandNamed;

	public boolean isRightOperandNamed() {
		return rightOperandNamed;
	}

	public void setRightOperandNamed(boolean rightOperandNamed) {
		this.rightOperandNamed = rightOperandNamed;
	}

	private boolean rightOperandDotNamed;

	public boolean isRightOperandDotNamed() {
		return rightOperandDotNamed;
	}

	public void setRightOperandDotNamed(boolean rightOperandDotNamed) {
		this.rightOperandDotNamed = rightOperandDotNamed;
	}

	private boolean rightOperandFunctioned;

	public boolean isRightOperandFunctioned() {
		return rightOperandFunctioned;
	}

	public void setRightOperandFunctioned(boolean rightOperandFunctioned) {
		this.rightOperandFunctioned = rightOperandFunctioned;
	}

	private boolean rightToLeft;

	public boolean isRightToLeft() {
		return rightToLeft;
	}

	public void setRightToLeft(boolean rightToLeft) {
		this.rightToLeft = rightToLeft;
	}

	private boolean optimize;

	public boolean isOptimize() {
		return optimize;
	}

	public void setOptimize(boolean optimize) {
		this.optimize = optimize;
	}

	private boolean associative;

	public boolean isAssociative() {
		return associative;
	}

	public void setAssociative(boolean associative) {
		this.associative = associative;
	}

	private boolean commutative;

	public boolean isCommutative() {
		return commutative;
	}

	public void setCommutative(boolean commutative) {
		this.commutative = commutative;
	}

}
