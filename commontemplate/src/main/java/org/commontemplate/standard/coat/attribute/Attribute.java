/**
 *
 */
package org.commontemplate.standard.coat.attribute;

/**
 * 标签属性
 *
 * @author GuiLeen
 * @since 2008-4-4 下午11:24:11
 */
public abstract class Attribute extends Segment {

	/**
	 * 获取属性名
	 *
	 * @return 属性名
	 */
	public abstract String getName();

	/**
	 * 获取属性值
	 *
	 * @return 属性值
	 */
	public abstract String getValue();

}
