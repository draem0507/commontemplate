package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * SelectorEntryOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class SelectorEntryOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new SelectorEntryOperatorHandler();
	}
	
	/**
	 * 对2元操作符 : 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Selector对象，一个Object。
	 * @result
	 * 结果<br>
	 * 如果 Selector.isSelected 为 true，那么返回 Selector 的 selectedValue。<br>
	 * 否则返回 Object。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		Selector selector = new Selector(true, "1");
		assertEvaluation(selector, "2", "1");
		
		selector = new Selector(false, "1");
		assertEvaluation(selector, "2", "2");
		
	}
}
