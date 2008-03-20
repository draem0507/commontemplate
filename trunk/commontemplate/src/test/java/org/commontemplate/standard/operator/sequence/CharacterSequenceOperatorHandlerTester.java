package org.commontemplate.standard.operator.sequence;

import org.commontemplate.config.BinaryOperatorHandler;

import junit.framework.TestCase;
/**
 * CharacterSequenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class CharacterSequenceOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new CharacterSequenceOperatorHandler();
	}
	
	/**
	 * 对2元操作符 .. 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个String对象，长度必须是1。
	 * @result
	 * 结果<br>
	 * 返回一个CharacterSequence序列对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception {
		
		CharacterSequence sequence = (CharacterSequence) handler.doEvaluate("A", "Z");
		
		assertEquals('A', sequence.getBegin());
		assertEquals('Z', sequence.getEnd());
	}
}
