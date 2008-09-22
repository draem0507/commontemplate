package org.commontemplate.core;

import java.io.Writer;

/**
 * 模板上下文工厂 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public interface ContextFactory {

	/**
	 * 获取全局上下文, 从同一工厂产生的Context都共享此全局上下文
	 *
	 * @return 全局上下文
	 */
	public GlobalContext getGlobalContext();

	/**
	 * 创建新的模板上下文 使用JRE本地区域信息，即: Locale.getDefault()和TimeZone.getDefault()
	 *
	 * @param out
	 *            输出接口
	 * @return 新的模板上下文
	 */
	public Context createContext(Writer out);

}
