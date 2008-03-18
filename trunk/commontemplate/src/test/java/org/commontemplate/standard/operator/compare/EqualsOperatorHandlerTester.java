package org.commontemplate.standard.operator.compare;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * EqualsOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class EqualsOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new EqualsOperatorHandler();
	}
	
	/**
	 * 对2元操作符 == 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回两个对象的比较结果。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		Character character = new Character('a');
		String str = "a";	
		assertEvaluation(character, str, Boolean.TRUE);
		assertEvaluation(str, character, Boolean.TRUE);
		
		character = new Character('a');
		str = "aa";		
		assertEvaluation(character, str, Boolean.FALSE);
		assertEvaluation(str, character, Boolean.FALSE);
		
		character = new Character('a');
		str = "b";
		
		assertEvaluation(character, str, Boolean.FALSE);
		assertEvaluation(str, character, Boolean.FALSE);
		
		assertEvaluation("abc", "abc", Boolean.TRUE);
	}
}
