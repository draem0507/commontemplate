package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 两个集合数据相加
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

		/* 允许不同类型集合相加
		if(! leftCollection.getClass().getName().equals(rightCollection.getClass().getName())) {
			Assert.fail("leftOperand and rightOperand is not same implement class!");
		}*/

		Collection sumCollection = null; // 新建Collection对象, 以保证不改变原有数据模型
		try {
			sumCollection = (Collection) leftCollection.getClass().newInstance();
		} catch (Exception e) { // 当左集合实现类没有无参构造子时, 试用右集合实现类
			try {
				sumCollection = (Collection) rightCollection.getClass().newInstance();
			} catch (Exception e2) { // 默认采用ArrayList
				sumCollection = new ArrayList(leftCollection.size() + rightCollection.size());
			}
		}
		sumCollection.addAll(leftCollection);
		sumCollection.addAll(rightCollection);
		return sumCollection;
	}

}