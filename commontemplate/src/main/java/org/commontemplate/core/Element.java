package org.commontemplate.core;

import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 模板组成元素 <p/> (线程安全)
 *
 * @see org.commontemplate.core.Template#getElements()
 * @see org.commontemplate.core.BlockDirective#getElements()
 * @author liangfei0201@163.com
 *
 */
public abstract class Element implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板元素渲染接口
	 *
	 * @param context
	 *            模板上下文
	 * @throws RenderingException 模板元素渲染出错时抛出
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
	 * @see org.commontemplate.core.Element#accept(TemplateVisitor)
	 * @param visitor 访问者
	 * @param isEnter 是否为入口, 在入口处忽略StopVisitException
	 */
	public abstract void accept(TemplateVisitor visitor, boolean isEnter);

	/**
	 * 获取模板元素名称
	 *
	 * @return 模板元素名称
	 */
	public abstract String getName();

	/**
	 * 获取元素所属模板
	 *
	 * @return 模板
	 */
	public abstract Template getTemplate();

	/**
	 * 获取模板元素在模板中的位置
	 *
	 * @return 元素在模板中的位置
	 */
	public abstract Location getLocation();

	/**
	 * 获取模板元素的标准组成
	 *
	 * @return 模板元素的标准组成
	 */
	public String getSource() {
		Template template = getTemplate();
		Location location = getLocation();
		if (template != null && location != null)
			return template.getSource(location);
		return null;
	}

	/**
	 * 返回模板元素的标准组成, 同getCanonicalForm()
	 *
	 * @return 模板元素的标准组成
	 */
	public String toString() {
		return getSource();
	}

}
