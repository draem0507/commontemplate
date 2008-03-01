package org.commontemplate.config;

/**
 * 缓存异常
 * 
 * @see org.commontemplate.config.Cache
 * @author liangfei0201@163.com
 * 
 */
public class CacheException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CacheException(Object key) {
		super();
		this.key = key;
	}

	public CacheException(Object key, Throwable cause) {
		super(cause);
		this.key = key;
	}

	public CacheException(Object key, String message) {
		super(message);
		this.key = key;
	}

	public CacheException(Object key, String message, Throwable cause) {
		super(message, cause);
		this.key = key;
	}

	private Object key;

	/**
	 * 获取异常发生时，正在处理的缓存对象名称
	 * 
	 * @return 缓存对象名称
	 */
	public Object getCacheKey() {
		return key;
	}

}
