package org.commontemplate.standard.operator.string;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * StringNotMatchOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class StringNotMatchOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new StringNotMatchOperatorHandler();
	}
	
	/**
	 * 对2元操作符 !~ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个string和一个正则表达式。
	 * @result
	 * 结果<br>
	 * 返回string和正则表达式是否不匹配的boolean对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		assertEvaluation("tan", "t[aeno]n", Boolean.FALSE);		
		assertEvaluation("taan", "t[aeno]n", Boolean.TRUE);
	}
}
