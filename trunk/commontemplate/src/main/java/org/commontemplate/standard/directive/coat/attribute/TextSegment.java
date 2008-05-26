/**
 *
 */
package org.commontemplate.standard.directive.coat.attribute;

import org.commontemplate.util.Assert;

/**
 * 文本片段
 *
 * @author GuiLeen
 * @since 2008-4-5 上午02:36:24
 */
public class TextSegment extends Segment {

	private final String text;

	public TextSegment(final String text) {
		Assert.assertNotNull(text);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return text;
	}
}
