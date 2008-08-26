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
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Variable;
import org.commontemplate.engine.expression.ExpressionEngine;
import org.commontemplate.util.Assert;
import org.commontemplate.util.Location;
import org.commontemplate.util.TypeUtils;
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

	private final ExpressionEngine expressionEngine;

	private final TextFilter textFilter;

	private final List renderInterceptors;

	DirectiveProvider(Syntax syntax, DirectiveHandlerProvider directiveHandlerProvider,
			ExpressionEngine expressionParser, TextFilter textFilter, List renderInterceptors) {
		this.syntax = syntax;
		this.directiveHandlerProvider = directiveHandlerProvider;
		this.expressionEngine = expressionParser;
		this.textFilter = textFilter;
		this.renderInterceptors = renderInterceptors;
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
				String value = message.substring(2, message.length() - 2);
				value = cleanStringEscape(value, String.valueOf(syntax.getNoParse()) + String.valueOf(syntax.getDirectiveLeader()));
				return parseText(String.valueOf(syntax.getNoParse()), token, value);
			} else if (trim.charAt(1) == syntax.getLineComment()) { // 行注释指令
				if (trim.length() > 2
						&& trim.charAt(2) == syntax.getLineComment()) // 运行期保留
					return new CommentImpl(String.valueOf(syntax.getLineComment()), token.getLocation(), message.substring(3), trim, renderInterceptors);
				return null;
			} else if (trim.charAt(1) == syntax.getBlockComment()) { // 块注释指令
				if (trim.length() < 4
						|| trim.charAt(trim.length() - 2) != syntax.getBlockComment()
						|| trim.charAt(trim.length() - 1) != syntax.getDirectiveLeader())
					throw new ParsingException(token.getLocation(), "DirectiveFactory.block.comment.error", new Object[]{String.valueOf(syntax.getDirectiveLeader()), String.valueOf(syntax.getBlockComment())});
				if (trim.length() > 4
						&& trim.charAt(2) == syntax.getBlockComment()) { // 运行期保留
					String value = message.substring(3, message.length() - 2);
					value = cleanStringEscape(value, String.valueOf(syntax.getBlockComment()) + String.valueOf(syntax.getDirectiveLeader()));
					return new CommentImpl(String.valueOf(syntax.getBlockComment()), token.getLocation(), value, trim, renderInterceptors);
				}
				return null;
			} else { //指令
				return parseDirective(token, trim);
			}
		} else { // 文本
			return parseText(null, token, cleanCharEscape(message, syntax.getDirectiveLeader(), isLast)); // 这里不能用trim, 需保留空格
		}
	}

	private Element parseText(String name, Token token, String message) {
		if (textFilter != null)
			message = textFilter.filter(message);
		return new TextImpl(name, token.getLocation(), message, renderInterceptors);
	}

	/**
	 * 清除文本块内转义符.
	 * 单数斜线：将"\!$"转成"!$", 将"\\\\\!$"转成"\\!$".
	 *         将"\*$"转成"*$", 将"\\\\\*$"转成"\\*$".
	 * 双数斜线：最后的自转义斜线.
	 * 如：$!aaa\\!$，因为双斜线自转义了，所以结束符有效，留下内容块："aaa\\"
	 *
	 * @param text 文本内容
	 * @param sign 被转义的标记, 如: !$ 或 *$
	 * @return 清除转义符的内容
	 */
	String cleanStringEscape(String text, String sign) {
		if (text == null || text.length() == 0
				|| sign == null || sign.length() == 0)
			return text;
		// 首先将自转义斜线减半(双数斜线)
		int last = countStringLastSlash(text, text.length());
		if (last > 0) {
			Assert.assertTrue(last % 2 == 0, "DirectiveFactory.escape.error");
			text = text.substring(0, text.length() - (last / 2));
		}
		// 再将转义符斜线减半(单数斜线)
		int pre = 0;
		int cur = 0;
		StringBuffer buf = new StringBuffer();
		while (cur < text.length()) {
			int loc = text.indexOf(sign, cur);
			if (loc < 0) {
				buf.append(text.substring(cur));
				break;
			} else {
				pre = cur;
				cur = loc + sign.length();
				int count = countStringLastSlash(text, loc);
				Assert.assertTrue(count % 2 != 0, "DirectiveFactory.escape.error");
				int del = (count - 1) / 2 + 1;
				if (loc - del > pre)
					buf.append(text.substring(pre, loc - del));
				buf.append(sign);
			}
		}
		return buf.toString();
	}

	// 统计text在loc之前的斜线个数
	int countStringLastSlash(String text, int loc) {
		int count = 0;
		for (int i = loc - 1; i >= 0; i --) {
			char ch = text.charAt(i);
			if (ch == '\\')
				count ++;
			else
				break;
		}
		return count;
	}

	/**
	 * 清除文本块外转义符.
	 * 单数斜线：将"\$if"转成"$if", 将"\\\$if"转成"\$if".
	 * 双数斜线：将除模板最后一个的文本块"aaa\\"转成"aaa\", 将"aaa\\\\"转成"aaa\\".
	 * 如：aaa\\$if, 因为双斜线自转义了，所以$if被解析成为指令，而留下的文本块则为“aaa\\”.
	 * 但如果文本块是模板的最后一个元素，则不需要转义，因为后面不可能再有指令。
	 *
	 * @param text 文本块
	 * @param sign 待转义的符号
	 * @param isLast 是否为模板的最后一个元素
	 * @return 清除转义符的内容
	 */
	String cleanCharEscape(String text, char sign, boolean isLast) {
		if (text == null || text.length() < 2)
			return text;
		StringBuffer buf = new StringBuffer();
		for (int i = 0, n = text.length(); i < n ; i ++) {
			char ch = text.charAt(i);
			if (text.charAt(i) == sign
					|| (! isLast && i == n - 1 && ch == '\\')) {
				int count = countCharLastSlash(buf);
				Assert.assertTrue(count % 2 != 0, "DirectiveFactory.escape.error");
				int del = (count - 1) / 2 + 1;
				buf.delete(buf.length() - del, buf.length()); // 怱略反斜杠
			}
			buf.append(ch);
		}
		return buf.toString();
	}

	// 统计buf最后的斜线个数
	int countCharLastSlash(StringBuffer buf) {
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

	// 解析指令
	private Element parseDirective(Token token, String message) throws ParsingException {
		String name;
		String expressionSource = null;
		Expression expression = null;
		int i = message.indexOf(syntax.getExpressionBegin());
		if (i > -1) {
			Assert.assertTrue(message.charAt(message.length() - 1) == syntax.getExpressionEnd(), "DirectiveFactory.miss.parenthesis");
			name = message.substring(1, i);
			String param = message.substring(i + 1, message.length() - 1);
			if (param != null && param.length() > 0) {
				expressionSource = param.trim();
				if (! syntax.getEndDirectiveName().equals(name)) { // 结束指令不解析表达式
					expression = parseExpression(expressionSource,
						token.subToken(i + 1, message.length() - 1).getLocation());
				}
			}
		} else {
			int j = message.indexOf(":"); // TODO ":"语法待删除
			if (j > -1) {
				name = message.substring(1, j);
				String param = message.substring(j + 1);
				expressionSource = param.trim();
				if (! syntax.getEndDirectiveName().equals(name)) { // 结束指令不解析表达式
					expression = expressionEngine.createConstant(expressionSource);
				}
			} else {
				name = message.substring(1, message.length());
			}
		}
		return resolveDirective(token, name.trim(), expression, expressionSource);
	}

	// 解析表达式
	private Expression parseExpression(String expr, Location location) throws ParsingException {
		try {
			return expressionEngine.parseExpression(expr);
		} catch (ParsingException e) {
			throw e;
		} catch (Exception e) {
			throw new ParsingException(location, e);
		}
	}

	// 决定指令类型
	private Element resolveDirective(Token token, String name, Expression expression, String expressionSource) throws ParsingException {
		// 结束指令
		if (syntax.getEndDirectiveName().equals(name)) {
			if (expressionSource != null) { // 结束指令不解析表达式
				if (TypeUtils.isString(expressionSource)) // 兼容引号字符串参数
					expressionSource = expressionSource.substring(1, expressionSource.length() - 1);
				return new EndDirective(expressionSource);
			}
			return EndDirective.END_DIRECTIVE;
		}
		// SPI指令
		DirectiveHandler handler = directiveHandlerProvider.getDirectiveHandler(name);
		if (handler == null) // 指令处理器不能为空
			throw new ParsingException(token.getLocation(), "DirectiveFactory.handler.not.such", new Object[]{name});
		if (handler.isExpressionNamed() && expression instanceof Variable) // 将名称指令的变量参数转为字符串常量
			expression = expressionEngine.createConstant(((Variable)expression).getName());
		if (handler instanceof MiddleBlockDirectiveHandler) // 中间块指令
			return new MiddleBlockDirectiveImpl(name, token.getLocation(), expression,
					(MiddleBlockDirectiveHandler)handler, token.getMessage(),
					syntax.getDirectiveLeader() + syntax.getEndDirectiveName(), renderInterceptors);
		if (handler instanceof BlockDirectiveHandler) // 起始块指令
			return new BlockDirectiveImpl(name, token.getLocation(), expression,
					(BlockDirectiveHandler)handler, token.getMessage(),
					syntax.getDirectiveLeader() + syntax.getEndDirectiveName(), renderInterceptors);
		if (handler instanceof DirectiveHandler) // 行指令
			return new DirectiveImpl(name, token.getLocation(), expression,
					(DirectiveHandler)handler, token.getMessage(), renderInterceptors);
		// 非法的指令处理器类型
		throw new ParsingException(token.getLocation(), "DirectiveFactory.handler.type.error", new Object[]{handler.getClass().getName()});
	}

}
