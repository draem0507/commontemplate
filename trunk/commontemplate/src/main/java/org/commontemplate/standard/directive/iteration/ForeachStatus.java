package org.commontemplate.standard.directive.iteration;

public class ForeachStatus {

	private int index = 0;

	private int total = 0;

	public ForeachStatus(int total) {
		this.total = total;
	}

	public void increment() {
		index ++;
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
