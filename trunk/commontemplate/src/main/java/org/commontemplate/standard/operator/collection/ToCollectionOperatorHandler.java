package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 单一对象转化成集合处理器, Object 转成 List, Entry 转成 Map
 * <p/>
 * 注: 此处理器应放在处理器链的最后, 其不再调用nextHandler.
 * 
 * @author liangfei0201@163.com
 *
 */
public class ToCollectionOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ToCollectionOperatorHandler() {
		super(Object.class);
	}

	public Object doEvaluate(Object operand) throws Exception {
		Object model = operand;
		if (model instanceof List
				|| model instanceof Map) {
			return model;
		}
		if (model instanceof Entry) {
			Entry entry = (Entry)model;
			Map map = new HashMap(1);
			map.put(entry.getKey(), entry.getValue());
			return map;
		}
		List list = new ArrayList(1);
		list.add(model);
		return list;
	}

}