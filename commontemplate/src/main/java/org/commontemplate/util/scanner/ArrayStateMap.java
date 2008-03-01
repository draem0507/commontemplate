package org.commontemplate.util.scanner;

/**
 * 使用数据作为状态图
 * @author liangfei0201@163.com
 *
 */
public class ArrayStateMap implements StateMap {
	
	private final int[][] states;
	
	/**
	 * @param states 目标状态图
	 * 
	 */
	public ArrayStateMap(int[][] states) {
		int n = states.length;
		int m = states[0].length;
		this.states = new int[n][m];
		// 保护性拷贝
		for (int i = 0; i < n; i ++)
			for (int j = 0; j < m; j ++)
				this.states[i][j] = states[i][j];
	}

	public int getNextState(int state, int type) {
		return states[state][type];
	}

}
