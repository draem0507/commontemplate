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
public abstract class Element implements Node, Renderable, Serializable {

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

	public int accept(Visitor visitor) {
		int v = visitor.visit(this);
		if (v == Visitor.STOP)
			return Visitor.STOP;
		if (v == Visitor.SKIP)
			return Visitor.NEXT;
		return guide(visitor);
	}

	protected int guide(Visitor visitor) {
		return Visitor.NEXT;
	}

}
