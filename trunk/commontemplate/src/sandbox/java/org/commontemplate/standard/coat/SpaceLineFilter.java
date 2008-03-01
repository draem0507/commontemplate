package org.commontemplate.standard.coat;

import org.commontemplate.config.TextFilter;

public class SpaceLineFilter implements TextFilter, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public String filter(String text) {
		for (int i = 0, n = text.length(); i < n; i ++) {
			char ch = text.charAt(i);
			if (ch != ' ' && ch != '\t' && ch != '\f' && ch != '\r') {
				if (ch == '\n') 
					text = text.substring(i + 1);
				break;
			}
			if (i == n - 1) {
				text = "";
			}
		}
		
		for (int i = text.length() - 1; i >= 0; i --) {
			char ch = text.charAt(i);
			if (ch != ' ' && ch != '\t' && ch != '\f' && ch != '\r') {
				if (ch == '\n') 
					text = text.substring(0, i);
				break;
			}
			if (i == 0) {
				text = "";
			}
		}
		return text;
	}

}
