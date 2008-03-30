package org.commontemplate.util.scanner;

import java.io.IOException;

/**
 * 分解器
 *
 * @author liangfei0201@163.com
 *
 */
public interface Scanner {

	/**
	 * 扫描字符流，将其分段并传给接收器
	 *
	 * @param charStream 字符流
	 * @param tokenReceiver 分段接收器
	 * @throws IOException 字符流出错时抛出
	 * @throws ScanningException 扫描出错时抛出
	 */
	public void scan(CharStream charStream, TokenReceiver tokenReceiver) throws IOException, ScanningException;

}
