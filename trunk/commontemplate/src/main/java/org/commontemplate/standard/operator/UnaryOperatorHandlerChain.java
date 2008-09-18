package org.commontemplate.standard.operator;

import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.util.I18nExceptionFactory;

/**
 * 一元操作符链, 将多个重载的一元操作符组装成一个一元操作符, 并负责按类型分派处理.
 *
 * @author liangfei0201@163.com
 *
 */
public class UnaryOperatorHandlerChain extends UnaryOperatorHandler {

	private static final long serialVersionUID = 1L;

	private List unaryOperatorHandlers;

	public void setUnaryOperatorHandlers(List unaryOperatorHandlers) {
		if (unaryOperatorHandlers == null)
			throw I18nExceptionFactory.createIllegalArgumentException("UnaryOperatorHandlerChain.handler.required");
		for (Iterator iterator = unaryOperatorHandlers.iterator(); iterator.hasNext();) {
			Object handler = iterator.next();
			if (handler == null)
				throw I18nExceptionFactory.createIllegalArgumentException(
						"UnaryOperatorHandlerChain.handler.required");
			if (! (handler instanceof UnaryOperatorHandlerMatcher))
				throw I18nExceptionFactory.createIllegalArgumentException(
						"UnaryOperatorHandlerChain.handler.type.error",
						new Object[]{ handler.getClass().getName()
						, UnaryOperatorHandlerMatcher.class.getName()});
		}
		this.unaryOperatorHandlers = unaryOperatorHandlers;
	}

	public Object doEvaluate(Object operand) throws Exception {
		UnhandleException exception = null;
		for (Iterator iterator = unaryOperatorHandlers.iterator(); iterator.hasNext();) {
			UnaryOperatorHandlerMatcher handler = (UnaryOperatorHandlerMatcher)iterator.next();
			if (handler.isMatch(operand)) {
				try {
					return handler.doEvaluate(operand);
				} catch (NullPointerException e) {
					return null;
				} catch (UnhandleException e) {
					exception = e;
					// continue next handler
				}
			}
		}
		if (operand == null) // 对null的默认处理
			return null;
		if (exception != null)
			throw exception;
		throw new UnhandleException("UnaryOperatorHandlerChain.unhandle.error",
				new Object[]{operand.getClass().getName(), operand, unaryOperatorHandlers});
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

	private boolean operandDotNamed = false;

	public boolean isOperandDotNamed() {
		return operandDotNamed;
	}

	public void setOperandDotNamed(boolean operandDotNamed) {
		this.operandDotNamed = operandDotNamed;
	}

	private boolean keyword;

	public boolean isKeyword() {
		return keyword;
	}

	public void setKeyword(boolean keyword) {
		this.keyword = keyword;
	}

	private boolean optimize;

	public boolean isOptimize() {
		return optimize;
	}

	public void setOptimize(boolean optimize) {
		this.optimize = optimize;
	}

}
