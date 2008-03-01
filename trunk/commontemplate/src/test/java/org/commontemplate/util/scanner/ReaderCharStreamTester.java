package org.commontemplate.util.scanner;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

/**
 * ReaderCharStream 测试
 * 
 * @author Yan Rong (yananay@126.com)
 *
 */
public class ReaderCharStreamTester extends TestCase{
	
	/**
	 * 测试使用 ReaderCharStream 来读取信息。
	 * @condition
	 *  条件<br>
	 *  构造一个字符串，内容是从0到10000，一共10001个元素。
	 * @result
	 *  结果<br>
	 *  读取出来的字符串应该和构造的字符串一致。
	 * @author Yan Rong
	 * @throws IOException
	 */
	public void testNextChar() throws IOException{
		
		StringBuffer reader = new StringBuffer();
		
		for(int i = 0; i < 10000; i++) {
			
			reader.append(i);
		}
		
		CharStream charStream = new ReaderCharStream(new StringReader(reader.toString()));
		
		StringBuffer result = new StringBuffer();
		char ch;
		while(( ch = charStream.nextChar()) != CharStream.END) {
			
			result.append(ch);
		}
		
		assertEquals(reader.toString(), result.toString());
	}
	
}
