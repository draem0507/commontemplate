package org.commontemplate.tools.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.commontemplate.util.StringUtils;

/**
 * 将Velocity模板转换为CommonTemplate
 *
 * @author liangfei0201@163.com
 *
 */
public class VelocityConverter implements TemplateConverter {

	private String[][] replaces = new String[][] {
			{ "\\#\\#", "\\$#", null },
			{ "\\#\\*", "\\$*", null },
			{ "\\*\\#", "*\\$", null },

			{ "\\#\\s*end", "\1end", null },
			{ "\\#\\s*\\{\\s*end\\s*\\}", "\1end{}", null },

			{ "\\#\\s*stop", "\1stop", null },
			{ "\\#\\s*\\{\\s*stop\\s*\\}", "\1stop{}", null },

			{ "\\#\\s*macro\\s*\\(\\s*(\\w+)[^\\)]*\\)", "\1macro{$1}", null },
			{ "\\#\\s*\\{\\s*macro\\s*\\}\\s*\\(\\s*(\\w+)[^\\)]*\\)", "\1macro{$1}", null },

			{ "\\#\\s*else", "\1else", null },
			{ "\\#\\s*\\{\\s*else\\s*\\}", "\1else{}", null },

			{ "\\#\\s*foreach\\s*\\(\\s*\\$([^\\$|\\s]+)\\s+in\\s+\\$([^\\$|\\s]+)\\s*\\)", "\1for{$1 : $2}", null },
			{ "\\#\\s*\\{\\s*foreach\\s*\\}\\s*\\(\\s*\\$([^\\$|\\s]+)\\s+in\\s+\\$([^\\$|\\s]+)\\s*\\)", "\1for{$1 : $2}", null },

			{ "\\#\\s*parse\\s*\\(\\s*\\\"(.+)\\\"\\s*\\)", "\1include{\"$1\"}", null },
			{ "\\#\\s*\\{\\s*parse\\s*\\}\\s*\\(\\s*\\\"(.+)\\\"\\s*\\)", "\1include{\"$1\"}", null} ,
			{ "\\#\\s*parse\\s*\\(\\s*\\$(\\w+)\\s*\\)", "\1include{$1}", null },
			{ "\\#\\s*\\{\\s*parse\\s*\\}\\s*\\(\\s*\\$(\\w+)\\s*\\)", "\1include{$1}", null },

			{ "\\#\\s*include\\s*\\(\\s*\\\"(.+)\\\"\\s*\\)", "\1display{\"$1\"}", null },
			{ "\\#\\s*\\{\\s*include\\s*\\}\\s*\\(\\s*\\\"(.+)\\\"\\s*\\)", "\1display{\"$1\"}", null },
			{ "\\#\\s*include\\s*\\(\\s*\\$(\\w+)\\s*\\)", "\1display{$1}", null },
			{ "\\#\\s*\\{\\s*include\\s*\\}\\s*\\(\\s*\\$(\\w+)\\s*\\)", "\1display{$1}", null },

			// TODO set指令对函数转换不完全
			{ "\\#\\s*set\\s*\\(\\s*\\$([^\\=]+)\\s*\\=\\s*\\$([^\\)]+)\\s*\\)", "\1set{$1 = $2}", "2" },
			{ "\\#\\s*\\{\\s*set\\s*\\}\\s*\\(\\s*\\$([^\\=]+)\\s*\\=\\s*\\$([^\\)]+)\\s*\\)", "\1set{$1 = $2}", "2" },
			{ "\\#\\s*set\\s*\\(\\s*\\$([^\\=]+)\\s*\\=\\s*([^\\)]+)\\s*\\)", "\1set{$1 = $2}", "2" },
			{ "\\#\\s*\\{\\s*set\\s*\\}\\s*\\(\\s*\\$([^\\=]+)\\s*\\=\\s*([^\\)]+)\\s*\\)", "\1set{$1 = $2}", "2" },

			// TODO if, elseif未转换

			// TODO 取值对函数转换不完全
			{ "\\$([_|a-z|A-Z|0-9|\\.]+)\\(([^\\)]+)\\)", "\1{$1($2)}", "2" },
			{ "\\$([_|a-z|A-Z|0-9|\\.]+)", "\1{$1}", null },

			{ "\1", "\\$", null }

	};

	public String convert(String source) {
		for (int i = 0, n = replaces.length; i < n; i++) {
			String regex = replaces[i][0];
			String replacement = replaces[i][1];
			String dollar = replaces[i][2];
			if (dollar != null && dollar.length() > 0) {
				source = replaceDollar(source, regex, replacement, Integer.parseInt(dollar));
			} else {
				source = source.replaceAll(regex, replacement);
			}
		}
		return source;
	}

	// 替换美元符号
	public String replaceDollar(String source, String regex, String replacement, int dollar) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String replacement1 = StringUtils.replace(replacement, "$" + dollar, matcher.group(2).replaceAll("\\$", ""));
			matcher.appendReplacement(sb, replacement1);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
