package org.commontemplate.engine.template;

import org.commontemplate.core.Context;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 结束指令
 * 
 * @author liangfei0201@163.com
 *
 */
final class EndDirective extends Directive {

	private static final long serialVersionUID = 1L;
	
	static final EndDirective END_DIRECTIVE = new EndDirective();
	
	private EndDirective() {}

	public void render(Context context) throws RenderingException {
		throw new UnsupportedOperationException("此指令为结束标识, 不应出现在指令树中, 也不应被执行!");
	}

	public void accept(Visitor visitor) {
		throw new UnsupportedOperationException("此指令为结束标识, 不应出现在指令树中, 也不应被执行!");
	}

	public Expression getExpression() {
		return null;
	}

	public String getCanonicalForm() {
		return "$end";
	}
	
	public Location getLocation() {
		return null;
	}

	public String getName() {
		return "end";
	}

}
