package org.commontemplate.util.scanner;

/**
 * 退回指定个数的char及其前面的空白符
 *
 * @author liangfei0201@163.com
 *
 */
public class BackBlankAccepter implements Accepter {

	private final int back;

	public BackBlankAccepter(int back) {
		this.back = back;
	}

	public int accept(String token) {
		if (token.length() < back)
			return 0;
		int b = this.back;
		// 计算空白符个数
		for (int i = token.length() - 1 - this.back; i >= 0; i --) {
			char s = token.charAt(i);
			if (s != ' ' && s != '\t' && s != '\n' && s != '\r' && s != '\f')
				break;
			b ++;
		}
		return token.length() - b;
	}

}
