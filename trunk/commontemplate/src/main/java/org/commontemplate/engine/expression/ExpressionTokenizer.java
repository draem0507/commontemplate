package org.commontemplate.engine.expression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Tokenizer;

/**
 * 表达式片断分解器
 *
 * @author liangfei0201@163.com
 *
 */
final class ExpressionTokenizer {

	//单字母命名, 保证状态机图简洁
	private static final int E = Tokenizer.END;

	private static final int B = Tokenizer.BREAK;

	private static final int R = Tokenizer.ERROR;

	private static final int C = Tokenizer.BACK;

	// 状态机输入类型
	private static final String[] types = {
		//0.空格, 1.字母, 2.数字, 3.点, 4.引号, 5.单引号, 6.反引号, 7.反斜杠, 8.括号, 9.其它
		" |\t|\n|\r|\f", "_|a-z|A-Z", "0-9", ".", "\"", "\'", "`", "\\", "(|)|[|]"
	};
	// 表达式语法状态机图
	private static final int states[][] = {
		         /* 0.空格, 1.字母, 2.数字, 3.点, 4.引号, 5.单引号, 6.反引号, 7.反斜杠, 8.括号, 9.其它 */ // 对应types
		/* 0.起始 */{ 0, 1, 2, 7, 4, 9, 11, 7, 6, 7},
		/* 1.变量 */{ B, 1, 1, B, R, R, R, B, B, B},
		/* 2.数字 */{ B, 2, 2, 8, R, R, R, B, B, B},
		/* 3.小数 */{ B, 3, 3, B, R, R, R, B, B, B},
		/* 4.字符 */{ 4, 4, 4, 4, E, 4, 4, 5, 4, 4},
		/* 5.转义 */{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
		/* 6.括号 */{ B, B, B, B, B, B, B, B, B, B},
		/* 7.操作 */{ B, B, B, 7, B, B, B, 7, B, 7},
		/* 8.点号 */{ C, C, 3, C, C, C, C, C, C, C},
		/* 9.字符 */{ 9, 9, 9, 9, 9, E, 9, 10, 9, 9},
		/* 10.转义 */{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
		/* 11.字符 */{ 11, 11, 11, 11, 11, 11, E, 12, 11, 11},
		/* 12.转义 */{ 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}
	};

	private static final Tokenizer tokenizer = new Tokenizer(types, states);

	ExpressionTokenizer(){

	}

	/**
	 * 将表达式串分解成表达式片断
	 *
	 * @param expressionText 表达式串
	 * @return 表达式片断, 类型: List&lt;Token&gt;
	 */
	List split(String expressionText) throws IOException, ScanningException {
		if (expressionText == null || expressionText.length() == 0)
			return new ArrayList(0);
		return tokenizer.split(expressionText);
	}

}
