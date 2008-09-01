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
	 * 接收访问者, 并带领访问者遍历整个树 (中序遍历)<br>
	 *
	 * @param visitor 访问者
	 */
	public abstract void accept(TemplateVisitor visitor);

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
