package org.commontemplate.standard.visitor;

import java.io.IOException;
import java.io.Writer;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.Constant;
import org.commontemplate.core.ExpressionVisitor;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.util.Assert;

public class ExpressionDumpVisitor extends ExpressionVisitor {

	private final Writer writer;

	public ExpressionDumpVisitor(Writer writer) {
		Assert.assertNotNull(writer);
		this.writer = writer;
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

	public void visitConstant(Constant constant) {
		// TODO Auto-generated method stub
		super.visitConstant(constant);
	}

	public void visitUnaryOperator(UnaryOperator unaryOperator) {
		// TODO Auto-generated method stub
		super.visitUnaryOperator(unaryOperator);
	}

	public void visitVariable(Variable variable) {
		// TODO Auto-generated method stub
		super.visitVariable(variable);
	}

}
