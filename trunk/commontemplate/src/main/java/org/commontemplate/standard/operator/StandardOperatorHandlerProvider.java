package org.commontemplate.standard.operator;

import java.io.Serializable;
import java.util.Map;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.util.TypeUtils;

/**
 * 标准操作符供给器实现
 *
 * @author liangfei0201@163.com
 *
 */
public class StandardOperatorHandlerProvider implements OperatorHandlerProvider, Serializable {

	private static final long serialVersionUID = 1L;

	private Map binaryOperatorHandlers;

	public void setBinaryOperatorHandlers(
			Map binaryOperatorHandlers) {
		this.binaryOperatorHandlers = binaryOperatorHandlers;
	}

	private Map unaryOperatorHandlers;

	public void setUnaryOperatorHandlers(
			Map unaryOperatorHandlers) {
		this.unaryOperatorHandlers = unaryOperatorHandlers;
	}

	private Map binaryOperatorPrioritys;

	public void setBinaryOperatorPrioritys(
			Map binaryOperatorPrioritys) {
		this.binaryOperatorPrioritys = binaryOperatorPrioritys;
	}

	public BinaryOperatorHandler getBinaryOperatorHandler(String name) {
		return (BinaryOperatorHandler)binaryOperatorHandlers.get(name);
	}

	public UnaryOperatorHandler getUnaryOperatorHandler(String name) {
		return (UnaryOperatorHandler) unaryOperatorHandlers.get(name);
	}

	public boolean hasBinaryOperatorHandler(String name) {
		return binaryOperatorHandlers.containsKey(name);
	}

	public boolean hasUnaryOperatorHandler(String name) {
		return unaryOperatorHandlers.containsKey(name);
	}

	public int getBinaryOperatorPriority(String name) {
		if (binaryOperatorPrioritys.containsKey(name.trim()))
			return ((Integer) binaryOperatorPrioritys.get(name.trim())).intValue();
		return 0;
	}

	private Map unaryOperatorPrioritys;

	public void setUnaryOperatorPrioritys(Map unaryOperatorPrioritys) {
		this.unaryOperatorPrioritys = unaryOperatorPrioritys;
	}

	public int getUnaryOperatorPriority(String name) {
		name = name.trim();
		if (unaryOperatorPrioritys.containsKey(name))
			return ((Integer) unaryOperatorPrioritys.get(name)).intValue();
		if (TypeUtils.isNamed(name))
			return functionPriority;
		return 0;
	}

	private int functionPriority = 0;

	public final void setFunctionPriority(int functionPriority) {
		this.functionPriority = functionPriority;
	}

	public int getFunctionPriority() {
		return functionPriority;
	}

}