package org.commontemplate.standard.operator.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class CollectionRepeatOpreatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionRepeatOpreatorHandler() {
		super(Collection.class, Integer.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		Collection src = (Collection)leftOperand;
		int repeat = ((Integer)rightOperand).intValue();
		Collection dest = null; // 新建Collection对象, 以保证不改变原有数据模型
		try {
			dest = (Collection) src.getClass().newInstance();
			for (int i = 0; i < repeat; i ++) {
				dest.addAll(src); // 有些集合可能不支持addAll操作, 所以放在try内
			}
		} catch (Exception e) {
			if (src instanceof Set) { // 默认采用ArrayList
				dest = new HashSet(src.size() * repeat);
				for (int i = 0; i < repeat; i ++) {
					dest.addAll(src);
				}
			} else { // 默认采用ArrayList
				dest = new ArrayList(src.size() * repeat);
				for (int i = 0; i < repeat; i ++) {
					dest.addAll(src);
				}
			}
		}
		return dest;
	}

}