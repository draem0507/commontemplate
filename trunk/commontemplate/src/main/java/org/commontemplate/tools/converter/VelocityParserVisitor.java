package org.commontemplate.tools.converter;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.runtime.parser.ParserVisitor;
import org.apache.velocity.runtime.parser.node.ASTAddNode;
import org.apache.velocity.runtime.parser.node.ASTAndNode;
import org.apache.velocity.runtime.parser.node.ASTAssignment;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.ASTComment;
import org.apache.velocity.runtime.parser.node.ASTDirective;
import org.apache.velocity.runtime.parser.node.ASTDivNode;
import org.apache.velocity.runtime.parser.node.ASTEQNode;
import org.apache.velocity.runtime.parser.node.ASTElseIfStatement;
import org.apache.velocity.runtime.parser.node.ASTElseStatement;
import org.apache.velocity.runtime.parser.node.ASTEscape;
import org.apache.velocity.runtime.parser.node.ASTEscapedDirective;
import org.apache.velocity.runtime.parser.node.ASTExpression;
import org.apache.velocity.runtime.parser.node.ASTFalse;
import org.apache.velocity.runtime.parser.node.ASTFloatingPointLiteral;
import org.apache.velocity.runtime.parser.node.ASTGENode;
import org.apache.velocity.runtime.parser.node.ASTGTNode;
import org.apache.velocity.runtime.parser.node.ASTIdentifier;
import org.apache.velocity.runtime.parser.node.ASTIfStatement;
import org.apache.velocity.runtime.parser.node.ASTIntegerLiteral;
import org.apache.velocity.runtime.parser.node.ASTIntegerRange;
import org.apache.velocity.runtime.parser.node.ASTLENode;
import org.apache.velocity.runtime.parser.node.ASTLTNode;
import org.apache.velocity.runtime.parser.node.ASTMap;
import org.apache.velocity.runtime.parser.node.ASTMethod;
import org.apache.velocity.runtime.parser.node.ASTModNode;
import org.apache.velocity.runtime.parser.node.ASTMulNode;
import org.apache.velocity.runtime.parser.node.ASTNENode;
import org.apache.velocity.runtime.parser.node.ASTNotNode;
import org.apache.velocity.runtime.parser.node.ASTObjectArray;
import org.apache.velocity.runtime.parser.node.ASTOrNode;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.ASTSetDirective;
import org.apache.velocity.runtime.parser.node.ASTStop;
import org.apache.velocity.runtime.parser.node.ASTStringLiteral;
import org.apache.velocity.runtime.parser.node.ASTSubtractNode;
import org.apache.velocity.runtime.parser.node.ASTText;
import org.apache.velocity.runtime.parser.node.ASTTrue;
import org.apache.velocity.runtime.parser.node.ASTWord;
import org.apache.velocity.runtime.parser.node.ASTprocess;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.commontemplate.core.TemplateBudiler;
import org.commontemplate.engine.Engine;

public class VelocityParserVisitor implements ParserVisitor {

	private Engine engine;

	private Writer writer;

	private TemplateBudiler templateBudiler;

	public VelocityParserVisitor(Engine engine, String templateName, Writer writer) {
		this.engine = engine;
		this.writer = writer;
		templateBudiler = engine.getTemplateBudiler(templateName);
 	}

	private void write(String str) {
		try {
			writer.write(str);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public Object visit(SimpleNode node, Object data) {
		return null;
	}

	public Object visit(ASTprocess node, Object data) {
		return null;
	}

	public Object visit(ASTEscapedDirective node, Object data) {
		try {
			((Writer)data).write("\\");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visit(ASTEscape node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTComment node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTFloatingPointLiteral node, Object data) {
		// TODO Auto-generated method stub
		node.value(null);
		return null;
	}

	public Object visit(ASTIntegerLiteral node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTStringLiteral node, Object data) {
		// TODO Auto-generated method stub
 		return null;
	}

	public Object visit(ASTIdentifier node, Object data) {
		// TODO Auto-generated method stub
		node.getLine();
		return null;
	}

	public Object visit(ASTWord node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTDirective node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTBlock node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTMap node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTObjectArray node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTIntegerRange node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTMethod node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTReference node, Object data) {
		return null;
	}

	public Object visit(ASTTrue node, Object data) {
		try {
			((Writer)data).write("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visit(ASTFalse node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTText node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTIfStatement node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTElseStatement node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTElseIfStatement node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTSetDirective node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTStop node, Object data) {
		write("stop");
		templateBudiler.addDirective("stop", null);
		return null;
	}

	public Object visit(ASTExpression node, Object data) {
		write("expr");
		return null;
	}

	public Object visit(ASTAssignment node, Object data) {
		write("=");
		engine.getExpressionBuilder().addBinaryOperator("=");
		return null;
	}

	public Object visit(ASTOrNode node, Object data) {
		write("||");
		engine.getExpressionBuilder().addBinaryOperator("||");
		return null;
	}

	public Object visit(ASTAndNode node, Object data) {
		write("&&");
		engine.getExpressionBuilder().addBinaryOperator("&&");
		return null;
	}

	public Object visit(ASTEQNode node, Object data) {
		write("==");
		engine.getExpressionBuilder().addBinaryOperator("==");
		return null;
	}

	public Object visit(ASTNENode node, Object data) {
		write("!=");
		engine.getExpressionBuilder().addBinaryOperator("!=");
		return null;
	}

	public Object visit(ASTLTNode node, Object data) {
		write("<");
		engine.getExpressionBuilder().addBinaryOperator("<");
		return null;
	}

	public Object visit(ASTGTNode node, Object data) {
		write(">");
		engine.getExpressionBuilder().addBinaryOperator(">");
		return null;
	}

	public Object visit(ASTLENode node, Object data) {
		write("<=");
		engine.getExpressionBuilder().addBinaryOperator("<=");
		return null;
	}

	public Object visit(ASTGENode node, Object data) {
		write(">=");
		engine.getExpressionBuilder().addBinaryOperator(">=");
		return null;
	}

	public Object visit(ASTAddNode node, Object data) {
		write("+");
		engine.getExpressionBuilder().addBinaryOperator("+");
		return null;
	}

	public Object visit(ASTSubtractNode node, Object data) {
		write("-");
		engine.getExpressionBuilder().addBinaryOperator("-");
		return null;
	}

	public Object visit(ASTMulNode node, Object data) {
		write("*");
		engine.getExpressionBuilder().addBinaryOperator("*");
		return null;
	}

	public Object visit(ASTDivNode node, Object data) {
		write("/");
		engine.getExpressionBuilder().addBinaryOperator("/");
		return null;
	}

	public Object visit(ASTModNode node, Object data) {
		write("%");
		engine.getExpressionBuilder().addBinaryOperator("%");
		return null;
	}

	public Object visit(ASTNotNode node, Object data) {
		write("!");
		engine.getExpressionBuilder().addUnaryOperator("!");
		return null;
	}

}
