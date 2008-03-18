package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.commontemplate.config.UnaryOperatorHandler;
import org.commontemplate.standard.operator.UnaryOperatorHandlerTester;
/**
 * 单一对象转化成集合处理器, Object 转成 List, Entry 转成 Map 的测试。
 * @author YanRong
 *
 */
public class ToCollectionOperatorHandlerTester extends UnaryOperatorHandlerTester {

	protected UnaryOperatorHandler newUnaryOperatorHandler() {
		return new ToCollectionOperatorHandler();
	}
	/**
	 * 对一元操作符　[ 的测试。<br>
	 * @condition
	 * 条件<br>
	 * 输入一个对象。
	 * @result
	 * 结果<br>
	 * 得到集合的形式的对象。
	 * @throws Exception
	 */
	public void testDoEvaluate() throws Exception{
		
		List list = new ArrayList();
		list.add("a");
		assertEvaluation(list, list);
		
		Map map = new HashMap();
		map.put("a", "1");
		assertEvaluation(map, map);
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		
		Entry entry = (Entry) it.next();

		assertEvaluation(entry, map);
		
		assertEvaluation("a", list);
		
	}
}
