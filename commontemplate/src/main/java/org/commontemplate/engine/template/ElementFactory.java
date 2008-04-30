package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Text;

public class ElementFactory {

	public Comment createComment(String comment) {
		return null;
	}

	public Text createText(String text) {
		return null;
	}

	public Directive createDirective(String name, String expression) {
		return null;
	}

	public BlockDirective createBlockDirective(String name, String expression, List elements) {
		return null;
	}

}
