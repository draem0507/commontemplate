/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.util.*;

import org.commontemplate.ext.coat.attribute.*;
import org.commontemplate.ext.coat.attribute.Segment;

import au.id.jericho.lib.html.*;

/**
 * @author GL
 * @since 2008-4-5 上午01:56:24
 */
public class JerichoDocument implements Document {

	private final Source source;
	private Segment[] segments;

	public JerichoDocument(final Source source) {
		super();
		this.source = source;
	}

	public Segment[] getTopSegments() {
		if (segments == null) {
			final List childs = source.getChildElements();
			segments = new Segment[childs.size() * 2 + 1];
			int pos = source.getBegin();
			int c = 0;
			for (final Iterator i = childs.iterator(); i.hasNext();) {
				final Element e = (Element) i.next();
				segments[c++] = new TextSegment((String) source.subSequence(
						pos, e.getBegin()));
				segments[c++] = new JerichoElement(source, e);
				pos = e.getEnd();
			}
			segments[c] = new TextSegment((String) source.subSequence(pos,
					source.getEnd()));
		}
		return segments;
	}

}
