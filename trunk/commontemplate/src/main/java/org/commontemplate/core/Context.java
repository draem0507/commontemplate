package org.commontemplate.core;

import java.io.Writer;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 模板上下文, 状态维护及外部访问接口. <p/> (非线程安全,使用时应为每个线程创建独立的上下文)
 *
 * @see org.commontemplate.core.ContextFactory
 * @see org.commontemplate.core.Factory
 * @author liangfei0201@163.com
 *
 */
public abstract class Context extends LocalContext implements LocalContextStack,
		TemplateStack, TemplateFactory, MessageSource, EventPublisher,
		Logger {

	/**
	 * 以当前上下文构造条件重建新的上下文 (共享Writer,不共享数据)
	 *
	 * @return 新的上下文
	 */
	public abstract Context createContext();

	/**
	 * 获取全局上下文
	 *
	 * @return 全局上下文
	 */
	public abstract GlobalContext getGlobalContext();

	/**
	 * 获取当前上下文的输出端
	 *
	 * @return 输出端
	 */
	public abstract Writer getOut();

	/**
	 * 设置区域信息
	 *
	 * @param locale 区域信息
	 */
	//public abstract void setLocale(Locale locale);

	/**
	 * 获取当前上下文所在区域
	 *
	 * @return 所在区域
	 */
	public abstract Locale getLocale();

	/**
	 * 设置时区信息
	 *
	 * @param timeZone 时区信息
	 */
	//public abstract void setTimeZone(TimeZone timeZone);

	/**
	 * 获取时区
	 *
	 * @return 时区
	 */
	public abstract TimeZone getTimeZone();

	/**
	 * 判断是否为调试模式
	 *
	 * @return 是否为调试模式
	 */
	public abstract boolean isDebugMode();

	public abstract boolean isStep();

	public abstract void setStep(boolean step);

	public abstract boolean isOver();

	public abstract void setOver(boolean over);

}