package org.commontemplate.core;

import java.io.IOException;
import java.io.Reader;

import org.commontemplate.util.IOUtils;

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
	 * 获取模板读取器
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
	 * 未知修改时间
	 */
	public static final long UNKOWN_MODIFIED = -1;

	/**
	 * 获取模板的最后修改时间
	 * 
	 * @return 模板的最后修改时间, 未知时返回<code>UNKOWN_MODIFIED</code>, 应总是不小于-1
	 */
	public abstract long getLastModified();

	/**
	 * 将模板源内容读取为字符串
	 * 
	 * @return 模板源内容
	 */
	public String toString() {
		try {
			return IOUtils.readToString(getReader());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
