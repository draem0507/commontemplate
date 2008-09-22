package org.commontemplate.config;

/**
 * 缓存策略接口. (引擎使用cache的内部锁同步处理相关并发)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Cache {

	/**
	 * 载入已缓存的对象, 不存在或过期则返回null.
	 *
	 * @param key
	 *            索引
	 * @return 缓存对象
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public abstract Object get(Object key) throws CacheException;

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
	public abstract void put(Object key, Object value) throws CacheException;

	/**
	 * 移除指定缓存对象
	 *
	 * @param key
	 *            索引
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public abstract void remove(Object key) throws CacheException;

	/**
	 * 清除所有缓存对象
	 *
	 * @throws CacheException
	 *             所有缓存过程出现的异常
	 */
	public abstract void clear() throws CacheException;

	/**
	 * 是否支持乐观并发尝试，如果缓存策略的get()方法只作简单的取值，未修改任何状态(即无副作用)，则返回true，引擎将尝试乐观并发无锁读取
	 *
	 * @return 是否支持乐观并发尝试，默认为true.
	 */
	public boolean isOptimism() {
		return true;
	}

	/**
	 * 是否内部支持并发处理，如果缓存策略内部已处理并发同步锁机制，则返回true，引擎将不再做重复工作
	 *
	 * @return 是否内部支持并发处理，默认为false.
	 */
	public boolean isConcurrent() {
		return false;
	}

}
