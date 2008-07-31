package org.commontemplate.engine.expression;

import java.io.IOException;
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

	private static final int B = Tokenizer.BACK;

	private static final int T = Tokenizer.BACK_TWO;

	private static final int R = Tokenizer.ERROR;

	// 状态机输入类型，对应状态机图states[][]的列
	// 类型中: "|" 表示 "或", "-" 表示 "到"
	// 按位置识别, "|"和"-"本身不需要转义, 如: "a-z|||-"表示"a"字符到"z"字符或"|"字符或"-"字符
	private static final String types[] = {
		// 0.空格, 1.字母, 2.数字, 3.点号, 4.双引号, 5.单引号, 6.反单引号, 7.反斜杠, 8.括号
		" |\t|\n|\r|\f", "_|a-z|A-Z", "0-9", ".", "\"", "\'", "`", "\\", "(|)|[|]"
	};

	// 表达式语法状态机图
	// 列表示输入字符的类型, 对应types[], 比types[]多一列表示"其它"类型
	// 行表示状态
	// 行列交点表示, 在该状态时, 遇到某类型的字符时, 切换到的下一状态(数组行号)
	// E/B/T表示接收前面经过的字符为一个片断, R表示错误状态(这些状态均为负数)
	private static final int states[][] = {
		          // 0.空格, 1.字母, 2.数字, 3.点号, 4.双引号, 5.单引号, 6.反单引号, 7.反斜杠, 8.括号, 9.其它
		/* 0.起始  */{ 0, 1, 2, 4, 7, 9, 11, 4, 6, 4}, // 初始状态或上一片断刚接收完成状态
		/* 1.变量  */{ B, 1, 1, B, R, R, R, B, B, B}, // 变量名识别
		/* 2.数字  */{ B, 2, 2, 5, R, R, R, B, B, B}, // 数字识别
		/* 3.小数  */{ B, 3, 3, B, R, R, R, B, B, B}, // 小数点号识别
		/* 4.操作符*/{ B, B, B, 4, B, B, B, 4, B, 4}, // 操作符识别
		/* 5.点号  */{ T, T, 3, T, T, T, T, T, T, T}, // 数字属性点号识别, 区分于小数点(如: 123.toString 或 11..15)
		/* 6.括号  */{ B, B, B, B, B, B, B, B, B, B}, // 括号
		/* 7.字符串*/{ 7, 7, 7, 7, E, 7, 7, 8, 7, 7}, // 双引号字符串识别
		/* 8.转义  */{ 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, // 双引号字符串转义
		/* 9.字符串*/{ 9, 9, 9, 9, 9, E, 9, 10, 9, 9}, // 单引号字符串识别
		/*10.转义  */{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, // 单引号字符串转义
		/*11.字符  */{ 11, 11, 11, 11, 11, 11, E, 12, 11, 11}, // 反单引号字符串识别
		/*12.转义串*/{ 11, 11, 11, 11, 11, 11, 11, 11, 11, 11} // 反单引号字符串转义
	};

	// 片断分解器, 逐字符读取表达式串, 按照上面的状态机图分割表达式
	private static final Tokenizer tokenizer = new Tokenizer(types, states);

	ExpressionTokenizer() {}

	/**
	 * 将表达式串分解成表达式片断
	 *
	 * @param expressionText 表达式串
	 * @return 表达式片断, 类型: List&lt;Token&gt;
	 */
	List split(String expressionText) throws IOException, ScanningException {
		if (expressionText == null
				|| expressionText.length() == 0)
			return null;
		return tokenizer.split(expressionText);
	}

}
