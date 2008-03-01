package org.commontemplate.util.scanner;

import java.io.IOException;
import java.util.Map;

import org.commontemplate.util.Assert;
import org.commontemplate.util.Offset;
import org.commontemplate.util.Position;

/**
 * Deterministic Finite state Automata(DFA)
 * 确定性有限状态自动机实现
 * 
 * @author liangfei0201@163.com
 *
 */
public class DFAScanner implements Scanner {

	// 输入字符类型识别器
	private final TypeResolver typeResolver;

	// 状态图
	private final StateMap stateMap;
	
	// 接收策略，key必需是负整数
	private final Map accepters; // 类型：Map<Integer, Accepter>
	
	public DFAScanner(TypeResolver typeResolver, StateMap stateMap, Map accepters) {
		Assert.assertNotNull(typeResolver, "typeResolver == null!");
		Assert.assertNotNull(stateMap, "stateMap == null!");
		Assert.assertNotNull(accepters, "accepters == null!");
		
		this.typeResolver = typeResolver;
		this.stateMap = stateMap;
		this.accepters = accepters;
	}

	public void scan(CharStream charStream, TokenReceiver tokenReceiver) throws IOException, ScanningException {
		scan(charStream, tokenReceiver, Offset.ZERO);
	}
	
	public void scan(CharStream charStream, TokenReceiver tokenReceiver, Offset start) throws IOException, ScanningException {
		// 位置信息 ----
		long offset = start.getLength(); // 偏移量
		int row = start.getPosition().getRow(); // 所解释的char所在行
		int column = start.getPosition().getColumn(); // 所解释的char所在列
		int bufferRow = 0; // 缓存开始char所在行
		int bufferColumn = 0; // 缓存开始char所在列
		
		// 解析时状态 ----
		StringBuffer buffer = new StringBuffer(); // 缓存字符
		StringBuffer remain = new StringBuffer(); // 残存字符
		int state = 0; // 当前状态
		char ch; // 当前字符
		
		// 逐字解析 ----
		for(;;) {
			if (remain.length() > 0) { // 先处理残存
				ch = remain.charAt(0);
				remain.deleteCharAt(0);
			} else { // 没有残存则读取流
				ch = charStream.nextChar();
				if (ch == CharStream.END) // 直到流读完
					break;
			}
			
			offset ++;
			if (ch == '\n') { // 记录位置
				row ++; // FIXME 当回退中包含\n时，记录的位置不对，影响出错信息的定位
				column = 0;
			} else {
				column ++;
			}
			if (buffer.length() == 0) { // 记录缓存起始位置
				bufferRow = row;
				bufferColumn = column;
			}
			
			buffer.append(ch); // 将字符加入缓存
			int type = typeResolver.getType(ch); // 获取字符类型
			state = stateMap.getNextState(state, type); // 从状态机图中取下一状态
			if (state < 0) { // 负数表示接收状态
				Accepter accepter = (Accepter)accepters.get(new Integer(state));
				if (accepter == null) 
					throw new ScanningException(state, offset, row, column, "错误发生在字符:" + ch + " 位置:(" + row + "," + column + ")");
				int acceptLen = accepter.accept(buffer.toString());
				Assert.assertTrue(acceptLen >= 0 && acceptLen <= buffer.length(), "接收策略出错！");
				offset -= acceptLen;
				if (acceptLen > 0) // 完成接收
					tokenReceiver.receive(new Token(buffer.substring(0, acceptLen), offset, new Position(bufferRow, bufferColumn), state));
				if (acceptLen < buffer.length()) // 将未接收的缓存记入残存
					remain.insert(0, buffer.substring(acceptLen));
				buffer.setLength(0); // 清空缓存
				state = 0; // 回归到初始状态
			}
		}
		// 接收最后缓存中的内容
		if (buffer.length() > 0) 
			tokenReceiver.receive(new Token(buffer.toString(), 
					offset, new Position(bufferRow, bufferColumn), 0));
	}

}
