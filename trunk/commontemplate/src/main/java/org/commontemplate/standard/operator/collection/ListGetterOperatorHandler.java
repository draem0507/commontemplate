package org.commontemplate.standard.operator.collection;

import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 列表按索引号取值处理器
 * 
 * @author liangfei0201@163.com
 *
 */
public class ListGetterOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ListGetterOperatorHandler() {
		super(List.class, Integer.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		List list = (List)leftOperand;
		Integer index = (Integer)rightOperand;
		if (index.intValue() < 0) // 小于0表示倒数
			index = new Integer(list.size() - index.intValue());
		if (index.intValue() < 0 || index.intValue() >= list.size()) // 忽略越界
			return null;
		return list.get(index.intValue());
	}

}