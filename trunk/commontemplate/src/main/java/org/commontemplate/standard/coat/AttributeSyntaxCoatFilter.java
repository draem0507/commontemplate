package org.commontemplate.standard.coat;

import org.commontemplate.standard.coat.attribute.jericho.JerichoAttributeCoatFilter;

/**
 * HTML属性语法外套实现.<br>
 * 将HTML标签的特殊属性转换为指令.<br>
 * 如:<br>
 * <pre>
 * &lt;table ct:if="users != null && users.size &gt; 0" border="1"&gt;
 *     &lt;tr ct:for="user : users"&gt;
 *         &lt;td&gt;&lt;span ct:out="for.index + 1"&gt;1&lt;/span&gt;&lt;/td&gt;
 *         &lt;td&gt;&lt;span ct:out="user.name"&gt;james&lt;/span&gt;&lt;/td&gt;
 *         &lt;td&gt;&lt;span ct:out="user.coins"&gt;2.00&lt;/span&gt;&lt;/td&gt;
 *     &lt;/tr&gt;
 * &lt;/table&gt;
 * </pre>
 *
 * @author GuiLeen
 */
public class AttributeSyntaxCoatFilter extends JerichoAttributeCoatFilter {

	private static final long serialVersionUID = 1L;

}
