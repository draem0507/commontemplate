package org.commontemplate.standard.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;
import org.commontemplate.util.Assert;

/**
 * 磁盘序列化缓存
 * <p/>
 * 在保证整个被缓存对象树都是可序列化的前提下才可以使用该策略
 *
 * @author liangfei0201@163.com
 *
 */
public class DiskSerialCache implements Cache {

	// FIXME 并发时, 磁盘缓存发生java.io.EOFException, 读取到了不完整的缓存文件
	// 以及并发创建文件夹：./cache/ 失败！

	private String root;

	/**
	 * 根目录
	 *
	 * @param root 根目录
	 */
	public void setRootDirectory(String root) {
		this.root = filterDirectory(root);
	}

	private String directory;

	/**
	 * 设置缓存存储路径
	 *
	 * @param directory
	 *            缓存存储路径
	 */
	public void setDirectory(String directory) {
		this.directory = filterDirectory(directory);
	}

	private String filterDirectory(String directory) {
		if (directory == null)
			return null;
		directory = directory.trim();
		if (directory.length() > 0) {
			char end = directory.charAt(directory.length() - 1);
			if (end != '/' && end != '\\')
				return directory + '/';
		}
		return directory;
	}

	private File getDirectory() {
		String cacheDirectoryPath = directory;
		if (root != null)
			cacheDirectoryPath = root + cacheDirectoryPath;
		Assert.assertNotEmpty(cacheDirectoryPath, "DiskSerialCache.cache.directory.required");
		File cacheDirectory = new File(cacheDirectoryPath);
		if (cacheDirectory.exists() && ! cacheDirectory.isDirectory())
			if (! cacheDirectory.delete())
				throw new CacheException(null, "DiskSerialCache.delete.directory.error", new Object[]{cacheDirectoryPath});
		if (! cacheDirectory.exists())
			if (! cacheDirectory.mkdirs())
				throw new CacheException(null, "DiskSerialCache.create.directory.error", new Object[]{cacheDirectoryPath});
		return cacheDirectory;
	}

	private String prefix;

	/**
	 * 设置缓存文件前缀
	 *
	 * @param prefix
	 *            缓存文件前缀
	 */
	public void setFilePrefix(String prefix) {
		this.prefix = prefix;
	}

	private String suffix;

	/**
	 * 设置缓存文件后缀
	 *
	 * @param suffix
	 *            缓存文件后缀
	 */
	public void setFileSuffix(String suffix) {
		this.suffix = suffix;
	}

	private Cache memoryCache;

	/**
	 * 设置内存缓存
	 *
	 * @param memoryCache
	 *            内存缓存
	 */
	public void setMemoryCache(Cache memoryCache) {
		Assert.assertNotNull(memoryCache, "DiskSerialCache.memory.cache.required");
		this.memoryCache = memoryCache;
	}

	private File getCacheFile(Object key) {
		String name = String.valueOf(key);
		if (prefix != null)
			name = prefix + name;
		if (suffix != null)
			name = name + suffix;
		return new File(getDirectory(), replacePath(name));
	}

	private String replacePath(String path) {
		try {
			return URLEncoder.encode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new CacheException(e);
		}
	}

	public Object get(Object key) throws CacheException {
		Object t = memoryCache.get(key);
		if (t != null)
			return t;
		try {
			File file = getCacheFile(key);
			if (!file.exists())
				return null;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			Object t2 = ois.readObject();
			if (t2 == null)
				return null;
			memoryCache.put(key, t2);
			return t2;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			throw new CacheException(key, e);
		} catch (Exception e) {
			throw new CacheException(key, e);
		}
	}

	public void put(Object key, Object value) throws CacheException {
		memoryCache.put(key, value);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(getCacheFile(key)));
			oos.writeObject(value);
		} catch (Exception e) {
			throw new CacheException(key, e);
		}
	}

	public void remove(Object key) throws CacheException {
		File file = getCacheFile(key);
		if (file.exists())
			if (!file.delete())
				throw new CacheException(key, "DiskSerialCache.delete.directory.error", new Object[]{file.getPath()});
	}

	public void clear() throws CacheException {
		File[] files = getDirectory().listFiles();
		for (int i = 0, n = files.length; i < n; i++)
			if (!files[i].delete())
				throw new CacheException(null, "DiskSerialCache.delete.directory.error", new Object[]{files[i].getPath()});
	}

}
