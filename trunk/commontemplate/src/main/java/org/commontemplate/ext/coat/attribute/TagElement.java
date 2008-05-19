/**
 *
 */
package org.commontemplate.ext.coat.attribute;

import java.util.List;

/**
 * 用于表示一个完整的Tag元素，并可以对其Attributes进行修改，可以在之前或之后插入任意的元素.
 *
 * @author GuiLeen
 * @since 2008-4-4 下午11:20:44
 */
public abstract class TagElement extends Attributes {

	public abstract List getSegments();

}
