package org.commontemplate.config;

/**
 * 路径过滤器. <p/> 用于保证同一模板的引用路径相同.<br/> 因为缓存等需要用过滤后的路径作唯一索引.<br/>
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface TemplateNameFilter {

	/**
	 * 过滤模板路径. <p/> 将在缓存索引及加载模板源之前调用
	 * 
	 * @param templateName
	 *            引用的模板路径
	 * @param currentTemplateName
	 *            当前模板名, 如果是从Engine直接获取的模板则currentTemplateName为null
	 * 
	 * @return 相对于虚拟根目录的唯一路径
	 * @throws InvalidTemplateNameException
	 *             非法路径时抛出
	 */
	public String filter(String templateName, String currentTemplateName)
			throws InvalidTemplateNameException;

}
