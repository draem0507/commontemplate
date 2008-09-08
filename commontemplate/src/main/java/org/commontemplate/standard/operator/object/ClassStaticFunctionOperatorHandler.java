package org.commontemplate.standard.operator.object;

import org.commontemplate.util.Function;

/**
 * 类静态函数处理器.
 * 如:
 * &com.xxx.Utils.calc(xxx)
 * 或者:
 * &com.xxx.Utils.URI.substring(xxx)
 *
 * @author Guileen
 * @author liangfei0201@163.com
 *
 */
public class ClassStaticFunctionOperatorHandler extends ClassNamedOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticFunctionOperatorHandler() {
		super(Function.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return super.doEvaluateFunction((Function)operand);
	}
}