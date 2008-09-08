package org.commontemplate.standard.operator.object;

/**
 * 类元或类静态属性处理器.
 * 如:
 * &com.xxx.Utils
 * 或者:
 * &com.xxx.Utils.URI
 * 或者:
 * &com.xxx.Utils.URI.name
 *
 * @author Guileen
 * @author liangfei0201@163.com
 *
 */
public class ClassStaticPropertyOperatorHandler extends ClassNamedOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticPropertyOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return super.doEvaluateProperties((String)operand);
	}

}