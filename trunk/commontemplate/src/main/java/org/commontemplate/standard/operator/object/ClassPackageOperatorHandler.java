package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 包名前导操作符: "&"<br/>
 * 如: ${&com.xxx.Utils.sub()}<br/>
 * TODO 此操作符待考虑易理解性, 暂时用"&", 另考虑是否用"@".<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ClassPackageOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ClassPackageOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		String name = (String)operand;
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			return Package.getPackage(name);
		}
	}

}