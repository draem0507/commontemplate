/**
 *
 */
package org.commontemplate.ext.coat.attribute;

import java.io.*;

import org.commontemplate.config.ResourceFilter;

/**
 * 继承此类可以方便的实现AttributeFilter TODO: 优化性能
 *
 * @author GL
 * @since 2008-4-5 上午12:10:05
 */
public abstract class AbstractAttributeFilter implements ResourceFilter {

	public Reader filter(final Reader reader) throws IOException {
		final Document document = getDocument(reader);
		final Segment[] segments = document.getTopSegments();
		for (int i = 0; i < segments.length; i++) {
			if (segments[i] instanceof TagElement) {
				cycleParse((TagElement) segments[i]);
			}
		}
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < segments.length; i++) {
			sb.append(segments[i].getText());
		}
		return new StringReader(sb.toString());
	}

	private void cycleParse(final TagElement element) {
		final Segment[] segments = element.getSegments();
		for (int i = 0; i < segments.length; i++) {
			if (segments[i] instanceof TagElement) {
				cycleParse((TagElement) segments[i]);
			}
		}
		parse(element);
	}

	/**
	 * 将TagElement转换为想要的内容.<br>
	 * 不需要转换TagElement的children
	 *
	 * @param
	 * @return
	 */
	protected void parse(final TagElement element) {
		final Attribute[] attributes = element.getAttributes();
		for (int i = 0; i < attributes.length; i++) {
			final String name = attributes[i].getName();
			if (name.startsWith("ct:")) {
				final String directive = name.substring(3);
				element.removeAttribute(name);
				element.insertSegment(0, new TextSegment("$" + directive + "{"
						+ attributes[i].getValue() + "}"));
				element.insertSegment(element.getSegments().length,
						new TextSegment("$end"));
			}
		}

	}

	protected abstract Document getDocument(Reader reader) throws IOException;

}
