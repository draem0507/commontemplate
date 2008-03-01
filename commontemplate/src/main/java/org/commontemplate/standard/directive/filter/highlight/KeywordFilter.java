package org.commontemplate.standard.directive.filter.highlight;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.commontemplate.core.OutputFilter;

public class KeywordFilter implements OutputFilter, Serializable {

	private static final long serialVersionUID = 1L;
	
	private List keywords;
	
	private String color;
	
	public KeywordFilter(List keywords, String color) {
		this.keywords = keywords;
		this.color = color;
	}

	public String filter(String text) {
		if (keywords == null || keywords.size() ==0)
			return text;
		String result = text;
		for (int i = 0, n = keywords.size(); i < n; i ++) {
			result = highlight(String.valueOf(keywords.get(i)), result);
		}
		return result;
	}
	
	private String highlight(String keyword, String text) {
		if (keyword == null) 
			return text;
		if (text == null) 
			return null;
		
		String regex = escapeRegex(keyword);
		Pattern p = Pattern.compile("(" + regex + ")", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		String result = m.replaceAll("<font color=\"" + color + "\">$1</font>");
		return result;
	}
	
	private String escapeRegex(String source) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, n = source.length(); i < n; i ++) {
			char ch = source.charAt(i);
			if (ch == '\\' || ch == '.' || ch == '(' 
				|| ch == ')' || ch == '[' || ch == ']'
				|| ch == '*' || ch == '+' || ch == '?' 
					|| ch == '|' || ch == '&' || ch == '$'
						 || ch == '^' || ch == '{' || ch == '}') {
				buffer.append('\\');
			}
			buffer.append(ch);
		}
		return buffer.toString();
	}
}
