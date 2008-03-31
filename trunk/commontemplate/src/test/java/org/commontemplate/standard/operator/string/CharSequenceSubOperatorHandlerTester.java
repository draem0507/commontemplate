package org.commontemplate.standard.operator.string;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.sequence.IntegerSequence;

import junit.framework.TestCase;
/**
 * CharSequenceSubOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CharSequenceSubOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	protected void setUp() throws Exception {
		handler = new CharSequenceSubOperatorHandler();
	}
	
	/**
	 * 对2元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为CharSequence 对象， IntegerSequence 对象。
	 * @result
	 * 结果<br>
	 * 返回CharSequence对象的子对象。
	 * @throws Exception
	 */
	public void testDoEvaluateByIntegerSequence() throws Exception {
		
		CharSequence charSequence = "abcdef";
		
		IntegerSequence sequence = new IntegerSequence(1,3);
		
		assertEquals("bcd", handler.doEvaluate(charSequence, sequence));
	}
	
	/**
	 * 对2元操作符 [ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为CharSequence 对象， Integer List 对象。
	 * @result
	 * 结果<br>
	 * 返回CharSequence对象的子对象。
	 * @throws Exception
	 */
	public void testDoEvaluateByIntegerList() throws Exception {
		
		CharSequence charSequence = "abcdef";
		
		List list = new ArrayList();
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		
		assertEquals("bcd", handler.doEvaluate(charSequence, list));
	}
	
}
