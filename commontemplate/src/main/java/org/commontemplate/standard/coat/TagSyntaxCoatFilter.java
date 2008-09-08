package org.commontemplate.standard.coat;

import org.commontemplate.standard.filter.StringResourceFilter;

/**
 * HTML标签语法外套实现.<br>
 * 将HTML特殊标签转换为指令.<br>
 * 如:<br>
 * <pre>
 * &lt;ct:if param="user != null"&gt;
 * &lt;/ct:if&gt;
 * </pre>
 *
 * @author liangfei0201@163.com
 *
 */
public class TagSyntaxCoatFilter extends StringResourceFilter {

	private static final long serialVersionUID = 1L;

	public String filter(String text) {
		// TODO 未实现
		return null;
	}

}
