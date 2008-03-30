package org.commontemplate.engine.template;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.config.Syntax;
import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Tokenizer;

/**
 * 指令片断分解器
 *
 * @author liangfei0201@163.com
 *
 */
final class DirectiveTokenizer {

	//单字母命名, 保证状态机图简洁
	private static final int E = Tokenizer.END;

	private static final int B = Tokenizer.BREAK;

	private static final int R = Tokenizer.ERROR;

	private static final int S = Tokenizer.BACK_SPACE;

	// 指令语法状态机图
	private static final int states[][] = {
		            /* 0.空格, 1.反斜杠, 2.$, 3.字母, 4.{, 5.}, 6.!, 7.*, 8.#, 9.\n, 10.", 11.', 12.`, 14.其它 */ // 对应types
		/* 0.起始    */ { 9, 1, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
		/* 1.转义    */ { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, // $符转义
		/* 2.指令    */ { 2, R, R, 3, 5, R, 6, 7, 8, R, R, R, R, R},
		/* 3.名称    */ { 4, R, B, 3, 5, B, B, B, B, B, B, B, B, B},
		/* 4.中空    */ { 4, R, S, S, 5, S, S, S, S, S, S, S, S, S}, // 退还空格
		/* 5.表达式  */ { 5, 5, 5, 5, R, E, 5, 5, 5, 5, 12, 14, 16, 5},
		/* 6.不解释块*/ { 6, 6, 6, 6, 6, 6, 6, 10, 6, 6, 6, 6, 6, 6},
		/* 7.块注释  */ { 7, 7, 6, 7, 7, 7, 7, 11, 7, 7, 7, 7, 7, 7},
		/* 8.行注释  */ { 8, 8, 8, 8, 8, 8, 8, 8, 8, E, 8, 8, 8, 8},
		/* 9.文本    */ { 9, 1, B, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
		/*10.结束6  */ { 6, 6, E, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
		/*11.结束7  */ { 7, 7, E, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
		/*12.引号    */ { 12, 13, 12, 12, 12, 12, 12, 12, 12, 12, 5, 12, 12, 12}, // 在表达式中遇到引号时忽略关键字符$,{,}等
		/*13.转义    */ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}, // 字符串转义
		/*14.单引号  */ { 14, 15, 14, 14, 14, 14, 14, 14, 14, 14, 14, 5, 14, 14}, // 在表达式中遇到引号时忽略关键字符$,{,}等
		/*15.转义    */ { 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14}, // 字符串转义
		/*16.反单引号*/ { 16, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 5, 16}, // 在表达式中遇到引号时忽略关键字符$,{,}等
		/*17.转义    */ { 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16} // 字符串转义
	};

	private final Tokenizer tokenizer;

	DirectiveTokenizer(Syntax syntax) {
		// 状态机输入类型，对应状态机图的列
		final String[] types = {
			// 0.空格, 1.反斜杠, 2.$, 3.字母, 4.{, 5.}, 6.!, 7.*, 8.#, 9.\n, 10.", 11.', 12.`, 14.其它
			" |\t|\r|\f", "\\", String.valueOf(syntax.getLeader()),
			"0-9|_|a-z|A-Z", String.valueOf(syntax.getExpressionBegin()),
			String.valueOf(syntax.getExpressionEnd()), String.valueOf(syntax.getNoParse()),
			String.valueOf(syntax.getBlockComment()), String.valueOf(syntax.getLineComment()),
			"\n", "\"", "\'", "`"
		};
		tokenizer = new Tokenizer(types, states);
	}

	/**
	 * 分解模板指令
	 *
	 * @param templateProvider 模板内容读取器
	 * @return 指令片断列表, 类型: List&lt;Token&gt;
	 * @throws IOException 读取模板内容失败时抛出
	 * @throws ScanningException 模板语法错误时抛出
	 */
	List split(Reader templateProvider) throws IOException, ScanningException {
		return tokenizer.split(templateProvider);
	}

}
