package org.commontemplate.standard.operator.number;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

public class MaxOperatorHandler extends NumbersUnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MaxOperatorHandler() {
		super();
	}

	public Object doEvaluateNumbers(Collection numbers) throws Exception {
		if (numbers instanceof IntegerSequence) {
			IntegerSequence seq = (IntegerSequence)numbers;
			return new Integer(seq.getMax());
		}
		int max = Integer.MIN_VALUE;
		if (numbers.size() > 0) {
			for (Iterator iterator = numbers.iterator(); iterator.hasNext();) {
				Number number = (Number)iterator.next();
				int value = number.intValue();
				if (max < value)
					max = value;
			}
		}
		return new Integer(max);
	}

}
