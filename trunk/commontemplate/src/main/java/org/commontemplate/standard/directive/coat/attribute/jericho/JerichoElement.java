/**
 *
 */
package org.commontemplate.standard.directive.coat.attribute.jericho;

import java.util.*;

import org.commontemplate.ext.coat.attribute.*;
import org.commontemplate.standard.directive.coat.attribute.Attributes;
import org.commontemplate.standard.directive.coat.attribute.Segment;
import org.commontemplate.standard.directive.coat.attribute.TagElement;
import org.commontemplate.standard.directive.coat.attribute.TextSegment;

import au.id.jericho.lib.html.*;

/**
 * @author GL
 * @since 2008-4-5 上午02:37:05
 */
public class JerichoElement extends TagElement {

	private final Attributes attributes;

	private final List segments;

	public JerichoElement(final Source source, final Element element) {
		final List childs = element.getChildElements();

		segments = new ArrayList();
		int pos = element.getBegin();

		au.id.jericho.lib.html.Attributes attrs = element.getAttributes();
		segments.add(new TextSegment(source.subSequence(pos, attrs.getBegin()).toString()));
		attributes = new JerichoAttributes(attrs);
		segments.add(attributes);
		pos = attrs.getEnd();

		for (final Iterator i = childs.iterator(); i.hasNext();) {
			final Element e = (Element) i.next();
			String text = source.subSequence(
					pos, e.getBegin()).toString();
			if(text!=null && text.length()>0)
				segments.add(new TextSegment(text));
			segments.add(new JerichoElement(source,e));
			pos = e.getEnd();
		}
		String text = source.subSequence(
				pos, element.getEnd()).toString();
		if(text!=null && text.length()>0)
			segments.add(new TextSegment(text));
	}

	public List getAttributes() {
		return attributes.getAttributes();
	}

	public List getSegments() {
		return segments;
	}

	public String getText() {
		final StringBuffer sb = new StringBuffer();
		for(Iterator i= getSegments().iterator();i.hasNext();){
			Segment s = (Segment) i.next();
			if(s != null)
				sb.append(s.getText());
		}
		return sb.toString();
	}

	public String toString() {
		return getText();
	}

}
