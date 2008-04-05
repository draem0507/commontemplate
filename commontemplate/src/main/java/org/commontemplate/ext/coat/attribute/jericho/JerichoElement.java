/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.util.*;

import org.commontemplate.ext.coat.attribute.*;
import org.commontemplate.ext.coat.attribute.Attributes;
import org.commontemplate.ext.coat.attribute.Segment;

import au.id.jericho.lib.html.*;

/**
 * @author GL
 * @since 2008-4-5 上午02:37:05
 */
public class JerichoElement implements TagElement {
	private Attributes attributes;
	private List segments;

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
			if(s!=null)
				sb.append(s.getText());
		}
		return sb.toString();
	}

	public String toString() {
		return getText();
	}

}
