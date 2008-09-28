package org.commontemplate.config;

import org.commontemplate.core.ResourceLoader;

public abstract class ResourceConfiguration extends TemplateConfiguration {

	/**
	 * 获取模板加载器
	 *
	 * @return 模板加载器
	 */
	public abstract ResourceLoader getResourceLoader();

	/**
	 * 获取模板缓存器
	 *
	 * @return 缓存器
	 */
	public abstract Cache getTemplateCache();

	/**
	 * 获取模板持久化缓存器
	 *
	 * @return 缓存器
	 */
	public abstract Cache getTemplatePersistentCache();

	/**
	 * 获取热加载检查器
	 *
	 * @return 热加载检查器
	 */
	public abstract ReloadController getReloadController();

	/**
	 * 获取模板源比较器
	 *
	 * @return 模板源比较器
	 */
	public abstract ResourceComparator getResourceComparator();

	/**
	 * 获取路径过滤器
	 *
	 * @return 路径过滤器
	 */
	public abstract TemplateNameFilter getTemplateNameFilter();

}
