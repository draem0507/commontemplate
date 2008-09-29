package org.commontemplate.standard.scope;

import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;

public class ElementScopeHandler implements ScopeHandler, Serializable {

	private static final long serialVersionUID = 1L;

	public Object getScopeVariable(Context context, int level) {
		if (level == 0)
			return context.getCurrentElement();
		List elements = context.getElementStackValues();
		if (elements != null
				&& elements.size() > 0
				&& elements.size() > level) {
			return (Element)elements.get(elements.size() - level - 1);
		}
		return null;
	}

}
