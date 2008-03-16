package org.commontemplate.standard.operator.collection;

import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 数字下标操作符基类<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class IndexedBinaryOperatorHandlerSupport extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public IndexedBinaryOperatorHandlerSupport(Class leftOperandClass) {
		super(leftOperandClass, List.class);
	}

	public boolean isMatch(Object leftOperand, Object rightOperand) {
		boolean m = super.isMatch(leftOperand, rightOperand);
		if (! m)
			return false;
		List indexs = (List)rightOperand;
		for (int i = 0, n = indexs.size(); i < n ; i ++) {
			if (! (indexs.get(i) instanceof Integer)) {
				return false;
			}
		}
		return true;
	}

}
