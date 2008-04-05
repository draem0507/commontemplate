/**
 *
 */
package org.commontemplate.ext.coat.attribute;

/**
 * 用于构造Attribute
 * 
 * @author GL
 * @since 2008-4-4 下午11:24:11
 */
public class StandardAttribute implements Attribute {
	private String name;
	private String value;

	public StandardAttribute(final String name, final String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public String getText() {
		// xml encode
		return " " + name + "=\"" + value + "\"";
	}
}
