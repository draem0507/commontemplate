package org.commontemplate.standard.operator.bool;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 此操作符用于辅助<code>BooleanSelectOperatorHandler</code>完成三目运算
 * 
 * @see org.commontemplate.standard.operator.bool.BooleanSelectOperatorHandler
 * @author liangfei0201@163.com
 *
 */
public class SelectorEntryOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public SelectorEntryOperatorHandler() {
		super(Selector.class, Object.class, true);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		Selector selector = (Selector)leftOperand;
		if (selector.isSelected())
			return selector.getSelectedValue();
		return rightOperand;
	}

}