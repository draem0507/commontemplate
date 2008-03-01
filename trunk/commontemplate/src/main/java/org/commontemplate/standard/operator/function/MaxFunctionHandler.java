package org.commontemplate.standard.operator.function;

import java.util.List;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.CompareUtils;

public class MaxFunctionHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MaxFunctionHandler() {
		super(List.class);
	}
	
	public boolean isMatch(Object operand) {
		boolean m = super.isMatch(operand);
		if (! m)
			return false;
		List numbers = (List)operand;
		for (int i = 0, n = numbers.size(); i < n ; i ++) {
			if (! (numbers.get(i) instanceof Integer)) {
				return false;
			}
		}
		return true;
	}

	public Object doEvaluate(Object operand) throws Exception {
		List numbers = (List)operand;
		Number max = null;
		for (int i = 0, n = numbers.size(); i < n ; i ++) {
			Number num = (Number)numbers.get(i);
			if (i == 0 || CompareUtils.compareNumber(num, max) > 0) {
				max = num;
			}
		}
		return max;
	}

}