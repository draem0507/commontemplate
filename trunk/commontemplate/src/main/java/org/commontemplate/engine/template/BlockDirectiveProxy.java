package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.I18nExceptionFactory;
import org.commontemplate.util.Location;

/**
 * 块指令代理
 *
 * @author liangfei0201@163.com
 *
 */
class BlockDirectiveProxy extends BlockDirective {

	private static final long serialVersionUID = 3260814916076018094L;

	private final BlockDirectiveImpl blockDirective;

	BlockDirectiveProxy(BlockDirectiveImpl blockDirective) {
		this.blockDirective = blockDirective;
	}

	public void accept(TemplateVisitor visitor) {
		blockDirective.accept(visitor);
	}

	public String getSource() throws IOException {
		return blockDirective.getSource();
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

	public void render(Context context) throws RenderingException {
		throw I18nExceptionFactory.createUnsupportedOperationException("BlockDirectiveProxy.cycle.render", new Object[]{getName()});
	}

	public int hashCode() {
		return blockDirective.hashCode();
	}

	public boolean equals(Object obj) {
		return blockDirective.equals(obj);
	}

	public String toString() {
		return blockDirective.toString();
	}

	public Template getTemplate() {
		return blockDirective.getTemplate();
	}

	BlockDirectiveImpl getTarget() {
		return blockDirective;
	}

}
