package org.commontemplate.standard.operator.bool;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.LazyOperand;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.standard.operator.LazyOperandMock;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * BooleanSelectOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class BooleanSelectOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	BinaryOperatorHandlerChain handler;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		handler =
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("?");
	}

	/**
	 * 对2元操作符 ? 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回Selector对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{

		LazyOperand rightOperand = new LazyOperandMock("2");
		Boolean leftOperand = Boolean.TRUE;

		Selector selector = (Selector) handler.doEvaluate(leftOperand, rightOperand);
		assertEquals(true, selector.isSelected());
		assertEquals("2", (String) selector.getSelectedValue());

		leftOperand = Boolean.FALSE;
		selector = (Selector) handler.doEvaluate(leftOperand, rightOperand);
		assertEquals(false, selector.isSelected());
		assertNull(selector.getSelectedValue());

		Float f = new Float(1.2);
		selector = (Selector) handler.doEvaluate(f, rightOperand);
		assertEquals(true, selector.isSelected());
		assertEquals("2", (String) selector.getSelectedValue());

		f = new Float(0.2);
		selector = (Selector) handler.doEvaluate(f, rightOperand);
		assertEquals(true, selector.isSelected());
		assertNotNull(selector.getSelectedValue());
	}
}
