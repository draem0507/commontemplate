package org.commontemplate.util.scanner;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分解器，此类是scanner的一个工具类
 *
 * @author liangfei0201@163.com
 *
 */
public class Tokenizer {

	public static final int END = -10; // 结束状态(包含最后一个字符)

	public static final int BREAK = -11; // 中止状态(不包含最后一个字符)

	public static final int BACK = -12; // 中止状态(不包含最后一个字符并回退其前面的一个char)

	public static final int BACK_SPACE = -13; // 中止状态(不包含最后一个字符并回退其前面的空格)

	public static final int ERROR = -20; // 错误状态

	private static final Map accepters = initAccepters();

	private static final Map initAccepters() {
		Map acc = new HashMap();
		acc.put(new Integer(END), new AllAccepter());
		acc.put(new Integer(BREAK), new BackAccepter(1));
		acc.put(new Integer(BACK), new BackAccepter(2));
		acc.put(new Integer(BACK_SPACE), new BackSpaceAccepter(1));
		return Collections.unmodifiableMap(acc);
	}

	private final Scanner scanner;

	public Tokenizer(String[] types, int states[][]) {
		if (types.length != states[0].length - 1)
			throw new java.lang.IllegalArgumentException("类型的个数与状态列不配置！状态列的个数应等于类型个数+1，多出的一列状态用于处理其它未定义类型。");
		scanner = new DFAScanner(new PatternTypeResolver(types), new ArrayStateMap(states), accepters);
	}

	public List split(CharStream charStream) throws IOException, ScanningException {
		ListTokenReceiver listTokenReceiver = new ListTokenReceiver();
		scanner.scan(charStream, listTokenReceiver);
		return listTokenReceiver.getTokens();
	}

	public List split(Reader reader) throws IOException, ScanningException {
		return split(new ReaderCharStream(reader));
	}

	public List split(String str) throws IOException, ScanningException {
		return split(new StringCharStream(str));
	}

}
