package org.commontemplate.standard.operator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.SpecialUnaryOperatorHandler;

/**
 * 一元操作符链, 将多个重载的一元操作符组装成一个一元操作符, 并负责按类型分派处理.
 *
 * @author liangfei0201@163.com
 *
 */
public class UnaryOperatorHandlerChain extends SpecialUnaryOperatorHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private List unaryOperatorHandlers;

	public void setUnaryOperatorHandlers(List unaryOperatorHandlers) {
		if (unaryOperatorHandlers == null)
			throw new java.lang.IllegalArgumentException("一元操作符处理类不能为空!");
		for (Iterator iterator = unaryOperatorHandlers.iterator(); iterator.hasNext();) {
			Object handler = iterator.next();
			if (handler == null)
				throw new java.lang.IllegalArgumentException("一元操作符处理类不能为空!");
			if (! (handler instanceof UnaryOperatorHandlerMatcher))
				throw new java.lang.IllegalArgumentException("一元操作符处理类:" + handler.getClass().getName()
						+ " 未实现接口:" + UnaryOperatorHandlerMatcher.class.getName());
		}
		this.unaryOperatorHandlers = unaryOperatorHandlers;
	}

	public Object doEvaluate(Object operand) throws Exception {
		for (Iterator iterator = unaryOperatorHandlers.iterator(); iterator.hasNext();) {
			UnaryOperatorHandlerMatcher handler = (UnaryOperatorHandlerMatcher)iterator.next();
			if (handler.isMatch(operand)) {
				try {
					return handler.doEvaluate(operand);
				} catch (NullPointerException e) {
					return null;
				} catch (UnhandleException e) {
					// ignore, continue next
				}
			}
		}
		if (operand == null) // 对null的默认处理
			return null;
		throw new UnhandleException("未找到相应处理类，用于处理操作符: \"" + getClass().getName() + "\", 参数: ("
				+ operand.getClass().getName() + ") 值: (" + operand + ")"
				+ " 被调用的处理类:" + unaryOperatorHandlers);
	}

	private boolean operandLazy = false;

	public boolean isOperandLazy() {
		return operandLazy;
	}

	public void setOperandLazy(boolean operandLazy) {
		this.operandLazy = operandLazy;
	}

	private boolean operandNamed = false;

	public boolean isOperandNamed() {
		return operandNamed;
	}

	public void setOperandNamed(boolean operandNamed) {
		this.operandNamed = operandNamed;
	}

	private boolean operandFunctioned;

	public boolean isOperandFunctioned() {
		return operandFunctioned;
	}

	public void setOperandFunctioned(boolean operandFunctioned) {
		this.operandFunctioned = operandFunctioned;
	}

}
