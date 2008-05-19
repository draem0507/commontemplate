/**
 *
 */
package org.commontemplate.ext.coat.attribute;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.ResourceFilter;

/**
 * 继承此类可以方便的实现AttributeFilter TODO: 优化性能
 *
 * @author GL
 * @since 2008-4-5 上午12:10:05
 */
public abstract class AbstractAttributeFilter implements ResourceFilter {

	public Reader filter(final Reader reader) throws IOException {
		final StringBuffer sb = new StringBuffer();
		final Document document = getDocument(reader);
		if (document != null) {
			final List segments = document.getTopSegments();
			if (segments != null) {
				for (final Iterator i = segments.iterator(); i.hasNext();) {
					final Segment segment = (Segment) i.next();
					if (segment != null) {
						if (segment instanceof TagElement)
							cycleParse((TagElement) segment);
						sb.append(segment.getText());
					}
				}
			}
		}
		return new StringReader(sb.toString());
	}

	/**
	 * 迭代调用parse方法，深度优先
	 *
	 * @param element 标签元素
	 */
	private void cycleParse(final TagElement element) {
		if (element != null) {
			for (final Iterator i = element.getSegments().iterator(); i.hasNext();) {
				final Segment s = (Segment) i.next();
				if (s instanceof TagElement) {
					// Go into deep level first
					cycleParse((TagElement) s);
				}
			}
			// parse the TagElement next
			parse(element);
		}
	}

	/**
	 * 将TagElement转换为想要的内容.<br>
	 * 不需要转换TagElement的children
	 *
	 * @param element 标签元素
	 */
	protected void parse(final TagElement element) {
		if (element != null) {
			final List attributes = element.getAttributes();
			if (attributes != null && attributes.size() > 0) {
				int startTagAt = 0; // 外套标记插入的位置，最先出现的attribute套在最外层
				for (final Iterator i = attributes.iterator(); i.hasNext();) {
					final Attribute attr = (Attribute) i.next();
					if (attr != null) {
						String attrName = attr.getName();
						if (attrName != null && attrName.startsWith("ct:")) {
							// 获取directive并外套到Tag之外
							final String directive = attrName.substring(3);
							element.getSegments().add(startTagAt, new TextSegment(
									"$" + directive + "{" + attr.getValue() + "}"));
							element.getSegments().add(new TextSegment("$end"));
							// remove the ct: attribute
							i.remove();
							// 后出现的attribute将往后面插入
							startTagAt++;
						}
					}
				}
			}
		}
	}

	protected abstract Document getDocument(Reader reader) throws IOException;

}
