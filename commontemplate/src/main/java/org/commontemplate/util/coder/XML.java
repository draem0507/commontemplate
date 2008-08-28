package org.commontemplate.util.coder;

public class XML {

	 public static final String encode(String src) {
		 return src.replaceAll("&", "&amp;")
			.replaceAll(">", "&gt;")
			.replaceAll("<", "&lt;")
			.replaceAll("\"", "&quot;")
			.replaceAll("\'", "&apos;");
	 }

}
