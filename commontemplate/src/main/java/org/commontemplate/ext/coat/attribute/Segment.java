/**
 *
 */
package org.commontemplate.ext.coat.attribute;

/**
 * 用于表示文档中的片段，TagElement继承Segment
 *
 * @author GuiLeen
 * @since 2008-4-5 上午12:42:54
 */
public abstract class Segment {

	/**
	 * 获取片段文本内容
	 *
	 * @return 片段文本内容
	 */
	public abstract String getText();

	public String toString() {
		return getText();
	}

}
