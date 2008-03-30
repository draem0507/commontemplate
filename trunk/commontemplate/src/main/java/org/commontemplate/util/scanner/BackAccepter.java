package org.commontemplate.util.scanner;

/**
 * 回退接收器
 *
 * @author liangfei0201@163.com
 *
 */
public class BackAccepter implements Accepter {

	private final int back;

	/**
	 * 构造回退接收器
	 *
	 * @param back 回退字符个数
	 */
	public BackAccepter(int back) {
		this.back = back;
	}

	public int accept(String token) {
		return token.length() - back;
	}

}
