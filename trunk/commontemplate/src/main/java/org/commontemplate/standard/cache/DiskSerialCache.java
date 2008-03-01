package org.commontemplate.standard.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.commontemplate.config.CacheException;
import org.commontemplate.config.Cache;
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
		Assert.assertNotEmpty(cacheDirectoryPath, "缓存存储位置不能为空!");
		File cacheDirectory = new File(cacheDirectoryPath);
		if (cacheDirectory.exists() && ! cacheDirectory.isDirectory())
			if (! cacheDirectory.delete())
				throw new RuntimeException("删除文件：" + cacheDirectoryPath + " 失败！");
		if (! cacheDirectory.exists())
			if (! cacheDirectory.mkdirs())
				throw new RuntimeException("创建文件夹：" + cacheDirectoryPath + " 失败！");
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
		Assert.assertNotNull(memoryCache, "内存缓存为空!");
		this.memoryCache = memoryCache;
	}
	
	private String replacer;
	
	public void setPathSeparatorReplacer(String replacer) {
		this.replacer = replacer;
	}
	
	private File getCacheFile(Object key) {
		String name = String.valueOf(key);
		if (prefix != null)
			name = prefix + name;
		if (suffix != null)
			name = name + suffix;
		return new File(getDirectory(), replacePath(name));
	}
	
	private static final String DEFAULT_REPLACER = "_-";
	
	private String replacePath(String path) {
		String r = replacer;
		if (r == null)
			r = DEFAULT_REPLACER;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, n = path.length(); i < n; i ++) {
			char ch = path.charAt(i);
			if (ch == '/' || ch == '\\')
				buffer.append(r);
			else
				buffer.append(ch);
		}
		return buffer.toString();
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
				throw new CacheException("删除文件：" + file.getPath() + " 失败！");
	}

	public void clear() throws CacheException {
		File[] files = getDirectory().listFiles();
		for (int i = 0, n = files.length; i < n; i++)
			if (!files[i].delete())
				throw new CacheException("删除文件：" + files[i].getPath() + " 失败！");
	}

}
