package org.commontemplate.standard.visit;

import org.commontemplate.core.BreakVisitException;
import org.commontemplate.core.Element;
import org.commontemplate.core.FilteredVisitor;
import org.commontemplate.core.Visitable;

/**
 * 模板元素访问器
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class ElementVisitor implements FilteredVisitor {

	public boolean isVisit(Visitable node) {
		return (node instanceof Element);
	}

	// 接口适配
	public final void visit(Visitable node) throws BreakVisitException {
		visit((Element)node);
	}

	/**
	 * 访问到了某个模板元素
	 *
	 * @param element 模板元素
	 * @throws BreakVisitException 当需要停止访问时抛出
	 */
	public abstract void visit(Element element) throws BreakVisitException;

}
