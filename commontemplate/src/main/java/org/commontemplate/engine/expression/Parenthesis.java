package org.commontemplate.engine.expression;

import java.util.List;

import org.commontemplate.core.EvaluationException;
import org.commontemplate.core.Operator;
import org.commontemplate.core.VariableResolver;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 括号, 加强优先级.
 * (单例)
 * 
 * @author liangfei0201@163.com
 *
 */
final class Parenthesis extends Operator {

	private static final long serialVersionUID = 1L;

	static final Parenthesis LEFT_PARENTHESIS = new Parenthesis("(");
	
	static final Parenthesis RIGHT_PARENTHESIS = new Parenthesis(")");
	
	private final String name;

	private Parenthesis(String name) {
		this.name = name;
	}

	public Object evaluate(VariableResolver context) throws EvaluationException {
		throw new UnsupportedOperationException("括号只作为优先级标识, 不应出现在结果树中, 不能执行运算!");
	}

	public void accept(Visitor visitor) {
		throw new UnsupportedOperationException("括号只作为优先级标识, 不应出现在结果树中, 也就不能被访问到!");
	}

	public List getOperands() {
		return null;
	}

	public Location getLocation() {
		return null;
	}

	public String getName() {
		return name;
	}

	public String getCanonicalForm() {
		return name;
	}

	public int getPriority() {
		return Integer.MAX_VALUE;
	}

}