package org.commontemplate.core;

/**
 * 国际化信息源
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface MessageSource {

	/**
	 * 获取默认的本地信息资源
	 * 
	 * @param key
	 *            索引
	 * @return 本地信息资源
	 * @throws NoSuchMessageException
	 *             当信息不存在时抛出
	 */
	public String getMessage(String key) throws NoSuchMessageException;

	/**
	 * 获取默认的本地信息资源
	 * 
	 * @param key
	 *            索引
	 * @param args
	 *            动态参数
	 * @return 本地信息资源
	 * @throws NoSuchMessageException
	 *             当信息不存在时抛出
	 */
	public String getMessage(String key, Object[] args)
			throws NoSuchMessageException;

	/**
	 * 获取默认的本地信息资源
	 * 
	 * @param key
	 *            索引
	 * @param defaultValue
	 *            默认值，当本地信息不存在时返回
	 * @return 本地信息资源
	 */
	public String getMessage(String key, String defaultValue);

	/**
	 * 获取默认的本地信息资源
	 * 
	 * @param key
	 *            索引
	 * @param args
	 *            动态参数
	 * @param defaultValue
	 *            默认值，当本地信息不存在时返回
	 * @return 本地信息资源
	 */
	public String getMessage(String key, Object[] args, String defaultValue);

}
