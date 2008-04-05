/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.commontemplate.ext.coat.attribute.Attribute;
import org.commontemplate.ext.coat.attribute.Segment;
import org.commontemplate.ext.coat.attribute.TagElement;
import org.commontemplate.ext.coat.attribute.TextSegment;

import au.id.jericho.lib.html.Attributes;
import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.Source;

/**
 * @author GL
 * @since 2008-4-5 上午02:37:05
 */
public class JerichoElement implements TagElement {
	private Source source;
	private Element element;
	private Attribute[] attributes;
	private Segment[] segments;

	public JerichoElement(Source source, Element element) {
		this.source = source;
		this.element = element;
	}

	public Attribute[] getAttributes() {
		if (attributes == null) {
			Attributes attrs = element.getAttributes();
			attributes = new Attribute[attrs.size()];
			int i = 0;
			for (Iterator it = attrs.iterator(); it.hasNext(); i++) {
				au.id.jericho.lib.html.Attribute attr = (au.id.jericho.lib.html.Attribute) it
						.next();
				attributes[i] = new Attribute(attr.getName(), attr.getValue());
			}
		}
		return attributes;
	}

	public Segment[] getSegments() {
		if (segments == null) {
			List childs = element.getChildElements();
			segments = new Segment[childs.size() * 2 + 1];
			int pos = element.getBegin();
			int c = 0;
			for (Iterator i = childs.iterator(); i.hasNext();) {
				Element e = (Element) i.next();
				segments[c++] = new TextSegment((String) source.subSequence(
						pos, e.getBegin()));
				segments[c++] = new JerichoElement(source, e);
				pos = e.getEnd();
			}
			segments[c] = new TextSegment((String) source.subSequence(pos,
					element.getEnd()));
		}
		return segments;
	}

	public void removeAttribute(String name) {
		// TODO:remove attribute
	}

	public void insertSegment(int i, Segment segment) {
		Segment[] segments = new Segment[getSegments().length + 1];
		for (int x = segments.length - 1; x > i; x--) {
			segments[x] = this.segments[x - 1];
		}
		for (int x = i - 1; x >= 0; x--) {
			segments[x] = this.segments[x];
		}
		segments[i] = segment;
		this.segments = segments;
	}

	public void replaceSegment(int i, Segment segment) {
		getSegments()[i] = segment;
	}

	public String getText() {
		StringBuffer sb = new StringBuffer();
		Segment[] segments = getSegments();
		for (int i = 0; i < segments.length; i++) {
			if (segments[i] != null)
				sb.append(segments[i].getText());
			else
				Logger.getLogger(getClass()).warn("segment[" + i + "] is null");
		}
		return sb.toString();
	}

	public String toString() {
		return getText();
	}

}
