package org.commontemplate.util;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 路径清理工具
 *
 * @author liangfei0201@163.com
 *
 */
public class UrlCleaner {

	public static final String PROTOCOL_SEPARATOR = "://";

	public static final String PATH_SEPARATOR = "/";

	public static final char PATH_SEPARATOR_CHAR = '/';

	public static final String PARENT_PATH = "..";

	public static final String CURRENT_PATH = ".";

	/**
	 * 清理相对路径. 处理"../"和"./"相对于根目录"/"的正确路径.
	 *
	 * @param url
	 *            相对路径
	 * @return 对根目录的绝对路径
	 * @throws MalformedURLException
	 *             访问路径超越根目录时抛出
	 * @throws NullPointerException
	 *             传入path为空时抛出
	 */
	public static String clean(String url) throws MalformedURLException {
		if (url == null)
			throw new MalformedURLException("url == null!");


		String domain = "";
		int idx = getDomainIndex(url);
		if (idx > 0) {
			domain = url.substring(0, idx);
			url = url.substring(idx);
		}

		String[] tokens = url.split(PATH_SEPARATOR);
		LinkedList list = new LinkedList();
		for (int i = 0, n = tokens.length; i < n; i++) {
			if (PARENT_PATH.equals(tokens[i])) {
				if (list.isEmpty())
					throw new MalformedURLException("非法路径访问，不允许\"../\"访问根目录\"/\"以上的目录！");
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
		return domain + buf.toString();
	}

	/**
	 * 获取不包括文件名的路径
	 *
	 * @param url 路径
	 * @return 去掉文件名的路径
	 */
	public static String getDirectory(String url) {
		if (url != null) {
			int idx = url.lastIndexOf(PATH_SEPARATOR_CHAR);
			if (idx >= 0)
				return url.substring(0, idx + 1);
		}
		return PATH_SEPARATOR;
	}

	/**
	 * 获取URL域名的分割位置
	 *
	 * @param url 路径
	 * @return 域名分割位置
	 */
	public static int getDomainIndex(String url) {
		if (url != null) {
			int protocolIndex = url.indexOf(PROTOCOL_SEPARATOR);
			if (protocolIndex > 0) {
				int domainIndex = url.indexOf(PATH_SEPARATOR_CHAR, protocolIndex + PROTOCOL_SEPARATOR.length());
				if (domainIndex == -1) { // 只有域名的URL
					return url.length();
				} else {
					return domainIndex;
				}
			}
		}
		return -1;
	}

}
