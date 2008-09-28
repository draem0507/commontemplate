package org.commontemplate.standard.operator.map;

import junit.framework.TestCase;

import org.commontemplate.config.BinaryOperatorHandler;
import org.commontemplate.standard.operator.map.EntryOperatorHandler;
import org.commontemplate.util.MapEntry;
/**
 * LiteralEntryOperatorHandler 的测试。
 * @author YanRong
 *
 */
public class EntryOperatorHandlerTester extends TestCase {

	BinaryOperatorHandler handler;
	
	public void setUp() {

		handler = new EntryOperatorHandler();
	}
	
	/**
	 * 对2元操作符 : 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回一个MapEntry对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		MapEntry mapEntry = (MapEntry) handler.doEvaluate("a", "b");
		assertEquals("a", mapEntry.getKey());
		assertEquals("b", mapEntry.getValue());
		
	}
	
	/**
	 * 对2元操作符 = 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 参数为两个对象。
	 * @result
	 * 结果<br>
	 * 返回一个MapEntry对象。
	 * @throws Exception
	 */
	public void testDoEvaluate2() throws Exception{
		
		MapEntry mapEntry = (MapEntry) handler.doEvaluate("a", "b");
		assertEquals("a", mapEntry.getKey());
		assertEquals("b", mapEntry.getValue());
		
	}
}
