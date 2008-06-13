/**
 *
 */
package org.commontemplate.standard.directive.coat.attribute.jericho;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import au.id.jericho.lib.html.Attribute;
import au.id.jericho.lib.html.Attributes;

/**
 * @author GL
 * @since 2008-4-5 下午05:35:16
 */
public class JerichoAttributes extends org.commontemplate.standard.directive.coat.attribute.Attributes{

	private final List attributes;

	/**
	 * @param attrs
	 */
	public JerichoAttributes(Attributes attrs) {
		attributes = new ArrayList();
		for(Iterator i = attrs.iterator(); i.hasNext();){
			Attribute attr = (Attribute) i.next();
			attributes.add(new JerichoAttribute(attr));
		}
	}

	public List getAttributes() {
		return attributes;
	}

	public String getText() {
		StringBuffer sb = new StringBuffer();
		for(Iterator i= attributes.iterator();i.hasNext();){
			org.commontemplate.standard.directive.coat.attribute.Attribute attr = (org.commontemplate.standard.directive.coat.attribute.Attribute) i.next();
			sb.append(" ");
			sb.append(attr.getText());
		}
		return sb.toString();
	}

}