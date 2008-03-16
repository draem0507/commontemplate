package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字面列表操作符: ","<br/>
 * 如: ${"aa","bb","cc"]<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class LiteralListOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public LiteralListOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (rightOperand instanceof Entry) {
			if (leftOperand instanceof Map){
				Map literalMap = (Map)leftOperand;
				Entry literalEntry = (Entry)rightOperand;
				literalMap.put(literalEntry.getKey(), literalEntry.getValue());
				return literalMap;
			} else if (leftOperand instanceof Entry) {
				Map literalMap = new HashMap();
				Entry leftEntry = (Entry)leftOperand;
				literalMap.put(leftEntry.getKey(), leftEntry.getValue());
				Entry literalEntry = (Entry)rightOperand;
				literalMap.put(literalEntry.getKey(), literalEntry.getValue());
				return literalMap;
			}
		}

		if (leftOperand instanceof List) {
			List literalList = (List)leftOperand;
			literalList.add(rightOperand);
			return literalList;
		}

		List literalList = new ArrayList();
		literalList.add(leftOperand);
		literalList.add(rightOperand);
		return literalList;
	}

}
