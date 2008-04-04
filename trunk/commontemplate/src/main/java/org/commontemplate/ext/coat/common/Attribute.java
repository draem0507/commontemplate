/**
 *
 */
package org.commontemplate.ext.coat.common;

/**
 * @author GL
 * @since 2008-4-4 下午11:24:11
 */
public class Attribute {
	private String name;
	private String value;

	public Attribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
