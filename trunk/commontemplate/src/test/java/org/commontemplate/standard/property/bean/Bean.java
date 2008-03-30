package org.commontemplate.standard.property.bean;

public class Bean {

	private String name;

	private int count;

	private boolean ok;

	public Bean(String name, int count, boolean ok) {
		this.name = name;
		this.count = count;
		this.ok = ok;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}
