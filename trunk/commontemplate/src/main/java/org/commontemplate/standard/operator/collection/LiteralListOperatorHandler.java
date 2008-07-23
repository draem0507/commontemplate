package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.sequence.Sequence;

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
				try {
					literalMap.put(literalEntry.getKey(), literalEntry.getValue());
					return literalMap;
				} catch (UnsupportedOperationException e) { // 当Map不支持put方法时，即unmodified
					Map newMap = new HashMap();
					newMap.putAll(literalMap);
					newMap.put(literalEntry.getKey(), literalEntry.getValue());
					return newMap;
				}
			} else if (leftOperand instanceof Entry) {
				Map literalMap = new HashMap();
				Entry leftEntry = (Entry)leftOperand;
				literalMap.put(leftEntry.getKey(), leftEntry.getValue());
				Entry literalEntry = (Entry)rightOperand;
				literalMap.put(literalEntry.getKey(), literalEntry.getValue());
				return literalMap;
			}
		}

		if (leftOperand instanceof List
				&& ! (leftOperand instanceof Sequence)) { // 序列除外
			List literalList = (List)leftOperand;
			try {
				literalList.add(rightOperand);
				return literalList;
			} catch (UnsupportedOperationException e) { // 当集合不支持add方法时，即unmodified
				List newList = new ArrayList();
				newList.addAll(literalList);
				newList.add(rightOperand);
				return newList;
			}
		}

		List literalList = new ArrayList();
		literalList.add(leftOperand);
		literalList.add(rightOperand);
		return literalList;
	}

}
