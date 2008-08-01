package org.commontemplate.standard.operator.collection;

import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 数字下标二元操作符基类
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class IndexedBinaryOperatorHandlerSupport extends BinaryOperatorHandlerSupport {

	public IndexedBinaryOperatorHandlerSupport(Class leftOperandClass) {
		super(leftOperandClass, List.class);
	}

	public boolean isMatch(Object leftOperand, Object rightOperand) {
		boolean m = super.isMatch(leftOperand, rightOperand);
		if (! m)
			return false;
		if (rightOperand instanceof IntegerSequence)
			return true;
		List indexs = (List)rightOperand;
		for (int i = 0, n = indexs.size(); i < n ; i ++) {
			if (! (indexs.get(i) instanceof Integer)) {
				return false;
			}
		}
		return true;
	}

}
