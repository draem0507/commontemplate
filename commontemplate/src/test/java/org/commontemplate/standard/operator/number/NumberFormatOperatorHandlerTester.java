package org.commontemplate.standard.operator.number;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * NumberFormatOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class NumberFormatOperatorHandlerTester extends TestCase {
	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new NumberFormatOperatorHandler();			
	}
	
	/**
	 * 对2元操作符 # 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Number 对象, 格式化格式。
	 * @result
	 * 结果<br>
	 * 按照格式化的格式返回数字的字符串。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		Integer integer = new Integer(100);
		String format = (String) handler.doEvaluate(integer, "###,###0.00");
		assertEquals("100.00", format);
	}
	
	/**
	 * 对2元操作符 # 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Number 对象, 格式化格式。
	 * @result
	 * 结果<br>
	 * 按照格式化的格式返回数字的字符串。
	 * @throws Exception
	 */
	public void testDoEvaluate2() throws Exception{
		
		Integer integer = new Integer(1000000);
		String format = (String) handler.doEvaluate(integer, "###,###.00");
		assertEquals("1,000,000.00", format);
	}
	
	/**
	 * 对2元操作符 # 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Number 对象, 格式化格式。
	 * @result
	 * 结果<br>
	 * 按照格式化的格式返回数字的字符串。
	 * @throws Exception
	 */
	public void testDoEvaluate3() throws Exception{
		
		Number number = new Double(3.1415);
		String format = (String) handler.doEvaluate(number, "###,###.00");
		assertEquals("3.14", format);
	}
	
	/**
	 * 对2元操作符 # 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为Number 对象, 格式化格式。
	 * @result
	 * 结果<br>
	 * 按照格式化的格式返回数字的字符串。
	 * @throws Exception
	 */
	public void testDoEvaluate4() throws Exception{
		
		Number number = new Double(3.145);
		String format = (String) handler.doEvaluate(number, "###,###.00");
		assertEquals("3.14", format);
	}
}
