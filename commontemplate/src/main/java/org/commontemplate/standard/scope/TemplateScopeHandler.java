package org.commontemplate.standard.scope;

import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;

public class TemplateScopeHandler implements ScopeHandler, Serializable {

	private static final long serialVersionUID = 1L;

	public Object getScopeVariable(Context context, int level) {
		if (level == 0)
			return context.getCurrentTemplate();
		List templates = context.getTemplateStackValues();
		if (templates != null
				&& templates.size() > 0
				&& templates.size() > level) {
			return (Template)templates.get(templates.size() - level - 1);
		}
		return null;
	}

}
