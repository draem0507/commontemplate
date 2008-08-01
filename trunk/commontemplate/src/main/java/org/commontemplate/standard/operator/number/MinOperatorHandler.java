package org.commontemplate.standard.operator.number;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

public class MinOperatorHandler extends NumbersUnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MinOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object operand) throws Exception {
		Collection numbers = (Collection)operand;
		if (numbers instanceof IntegerSequence) {
			IntegerSequence seq = (IntegerSequence)numbers;
			return new Integer(seq.getMax());
		}
		int min = Integer.MAX_VALUE;
		if (numbers.size() > 0) {
			for (Iterator iterator = numbers.iterator(); iterator.hasNext();) {
				Number number = (Number)iterator.next();
				int value = number.intValue();
				if (min > value)
					min = value;
			}
		}
		return new Integer(min);
	}

}
