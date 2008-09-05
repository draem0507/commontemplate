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
public abstract class Template extends Resource implements Serializable {

	/**
	 * 模板元素渲染接口
	 *
	 * @param context
	 *            模板上下文
	 * @throws RenderingException 模板渲染出错时抛出
	 */
	public abstract void render(Context context) throws RenderingException;

	/**
	 * 接收访问者, 并带领访问者遍历整个树
	 *
	 * @param visitor 访问者
	 */
	public void accept(TemplateVisitor visitor) {
		accept(visitor, true);
	}

	/**
	 * 接收访问者, 并带领访问者遍历整个树<br>
	 * 通常直接使用accept(Visitor visitor)<br>
	 *
	 * @see org.commontemplate.core.Template#accept(TemplateVisitor)
	 * @param visitor 访问者
	 * @param isEnter 是否为入口, 在入口处忽略StopVisitException
	 */
	public abstract void accept(TemplateVisitor visitor, boolean isEnter);

	/**
	 * 获取模板组成元素
	 *
	 * @return 模板组成元素, 类型: List&lt;Element&gt;
	 */
	public abstract List getElements();

}