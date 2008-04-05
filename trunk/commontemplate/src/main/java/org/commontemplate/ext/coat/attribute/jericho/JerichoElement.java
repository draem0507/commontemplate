/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.util.*;

import org.apache.log4j.Logger;
import org.commontemplate.ext.coat.attribute.*;
import org.commontemplate.ext.coat.attribute.Attribute;
import org.commontemplate.ext.coat.attribute.Segment;

import au.id.jericho.lib.html.*;

/**
 * @author GL
 * @since 2008-4-5 上午02:37:05
 */
public class JerichoElement implements TagElement {
	private final Source source;
	private final Element element;
	private Attribute[] attributes;
	private Segment[] segments;

	public JerichoElement(final Source source, final Element element) {
		this.source = source;
		this.element = element;
	}

	public Attribute[] getAttributes() {
		if (attributes == null) {
			final Attributes attrs = element.getAttributes();
			attributes = new Attribute[attrs.size()];
			int i = 0;
			for (final Iterator it = attrs.iterator(); it.hasNext(); i++) {
				final au.id.jericho.lib.html.Attribute attr = (au.id.jericho.lib.html.Attribute) it
						.next();
				attributes[i] = new Attribute(attr.getName(), attr.getValue());
			}
		}
		return attributes;
	}

	public Segment[] getSegments() {
		if (segments == null) {
			final List childs = element.getChildElements();
			segments = new Segment[childs.size() * 2 + 1];
			int pos = element.getBegin();
			int c = 0;
			for (final Iterator i = childs.iterator(); i.hasNext();) {
				final Element e = (Element) i.next();
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

	public void removeAttribute(final String name) {
		// TODO:remove attribute
	}

	public void insertSegment(final int i, final Segment segment) {
		final Segment[] segments = new Segment[getSegments().length + 1];
		for (int x = segments.length - 1; x > i; x--) {
			segments[x] = this.segments[x - 1];
		}
		for (int x = i - 1; x >= 0; x--) {
			segments[x] = this.segments[x];
		}
		segments[i] = segment;
		this.segments = segments;
	}

	public void replaceSegment(final int i, final Segment segment) {
		getSegments()[i] = segment;
	}

	public String getText() {
		final StringBuffer sb = new StringBuffer();
		final Segment[] segments = getSegments();
		for (int i = 0; i < segments.length; i++) {
			if (segments[i] != null) {
				sb.append(segments[i].getText());
			} else {
				Logger.getLogger(getClass()).warn("segment[" + i + "] is null");
			}
		}
		return sb.toString();
	}

	public String toString() {
		return getText();
	}

}
