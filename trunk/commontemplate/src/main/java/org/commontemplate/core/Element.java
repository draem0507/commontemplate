package org.commontemplate.core;

import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 模板组成元素 <p/> (线程安全)
 * 
 * @author liangfei0201@163.com
 * 
 */
public abstract class Element implements Visitable, Serializable {

	/**
	 * 模板元素渲染接口
	 * 
	 * @param context
	 *            负责状态管理，输出等
	 * @throws RenderingException 模板元素渲染出错时抛出
	 */
	public abstract void render(Context context) throws RenderingException;
	
	/**
	 * 获取模板元素的名称
	 * 
	 * @return 模板元素名称
	 */
	public abstract String getName();

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
	public abstract String getCanonicalForm();

	/**
	 * 返回模板元素的标准组成, 同getCanonicalForm()
	 * 
	 * @return 模板元素的标准组成
	 */
	public String toString() {
		return getCanonicalForm();
	}

}
