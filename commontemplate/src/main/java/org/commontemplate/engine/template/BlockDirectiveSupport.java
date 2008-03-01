package org.commontemplate.engine.template;

import java.util.Collections;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Expression;
import org.commontemplate.core.RenderingException;
import org.commontemplate.core.Visitor;
import org.commontemplate.core.event.RenderedEvent;
import org.commontemplate.core.event.RenderingEvent;

/**
 * 内部指令可关联性基类
 * 
 * @author liangfei0201@163.com
 *
 */
abstract class BlockDirectiveSupport extends BlockDirective implements java.io.Serializable {
	
	public void render(Context context) throws RenderingException {
		context.publishEvent(new RenderingEvent(this));
		try {
			context.pushLocalContext();
			try {
				doRender(context);
			} finally {
				context.popLocalContext();
			}
		} finally {
			context.publishEvent(new RenderedEvent(this));
		}
	}

	protected abstract void doRender(Context context) throws RenderingException;

	private List elements;
	
	public List getElements() {
		return elements;
	}

	void setElements(List elements) {
		this.elements = Collections.unmodifiableList(elements);
	}
	
	protected void acceptExpression(Visitor visitor) {
		Expression expression = getExpression();
		if (expression != null)
			expression.accept(visitor);
	}
	
	protected void acceptAll(Visitor visitor) {
		for (int i = 0, n = elements.size(); i < n; i ++) {
			Element directive = (Element)elements.get(i);
			directive.accept(visitor);
		}
	}

	protected String getCanonicalFormAll() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = elements.size(); i < n; i ++) {
			Element directive = (Element)elements.get(i);
			buf.append(directive.getCanonicalForm());
		}
		return buf.toString();
	}

}
