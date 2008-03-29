package org.commontemplate.ext.coat;

import java.util.List;

import org.commontemplate.config.Syntax;
import org.commontemplate.util.LinkedStack;
import org.commontemplate.util.Stack;
import org.commontemplate.util.scanner.Token;

class TagTranslator {
	
	private static final String attribute = "\\s+ct\\:(if|for|out|message)\\=\\\"([^\\\"]*)\\\"";
	
	private static final String matchesAttribute = ".*" + attribute + ".*";
	
	private static final String moveAttribute = "(.*)" + attribute + "(.*)";
	
	private Syntax syntax;
	
	public TagTranslator(Syntax syntax) {
		this.syntax = syntax;
	}
	
	public String translate(List tokens) {
		Stack stack = new LinkedStack();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, n = tokens.size(); i < n; i ++) {
			Token token = (Token)tokens.get(i);
			String message = token.getMessage();
			if (message.charAt(0) == '<' && message.charAt(1) != '!') {
				if (message.charAt(1) == '/') {
					String name = message.substring(2, message.length() - 1);
					CountEntry entry = (CountEntry)stack.pop();
					while (! name.trim().equalsIgnoreCase(entry.getName())) {
						entry = (CountEntry)stack.pop();
					}
					if (entry.getCount() > 0) {
						for (int j = 0; j < entry.getCount(); j ++) {
							message += syntax.getLeader() + syntax.getEndDirectiveName() + syntax.getExpressionBegin() + syntax.getExpressionEnd();
						}
					}
				} else {
					String name = message.substring(1, firstSpace(message));
					boolean isEnd = (message.charAt(message.length() - 2) == '/');
					int count = 0;
					while (message.matches(matchesAttribute)) {
						count ++;
						message = message.replaceFirst(moveAttribute, syntax.getLeader() + "$2" + syntax.getExpressionBegin() + "$3" + syntax.getExpressionEnd() + "$1$4");
					}
					if (isEnd) {
						for (int j = 0; j < count; j ++) {
							message += syntax.getLeader() + syntax.getEndDirectiveName() + syntax.getExpressionBegin() + syntax.getExpressionEnd();
						}
					} else {
						stack.push(new CountEntry(name.trim().toLowerCase(), count));
					}
				}
			}
			buffer.append(message);
		}
		return buffer.toString();
	}
	
	private int firstSpace(String message) {
		for (int i = 0, n = message.length(); i < n; i ++) {
			char ch = message.charAt(i);
			if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
				return i;
			}
		}
		return message.length() - 1;
	}
	
	private static final class CountEntry {
		
		private String name;
		
		private int count;
		
		public CountEntry(String name, int count) {
			this.name = name;
			this.count = count;
		}

		public String getName() {
			return name;
		}

		public int getCount() {
			return count;
		}
		
		public String toString() {
			return name + ":" + count;
		}

	}

}
