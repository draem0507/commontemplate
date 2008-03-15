package org.commontemplate.standard.operator.context;

import org.commontemplate.standard.operator.UnaryOperatorHandlerSupport;

/**
 * 立即执行变量一元操作符: "\"<br/>
 * 如: ${\key : value} ${bean.\property} 这里key和property将取变量,<br/>
 * 如果没有"\", 因为":"和"."号操作符的isLeftOperandNamed和isRightOperandNamed特殊状态位, 将变量名作为字符串.<br/>
 * 此操作通过加强优先级达到变量先于操作符执行.<br/>
 *
 * @see org.commontemplate.config.SpecialBinaryOperatorHandler#isLeftOperandNamed()
 * @see org.commontemplate.config.SpecialBinaryOperatorHandler#isRightOperandNamed()
 * @author liangfei0201@163.com
 *
 */
public class VariableOperatorHandler extends UnaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public VariableOperatorHandler() {
		super(Object.class);
	}

	public Object doEvaluate(Object operand)
			throws Exception {
		return operand; // 直接返回操作数, 此操作符只是加强变量优先级
	}

}
