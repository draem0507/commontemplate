package org.commontemplate.engine.template;

import java.io.IOException;
import java.util.List;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.MiddleBlockDirectiveHandler;
import org.commontemplate.config.Syntax;
import org.commontemplate.config.TextFilter;
import org.commontemplate.core.Element;
import org.commontemplate.core.Expression;
import org.commontemplate.core.ExpressionParser;
import org.commontemplate.core.ParsingException;
import org.commontemplate.util.Assert;
import org.commontemplate.util.scanner.Token;

/**
 * 指令工厂
 *
 * @author liangfei0201@163.com
 *
 */
final class DirectiveProvider {

	private final Syntax syntax;

	private final DirectiveHandlerProvider directiveHandlerProvider;

	private final ExpressionParser expressionParser;

	private final TextFilter textFilter;

	private final List elementInterceptors;

	DirectiveProvider(Syntax syntax, DirectiveHandlerProvider directiveHandlerProvider,
			ExpressionParser expressionParser, TextFilter textFilter, List elementInterceptors) {
		this.syntax = syntax;
		this.directiveHandlerProvider = directiveHandlerProvider;
		this.expressionParser = expressionParser;
		this.textFilter = textFilter;
		this.elementInterceptors = elementInterceptors;
	}

	/**
	 * 识别指令
	 *
	 * @param token 指令片断
	 * @param isLast 是否为最后一个指令
	 * @return 指令
	 * @throws IOException
	 * @throws ParsingException
	 * @throws ParsingException
	 */
	Element getDirective(Token token, boolean isLast) throws ParsingException {
		String message = token.getMessage();
		String trim = message.trim();
		if (trim.length() > 1 && trim.charAt(0) == syntax.getDirectiveLeader()) {
			if (trim.charAt(1) == syntax.getNoParse()) { // 不解释块指令
				if (trim.length() < 4
						|| trim.charAt(trim.length() - 2) != syntax.getNoParse()
						|| trim.charAt(trim.length() - 1) != syntax.getDirectiveLeader())
					throw new ParsingException(token.getLocation(), "DirectiveFactory.no.parse.error", new Object[]{String.valueOf(syntax.getDirectiveLeader()), String.valueOf(syntax.getNoParse())});
				return parseText(String.valueOf(syntax.getNoParse()), token, message.substring(2, message.length() - 2));
			} else if (trim.charAt(1) == syntax.getLineComment()) { // 行注释指令
				if (trim.length() > 2
						&& trim.charAt(2) == syntax.getLineComment()) // 运行期保留
					return new CommentImpl(String.valueOf(syntax.getLineComment()), token.getLocation(), message.substring(3), trim, elementInterceptors);
				return null;
			} else if (trim.charAt(1) == syntax.getBlockComment()) { // 块注释指令
				if (trim.length() < 4
						|| trim.charAt(trim.length() - 2) != syntax.getBlockComment()
						|| trim.charAt(trim.length() - 1) != syntax.getDirectiveLeader())
					throw new ParsingException(token.getLocation(), "DirectiveFactory.block.comment.error", new Object[]{String.valueOf(syntax.getDirectiveLeader()), String.valueOf(syntax.getBlockComment())});
				if (trim.length() > 4
						&& trim.charAt(2) == syntax.getBlockComment()) // 运行期保留
					return new CommentImpl(String.valueOf(syntax.getBlockComment()), token.getLocation(), message.substring(3, message.length() - 2), trim, elementInterceptors);
				return null;
			} else { //指令
				return parseDirective(token, trim);
			}
		} else { // 文本
			return parseText("text", token, cleanEscape(message, isLast)); // 这里不能用trim, 需保留空格
		}
	}

	private Element parseText(String name, Token token, String message) {
		if (textFilter != null)
			message = textFilter.filter(message);
		return new TextImpl(name, token.getLocation(), message, elementInterceptors);
	}

	String cleanEscape(String text, boolean isLast) {
		if (text == null || text.length() < 2)
			return text;
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = text.length(); i < n ; i ++) {
			char ch = text.charAt(i);
			if (text.charAt(i) == syntax.getDirectiveLeader()
					|| (! isLast && i == n - 1 && ch == '\\')) {
				int count = countLastSlash(buf);
				Assert.assertTrue(count % 2 != 0, "DirectiveFactory.escape.error");
				int del = (count - 1) / 2 + 1;
				buf.delete(buf.length() - del, buf.length()); // 怱略反斜杠
			}
			buf.append(ch);
		}
		return buf.toString();
	}

	int countLastSlash(StringBuffer buf) {
		if (buf == null || buf.length() == 0)
			return 0;
		int count = 0;
		for (int i = buf.length() - 1; i >= 0; i --) {
			if (buf.charAt(i) == '\\') {
				count ++;
			} else {
				break;
			}
		}
		return count;
	}

	private Element parseDirective(Token token, String message) throws ParsingException {
		String name;
		Expression expression;
		int i = message.indexOf(syntax.getExpressionBegin());
		if (i > -1) {
			Assert.assertTrue(message.charAt(message.length() - 1) == syntax.getExpressionEnd(), "DirectiveFactory.miss.parenthesis");
			name = message.substring(1, i);
			String expressionText = message.substring(i + 1, message.length() - 1);
			expression = expressionParser.parseExpression(expressionText.trim());
		} else {
			name = message.substring(1, message.length());
			expression = expressionParser.parseExpression(null);
		}
		return resolveDirective(token, name.trim(), expression);
	}

	private Element resolveDirective(Token token, String name, Expression expression) throws ParsingException {
		// 结束指令
		if (syntax.getEndDirectiveName().equals(name)) {
			if (expression != null) {
				Object obj = expression.evaluate(null);
				if (obj != null && obj instanceof String)
					return new EndDirective((String)obj);
			}
			return EndDirective.END_DIRECTIVE;
		}
		// SPI指令
		DirectiveHandler handler = directiveHandlerProvider.getDirectiveHandler(name);
		if (handler == null)
			throw new ParsingException(token.getLocation(), "DirectiveFactory.handler.not.such", new Object[]{name});
		if (handler instanceof MiddleBlockDirectiveHandler)
			return new MiddleBlockDirectiveImpl(name, token.getLocation(), expression, (MiddleBlockDirectiveHandler)handler, token.getMessage(), syntax.getDirectiveLeader() + syntax.getEndDirectiveName(), elementInterceptors);
		if (handler instanceof BlockDirectiveHandler)
			return new BlockDirectiveImpl(name, token.getLocation(), expression, (BlockDirectiveHandler)handler, token.getMessage(), syntax.getDirectiveLeader() + syntax.getEndDirectiveName(), elementInterceptors);
		if (handler instanceof DirectiveHandler)
			return new DirectiveImpl(name, token.getLocation(), expression, (DirectiveHandler)handler, token.getMessage(), elementInterceptors);
		throw new ParsingException(token.getLocation(), "DirectiveFactory.handler.type.error", new Object[]{handler.getClass().getName()});
	}

}
