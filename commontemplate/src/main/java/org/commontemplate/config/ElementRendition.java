package org.commontemplate.config;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;

/**
 * 模块元素待渲染过程封装
 *
 * @author liangfei0201@163.com
 *
 */
public interface ElementRendition {

	/**
	 * 获取待渲染模块元素
	 *
	 * @return 待渲染模块元素
	 */
	public Element getElement();

	/**
	 * 获取待渲染上下文
	 *
	 * @return 待渲染上下文
	 */
	public Context getContext();

	/**
	 * 是否已渲染过
	 *
	 * @return 是否已渲染过
	 */
	public boolean isRendered();

	/**
	 * 执行渲染过程或调用上一拦截器
	 */
	public void doRender();

}
