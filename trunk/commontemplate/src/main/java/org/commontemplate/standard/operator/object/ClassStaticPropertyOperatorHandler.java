package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.BeanUtils;

/**
 * 静态属性操作符: "."<br/>
 * 如: ${&com.xxx.XXXClass.xxx}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ClassStaticPropertyOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassStaticPropertyOperatorHandler() {
		super(Class.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		return BeanUtils.getStaticProperty((Class)leftOperand, (String)rightOperand);
	}

}
