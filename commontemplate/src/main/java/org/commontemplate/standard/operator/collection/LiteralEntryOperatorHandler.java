package org.commontemplate.standard.operator.collection;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;
import org.commontemplate.util.MapEntry;

/**
 * 字面键值对操作符: ":"<br/>
 * 如: ${key : value}<br/>
 * 注: 此处理器应放在处理器链的最后, 其不再调用nextHandler.<br/>
 *
 * @author liangfei0201@163.com
 *
 */
public class LiteralEntryOperatorHandler extends BinaryOperatorHandlerSupport {

	public LiteralEntryOperatorHandler() {
		super(Object.class, Object.class, true);
	}

	private static final long serialVersionUID = 1L;

	public Object doEvaluate(Object leftOperand, Object rightOperand)
			throws Exception {
		return new MapEntry(leftOperand, rightOperand);
	}

}