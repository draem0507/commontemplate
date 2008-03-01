package org.commontemplate.util.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 将所有输出片断收集成列表
 * @author liangfei0201@163.com
 *
 */
public class ListTokenReceiver implements TokenReceiver {
	
	private final List tokens = new ArrayList();

	public void receive(Token token) {
		tokens.add(token);
	}

	public List getTokens() {
		return tokens;
	}

}
