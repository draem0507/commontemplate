package org.commontemplate.standard.directive.iteration;

/**
 * 迭代状态信息
 *
 * @author liangfei0201@163.com
 *
 */
public class ForeachStatus {

	private final int total;

	private final int level;

	public ForeachStatus(int total) {
		this.total = total;
		this.level = 0;
	}

	public ForeachStatus(int total, int level) {
		this.total = total;
		this.level = level;
	}

	private int index = 0;

	public void increment() {
		index ++;
	}

	public int getLevel() {
		return level;
	}

	public int getSize() {
		return total;
	}

	public int getIndex() {
		return index;
	}

	public int getCount() {
		return index + 1;
	}

	public boolean isFirst() {
		return index == 0;
	}

	public boolean isLast() {
		return index == total - 1;
	}

	public boolean isMiddle() {
		return index > 0 && index < total - 1;
	}

	public boolean isEven() {
		return (index + 1) % 2 == 0;
	}

	public boolean isOdd() {
		return (index + 1) % 2 != 0;
	}

	public String toString() {
		return getCount() + "/" + getSize();
	}
}
