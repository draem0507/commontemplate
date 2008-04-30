package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.BreakVisitException;
import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.util.Location;

class BlockDirectiveProxy extends BlockDirective {

	private static final long serialVersionUID = 3260814916076018094L;

	private final BlockDirectiveImpl blockDirective;

	BlockDirectiveProxy(BlockDirectiveImpl blockDirective) {
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
		blockDirective.doRender(context); // 绕过拦截器
	}

	public String toString() {
		return blockDirective.toString();
	}

	public String getSignature() {
		return blockDirective.getSignature();
	}

}