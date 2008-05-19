package org.commontemplate.util.scanner;

/**
 * 退回指定个数的char及其前面的换行符
 *
 * @author liangfei0201@163.com
 *
 */
public class BackNewlineAccepter implements Accepter {

	private final int back;

	public BackNewlineAccepter(int back) {
		this.back = back;
	}

	public int accept(String token) {
		if (token.length() < back)
			return 0;
		int b = this.back;
		// 计算换行符个数
		for (int i = token.length() - 1 - this.back; i >= 0; i --) {
			char s = token.charAt(i);
			if (s != '\n' && s != '\r')
				break;
			b ++;
		}
		return token.length() - b;
	}

}