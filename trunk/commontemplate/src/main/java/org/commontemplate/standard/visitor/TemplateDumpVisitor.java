package org.commontemplate.standard.visitor;

import java.io.IOException;
import java.io.Writer;

import org.commontemplate.config.Syntax;
import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.core.TemplateVisitor;
import org.commontemplate.util.Assert;

public class TemplateDumpVisitor extends TemplateVisitor {

	private final Writer writer;

	private final Syntax syntax;

	public TemplateDumpVisitor(Writer writer) {
		this(writer, Syntax.DEFAULT);
	}

	public TemplateDumpVisitor(Writer writer, Syntax syntax) {
		Assert.assertNotNull(writer);
		Assert.assertNotNull(syntax);
		this.writer = writer;
		this.syntax = syntax;
	}

	private void write(char ch) {
		write(String.valueOf(ch));
	}

	private void write(String str) {
		try {
			writer.write(str);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void visitBinaryOperator(BinaryOperator binaryOperator) {

		write(binaryOperator.getName());
	}

	public void visitBlockDirective(BlockDirective blockDirective) {
		write(syntax.getDirectiveLeader());
		write(blockDirective.getName());
	}

	public void visitComment(Comment comment) {
		write(syntax.getDirectiveLeader());
		write(syntax.getBlockComment());
		write(comment.getValue());
		write(syntax.getBlockComment());
		write(syntax.getDirectiveLeader());
	}

	public void visitConstant(Constant constant) {
		write(String.valueOf(constant.getValue()));
	}

	public void visitDirective(Directive directive) {
		write(directive.getName());
	}

	public void visitEndBlockDirective() {
		write("end");
	}

	public void visitText(Text text) {
		write(text.getValue());
	}

	public void visitUnaryOperator(UnaryOperator unaryOperator) {
		write(unaryOperator.getName());
	}

	public void visitVariable(Variable variable) {
		write(variable.getName());
	}

}
