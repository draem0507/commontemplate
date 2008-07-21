package org.commontemplate.standard.operator.string;

import org.commontemplate.standard.operator.BinaryOperatorHandlerSupport;

/**
 * 名称对操作符. 使用如：
 * name1 -&gt; name2
 *
 * @author liangfei0201@163.com
 *
 */
public class NamePairOperatorHandler extends BinaryOperatorHandlerSupport {

	private static final long serialVersionUID = 1L;

	public NamePairOperatorHandler() {
		super(String.class, String.class);
	}

	public Object doEvaluate(Object leftOperand, Object rightOperand) throws Exception {
		return new NamePair((String)leftOperand, (String)rightOperand);
	}

}