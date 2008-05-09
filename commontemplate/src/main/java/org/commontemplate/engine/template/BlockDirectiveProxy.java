package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.BreakVisitException;
import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

/**
 * 块指令代理
 *
 * @author liangfei0201@163.com
 *
 */
class BlockDirectiveProxy extends BlockDirective {

	private static final long serialVersionUID = 3260814916076018094L;

	private final BlockDirective blockDirective;

	BlockDirectiveProxy(BlockDirective blockDirective) {
		this.blockDirective = blockDirective;
	}

	public void accept(Visitor visitor) throws BreakVisitException {
		blockDirective.accept(visitor);
	}

	public boolean equals(Object obj) {
		return blockDirective.equals(obj);
	}

	public String getCanonicalForm() {
		return blockDirective.getCanonicalForm();
	}

	public List getElements() {
		return blockDirective.getElements();
	}

	public Expression getExpression() {
		return blockDirective.getExpression();
	}

	public Location getLocation() {
		return blockDirective.getLocation();
	}

	public String getName() {
		return blockDirective.getName();
	}

	public int hashCode() {
		return blockDirective.hashCode();
	}

	public void render(Context context) throws RenderingException {
		throw new java.lang.UnsupportedOperationException("不能在指令处理器内回调render,否则将死循环调用.");
	}

	public String toString() {
		return blockDirective.toString();
	}

	public String getSignature() {
		return blockDirective.getSignature();
	}

}
