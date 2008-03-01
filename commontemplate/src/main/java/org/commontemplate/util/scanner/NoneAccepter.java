package org.commontemplate.util.scanner;

/**
 * 全部不接收
 * 
 * @author liangfei0201@163.com
 *
 */
public class NoneAccepter implements Accepter {

	public int accept(String token) {
		return 0;
	}

}
