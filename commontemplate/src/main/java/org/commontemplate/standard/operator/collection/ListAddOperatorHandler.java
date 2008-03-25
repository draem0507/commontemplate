package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 集合数据相加操作符: "+"<br/>
 * 如：${list1 + ["xxx", "yyy"]} ${list1 + list2}<br/>
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

		Collection sumCollection = null; // 新建Collection对象, 以保证不改变原有数据模型
		try {
			sumCollection = (Collection) leftCollection.getClass().newInstance();
			sumCollection.addAll(leftCollection); // 有些集合可能不支持addAll操作, 所以放在try内
			sumCollection.addAll(rightCollection);
		} catch (Exception e) { // 当左集合实现类没有无参构造子时, 试用右集合实现类
			try {
				sumCollection = (Collection) rightCollection.getClass().newInstance();
				sumCollection.addAll(leftCollection);
				sumCollection.addAll(rightCollection);
			} catch (Exception e2) { // 默认采用ArrayList
				sumCollection = new ArrayList(leftCollection.size() + rightCollection.size());
				sumCollection.addAll(leftCollection);
				sumCollection.addAll(rightCollection);
			}
		}
		return sumCollection;
	}

}