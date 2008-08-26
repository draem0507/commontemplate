package org.commontemplate.config;

import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Template;

/**
 * 模块元素待渲染过程封装
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Rendition {

	/**
	 * 获取待渲染模板元素
	 *
	 * @return 待渲染模板元素，如果拦截到的是模板渲染过程，则返回null
	 */
	public abstract Element getElement();

	/**
	 * 获取待渲染模板
	 *
	 * @return 待渲染模板，如果拦截到的是模板元素渲染过程，则返回元素所在模板
	 */
	public abstract Template getTemplate();

	/**
	 * 获取待渲染上下文
	 *
	 * @return 待渲染上下文
	 */
	public abstract Context getContext();

	/**
	 * 是否已渲染过
	 *
	 * @return 是否已渲染过
	 */
	public abstract boolean isRendered();

	/**
	 * 执行渲染过程或调用下一拦截器
	 */
	public abstract void doRender();

}
