package org.commontemplate.standard.operator.object;

/**
 * 类无参构造函数处理器.
 * 如:
 * new com.xxx.User
 * 或者:
 * new com.xxx.User.xxx
 *
 * @author Guileen
 * @author liangfei0201@163.com
 *
 */
public class NewInstanceOperatorHandler extends NewInstanceOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NewInstanceOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		return super.doEvaluateProperties((String)operand);
	}

}