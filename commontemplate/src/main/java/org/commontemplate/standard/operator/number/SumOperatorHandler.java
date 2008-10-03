package org.commontemplate.standard.operator.number;

import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.standard.operator.sequence.IntegerSequence;

public class SumOperatorHandler extends NumbersUnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public SumOperatorHandler() {
		super();
	}

	public Object doEvaluateNumbers(Collection numbers) throws Exception {
		if (numbers.size() == 0)
			return new Integer(0);
		if (numbers instanceof IntegerSequence) {
			IntegerSequence seq = (IntegerSequence)numbers;
			long min = seq.getMin();
			long max = seq.getMax();
			return new Long((min + max) * numbers.size() / 2);
		}
		long sum = 0;
		for (Iterator iterator = numbers.iterator(); iterator.hasNext();) {
			Number number = (Number)iterator.next();
			sum += number.intValue();
		}
		return new Long(sum);
	}

}
