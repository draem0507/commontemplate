package org.commontemplate.config;

import java.util.Map;

import org.commontemplate.core.EventListener;
import org.commontemplate.core.OutputFormatter;


/**
 * 总配置，上下文配置
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Configuration extends ResourceConfiguration {

	/**
	 * 获取是否为调试模式设置
	 *
	 * @return 是否为调试模式
	 */
	public boolean isDebug() {
		return false;
	}

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
	 * 获取上下文初始化器
	 *
	 * @return 上下文初始化器
	 */
	public abstract ContextInitializer getContextInitializer();

	/**
	 * 获取区域变量处理器
	 *
	 * @return 区域变量处理器
	 */
	public abstract Map getScopeHandlers();

	/**
	 * 获取输出缓存器
	 *
	 * @return 缓存器
	 */
	// public abstract Cache getOutputCache();

}