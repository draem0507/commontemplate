package org.commontemplate.standard.operator.number;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

public class AvgOperatorHandler extends NumbersUnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public AvgOperatorHandler() {
		super();
	}

	public Object doEvaluate(Object operand) throws Exception {
		Collection numbers = (Collection)operand;
		if (numbers.size() == 0)
			return new Integer(0);
		if (numbers instanceof IntegerSequence) {
			IntegerSequence seq = (IntegerSequence)numbers;
			int min = seq.getMin();
			int max = seq.getMax();
			return new Integer((min + max) * numbers.size() / (2 * numbers.size()));
		}
		long sum = 0;
		for (Iterator iterator = numbers.iterator(); iterator.hasNext();) {
			Number number = (Number)iterator.next();
			sum += number.intValue();
		}
		return new Integer((int)(sum / numbers.size()));
	}

}
