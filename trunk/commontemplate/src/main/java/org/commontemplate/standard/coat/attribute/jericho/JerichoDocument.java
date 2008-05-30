/**
 *
 */
package org.commontemplate.standard.coat.attribute.jericho;

import java.util.*;

import org.commontemplate.standard.coat.attribute.*;

import au.id.jericho.lib.html.*;

/**
 * @author GL
 * @since 2008-4-5 上午01:56:24
 */
public class JerichoDocument implements Document {

	private final Source source;

	public JerichoDocument(final Source source) {
		this.source = source;
	}

	public List getTopSegments() {
		List segments = new ArrayList();
		final List childs = source.getChildElements();
		int pos = source.getBegin();
		for (final Iterator i = childs.iterator(); i.hasNext();) {
			final Element e = (Element) i.next();
			String text = source.subSequence(pos, e.getBegin()).toString();
			if (text != null && text.length() > 0) {
				segments.add(new TextSegment(text));
			}
			segments.add(new JerichoElement(source, e));
			pos = e.getEnd();
		}
		String text = source.subSequence(pos, source.getEnd()).toString();
		if (text != null && text.length() > 0) {
			segments.add(new TextSegment(text));
		}
		return segments;
	}

}
