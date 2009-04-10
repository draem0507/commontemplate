package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 字面列表操作符: ","<br/>
 * 如: ${"aa","bb","cc"}<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class CollectionOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public CollectionOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
	    LiteralList literalList = new LiteralList();
		literalList.add(leftOperand);
		literalList.add(rightOperand);
		return literalList;
	}

}
