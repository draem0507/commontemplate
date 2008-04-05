/**
 *
 */
package org.commontemplate.ext.coat.attribute;

/**
 * @author GL
 * @since 2008-4-4 下午11:20:44
 */
public interface TagElement extends Segment {
	public Attribute[] getAttributes();

	public void removeAttribute(String name);

	/**
	 *
	 * @return
	 */
	public Segment[] getSegments();

	public void insertSegment(int i, Segment segment);

	public void replaceSegment(int i, Segment segment);
}
