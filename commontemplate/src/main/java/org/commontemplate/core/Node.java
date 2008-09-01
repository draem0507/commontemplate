package org.commontemplate.core;

import java.io.IOException;

/**
 * 模板与模板元素的共同抽象,渲染接口.
 *
 * @author liangfei0201@163.com
 *
 */
public interface Node {

	/**
	 * 获取模板/元素名称
	 *
	 * @return 模板/元素名称
	 */
	public abstract String getName();

	/**
	 * 获取节点类型
	 *
	 * @return 节点类型
	 */
	public abstract String getType();

	/**
	 * 获取模板/元素的标准组成
	 *
	 * @return 模板/元素的标准组成
	 * @throws IOException 获取源失败时报错
	 */
	public abstract String getSource() throws IOException;

}
