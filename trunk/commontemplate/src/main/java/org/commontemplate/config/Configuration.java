package org.commontemplate.config;

import org.commontemplate.core.EventListener;
import org.commontemplate.core.Logger;
import org.commontemplate.core.OutputFormatter;
import org.commontemplate.core.ResourceLoader;

/**
 * 总配置，上下文配置
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Configuration extends TemplateConfiguration {

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
	 * 获取输出缓存器
	 *
	 * @return 缓存器
	 */
	public abstract Cache getOutCache();

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
	 * 获取日志输出端
	 *
	 * @return 日志输出端
	 */
	public abstract Logger getLogger();

	/**
	 * 获取是否为调试模式设置
	 *
	 * @return 是否为调试模式
	 */
	public boolean isDebugMode() {
		return false;
	}

	/**
	 * 获取国际化资源工厂
	 *
	 * @return 国际化资源工厂
	 */
	public abstract ResourceBundleProvider getResourceBundleProvider();

	/**
	 * 获取默认的格式化过滤器
	 *
	 * @return 默认的格式化过滤器
	 */
	public abstract OutputFormatter getDefaultOutputFormatter();

	/**
	 * 获取事件监听器
	 *
	 * @return 事件监听器
	 */
	public abstract EventListener getEventListener();

	/**
	 * 获取路径过滤器
	 *
	 * @return 路径过滤器
	 */
	public abstract TemplateNameFilter getTemplateNameFilter();

}