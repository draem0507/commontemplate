package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.map.LiteralMap;

/**
 * 单一对象转化成集合一元操作符: "[]"<br/>
 * Object 转成 List, Entry 转成 Map <br/>
 * 如: ${[xxx]}<br/>
 * 注: 此处理器应放在处理器链的最后, 其不再调用nextHandler.<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class ToCollectionOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ToCollectionOperatorHandler() {
		super(Object.class, true);
	}

	public Object doEvaluate(Object operand) throws Exception {
		if (operand == null)
			return new ArrayList(0);
		if (operand instanceof LiteralList) {
	        return ((LiteralList)operand).getList();
		}
		if (operand instanceof LiteralMap) {
            return ((LiteralMap)operand).getMap();
        }
		if (operand.getClass().isArray()
				|| operand instanceof List
				|| operand instanceof Map) {
			return operand;
		}
		if (operand instanceof Entry) {
			Entry entry = (Entry)operand;
			Map map = new HashMap(1);
			map.put(entry.getKey(), entry.getValue());
			return map;
		}
		List list = new ArrayList(1);
		list.add(operand);
		return list;
	}

}