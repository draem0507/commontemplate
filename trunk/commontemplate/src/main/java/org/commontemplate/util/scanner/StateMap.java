package org.commontemplate.util.scanner;

/**
 * 
 * 状态图
 * @author liangfei0201@163.com
 *
 */
public interface StateMap {

	/**
	 * 获取下一状态
	 * @param state 当前状态
	 * @param type 输入字符类型
	 * @return 下一状态
	 */
	public int getNextState(int state, int type);

}
