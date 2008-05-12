package org.commontemplate.core;

/**
 * 渲染接口
 *
 * @author liangfei0201@163.com
 *
 */
public interface Node extends Visitable {

	/**
	 * 获取模板/元素名称
	 *
	 * @return 模板/元素名称
	 */
	public abstract String getName();

	/**
	 * 获取模板/元素的标准组成
	 *
	 * @return 模板/元素的标准组成
	 */
	public abstract String getCanonicalForm();

	/**
	 * 模板/模板元素渲染接口
	 *
	 * @param context
	 *            模板上下文
	 * @throws RenderingException 模板渲染出错时抛出
	 */
	public abstract void render(Context context) throws RenderingException;

}
