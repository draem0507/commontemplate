package org.commontemplate.core;

import java.util.List;

/**
 * 模板元素上下文栈
 *
 * @author liangfei0201@163.com
 *
 */
public interface ElementStack {

	/**
	 * 压入模板元素
	 *
	 * @param element 模板元素
	 */
	public void pushElement(Element element);

	/**
	 * 弹出模板元素
	 */
	public void popElement();

	/**
	 * 获取当前(栈顶)模板元素
	 *
	 * @return 当前(栈顶)模板元素
	 */
	public Element getCurrentElement();

	/**
	 * 通过模板元素名称查找栈中的模板元素
	 *
	 * @param name 模板元素名称
	 * @return 模板元素
	 */
	public Element findElement(String name);

	/**
	 * 获取模板元素栈中所有元素
	 *
	 * @return 栈中所有模板元素, 类型: List&lt;Element&gt;
	 */
	public List getElementStackValues();

	/**
	 * 清空栈中所有模板元素
	 */
	public void clearElements();

}
