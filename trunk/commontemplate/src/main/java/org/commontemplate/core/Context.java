package org.commontemplate.core;

import java.io.Serializable;
import java.io.Writer;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 模板上下文, 状态维护及外部访问接口.
 * <p/> (非线程安全,使用时应为每个线程创建独立的上下文)
 * <p/> (序列化后, 将抛弃Writer, 只作为数据容器传递, 反序列化后, 不可再用getOut()与output()等方法)
 *
 * @see org.commontemplate.core.ContextFactory
 * @see org.commontemplate.core.Factory
 * @author liangfei0201@163.com
 *
 */
public abstract class Context extends LocalContext
			implements LocalContextStack, ElementStack, TemplateStack,
			ContextFactory, EventPublisher, Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract TemplateLoader getTemplateLoader();

	/**
	 * 以当前上下文构造条件重建新的上下文 (共享Writer,不共享数据)
	 *
	 * @return 新的上下文
	 */
	public abstract Context createContext();

	/**
	 * 获取当前上下文的输出端.
	 * 如果从输出端直接输出将绕过所有<code>OutputFilter</code>与<code>OutputFormatter</code>.
	 *
	 * @see org.commontemplate.core.OutputController#output(Object)
	 * @return 输出端
	 */
	public abstract Writer getOut();

	/**
	 * 设置区域信息
	 *
	 * @param locale 区域信息
	 */
	public abstract void setLocale(Locale locale);

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
	public abstract void setTimeZone(TimeZone timeZone);

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
	public abstract boolean isDebug();

	/**
	 * 设置调试状态
	 *
	 * @param debug 是否为调试状态
	 */
	public abstract void setDebug(boolean debug);

}