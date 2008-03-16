package org.commontemplate.standard.operator.function;

import java.util.List;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;
import org.commontemplate.util.CompareUtils;

/**
 * 最小值静态函数操作符: "min"<br/>
 * 如: ${min(3,11,7) + 1}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class MinFunctionHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public MinFunctionHandler() {
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
		Number min = null;
		for (int i = 0, n = numbers.size(); i < n ; i ++) {
			Number num = (Number)numbers.get(i);
			if (i == 0 || CompareUtils.compareNumber(num, min) < 0) {
				min = num;
			}
		}
		return min;
	}

}
