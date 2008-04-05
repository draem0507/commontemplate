/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import org.commontemplate.ext.coat.attribute.Attribute;

/**
 * @author GL
 * @since 2008-4-5 下午05:34:50
 */
public class JerichoAttribute implements Attribute{
	private final au.id.jericho.lib.html.Attribute attr;

	/**
	 * @param attr
	 */
	public JerichoAttribute(au.id.jericho.lib.html.Attribute attr) {
		this.attr = attr;
	}

	public String getName() {
		return attr.getName();
	}

	public String getText() {
		return attr.toString();
	}

	public String getValue() {
		return attr.getValue();
	}
}
