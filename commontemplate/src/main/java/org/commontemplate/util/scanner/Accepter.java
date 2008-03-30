package org.commontemplate.util.scanner;

/**
 * 接收控制器，未接收的字符，状态机将重新扫描
 *
 * @author liangfei0201@163.com
 *
 */
public interface Accepter {

	/**
	 * 接收长度
	 *
	 * @param token 待接收内容
	 * @return 接收长度 (不变式: 返回值必须大于或等于0, 小于或等于token.length())
	 */
	public int accept(String token);

}
