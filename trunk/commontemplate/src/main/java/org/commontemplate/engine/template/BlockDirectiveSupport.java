package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Element;
import org.commontemplate.core.Visitor;

/**
 * 内部指令可关联性基类
 *
 * @author liangfei0201@163.com
 *
 */
abstract class BlockDirectiveSupport extends BlockDirective implements java.io.Serializable {

	private List elements;

	public List getElements() {
		return elements;
	}

	void setElements(List elements) {
		this.elements = Collections.unmodifiableList(elements);
	}

	protected int acceptElements(Visitor visitor) {
		for (int i = 0, n = elements.size(); i < n; i ++) {
			Element element = (Element)elements.get(i);
			int v = element.accept(visitor);
			if (v == Visitor.STOP)
				return Visitor.STOP;
		}
		return Visitor.NEXT;
	}

	protected String getElementsSource() throws IOException {
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = elements.size(); i < n; i ++) {
			Element element = (Element)elements.get(i);
			buf.append(element.getSource());
		}
		return buf.toString();
	}

}
