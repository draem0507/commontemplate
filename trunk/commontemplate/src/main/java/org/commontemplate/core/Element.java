package org.commontemplate.core;

import java.io.IOException;
import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 模板组成元素 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Element implements Node, Serializable {

	/**
	 * 模板元素渲染接口
	 *
	 * @param context
	 *            模板上下文
	 * @throws RenderingException 模板元素渲染出错时抛出
	 */
	public abstract void render(Context context) throws RenderingException;

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
	 * 返回模板元素的标准组成, 同getCanonicalForm()
	 *
	 * @return 模板元素的标准组成
	 */
	public String toString() {
		try {
			return getSource();
		} catch (IOException e) {
			return "ERROR:" + e.getMessage();
		}
	}

}
