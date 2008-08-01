package org.commontemplate.standard.operator.number;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 数字集合操作数一元操作符基类
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class NumbersUnaryOperatorHandlerSupport extends UnaryOperatorHandlerSupport {

	public NumbersUnaryOperatorHandlerSupport() {
		super(Collection.class);
	}

	public boolean isMatch(Object operand) {
		boolean m = super.isMatch(operand);
		if (! m)
			return false;
		if (operand instanceof IntegerSequence)
			return true;
		Collection numbers = (Collection)operand;
		for (Iterator iterator = numbers.iterator(); iterator.hasNext();) {
			if (! (iterator.next() instanceof Number)) {
				return false;
			}
		}
		return true;
	}

}
