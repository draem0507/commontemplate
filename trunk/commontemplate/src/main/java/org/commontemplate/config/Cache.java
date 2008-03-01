package org.commontemplate.config;

/**
 * 缓存策略接口. (引擎使用cache的内部锁同步处理相关并发)
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface Cache {

	/**
	 * 载入已缓存的对象, 不存在或过期则返回null.
	 * 
	 * @param key
	 *            索引
	 * @return 缓存对象
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public Object get(Object key) throws CacheException;

	/**
	 * 缓存对象
	 * 
	 * @param key
	 *            索引
	 * @param value
	 *            缓存对象
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public void put(Object key, Object value) throws CacheException;

	/**
	 * 移除指定缓存对象
	 * 
	 * @param key
	 *            索引
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public void remove(Object key) throws CacheException;

	/**
	 * 清除所有缓存对象
	 * 
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public void clear() throws CacheException;

}
