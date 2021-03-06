package org.commontemplate.util.coder;

/**
 * XML转义器.
 *
 * @author liangfei0201@163.com
 *
 */
public class XML {

	 public static final String encode(String src) {
		 if (src == null)
			 return null;
		 return src.replaceAll("&", "&amp;")
			.replaceAll(">", "&gt;")
			.replaceAll("<", "&lt;")
			.replaceAll("\"", "&quot;")
			.replaceAll("\'", "&apos;");
	 }

}
