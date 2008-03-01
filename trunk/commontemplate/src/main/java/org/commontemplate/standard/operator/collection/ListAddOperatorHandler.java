package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 集合数据相加
 * 
 * @author liangfei0201@163.com
 *
 */
public class ListAddOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public ListAddOperatorHandler() {
		super(Collection.class, Collection.class);
	}
	
	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection leftCollection = (Collection)leftOperand;
		Collection rightCollection = (Collection)rightOperand;
		Collection sumCollection = new ArrayList(leftCollection.size() + rightCollection.size()); // 新建Collection对象, 以保证不改变原有数据模型
		sumCollection.addAll(leftCollection);
		sumCollection.addAll(rightCollection);
		return sumCollection;
	}

}