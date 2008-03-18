package org.commontemplate.standard.operator.collection;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * MapPresenceOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class MapPresenceOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new MapPresenceOperatorHandler();
	}
	/**
	 * 对一元操作符 ？ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为一个map。
	 * @result
	 * 结果<br>
	 * 根据map是否为空，以及size是否大于零而返回boolean类型。。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		
		assertEvaluation(null, Boolean.FALSE);
		assertEvaluation(new HashMap(), Boolean.FALSE);
		
		Map map = new HashMap();
		map.put("a", "a");		
		assertEvaluation(map, Boolean.TRUE);
		
	}
}
