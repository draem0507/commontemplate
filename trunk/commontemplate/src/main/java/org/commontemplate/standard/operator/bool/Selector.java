package org.commontemplate.standard.operator.bool;

/**
 * 选择器缓存信息
 * 
 * @author liangfei00201@163.com
 *
 */
public final class Selector {
	
	private final boolean selected;
	
	private final Object selectedValue;
	
	public Selector(boolean selected, Object selectedValue) {
		this.selected = selected;
		this.selectedValue = selectedValue;
	}

	public boolean isSelected() {
		return selected;
	}

	public Object getSelectedValue() {
		return selectedValue;
	}
	
	public String toString() {
		throw new RuntimeException("三目运算，\"?\"号应与\":\"匹配!");
	}

}
