package org.commontemplate.standard.operator.sequence;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
/**
 * IntegerSequenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class IntegerSequenceOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new IntegerSequenceOperatorHandler();
	}
	
	/**
	 * 对2元操作符 .. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个Number对象。
	 * @result
	 * 结果<br>
	 * 返回一个数字序列对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception {
		
		IntegerSequence sequence = (IntegerSequence) handler.doEvaluate(new Integer(2), new Integer(5));
		
		assertEquals(2, sequence.getBegin());
		assertEquals(5, sequence.getEnd());
	}
	
}
