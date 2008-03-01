package org.commontemplate.util.scanner;

/**
 * 全部接收
 * 
 * @author liangfei0201@163.com
 *
 */
public class AllAccepter implements Accepter {

	public int accept(String token) {
		return token.length();
	}

}
