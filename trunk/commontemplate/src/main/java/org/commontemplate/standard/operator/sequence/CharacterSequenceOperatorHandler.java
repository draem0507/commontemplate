package org.commontemplate.standard.operator.sequence;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字符序列操作符: ".."<br/>
 * 如: ${"A".."Z"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CharacterSequenceOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CharacterSequenceOperatorHandler() {
		super(String.class, String.class);
	}

	public boolean isMatch(Object leftOperand, Object rightOperand) {
		boolean m = super.isMatch(leftOperand, rightOperand);
		if (! m)
			return false;
		if (((String)leftOperand).length() != 1 || ((String)rightOperand).length() != 1)
			return false;
		return true;
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new CharacterSequence(((String)leftOperand).charAt(0), ((String)rightOperand).charAt(0));
	}

}