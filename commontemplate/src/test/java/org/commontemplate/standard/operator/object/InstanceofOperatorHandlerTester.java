package org.commontemplate.standard.operator.object;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.BinaryOperatorHandlerTester;
/**
 * InstanceofOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class InstanceofOperatorHandlerTester extends BinaryOperatorHandlerTester {

	protected BinaryOperatorHandler newBinaryOperatorHandler() {
		return new InstanceofStringOperatorHandler();
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
		
		assertEvaluation(this, 
				"org.commontemplate.standard.operator.object.InstanceofOperatorHandlerTester", 
				Boolean.TRUE);
		
		assertEvaluation(new Integer(2), 
				"org.commontemplate.standard.operator.object.InstanceofOperatorHandlerTester", 
				Boolean.FALSE);
		
		assertEvaluation(new Integer(2), 
				"java.lang.Integer", 
				Boolean.TRUE);
		// 父类是false
		assertEvaluation(this, 
				"junit.framework.TestCase", 
				Boolean.FALSE);			
	}
}
