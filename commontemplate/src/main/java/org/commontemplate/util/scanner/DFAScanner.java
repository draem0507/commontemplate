package org.commontemplate.util.scanner;

import java.io.IOException;
import java.util.Map;

import org.commontemplate.util.Assert;
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

	/**
	 * 构造状态机
	 *
	 * @param typeResolver 输入字符类型识别器
	 * @param stateMap 状态图
	 * @param accepters 接收策略，类型：Map<Integer, Accepter>，key必需是负整数
	 */
	public DFAScanner(TypeResolver typeResolver, StateMap stateMap, Map accepters) {
		Assert.assertNotNull(typeResolver, "typeResolver == null!");
		Assert.assertNotNull(stateMap, "stateMap == null!");
		Assert.assertNotNull(accepters, "accepters == null!");

		this.typeResolver = typeResolver;
		this.stateMap = stateMap;
		this.accepters = accepters;
	}

	public void scan(CharStream charStream, TokenReceiver tokenReceiver) throws IOException, ScanningException {
		// 解析时状态 ----
		StringBuffer buffer = new StringBuffer(); // 缓存字符
		StringBuffer remain = new StringBuffer(); // 残存字符
		Position last = Position.ZERO; // 已被接收token的最后位置
		int state = 0; // 当前状态
		char ch; // 当前字符

		// 逐字解析 ----
		for(;;) {
			if (remain.length() > 0) { // 先处理残存字符
				ch = remain.charAt(0);
				remain.deleteCharAt(0);
			} else { // 没有残存字符则读取字符流
				ch = charStream.nextChar();
				if (ch == CharStream.END) // 直到流读完
					break;
			}

			buffer.append(ch); // 将字符加入缓存
			int type = typeResolver.getType(ch); // 获取字符类型
			state = stateMap.getNextState(state, type); // 从状态机图中取下一状态
			if (state < 0) { // 负数表示接收状态
				Accepter accepter = (Accepter)accepters.get(new Integer(state));
				if (accepter == null)
					throw new ScanningException(new Token(buffer.toString(), last, state).getEndPosition(),
							state, ch, "接收策略不能为空！");
				int acceptLength = accepter.accept(buffer.toString());
				if (acceptLength < 0 || acceptLength > buffer.length())
					throw new ScanningException(new Token(buffer.toString(), last, state).getEndPosition(),
							state, ch, "接收策略返回接收长度必须在0到缓存长度之间！");
				if (acceptLength != 0) {
					Token token = new Token(buffer.substring(0, acceptLength), last, state);
					last = token.getEndPosition(); // 记录当前接收token的结束位置，作为下一token的起始位置
					tokenReceiver.receive(token);// 完成接收
				}
				if (acceptLength != buffer.length())
					remain.insert(0, buffer.substring(acceptLength)); // 将未接收的缓存记入残存
				buffer.setLength(0); // 清空缓存
				state = 0; // 回归到初始状态
			}
		}
		// 接收最后缓存中的内容
		if (buffer.length() > 0)
			tokenReceiver.receive(new Token(buffer.toString(), last, 0));
	}

}
