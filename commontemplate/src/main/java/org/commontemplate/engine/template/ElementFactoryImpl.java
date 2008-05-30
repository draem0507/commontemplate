package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Directive;
import org.commontemplate.core.ElementFactory;
import org.commontemplate.core.Expression;
import org.commontemplate.core.Text;

class ElementFactoryImpl implements ElementFactory {

	public Comment createComment(String comment) {
		return null;
	}

	public Text createText(String text) {
		return null;
	}

	public Directive createDirective(String name, Expression expression) {
		return null;
	}

	public BlockDirective createBlockDirective(String name, Expression expression, List elements) {
		return null;
	}

}
