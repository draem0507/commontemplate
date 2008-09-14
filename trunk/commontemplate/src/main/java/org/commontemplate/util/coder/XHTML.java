package org.commontemplate.util.coder;

/**
 * XHTML转义器.
 * 类似XML转义器, 但"&apos;" 改为 "&#39;"
 * 参见：<a href="http://www.w3.org/TR/xhtml1/#C_16">http://www.w3.org/TR/xhtml1/#C_16</a>
 *
 * @author liangfei0201@163.com
 *
 */
public class XHTML {

	 public static final String encode(String src) {
		 if (src == null)
			 return null;
		 return src.replaceAll("&", "&amp;")
			.replaceAll(">", "&gt;")
			.replaceAll("<", "&lt;")
			.replaceAll("\"", "&quot;")
			.replaceAll("\'", "&#39;");
	 }

}
