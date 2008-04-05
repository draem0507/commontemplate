package org.commontemplate.ext.coat.attribute.simple;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.commontemplate.util.scanner.ScanningException;
import org.commontemplate.util.scanner.Tokenizer;

class TagTokenizer {
	
	//单字母命名, 保证状态机图简洁
	private static final int E = Tokenizer.END;
	
	private static final int B = Tokenizer.BREAK;
	
	//private static final int R = Tokenizer.ERROR;
	
	private static final String[] types = {
		" |\t|\n|\r", "<", ">", "\"", "\'"
	};
	
	// 状态机图
	private static final int states[][] = {
	           /* 0.空格, 1.<, 2.>, 3.", 4.', 5.其它 */
	/* 0.起始 */{ 5, 1, 5, 5, 5, 5 },
	/* 1.标签 */{ 2, B, E, 2, 2, 1 },
	/* 2.属性 */{ 2, 2, E, 3, 4, 2 },
	/* 3.字符 */{ 3, 3, 3, 2, 3, 3 },
	/* 4.字符 */{ 4, 4, 4, 4, 2, 4 },
	/* 5.文本 */{ 5, B, 5, 5, 5, 5 }
	};
	
	private static final Tokenizer tokenizer = new Tokenizer(types, states);
	
	public List split(Reader templateProvider) throws IOException, ScanningException {
		return tokenizer.split(templateProvider);
	}

}
