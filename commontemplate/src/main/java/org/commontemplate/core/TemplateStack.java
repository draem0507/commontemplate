package org.commontemplate.core;

import java.util.List;

/**
 * 模板上下文栈
 *
 * @author liangfei0201@163.com
 *
 */
public interface TemplateStack {

	/**
	 * 压入当前模板名称
	 *
	 * @param template
	 *            当前模板名称
	 */
	public void pushTemplate(Template template);

	/**
	 * 弹出当前模板名称
	 */
	public void popTemplate();

	/**
	 * 获取当前模板名称
	 *
	 * @return 当前模板名称
	 */
	public Template getCurrentTemplate();

	/**
	 * 查找模板
	 *
	 * @param name 模板名称
	 * @return 对应的模板，未找到时返回null
	 */
	public Template findTemplate(String name);

	/**
	 * 获取模板包含关系栈
	 *
	 * @return 模板包含关系栈的值, 类型: List&lt;Template&gt;
	 */
	public List getTemplateStackValues();

	/**
	 * 清空模板名称栈
	 */
	public void clearTemplates();

	/**
	 * 根据当前模板名称过滤相关的模板名称
	 *
	 * @param name 模板名称
	 * @return 过滤后的模板名称
	 */
	public String relateTemplateName(String name);

}
