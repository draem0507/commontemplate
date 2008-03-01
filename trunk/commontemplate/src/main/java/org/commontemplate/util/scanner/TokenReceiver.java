package org.commontemplate.util.scanner;

/**
 * 
 * 片断接收器
 * @author liangfei0201@163.com
 *
 */
public interface TokenReceiver {
	
	/**
	 * 接收片断
	 * @param token 片断
	 */
	public void receive(Token token);

}
