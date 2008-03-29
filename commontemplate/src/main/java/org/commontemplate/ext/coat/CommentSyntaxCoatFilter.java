package org.commontemplate.ext.coat;

import org.commontemplate.config.TextFilter;

/**
 * HTML注释语法外套过滤器
 * 
 * @author liangfei0201@163.com
 *
 */
public class CommentSyntaxCoatFilter implements TextFilter, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String text) {
		if (text.startsWith("-->")) 
			text = text.substring(3);
		if (text.endsWith("<!--")) 
			text = text.substring(0, text.length() - 4);
		return text;
	}

}
