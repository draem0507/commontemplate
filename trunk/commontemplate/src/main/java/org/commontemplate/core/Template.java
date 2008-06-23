package org.commontemplate.core;

import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 模板接口.
 * <p/>
 * (线程安全)
 *
 * @see org.commontemplate.core.TemplateFactory
 * @see org.commontemplate.core.Factory
 * @author liangfei0201@163.com
 *
 */
public abstract class Template extends Resource implements Block, Serializable {

	/**
	 * 获取指定位置的模板源代码
	 *
	 * @param location 模板位置
	 * @return 模板源代码
	 */
	public abstract String getSource(Location location);

	/**
	 * 获取指定位置的模板源代码
	 *
	 * @param beginOffset 模板起始位置
	 * @param endOffset 模板结束位置
	 * @return 模板源代码
	 */
	public abstract String getSource(int beginOffset, int endOffset);

	/**
	 * 获取位置所在行的源代码
	 *
	 * @param location 位置
	 * @return 模板源代码
	 */
	public abstract String getLineSource(Location location);

	/**
	 * 获取位置所在行的源代码
	 *
	 * @param beginLine 模板起始位置
	 * @param endLine 模板结束位置
	 * @return 模板源代码
	 */
	public abstract String getLineSource(int beginLine, int endLine);

}