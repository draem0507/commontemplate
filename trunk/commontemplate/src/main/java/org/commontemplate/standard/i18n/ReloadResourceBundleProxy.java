package org.commontemplate.standard.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

import sun.misc.SoftCache;
/**
 * 改造的ResourceBundle，可以实现热加载功能。<br>
 * 虽然这个类继承自 ResourceBundle，但是因为ResourceBundle 自身的特点，<br>
 * 所以无法完全重写来实现，所以这个类仅仅是照搬了JDK1.4 的代码，对于热加载进行了<br>
 * 部分改造，所以这个类只能说是 ResourceBundle 的一个代理类。<br>
 * <b>修改的方法：</b><br>
 * <li>findBundleInCache</li>
 * <br>增加了cache 的处理。
 * <li>putBundleInCache</li>
 * <br>增加了 cache 的处理。
 * <br><b>增加的方法:</b><br>
 * <li>isFileModified</li>
 * <li>cleanCacheProcess</li>
 * <li>removeCacheObject</li>
 * <li>cleanAllModifiedBundleCache</li>
 * <li>clearCache</li>
 * @author YanRong
 *
 */
public class ReloadResourceBundleProxy extends ResourceBundle {

	/** reference JDK1.4 source */
	private static final ResourceCacheKey cacheKey = new ResourceCacheKey();

	/** reference JDK1.4 source */
	private static final int INITIAL_CACHE_SIZE = 25;

	/** reference JDK1.4 source */
	private static final float CACHE_LOAD_FACTOR = (float) 1.0;

	/** reference JDK1.4 source */
	private static final int MAX_BUNDLES_SEARCHED = 3;

	/** reference JDK1.4 source */
	private static final Hashtable underConstruction = new Hashtable(
			MAX_BUNDLES_SEARCHED, CACHE_LOAD_FACTOR);

	/** reference JDK1.4 source */
	private static final Integer DEFAULT_NOT_FOUND = new Integer(-1);

	/** reference JDK1.4 source */
	private static SoftCache cacheList = new SoftCache(INITIAL_CACHE_SIZE,
			CACHE_LOAD_FACTOR);

	/** reference JDK1.4 source */
	protected ResourceBundle parent = null;

	/** 真正的 ResourceBundle */
	protected ResourceBundle realBundle;

	/**
	 * 刷新间隔的时间
	 */
	private static long refreshInterval;

	/**
	 * 对每一个basename,都保存最新的修改的时间<br>
	 * key 是 .properties文件，包含全路径<br>
	 * value 是 最后的修改时间。
	 */
	private static SoftCache baseNameModifyCache = new SoftCache(INITIAL_CACHE_SIZE,
			CACHE_LOAD_FACTOR);

	/**
	 * 对每一个basename,都保存最后的读取的时间<br>
	 * key 是 baseName<br>
	 * value 是 最后的读取时间。
	 */
	private static SoftCache baseNameIntervalCache = new SoftCache(INITIAL_CACHE_SIZE,
			CACHE_LOAD_FACTOR);

	/** reference JDK1.4 source */
	private Locale locale = null;

	/**
	 * 用于实现代理功能的构造方法
	 * @param bundle
	 */
	private ReloadResourceBundleProxy(ResourceBundle bundle) {

		realBundle = bundle;
	}

	/** reference JDK1.4 source */
	public Locale getLocale() {
		return locale;
	}

	/** reference JDK1.4 source */
	private void setLocale(String baseName, String bundleName) {
		if (baseName.length() == bundleName.length()) {
			locale = new Locale("", "");
		} else if (baseName.length() < bundleName.length()) {
			int pos = baseName.length();
			String temp = bundleName.substring(pos + 1);
			pos = temp.indexOf('_');
			if (pos == -1) {
				locale = new Locale(temp, "", "");
				return;
			}

			String language = temp.substring(0, pos);
			temp = temp.substring(pos + 1);
			pos = temp.indexOf('_');
			if (pos == -1) {
				locale = new Locale(language, temp, "");
				return;
			}

			String country = temp.substring(0, pos);
			temp = temp.substring(pos + 1);

			locale = new Locale(language, country, temp);
		} else {
			//The base name is longer than the bundle name.  Something is very wrong
			//with the calling code.
			throw new IllegalArgumentException();
		}
	}

	/** reference JDK1.4 source */
	protected void setParent(ResourceBundle parent) {
		this.parent = parent;
	}

	/** reference JDK1.4 source */
	private static final class ResourceCacheKey implements Cloneable {
		private SoftReference loaderRef;

		private String searchName;

		private Locale defaultLocale;

		private int hashCodeCache;

		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			try {
				final ResourceCacheKey otherEntry = (ResourceCacheKey) other;
				//quick check to see if they are not equal
				if (hashCodeCache != otherEntry.hashCodeCache) {
					return false;
				}
				//are the names the same?
				if (!searchName.equals(otherEntry.searchName)) {
					return false;
				}
				// are the default locales the same?
				if (defaultLocale == null) {
					if (otherEntry.defaultLocale != null) {
						return false;
					}
				} else {
					if (!defaultLocale.equals(otherEntry.defaultLocale)) {
						return false;
					}
				}
				//are refs (both non-null) or (both null)?
				if (loaderRef == null) {
					return otherEntry.loaderRef == null;
				} else {
					return (otherEntry.loaderRef != null)
							&& (loaderRef.get() == otherEntry.loaderRef.get());
				}
			} catch (NullPointerException e) {
				return false;
			} catch (ClassCastException e) {
				return false;
			}
		}

		public int hashCode() {
			return hashCodeCache;
		}

		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				//this should never happen
				throw new InternalError();
			}
		}

		public void setKeyValues(ClassLoader loader, String searchName,
				Locale defaultLocale) {
			this.searchName = searchName;
			hashCodeCache = searchName.hashCode();
			this.defaultLocale = defaultLocale;
			if (defaultLocale != null) {
				hashCodeCache ^= defaultLocale.hashCode();
			}
			if (loader == null) {
				this.loaderRef = null;
			} else {
				loaderRef = new SoftReference(loader);
				hashCodeCache ^= loader.hashCode();
			}
		}

		public void clear() {
			setKeyValues(null, "", null);
		}
	}

	/** reference JDK1.4 source */
	public static ResourceBundle getBundle(String baseName, Locale locale,
			ClassLoader loader) {
		if (loader == null) {
			throw new NullPointerException();
		}

		return getBundleImpl(baseName, locale, loader);
	}

	/** reference JDK1.4 source */
	private static ResourceBundle getBundleImpl(String baseName, Locale locale,
			ClassLoader loader) {
		if (baseName == null) {
			throw new NullPointerException();
		}

		//We use the class loader as the "flag" value that signifies a bundle
		//that could not be found.  This allows the entries to be garbage
		//collected when the loader gets garbage collected.  If we don't
		//have a loader, use a default value for NOTFOUND.
		final Object NOTFOUND = (loader != null) ? (Object) loader
				: (Object) DEFAULT_NOT_FOUND;

		//fast path the case where the bundle is cached
		String bundleName = baseName;
		String localeSuffix = locale.toString();
		if (localeSuffix.length() > 0) {
			bundleName += "_" + localeSuffix;
		} else if (locale.getVariant().length() > 0) {
			//This corrects some strange behavior in Locale where
			//new Locale("", "", "VARIANT").toString == ""
			bundleName += "___" + locale.getVariant();
		}

		// The default locale may influence the lookup result, and
		// it may change, so we get it here once.
		Locale defaultLocale = Locale.getDefault();

		Object lookup = findBundleInCache(loader, baseName, bundleName, locale, defaultLocale);
		if (lookup == NOTFOUND) {
			throwMissingResourceException(baseName, locale);
		} else if (lookup != null) {
			return (ResourceBundle) lookup;
		}

		//The bundle was not cached, so start doing lookup at the root
		//Resources are loaded starting at the root and working toward
		//the requested bundle.

		//If findBundle returns null, we become responsible for defining
		//the bundle, and must call putBundleInCache to complete this
		//task.  This is critical because other threads may be waiting
		//for us to finish.

		Object parent = NOTFOUND;
		try {
			//locate the root bundle and work toward the desired child
			Object root = findBundle(loader, baseName, defaultLocale, baseName,
					null, NOTFOUND);
			if (root == null) {
				putBundleInCache(loader, baseName, defaultLocale, NOTFOUND);
				root = NOTFOUND;
			}

			// Search the main branch of the search tree.
			// We need to keep references to the bundles we find on the main path
			// so they don't get garbage collected before we get to propagate().
			final Vector names = calculateBundleNames(baseName, locale);
			Vector bundlesFound = new Vector(MAX_BUNDLES_SEARCHED);
			// if we found the root bundle and no other bundle names are needed
			// we can stop here. We don't need to search or load anything further.
			boolean foundInMainBranch = (root != NOTFOUND && names.size() == 0);

			if (!foundInMainBranch) {
				parent = root;
				for (int i = 0; i < names.size(); i++) {
					bundleName = (String) names.elementAt(i);
					lookup = findBundle(loader, bundleName, defaultLocale,
							baseName, parent, NOTFOUND);
					bundlesFound.addElement(lookup);
					if (lookup != null) {
						parent = lookup;
						foundInMainBranch = true;
					}
				}
			}
			parent = root;
			if (!foundInMainBranch) {
				//we didn't find anything on the main branch, so we do the fallback branch
				final Vector fallbackNames = calculateBundleNames(baseName,
						defaultLocale);
				for (int i = 0; i < fallbackNames.size(); i++) {
					bundleName = (String) fallbackNames.elementAt(i);
					if (names.contains(bundleName)) {
						//the fallback branch intersects the main branch so we can stop now.
						break;
					}
					lookup = findBundle(loader, bundleName, defaultLocale,
							baseName, parent, NOTFOUND);
					if (lookup != null) {
						parent = lookup;
					} else {
						//propagate the parent to the child.  We can do this
						//here because we are in the default path.
						putBundleInCache(loader, bundleName, defaultLocale,
								parent);
					}
				}
			}
			//propagate the inheritance/fallback down through the main branch
			parent = propagate(loader, names, bundlesFound, defaultLocale,
					parent);
		} catch (Exception e) {
			//We should never get here unless there has been a change
			//to the code that doesn't catch it's own exceptions.
			cleanUpConstructionList();
			throwMissingResourceException(baseName, locale);
		} catch (Error e) {
			//The only Error that can currently hit this code is a ThreadDeathError
			//but errors might be added in the future, so we'll play it safe and
			//clean up.
			cleanUpConstructionList();
			throw e;
		}
		if (parent == NOTFOUND) {
			throwMissingResourceException(baseName, locale);
		}
		return (ResourceBundle) parent;
	}

	/**
	 * 判断 properties 文件是否被修改了。
	 * @param loader
	 * @param bundleName
	 * @return
	 */
	private static boolean isFileModified(final ClassLoader loader, String bundleName) {
		try {

			final String resName = bundleName.replace('.', '/') + ".properties";
			URL url = (URL) java.security.AccessController
					.doPrivileged(new java.security.PrivilegedAction() {
						public Object run() {
							if (loader != null) {
								return loader.getResource(resName);
							} else {
								return ClassLoader.getSystemResource(resName);
							}
						}
					});

			// 文件存在的话
			if(url != null) {
				Long cachedLastModified = (Long) baseNameModifyCache.get(resName);
				long lastModified = url.openConnection().getLastModified();

				if(cachedLastModified == null) {
					cachedLastModified = new Long(0);
				}

				if(cachedLastModified.longValue() != lastModified) {
					cachedLastModified = new Long(lastModified);
					baseNameModifyCache.put(resName, cachedLastModified);
					return true;
				}
			}
		} catch (IOException e) {
			// 如果出现 IOException ，也认为文件被修改了
			return true;
		}
		return false;
	}

	/** reference JDK1.4 source */
	private static Object propagate(ClassLoader loader, Vector names,
			Vector bundlesFound, Locale defaultLocale, Object parent) {
		for (int i = 0; i < names.size(); i++) {
			final String bundleName = (String) names.elementAt(i);
			final Object lookup = bundlesFound.elementAt(i);
			if (lookup == null) {
				putBundleInCache(loader, bundleName, defaultLocale, parent);
			} else {
				parent = lookup;
			}
		}
		return parent;
	}

	/** reference JDK1.4 source */
	private static void throwMissingResourceException(String baseName,
			Locale locale) throws MissingResourceException {
		throw new MissingResourceException("Can't find bundle for base name "
				+ baseName + ", locale " + locale, baseName + "_" + locale, "");
	}

	/** reference JDK1.4 source */
	private static void cleanUpConstructionList() {
		synchronized (cacheList) {
			final Collection entries = underConstruction.values();
			final Thread thisThread = Thread.currentThread();
			while (entries.remove(thisThread)) {
			}
		}
	}

	/** reference JDK1.4 source */
	private static Object findBundle(ClassLoader loader, String bundleName,
			Locale defaultLocale, String baseName, Object parent,
			final Object NOTFOUND) {
		Object result;
		synchronized (cacheList) {
			//check for bundle in cache

			cacheKey.setKeyValues(loader, bundleName, defaultLocale);
			result = cacheList.get(cacheKey);
			if (result != null) {
				cacheKey.clear();
				return result;
			}
			// check to see if some other thread is building this bundle.
			// Note that there is a rare chance that this thread is already
			// working on this bundle, and in the process getBundle was called
			// again, in which case we can't wait (4300693)
			Thread builder = (Thread) underConstruction.get(cacheKey);
			boolean beingBuilt = (builder != null && builder != Thread
					.currentThread());
			//if some other thread is building the bundle...
			if (beingBuilt) {
				//while some other thread is building the bundle...
				while (beingBuilt) {
					cacheKey.clear();
					try {
						//Wait until the bundle is complete
						cacheList.wait();
					} catch (InterruptedException e) {
					}
					cacheKey.setKeyValues(loader, bundleName, defaultLocale);
					beingBuilt = underConstruction.containsKey(cacheKey);
				}

				cacheKey.setKeyValues(loader, bundleName, defaultLocale);
				//if someone constructed the bundle for us, return it
				result = cacheList.get(cacheKey);
				if (result != null) {
					cacheKey.clear();
					return result;
				}
			}
			//The bundle isn't in the cache, so we are now responsible for
			//loading it and adding it to the cache.
			final Object key = cacheKey.clone();
			underConstruction.put(key, Thread.currentThread());
			//the bundle is removed from the cache by putBundleInCache
			cacheKey.clear();
		}

		//try loading the bundle via the class loader
		result = loadBundle(loader, bundleName, defaultLocale);
		if (result != null) {
			// check whether we're still responsible for construction -
			// a recursive call to getBundle might have handled it (4300693)
			boolean constructing;
			synchronized (cacheList) {
				cacheKey.setKeyValues(loader, bundleName, defaultLocale);
				constructing = underConstruction.get(cacheKey) == Thread
						.currentThread();
				cacheKey.clear();
			}
			if (constructing) {
				// set the bundle's parent and put it in the cache
				final ReloadResourceBundleProxy bundle = (ReloadResourceBundleProxy) result;
				if (parent != NOTFOUND && bundle.parent == null) {
					bundle.setParent((ResourceBundle) parent);
				}
				bundle.setLocale(baseName, bundleName);
				putBundleInCache(loader, bundleName, defaultLocale, result);
			}
		}
		return result;
	}

	/** reference JDK1.4 source */
	private static Vector calculateBundleNames(String baseName, Locale locale) {
		final Vector result = new Vector(MAX_BUNDLES_SEARCHED);
		final String language = locale.getLanguage();
		final int languageLength = language.length();
		final String country = locale.getCountry();
		final int countryLength = country.length();
		final String variant = locale.getVariant();
		final int variantLength = variant.length();

		if (languageLength + countryLength + variantLength == 0) {
			//The locale is "", "", "".
			return result;
		}
		final StringBuffer temp = new StringBuffer(baseName);
		temp.append('_');
		temp.append(language);
		if (languageLength > 0) {
			result.addElement(temp.toString());
		}

		if (countryLength + variantLength == 0) {
			return result;
		}
		temp.append('_');
		temp.append(country);
		if (countryLength > 0) {
			result.addElement(temp.toString());
		}

		if (variantLength == 0) {
			return result;
		}
		temp.append('_');
		temp.append(variant);
		result.addElement(temp.toString());

		return result;
	}

	/** reference JDK1.4 source */
	private static Object findBundleInCache(ClassLoader loader,
			String baseName, String bundleName, Locale locale, Locale defaultLocale) {
		//Synchronize access to cacheList, cacheKey, and underConstruction
		synchronized (cacheList) {

			// 清空缓存的方法
			cleanCacheProcess(baseName, locale, defaultLocale, loader);

			cacheKey.setKeyValues(loader, bundleName, defaultLocale);
			Object result = cacheList.get(cacheKey);
			cacheKey.clear();
			return result;
		}
	}

	/**
	 * 查看是否需要检查文件被更新了，如果需要检查，则调用 cleanAllModifiedBundleCache <br>
	 * 去清空缓存。
	 * @param baseName
	 * @param locale
	 * @param defaultLocale
	 * @param loader
	 * @return
	 */
	private static boolean cleanCacheProcess(String baseName,
			Locale locale, Locale defaultLocale, ClassLoader loader) {

		Long lastRefreshTime = (Long) baseNameIntervalCache.get(baseName);
 		long now = System.currentTimeMillis();
		if(lastRefreshTime == null) {
			lastRefreshTime = new Long(now);
			// 保存最新的刷新时间
			baseNameIntervalCache.put(baseName, lastRefreshTime);
		}
		// 如果当前时间 - 最后刷新时间 > 间隔时间，那么就检查文件是否被修改了。
		if(now - lastRefreshTime.longValue() - refreshInterval > 0) {
			// 保存最新的刷新时间
			baseNameIntervalCache.put(baseName, new Long(now));

			cleanAllModifiedBundleCache(baseName, locale, defaultLocale, loader);
		}
		return false;
	}

	/**
	 * 清空缓存里的一个数据。
	 * @param loader
	 * @param baseName
	 * @param locale
	 */
	private static void removeCacheObject(final ClassLoader loader, String baseName, Locale locale) {
		cacheKey.setKeyValues(loader, baseName, locale);
		cacheList.remove(cacheKey);
		cacheKey.clear();
	}

	/**
	 * 根据basename 得到全部的可能的 .properties 文件，然后察看是否被修改了，<br>
	 * 如果修改，则清空缓存。
	 * @param baseName
	 * @param defaultLocale
	 * @return
	 */
	private static void cleanAllModifiedBundleCache(String baseName, Locale locale, Locale defaultLocale,
														final ClassLoader loader) {

		if(isFileModified(loader, baseName)) {

			removeCacheObject(loader, baseName, defaultLocale);
		}

		Vector names = calculateBundleNames(baseName, locale);
		
		for(int i = 0, size = names.size(); i < size; i++) {

			String name = (String) names.get(i);

			if(isFileModified(loader, name)) {

				removeCacheObject(loader, name, defaultLocale);
			}
		}

		names = calculateBundleNames(baseName, defaultLocale);
		
		for(int i = 0, size = names.size(); i < size; i++) {

			String name = (String) names.get(i);
			if(isFileModified(loader, name)) {

				removeCacheObject(loader, name, Locale.getDefault());
			}
		}

	}

	/** reference JDK1.4 source */
	private static void putBundleInCache(ClassLoader loader, String bundleName,
			Locale defaultLocale, Object value) {
		//we use a static shared cacheKey but we use the lock in cacheList since
		//the key is only used to interact with cacheList.
		synchronized (cacheList) {
			cacheKey.setKeyValues(loader, bundleName, defaultLocale);
			cacheList.put(cacheKey.clone(), value);
			underConstruction.remove(cacheKey);
			cacheKey.clear();

			baseNameIntervalCache.put(bundleName, new Long(System.currentTimeMillis()));

			//notify waiters that we're done constructing the bundle
			cacheList.notifyAll();
		}
	}

	/** reference JDK1.4 source */
	private static Object loadBundle(final ClassLoader loader,
			String bundleName, Locale defaultLocale) {
		// Search for class file using class loader
		try {
			Class bundleClass;
			if (loader != null) {
				bundleClass = loader.loadClass(bundleName);
			} else {
				bundleClass = Class.forName(bundleName);
			}
			if (ResourceBundle.class.isAssignableFrom(bundleClass)) {
				Object myBundle = bundleClass.newInstance();
				// Creating the instance may have triggered a recursive call to getBundle,
				// in which case the bundle created by the recursive call would be in the
				// cache now (4300693). For consistency, we'd then return the bundle from the cache.
				Object otherBundle = findBundleInCache(loader, null, bundleName,
						defaultLocale, defaultLocale);
				if (otherBundle != null) {
					return otherBundle;
				} else {
					return new ReloadResourceBundleProxy(
							(ResourceBundle) myBundle);
				}
			}
		} catch (Exception e) {
		} catch (LinkageError e) {
		}

		// Next search for a Properties file.
		final String resName = bundleName.replace('.', '/') + ".properties";
		InputStream stream = (InputStream) java.security.AccessController
				.doPrivileged(new java.security.PrivilegedAction() {
					public Object run() {
						if (loader != null) {
							return loader.getResourceAsStream(resName);
						} else {
							return ClassLoader
									.getSystemResourceAsStream(resName);
						}
					}
				});

		if (stream != null) {
			// make sure it is buffered
			stream = new java.io.BufferedInputStream(stream);
			try {
				return new ReloadResourceBundleProxy(
						new PropertyResourceBundle(stream));
			} catch (Exception e) {
			} finally {
				try {
					stream.close();
				} catch (Exception e) {
					// to avoid propagating an IOException back into the caller
					// (I'm assuming this is never going to happen, and if it does,
					// I'm obeying the precedent of swallowing exceptions set by the
					// existing code above)
				}
			}
		}
		return null;
	}

	/**
	 * 除非在单元测试中，否则不建议从外部直接调用这个方法。
	 *
	 */
	static void _clearCache() {

		synchronized (cacheList) {
			cacheList.clear();
			baseNameIntervalCache.clear();
			baseNameModifyCache.clear();
		}
	}

	/** reference JDK1.4 source */
	protected Object handleGetObject(String key) {
		
		Object obj = null;
		try {
			obj = realBundle.getObject(key);
		} catch (MissingResourceException e) {
			
		}
		if(obj == null) {
			if(parent != null) {
				obj = parent.getObject(key);
			}
		}
		
		return obj;
	}

	/**
	 * Returns an enumeration of the keys.
	 *
	 */
	public Enumeration getKeys() {

		Enumeration keys = realBundle.getKeys();
		if(keys == null) {
			if(parent != null) {
				keys = parent.getKeys();
			}
		}
		return keys;
	}

	public static long getRefreshInterval() {
		return refreshInterval;
	}

	public static void setRefreshInterval(long refreshInterval) {
		ReloadResourceBundleProxy.refreshInterval = refreshInterval;
	}

}
