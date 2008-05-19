/**
 *
 */
package org.commontemplate.ext.coat.attribute;

/**
 * 用于构造Attribute
 *
 * @author GuiLeen
 * @since 2008-4-4 下午11:24:11
 */
public class StandardAttribute extends Attribute {

	private final String name;

	private final String value;

	public StandardAttribute(final String name, final String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getText() {
		// xml encode
		return " " + name + "=\"" + value + "\"";
	}
}
