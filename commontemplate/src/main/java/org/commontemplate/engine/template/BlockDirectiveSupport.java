package org.commontemplate.engine.template;

import java.util.Collections;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Element;
import org.commontemplate.core.TemplateVisitor;

/**
 * 内部指令可关联性基类
 *
 * @author liangfei0201@163.com
 *
 */
abstract class BlockDirectiveSupport extends BlockDirective implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private List elements;

	public List getElements() {
		return elements;
	}

	void setElements(List elements) {
		this.elements = Collections.unmodifiableList(elements);
	}

	protected void acceptElements(TemplateVisitor visitor) {
		for (int i = 0, n = elements.size(); i < n; i ++) {
			Element element = (Element)elements.get(i);
			element.accept(visitor, false);
		}
	}

	protected String getElementsSource() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = elements.size(); i < n; i ++) {
			Element element = (Element)elements.get(i);
			buf.append(element.getSource());
		}
		return buf.toString();
	}

}
