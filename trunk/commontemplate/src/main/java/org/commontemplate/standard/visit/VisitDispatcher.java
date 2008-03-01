package org.commontemplate.standard.visit;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.BreakVisitException;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Template;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;
import org.commontemplate.core.Visitable;
import org.commontemplate.core.Visitor;

/**
 * 分类访问派遣器
 * 
 * @author liangfei0201@163.com
 *
 */
public abstract class VisitDispatcher implements Visitor {

	public final void visit(Visitable node) throws BreakVisitException {
		if (node instanceof Template)
			visitTemplate((Template)node);
		else if (node instanceof BlockDirective)
			visitBlockDirective((BlockDirective)node);
		else if (node instanceof Directive)
			visitLineDirective((Directive)node);
		else if (node instanceof Text)
			visitText((Text)node);
		else if (node instanceof Comment)
			visitComment((Comment)node);
		else if (node instanceof BinaryOperator)
			visitBinaryOperator((BinaryOperator)node);
		else if (node instanceof UnaryOperator)
			visitUnaryOperator((UnaryOperator)node);
		else if (node instanceof Variable)
			visitVariable((Variable)node);
		else if (node instanceof Constant)
			visitConstant((Constant)node);
	}
	
	public void visitTemplate(Template node) {}
	
	public void visitBlockDirective(BlockDirective node) {}
	
	public void visitLineDirective(Directive node) {}
	
	public void visitText(Text node) {}
	
	public void visitComment(Comment node) {}
	
	public void visitBinaryOperator(BinaryOperator node) {}
	
	public void visitUnaryOperator(UnaryOperator node) {}
	
	public void visitVariable(Variable node) {}
	
	public void visitConstant(Constant node) {}

}
