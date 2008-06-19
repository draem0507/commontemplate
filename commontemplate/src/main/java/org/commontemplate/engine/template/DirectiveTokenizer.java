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

	private static final int B = Tokenizer.BACK;

	private static final int L = Tokenizer.BACK_BLANK;

	private static final int N = Tokenizer.BACK_NEWLINE;

	private static final int R = Tokenizer.ERROR;

	// 指令语法状态机图
	// 列表示输入字符的类型, 对应types[], 比types[]多一列表示"其它"类型
	// 行表示状态
	// 行列交点表示: 在该状态时, 遇到某类型的字符时, 切换到的下一状态(数组行号)
	// E/B/L/N表示结束状态, 接收前面经过的字符序列为一个片断, R表示错误状态(这些状态均为负数)
	private static final int states[][] = { // TODO 支持指令名称为纯符号
					// 0.空格, 1.\, 2.$, 3.字母, 4.{, 5.}, 6.!, 7.*, 8.#, 9.\n, 10.", 11.', 12.`, 13.其它
		/* 0.起始    */ { 9, 1, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, // 初始状态或上一片断刚接收完成状态
		/* 1.转义    */ { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, // $符转义
		/* 2.指令    */ { 2, R, R, 3, 5, R, 6, 7, 8, R, R, R, R, R}, // 指令前导符识别
		/* 3.名称    */ { 4, R, B, 3, 5, B, B, B, B, B, B, B, B, B}, // 读取指令名
		/* 4.中空    */ { 4, R, L, L, 5, L, L, L, L, L, L, L, L, L}, // 退还没有表达式的指令名后的空白符, 处理场景: $end <空格>
		/* 5.表达式  */ { 5, 5, 5, 5, R, E, 5, 5, 5, 5, 12, 14, 16, 5}, // 表达式大括号识别
		/* 6.不解释块*/ { 6, 6, 6, 6, 6, 6, 10, 6, 6, 6, 6, 6, 6, 6}, // 不解释块特殊指令识别
		/* 7.块注释  */ { 7, 7, 7, 7, 7, 7, 7, 11, 7, 7, 7, 7, 7, 7}, // 块注释特殊指令识别
		/* 8.行注释  */ { 8, 8, 8, 8, 8, 8, 8, 8, 8, N, 8, 8, 8, 8}, // 行注释特殊指令识别, 注释不包含换行符
		/* 9.文本    */ { 9, 1, B, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, // 文本表示
		/*10.结束6  */ { 6, 6, E, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, // 结束不解释块
		/*11.结束7  */ { 7, 7, E, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, // 结束块注释
		/*12.引号    */ { 12, 13, 12, 12, 12, 12, 12, 12, 12, 12, 5, 12, 12, 12}, // 绕过表达式中的双引号字符串(不解析字符串中的内容)
		/*13.转义    */ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}, // 双引号字符串转义
		/*14.单引号  */ { 14, 15, 14, 14, 14, 14, 14, 14, 14, 14, 14, 5, 14, 14}, // 绕过表达式中的单引号字符串(不解析字符串中的内容)
		/*15.转义    */ { 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14}, // 单引号字符串转义
		/*16.反单引号*/ { 16, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 5, 16}, // 绕过表达式中的反单引号字符串(不解析字符串中的内容)
		/*17.转义    */ { 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16} // 反单引号字符串转义
	};

	private final Tokenizer tokenizer;

	DirectiveTokenizer(Syntax syntax) { // 支持可配置语法
		// 状态机输入类型，对应状态机图states[][]的列
		// 类型中: "|" 表示 "或", "-" 表示 "到"
		// 按位置识别, "|"和"-"本身不需要转义, 如: "a-z|||-"表示"a"字符到"z"字符或"|"字符或"-"字符
		final String types[] = {
			// 0.空格, 1.\, 2.$, 3.字母, 4.{, 5.}, 6.!, 7.*, 8.#, 9.\n, 10.", 11.', 12.`
			" |\t|\r|\f", "\\", String.valueOf(syntax.getDirectiveLeader()),
			"0-9|_|a-z|A-Z", String.valueOf(syntax.getExpressionBegin()),
			String.valueOf(syntax.getExpressionEnd()), String.valueOf(syntax.getNoParse()),
			String.valueOf(syntax.getBlockComment()), String.valueOf(syntax.getLineComment()),
			"\n", "\"", "\'", "`"
		};

		// 片断分解器, 逐字符读取模板, 按照上面的状态机图分割模板
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
