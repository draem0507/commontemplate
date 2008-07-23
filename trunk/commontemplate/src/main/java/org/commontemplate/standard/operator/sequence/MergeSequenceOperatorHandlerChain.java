package org.commontemplate.standard.operator.sequence;

import java.util.ArrayList;
import java.util.List;

import org.commontemplate.standard.operator.BinaryOperatorHandlerChain;

/**
 * 合并展开式序列操作符。
 * 如： ${1,3..6,9} 输出展开集合 [1,3,4,5,6,9]
 * 而： ${1,(3..6),9} 输出两层集合 [1,[3,4,5,6],9]
 * 注：逗号的优先级高于双点号
 *
 * @author liangfei0201@163.com
 *
 */
public class MergeSequenceOperatorHandlerChain extends BinaryOperatorHandlerChain {

	private static final long serialVersionUID = 1L;

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		if (leftOperand instanceof List
				|| rightOperand instanceof List) {
			// 左边集合最后一个对象
			Object leftObject = leftOperand;
			if (leftOperand instanceof List) {
				List leftList = (List)leftOperand;
				if (leftList.size() > 0)
					leftObject = leftList.get(leftList.size() - 1);
			}
			// 右边对集合第一个对象
			Object rightObject = rightOperand;
			if (rightOperand instanceof List) {
				List rightList = (List)rightOperand;
				if (rightList.size() > 0)
					rightObject = rightList.get(0);
			}
			// 将左边集合最后一个对象和右边对集合第一个对象组成序列
			List seq = (List)super.doEvaluate(leftObject, rightObject);
			// 添加左边集合剩余的对象
			List list = new ArrayList();
			if (leftOperand instanceof List) {
				List leftList = (List)leftOperand;
				if (leftList.size() > 1) {
					for (int i = 0; i < leftList.size() - 1; i ++) { // 去掉最后一个
						list.add(leftList.get(i));
					}
				}
			}
			// 添加序列集合对象
			if (seq != null && seq.size() > 0) {
				for (int i = 0; i < seq.size(); i ++) {
					list.add(seq.get(i));
				}
			}
			// 添加右边集合剩余的对象
			if (rightOperand instanceof List) {
				List rightList = (List)rightOperand;
				if (rightList.size() > 1) {
					for (int i = 1; i < rightList.size(); i ++) { // 去掉第一个
						list.add(rightList.get(i));
					}
				}
			}
			// 返回列表
			return list;
		} else {
			return super.doEvaluate(leftOperand, rightOperand);
		}
	}

}