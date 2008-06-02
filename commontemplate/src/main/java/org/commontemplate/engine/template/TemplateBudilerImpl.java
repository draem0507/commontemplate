package org.commontemplate.engine.template;

import java.io.IOException;

import org.commontemplate.core.Expression;
import org.commontemplate.core.Template;
import org.commontemplate.core.TemplateBudiler;

final class TemplateBudilerImpl implements TemplateBudiler {

	public void addComment(String comment) {
		// TODO Auto-generated method stub

	}

	public void addDirective(String directiveName, Expression expression) {
		// TODO Auto-generated method stub

	}

	public void addText(String text) {
		// TODO Auto-generated method stub

	}

	public Template getTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void endBlockDirective() {
		// TODO Auto-generated method stub

	}

	public void beginBlockDirective(String directiveName, Expression expression) {
		// TODO Auto-generated method stub

	}

	private String templateName;

	public void beginTemplate(String templateName) {
		this.templateName = templateName;
	}

	private Template template;

	public void endTemplate() {
		try {
			template = new TemplateImpl(null, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
