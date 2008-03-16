package org.commontemplate.standard.operator.string;

import java.util.List;

import org.commontemplate.standard.operator.collection.IndexedBinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 字符序列操作符: ".."<br/>
 * 如: ${'A'..'Z'} ${"A".."Z"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CharSequenceSubOperatorHandler extends IndexedBinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CharSequenceSubOperatorHandler() {
		super(CharSequence.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		CharSequence str = (CharSequence)leftOperand;
		if (rightOperand instanceof IntegerSequence) {
			IntegerSequence seq = (IntegerSequence)rightOperand;
			return str.subSequence(seq.getBegin(), seq.getEnd() + 1);
		}
		List indexs = (List)rightOperand;
		StringBuffer sub = new StringBuffer(indexs.size());
		for (int i = 0, n = indexs.size(); i < n ; i ++) {
			int index = ((Integer)indexs.get(i)).intValue();
			sub.append(str.charAt(index));
		}
		return sub.toString();
	}

}