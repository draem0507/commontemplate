package org.commontemplate.standard.visit;

import org.commontemplate.core.BinaryOperator;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Comment;
import org.commontemplate.core.Constant;
import org.commontemplate.core.Directive;
import org.commontemplate.core.Template;
import org.commontemplate.core.Text;
import org.commontemplate.core.UnaryOperator;
import org.commontemplate.core.Variable;

/**
 * 节点个数统计访问者
 * 
 * @author liangfei0201@163.com
 *
 */
public class CountVisitor extends VisitDispatcher {
	
	private int templateCount = 0;
	
	public void visitTemplate(Template node) {
		templateCount ++;
	}
	
	private int blockDirectiveCount = 0;
	
	public void visitBlockDirective(BlockDirective node) {
		blockDirectiveCount ++;
	}
	
	private int lineDirectiveCount = 0;
	
	public void visitLineDirective(Directive node) {
		lineDirectiveCount ++;
	}
	
	private int textCount = 0;
	
	public void visitText(Text node) {
		textCount ++;
	}
	
	private int commentCount = 0;
	
	public void visitComment(Comment node) {
		commentCount ++;
	}
	
	private int binaryOperatorCount = 0;
	
	public void visitBinaryOperator(BinaryOperator node) {
		binaryOperatorCount ++;
	}
	
	private int unaryOperatorCount = 0;
	
	public void visitUnaryOperator(UnaryOperator node) {
		unaryOperatorCount ++;
	}
	
	private int variableCount = 0;
	
	public void visitVariable(Variable node) {
		variableCount ++;
	}
	
	private int constantCount = 0;
	
	public void visitConstant(Constant node) {
		constantCount ++;
	}

	public int getTemplateCount() {
		return templateCount;
	}

	public int getBlockDirectiveCount() {
		return blockDirectiveCount;
	}

	public int getLineDirectiveCount() {
		return lineDirectiveCount;
	}

	public int getTextCount() {
		return textCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getBinaryOperatorCount() {
		return binaryOperatorCount;
	}

	public int getUnaryOperatorCount() {
		return unaryOperatorCount;
	}

	public int getVariableCount() {
		return variableCount;
	}

	public int getConstantCount() {
		return constantCount;
	}

}
