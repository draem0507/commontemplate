/**
 *
 */
package org.commontemplate.ext.coat.attribute;

import org.commontemplate.util.Assert;

/**
 * @author GL
 * @since 2008-4-5 上午02:36:24
 */
public class TextSegment implements Segment{
	private String text;

	public TextSegment(String text) {
		super();
		Assert.assertNotNull(text);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}
}
