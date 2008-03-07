package org.commontemplate.standard.operator.object;

import org.commontemplate.config.Configuration;
import org.commontemplate.config.OperatorHandlerProvider;
import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;
import org.commontemplate.tools.PropertiesConfigurationLoader;

import junit.framework.TestCase;
/**
 * InstanceofOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class InstanceofOperatorHandlerTester extends TestCase {

OperatorHandlerProvider operatorHandlerProvider;
	
	public void setUp() {

		Configuration config = PropertiesConfigurationLoader.loadStandardConfiguration();
		// 默认会取得 StandardOperatorHandlerProvider
		operatorHandlerProvider = config.getOperatorHandlerProvider();
	}
	
	/**
	 * 对2元操作符 instanceof 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个Object和string。
	 * @result
	 * 结果<br>
	 * 返回Object是否是string所标示的类的实例的boolean对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		BinaryOperatorHandlerChain handler = 
			(BinaryOperatorHandlerChain) operatorHandlerProvider.getBinaryOperatorHandler("instanceof");
		
		assertTrue(((Boolean)handler.doEvaluate(this, 
				"org.commontemplate.standard.operator.object.InstanceofOperatorHandlerTester")).booleanValue());
		assertFalse(((Boolean)handler.doEvaluate(new Integer(2), 
				"org.commontemplate.standard.operator.object.InstanceofOperatorHandlerTester")).booleanValue());
		
		assertTrue(((Boolean)handler.doEvaluate(new Integer(2), 
				"java.lang.Integer")).booleanValue());
		
		// 父类是false
		assertFalse(((Boolean)handler.doEvaluate(this, 
				"junit.framework.TestCase")).booleanValue());
		
	}
}
