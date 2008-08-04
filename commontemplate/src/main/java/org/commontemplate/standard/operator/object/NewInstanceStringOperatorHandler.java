package org.commontemplate.standard.operator.object;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 创建实例操作符: "new"<br/>
 * 如: ${new com.xxx.XXXClass()}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class NewInstanceStringOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NewInstanceStringOperatorHandler() {
		super(String.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		String name = (String)operand;
		try {
			return Class.forName(name).newInstance();
		} catch (ClassNotFoundException e) {
			return new NewPackage(Package.getPackage(name));
		} catch (InstantiationException e) {
			return new NewPackage(Package.getPackage(name));
		} catch (IllegalAccessException e) {
			return new NewPackage(Package.getPackage(name));
		}
	}

}