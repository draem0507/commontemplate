package org.commontemplate.standard.operator;

import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.SpecialBinaryOperatorHandler;

/**
 * 二元操作符链, 将多个重载的二元操作符组装成一个二元操作符, 并负责按类型分派处理.
 *
 * @author liangfei0201@163.com
 *
 */
public class BinaryOperatorHandlerChain extends SpecialBinaryOperatorHandler implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List binaryOperatorHandlers;

	public void setBinaryOperatorHandlers(List binaryOperatorHandlers) {
		if (binaryOperatorHandlers == null)
			throw new java.lang.IllegalArgumentException("二元操作符处理类不能为空!");
		for (Iterator iterator = binaryOperatorHandlers.iterator(); iterator.hasNext();) {
			Object handler = iterator.next();
			if (handler == null)
				throw new java.lang.IllegalArgumentException("二元操作符处理类不能为空!");
			if (! (handler instanceof BinaryOperatorHandlerMatcher))
				throw new java.lang.IllegalArgumentException("二元操作符处理类:" + handler.getClass().getName()
						+ " 未实现接口:" + BinaryOperatorHandlerMatcher.class.getName());
		}
		this.binaryOperatorHandlers = binaryOperatorHandlers;
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		for (Iterator iterator = binaryOperatorHandlers.iterator(); iterator.hasNext();) {
			BinaryOperatorHandlerMatcher handler = (BinaryOperatorHandlerMatcher)iterator.next();
			if (handler.isMatch(leftOperand, rightOperand)) {
				try {
					return handler.doEvaluate(leftOperand, rightOperand);
				} catch (NullPointerException e) {
					return null;
				} catch (UnhandleException e) {
					// ignore, continue next
				}
			}
		}
		if (leftOperand == null || rightOperand == null) // 对null的默认处理
			return null;
		throw new UnhandleException("无法处理参数类型为: ("
				+ leftOperand.getClass().getName() + ", "
				+ rightOperand.getClass().getName()
				+ ") 值为: (" + leftOperand + ", " + rightOperand + ")" + " 被调用的处理类:" + binaryOperatorHandlers);
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

	private boolean associativeLaw;

	public boolean isAssociativeLaw() {
		return associativeLaw;
	}

	public void setAssociativeLaw(boolean associativeLaw) {
		this.associativeLaw = associativeLaw;
	}

	private boolean commutativeLaw;

	public boolean isCommutativeLaw() {
		return commutativeLaw;
	}

	public void setCommutativeLaw(boolean commutativeLaw) {
		this.commutativeLaw = commutativeLaw;
	}

}
