package org.commontemplate.config;

/**
 * 模块元素渲染拦截器
 *
 * @author liangfei0201@163.com
 *
 */
public interface ElementInterceptor {

	/**
	 * 拦截模块元素渲染过程
	 *
	 * @param rendition 模块元素待渲染过程封装
	 */
	void intercept(ElementRendition rendition);

}
