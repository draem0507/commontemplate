package org.commontemplate.standard.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class DiskSerialCache extends Cache {

	private String root = "";

	/**
	 * 根目录
	 *
	 * @param root 根目录
	 */
	public void setRootDirectory(String root) {
		this.root = filterDirectory(root);
		initCacheDirectoryPath();
	}

	private String directory = "";

	/**
	 * 设置缓存存储路径
	 *
	 * @param directory
	 *            缓存存储路径
	 */
	public void setDirectory(String directory) {
		this.directory = filterDirectory(directory);
		initCacheDirectoryPath();
	}

	private String filterDirectory(String directory) {
		if (directory == null)
			return "";
		directory = directory.trim();
		if (directory.length() > 0) {
			char end = directory.charAt(directory.length() - 1);
			if (end != '/' && end != '\\')
				return directory + '/';
		}
		return directory;
	}

	private String cacheDirectoryPath = "";

	private void initCacheDirectoryPath() {
		cacheDirectoryPath = root + directory;
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

	private File getCacheDirectory() {
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

	private File getCacheFile(Object key) {
		String name = encodePath(String.valueOf(key));
		if (prefix != null)
			name = prefix + name;
		if (suffix != null)
			name = name + suffix;
		return new File(cacheDirectoryPath + name);
	}

	private String encodePath(String path) {
		try {
			return URLEncoder.encode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new CacheException(e);
		}
	}

	public Object get(Object key) throws CacheException {
		try {
			File file = getCacheFile(key);
			if (! file.exists())
				return null;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			return ois.readObject();
		} catch (Exception e) { // 读取对象流失败, 忽略, 不影响运行
			e.printStackTrace();
			return null;
		}
	}

	public void put(Object key, Object value) throws CacheException {
		try {
			getCacheDirectory(); // 确保缓存目录存在
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getCacheFile(key)));
			oos.writeObject(value);
		} catch (Exception e) { // 写入对象失败, 忽略, 不影响运行
			e.printStackTrace();
			// ignore
		}
	}

	public void remove(Object key) throws CacheException {
		File file = getCacheFile(key);
		if (file.exists()) {
			if (! file.delete())
				throw new CacheException(key, "DiskSerialCache.delete.file.error", new Object[]{file.getPath()});
		}
	}

	public void clear() throws CacheException {
		File[] files = getCacheDirectory().listFiles();
		for (int i = 0, n = files.length; i < n; i++) {
			File file = files[i];
			if (file.exists()) {
				if (! file.delete())
					throw new CacheException(null, "DiskSerialCache.delete.file.error", new Object[]{files[i].getPath()});
			}
		}
	}

}
