package org.commontemplate.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.commontemplate.util.IOUtils;
import org.commontemplate.util.Location;

/**
 * 模板源
 *
 * @see org.commontemplate.core.ResourceLoader
 * @see org.commontemplate.core.TemplateFactory
 * @see org.commontemplate.core.Factory
 * @author liangfei0201@163.com
 *
 */
public abstract class Resource {

	/**
	 * 获取模板读取器, 此方法可重复调用.
	 *
	 * @return 模板读取器
	 * @throws IOException
	 *             模板不存在或读取失败时抛出
	 */
	public abstract Reader getReader() throws IOException;

	/**
	 * 获取模板的名称
	 *
	 * @return 模板的名称
	 */
	public abstract String getName();

	/**
	 * 获取模板的编码方式
	 *
	 * @return 编码方式
	 */
	public abstract String getEncoding();

	/**
	 * 未知内容长度
	 */
	public static final long UNKOWN_LENGTH = -1;

	/**
	 * 获取模板的内容长度
	 *
	 * @return 内容长度
	 */
	public long getLength() {
		return UNKOWN_LENGTH;
	}

	/**
	 * 未知修改时间
	 */
	public static final long UNKOWN_MODIFIED = -1;

	/**
	 * 获取模板的最后修改时间
	 *
	 * @return 模板的最后修改时间, 未知时返回<code>UNKOWN_MODIFIED</code>, 应总是不小于-1
	 */
	public long getLastModified() {
		return UNKOWN_MODIFIED;
	}

	/**
	 * 获取原始内容
	 *
	 * @return 原始内容
	 * @throws IOException TODO
	 * @throws IOException
	 */
	public String getSource() throws IOException {
		return IOUtils.readToString(getReader());
	}

	/**
	 * 获取指定位置的模板源代码
	 *
	 * @param location 模板位置
	 * @return 模板源代码
	 * @throws IOException
	 */
	public String getSource(Location location) throws IOException {
		if (location == null)
			return "";
		return getSource(location.getBegin().getOffset(), location.getEnd().getOffset());
	}

	/**
	 * 获取指定位置的模板源代码
	 *
	 * @param beginOffset 模板起始位置
	 * @param endOffset 模板结束位置
	 * @return 模板源代码
	 * @throws IOException
	 */
	public String getSource(int beginOffset, int endOffset) throws IOException {
		if (beginOffset < 0)
			beginOffset = 0;
		if (endOffset <= beginOffset)
			return "";
		int offset = endOffset - beginOffset;
		Reader reader = null;
		try {
			reader = getReader();
			reader.skip(beginOffset);
			StringBuffer buf = new StringBuffer();
			char[] cbuf = new char[4096];
			int len;
			while ((len = reader.read(cbuf)) != -1) {
				if (len >= offset - buf.length()) {
					buf.append(cbuf, 0, offset - buf.length());
					break;
				} else {
					buf.append(cbuf, 0, len);
				}
			}
			return buf.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	/**
	 * 获取位置所在行的源代码
	 *
	 * @param location 位置
	 * @return 模板源代码
	 * @throws IOException
	 */
	public String getLineSource(Location location) throws IOException {
		if (location == null)
			return "";
		return getSource(location.getBegin().getLine(), location.getEnd().getLine());
	}

	/**
	 * 获取位置所在行的源代码
	 *
	 * @param beginLine 模板起始位置
	 * @param endLine 模板结束位置
	 * @return 模板源代码
	 * @throws IOException
	 */
	public String getLineSource(int beginLine, int endLine) throws IOException {
		if (beginLine < 0)
			beginLine = 0;
		if (endLine <= beginLine)
			return "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(getReader());
			StringBuffer buf = new StringBuffer();
			String str;
			int line = 0;
			do {
				str = reader.readLine();
				line ++;
				if (line > endLine)
					break;
				if (line >= beginLine)
					buf.append(str);
			} while (str != null);
			return buf.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	/**
	 * 将模板源内容读取为字符串
	 *
	 * @return 模板源内容
	 */
	public String toString() {
		try {
			return getSource();
		} catch (IOException e) {
			return "ERROR:" + e.getMessage();
		}
	}

}
