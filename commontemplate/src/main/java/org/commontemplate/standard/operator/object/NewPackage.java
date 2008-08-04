package org.commontemplate.standard.operator.object;

/**
 * 创建包封装
 *
 * @author liangfei0201@163.com
 *
 */
public class NewPackage {

	private final Package pkg;

	public NewPackage(Package pkg) {
		this.pkg = pkg;
	}

	public Package getNewPackage() {
		return pkg;
	}

}
