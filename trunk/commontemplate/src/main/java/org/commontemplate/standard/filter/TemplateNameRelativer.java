package org.commontemplate.standard.filter;

import java.util.Iterator;
import java.util.LinkedList;

import org.commontemplate.config.InvalidTemplateNameException;
import org.commontemplate.config.TemplateNameFilter;

/**
 * 路径关联器. 注册此类, 才可以在模板中使用相对路径.
 * 
 * @author liangfei0201@163.com
 *
 */
public class TemplateNameRelativer implements TemplateNameFilter {

	public String filter(String templateName, String currentTemplateName) throws InvalidTemplateNameException {
		if (templateName == null) 
			throw new InvalidTemplateNameException("模板名称不能为空！");
		if (templateName.indexOf(PROTOCOL_SEPARATOR) > 0) // 有协议头时不过滤
			return templateName;
		if (templateName.trim().charAt(0) == PATH_SEPARATOR_CHAR) // 根目录开头，不添加当前路径
			return clean(templateName);
		return clean(cutPath(currentTemplateName) + templateName);
	}

	// 提取不包括文件名的路径
	private String cutPath(String name) {
		if (name != null) {
			int idx = name.lastIndexOf(PATH_SEPARATOR_CHAR);
			if (idx >= 0)
				return name.substring(0, idx + 1);
		}
		return PATH_SEPARATOR;
	}
	
	public static final String PROTOCOL_SEPARATOR = "://";
	
	public static final String PATH_SEPARATOR = "/";
	
	public static final char PATH_SEPARATOR_CHAR = '/';

	public static final String PARENT_PATH = "..";

	public static final String CURRENT_PATH = ".";

	/**
	 * 清理相对路径. 处理"../"和"./"相对于根目录"/"的正确路径.
	 * 
	 * @param path
	 *            相对路径
	 * @return 对根目录的绝对路径
	 * @throws InvalidTemplateNameException
	 *             访问路径超越根目录时抛出
	 * @throws NullPointerException
	 *             传入path为空时抛出
	 */
	String clean(String path) throws InvalidTemplateNameException {
		String[] tokens = path.split(PATH_SEPARATOR);
		LinkedList list = new LinkedList();
		for (int i = 0, n = tokens.length; i < n; i++) {
			if (PARENT_PATH.equals(tokens[i])) {
				if (list.isEmpty())
					throw new InvalidTemplateNameException("非法路径访问，不允许\"../\"访问根目录\"/\"以上的目录！");
				list.removeLast();
			} else if (tokens[i] != null && tokens[i].trim().length() > 0
					&& !CURRENT_PATH.equals(tokens[i])) {
				list.addLast(tokens[i]);
			}
		}
		StringBuffer buf = new StringBuffer();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			buf.append(PATH_SEPARATOR);
			buf.append((String) iterator.next());
		}
		return buf.toString();
	}

}
