package org.commontemplate.engine.expression;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.util.Function;

/**
 * 函数型一元操作符(内置实现)
 *
 * @author liangfei0201@163.com
 *
 */
final class FunctionUnaryOperatorHandler extends UnaryOperatorHandler {

	private static final long serialVersionUID = 1L;

	private final String name;

	FunctionUnaryOperatorHandler(String name) {
		this.name = name;
	}

	public Object doEvaluate(Object operand) throws Exception {
		List args = null;
		if (operand == null) {
			args = new ArrayList(0);
		} else if (operand instanceof List) {
			args = (List)operand;
		} else {
			args = new ArrayList(1);
			args.add(operand);
		}
		return new Function(name, args);
	}

}
