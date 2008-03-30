package org.commontemplate.util.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 将所有输出片断收集成列表
 *
 * @author liangfei0201@163.com
 *
 */
public class ListTokenReceiver implements TokenReceiver {

	private final List tokens = new ArrayList();

	public void receive(Token token) {
		tokens.add(token);
	}

	/**
	 * 获取接收到的Token列表
	 *
	 * @return Token列表, 类型: List&lt;Token&gt;
	 */
	public List getTokens() {
		return tokens;
	}

}
