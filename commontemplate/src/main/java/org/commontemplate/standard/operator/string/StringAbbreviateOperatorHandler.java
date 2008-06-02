package org.commontemplate.standard.operator.string;

import org.apache.commons.lang.StringUtils;
import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

public class StringAbbreviateOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public StringAbbreviateOperatorHandler() {
		super(String.class, Number.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
				
		String str = (String)leftOperand;		
		int len = ((Number)rightOperand).intValue();
		if (len <= 3) {
			len = 4;
			// TODO 输出warning log, 提示用户， len 应该 >=4
		}	
		return StringUtils.abbreviate(str, len);
	}

}
