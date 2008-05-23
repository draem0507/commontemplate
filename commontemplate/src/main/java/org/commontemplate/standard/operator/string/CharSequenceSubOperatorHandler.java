package org.commontemplate.standard.operator.string;

import java.util.List;

import org.commontemplate.standard.operator.collection.IndexedBinaryOperatorHandlerSupport;
import org.commontemplate.standard.operator.sequence.IntegerSequence;

/**
 * 字符串子序列操作符: "[]"<br/>
 * 如: ${'abcd'[0..2]} 输出: "abc"<br/>
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
		CharSequence list = (CharSequence)leftOperand;
		StringBuffer sub = new StringBuffer();
		if (rightOperand instanceof IntegerSequence) {
			IntegerSequence indexs = (IntegerSequence) rightOperand;
			if (indexs.isAscending()) { // 升序序列
				for (int i = indexs.getMin(); i < list.length()
						&& i <= indexs.getMax(); i++) {
					if (i >= 0)
						sub.append(list.charAt(i));
				}
			} else { // 降序序列
				for (int i = indexs.getMax(); i >= 0
						&& i >= indexs.getMin(); i--) {
					if (i < list.length())
						sub.append(list.charAt(i));
				}
			}
		} else { // 散列索引
			List indexs = (List) rightOperand;
			for (int i = 0, n = indexs.size(); i < n; i++) {
				int index = ((Integer) indexs.get(i)).intValue();
				if (index >= 0 && index < list.length())
					sub.append(list.charAt(index));
			}
		}
		return sub.toString();
	}

}