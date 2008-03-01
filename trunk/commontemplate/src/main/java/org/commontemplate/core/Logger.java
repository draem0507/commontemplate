package org.commontemplate.core;

/**
 * 日志接口 <p/> 声明：引用自commons-logging
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface Logger {

	/**
	 * 输出调试信息
	 * 
	 * @param msg 信息内容
	 */
	public void debug(String msg);

	/**
	 * 输出调试信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	public void debug(String msg, Throwable e);

	/**
	 * 输出普通信息
	 * 
	 * @param msg 信息内容
	 */
	public void info(String msg);
	
	// 去掉原因：info应该表示正常信息，不应该用于输出异常信息
	//public void info(String msg, Throwable e);

	/**
	 * 输出警告信息
	 * 
	 * @param msg 信息内容
	 */
	public void warn(String msg);

	/**
	 * 输出警告信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	public void warn(String msg, Throwable e);

	/**
	 * 输出错误信息
	 * 
	 * @param msg 信息内容
	 */
	public void error(String msg);

	/**
	 * 输出错误信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	public void error(String msg, Throwable e);

	/**
	 * 调试信息是否开启
	 * 
	 * @return 是否开启
	 */
	public boolean isDebugEnabled();

	/**
	 * 普通信息是否开启
	 * 
	 * @return 是否开启
	 */
	public boolean isInfoEnabled();

	/**
	 * 警告信息是否开启
	 * 
	 * @return 是否开启
	 */
	public boolean isWarnEnabled();
	
	/**
	 * 错误信息是否开启
	 * 
	 * @return 是否开启
	 */
	public boolean isErrorEnabled();

	/**
	 * 致命信息是否开启
	 * 
	 * @return 是否开启
	 */
	public boolean isFatalEnabled();

}
