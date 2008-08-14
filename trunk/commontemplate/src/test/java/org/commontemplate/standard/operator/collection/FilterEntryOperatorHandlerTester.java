package org.commontemplate.standard.operator.collection;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.LazyOperand;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Visitor;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.standard.operator.LazyOperandMock;
import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.Location;

import junit.framework.TestCase;
/**
 * FilterEntryOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class FilterEntryOperatorHandlerTester extends TestCase {

	OperatorHandlerProvider operatorHandlerProvider;
	BinaryOperatorHandlerChain handler;

	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
		handler =
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("=>");
	}

	/**
	 * 对2元操作符 => 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回Filter对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{

		Integer leftOperand = new Integer(10);
		LazyOperand rightOperand = new LazyOperandMock("a", new ConstantImpMock("e", null));

		Filter filter = (Filter) handler.doEvaluate(leftOperand, rightOperand);

		assertEquals("10", filter.getName());
		Expression expression = filter.getExpression();
		assertTrue(expression instanceof ConstantImpMock);
		assertEquals("e", ((ConstantImpMock) expression).getValue());
	}
	/**
	 * 测试使用。目前不需要独立出去。
	 * @author YanRong
	 *
	 */
	private static class ConstantImpMock extends Constant {

		private static final long serialVersionUID = 1L;

		private final Object value;

		private final Location location;

		ConstantImpMock(Object value, Location location) {
			this.value = value;
			this.location = location;
		}

		public String getName() {
			return String.valueOf(value);
		}

		public String getSource() {
			return null;
		}

		public Location getLocation() {
			return location;
		}

		public int accept(Visitor visitor) {
			return Visitor.NEXT;
		}

		public Object getValue() {
			return value;
		}
	}

}
