package org.commontemplate.core;

import java.io.Serializable;
import java.util.List;

/**
 * 模板接口.
 * <p/>
 * (线程安全)
 *
 * @see org.commontemplate.core.TemplateLoader
 * @see org.commontemplate.core.Factory
 * @author liangfei0201@163.com
 *
 */
public abstract class Template extends Resource implements Node, Serializable {

	public static final String TYPE = "Template";

	public String getType() {
		return TYPE;
	}

	/**
	 * 获取模板组成元素
	 *
	 * @return 模板组成元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

	/**
	 * 模板元素渲染接口
	 *
	 * @param context
	 *            模板上下文
	 * @throws RenderingException 模板渲染出错时抛出
	 */
	public abstract void render(Context context) throws RenderingException;

	/**
	 * 接收访问者, 并带领访问者遍历整个树 (中序遍历)<br>
	 *
	 * @param visitor 访问者
	 */
	public abstract void accept(TemplateVisitor visitor);

}