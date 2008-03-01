package org.commontemplate.util.scanner;

/**
 * 退回指定个数的字符
 * 
 * @author liangfei0201@163.com
 *
 */
public class BackAccepter implements Accepter {
	
	private final int back;
	
	public BackAccepter(int back) {
		this.back = back;
	}

	public int accept(String token) {
		return token.length() - back;
	}

}
