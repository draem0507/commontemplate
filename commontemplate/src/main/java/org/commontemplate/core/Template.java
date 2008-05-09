package org.commontemplate.core;

import java.io.Serializable;
import java.util.List;

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
public abstract class Template extends Resource implements Visitable, Serializable {

	/**
	 * 模板渲染接口
	 *
	 * @param context
	 *            模板上下文
	 * @throws RenderingException 模板渲染出错时抛出
	 */
	public abstract void render(Context context) throws RenderingException;

	/**
	 * 获取模板组成元素
	 *
	 * @return 模板组成元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

	/**
	 * 获取模板的标准组成
	 *
	 * @return 模板的标准组成
	 */
	public abstract String getCanonicalForm();

	/**
	 * 获取原始内容
	 *
	 * @return 原始内容
	 */
	public abstract String getOriginalText();

}
