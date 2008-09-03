package org.commontemplate.standard.i18n;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Vector;

import sun.misc.SoftCache;
/**
 * 处理可重载的国际化资源的标准实现。实现了 ReloadResourceProvider 接口。<br>
 * 使用者可以根据需要实现自定义的可重载的实现，具体标准请参考 ReloadResourceProvider 接口。
 * @author YanRong
 * @see ReloadableResourceProvider
 */
public class StandardReloadableResourceProvider implements ReloadableResourceProvider {

	/** initial size of the bundle cache */
    private static final int INITIAL_CACHE_SIZE = 25;

    /** capacity of cache consumed before it should grow */
    private static final float CACHE_LOAD_FACTOR = (float)1.0;

    private static final int MAX_BUNDLES_SEARCHED = 3;

    private static final Integer DEFAULT_NOT_FOUND = new Integer(-1);

    private static final ResourceCacheKey cacheKey = new ResourceCacheKey();

    private static SoftCache cacheList = new SoftCache(INITIAL_CACHE_SIZE, CACHE_LOAD_FACTOR);

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

    // private ClassLoader resourceClassLoader;

    /** 可以处理的文件的名字，默认是 .properties 文件 */
    private static String DEFAULT_FILE_EXT_NAME = ".properties";
    
    public static final String MAP_FILE_EXT_NAME_KEY = "MAP_FILE_EXT_NAME_KEY";
    public static final String MAP_CLASS_LOADER_KEY = "MAP_CLASS_LOADER_KEY";
    public static final String MAP_RELOADABLE_RESOURCE_KEY = "MAP_RELOADABLE_RESOURCE_KEY";
    public static final String MAP_ENCODING_KEY = "MAP_ENCODING_KEY";    

    /**
	 * 刷新间隔的时间
	 */
	private long refreshInterval;
	
	
	/**
	 * 根据 resourceBaseName,locale,key,extInfo 得到一个对象。<br>
	 * 关于 extInfo, 可以进行如下的设置: <br>
	 * <b>设置 要处理的文件的扩展名</b><br>
	 * extInfo.put(StandardReloadableResourceProvider.MAP_FILE_EXT_NAME_KEY, ".xml");<br>
	 * 如果不设置的话，则使用 .properties<br>
	 * <b>设置 ClassLoader</b><br>
	 * extInfo.put(StandardReloadableResourceProvider.MAP_FILE_EXT_NAME_KEY, xxx.getClassLoader());<br>
	 * 如果不设置的话，则使用 Thread.currentThread().getContextClassLoader();<br>
	 * <b>设置 处理资源的实现类</b><br>
	 * extInfo.put(StandardReloadableResourceProvider.MAP_FILE_EXT_NAME_KEY, ReloadablePropertyResource.class);<br>
	 * 如果不设置的话，则使用 ReloadablePropertyResource.class<br>
	 * <b>设置 处理文件的字符集编码</b><br>
	 * extInfo.put(StandardReloadableResourceProvider.MAP_FILE_EXT_NAME_KEY, "UTF-8");<br>
	 * 如果不设置的话，则使用 Java 默认的 Unicode<br>
	 */
	public Object getObject(String resourceBaseName, Locale locale, String key, Map extInfo) {
		
		ClassLoader resourceClassLoader = getClassLoader(extInfo);

		if(resourceClassLoader == null || locale == null || resourceBaseName == null) {
			throw new NullPointerException();
		}		
		
		ResourceHolder resourceHolder = getResource(resourceBaseName, locale, extInfo);
		ReloadableResource resource = resourceHolder.getCurrent();

		Object value = resource.handleGetObject(key);
		if(value == null) {
			if(resourceHolder.getParent() != null) {
				resource = resourceHolder.getParent().getCurrent();
				value = resource.handleGetObject(key);
			}
		}
		if(value == null) {
			throwMissingResourceException(key, locale);
		}
		return value;
	}

	private ResourceHolder getResource(String baseName, Locale resourceLocale, Map extInfo) {
		
		ClassLoader resourceClassLoader = getClassLoader(extInfo);
		final Object NOTFOUND = (resourceClassLoader != null) ? (Object) resourceClassLoader
				: (Object) DEFAULT_NOT_FOUND;

		String bundleName = baseName;
		String localeSuffix = resourceLocale.toString();
		if (localeSuffix.length() > 0) {
			bundleName += "_" + localeSuffix;
		} else if (resourceLocale.getVariant().length() > 0) {
			//This corrects some strange behavior in Locale where
			//new Locale("", "", "VARIANT").toString == ""
			bundleName += "___" + resourceLocale.getVariant();
		}

		Locale defaultLocale = Locale.getDefault();

		Object lookup = findResourceInCache(baseName, bundleName, defaultLocale, resourceLocale, extInfo);
		if (lookup == NOTFOUND) {
			throwMissingResourceException(baseName, resourceLocale);
		} else if (lookup != null) {
			return (ResourceHolder) lookup;
		}

		Object parent = NOTFOUND;

		try {
			//locate the root bundle and work toward the desired child
			Object root = findResource(baseName, baseName, defaultLocale, resourceLocale, NOTFOUND, NOTFOUND, extInfo);
			if (root == null) {
				root = NOTFOUND;
			}

			// Search the main branch of the search tree.
			// We need to keep references to the bundles we find on the main path
			// so they don't get garbage collected before we get to propagate().
			final Vector names = calculateBundleNames(baseName, resourceLocale);
			// if we found the root bundle and no other bundle names are needed
			// we can stop here. We don't need to search or load anything further.
			boolean foundInMainBranch = (root != NOTFOUND && names.size() == 0);

			if (!foundInMainBranch) {
				parent = root;
				for (int i = 0; i < names.size(); i++) {
					bundleName = (String) names.elementAt(i);
					lookup = findResource(bundleName,
							baseName, defaultLocale, resourceLocale, parent, NOTFOUND, extInfo);
					if (lookup != null) {
						parent = lookup;
						foundInMainBranch = true;
					}
				}
			}

			if (!foundInMainBranch) {
				parent = root;
				//we didn't find anything on the main branch, so we do the fallback branch
				final Vector fallbackNames = calculateBundleNames(baseName,
						defaultLocale);
				for (int i = 0; i < fallbackNames.size(); i++) {
					bundleName = (String) fallbackNames.elementAt(i);
					if (names.contains(bundleName)) {
						continue;
					}
					lookup = findResource(bundleName,
							baseName, defaultLocale, resourceLocale, parent, NOTFOUND, extInfo);
					if (lookup != null) {
						parent = lookup;
					}
				}
			}

		} catch (Exception e) {

			throwMissingResourceException(baseName, resourceLocale);
		} catch (Error e) {

			throw e;
		}
		if (parent == NOTFOUND) {
			throwMissingResourceException(baseName, resourceLocale);
		}
		return (ResourceHolder) parent;
	}

	private Object findResource(String bundleName, String baseName, Locale defaultLocale, Locale resourceLocale,
			Object parent, final Object NOTFOUND, Map extInfo) {

		ResourceHolder result;
		result = findResourceInCache(baseName, bundleName, defaultLocale, resourceLocale, extInfo);
		if(result != null) {
			return result;
		}

		synchronized (cacheList) {
			//try loading the bundle via the class loader
			ReloadableResource resource = loadResource(bundleName, extInfo);
			if (resource != null) {
				result = new ResourceHolder(resource);
				if (parent != NOTFOUND) {
					result.setParent((ResourceHolder) parent);
				}
				// bundle.setLocale(baseName, bundleName);
				putResourceInCache(bundleName, result, extInfo);

			} else {
				putResourceInCache(bundleName, parent, extInfo);
			}
		}
		return result;
	}

	private ReloadableResource loadResource(String bundleName, Map extInfo) {
		// Search for class file using class loader
		final ClassLoader resourceClassLoader = getClassLoader(extInfo);
		try {
			Class resourceClass;
			if (resourceClassLoader != null) {
				resourceClass = resourceClassLoader.loadClass(bundleName);
			} else {
				resourceClass = Class.forName(bundleName);
			}
			if (ReloadableResource.class.isAssignableFrom(resourceClass)) {
				Object classResource = resourceClass.newInstance();

				return (ReloadableResource) classResource;
			}
		} catch (Exception e) {
		} catch (LinkageError e) {
		}

		// Next search for a Properties file.
		final String resName = bundleName.replace('.', '/') + getFileExtName(extInfo);
		URL url= (URL) java.security.AccessController
				.doPrivileged(new java.security.PrivilegedAction() {
					public Object run() {
						if (resourceClassLoader != null) {
							return resourceClassLoader.getResource(resName);
						} else {
							return ClassLoader.getSystemResource(resName);
						}
					}
				});

		if (url != null) {
			// make sure it is buffered
			try {
				Class clazz = getReloadableResource(extInfo);
				ReloadableResource resource = (ReloadableResource)clazz.newInstance();
				resource.loadFromURL(url, getEncoding(extInfo));
				return resource;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				url = null;
			}
		}
		return null;
	}

	private ResourceHolder findResourceInCache(String baseName, String bundleName, 
							Locale defaultLocale, Locale resourceLocale, Map extInfo) {

		synchronized (cacheList) {
			// 清空缓存的方法
			cleanCacheProcess(baseName, defaultLocale, resourceLocale, extInfo);

			cacheKey.setKeyValues(getClassLoader(extInfo), bundleName);
			Object result = cacheList.get(cacheKey);
			cacheKey.clear();
			return (ResourceHolder) result;
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
	private void cleanCacheProcess(String baseName, Locale defaultLocale, Locale resourceLocale, Map extInfo) {

		Long lastRefreshTime = (Long) baseNameIntervalCache.get(baseName);
 		long now = System.currentTimeMillis();

		// 如果当前时间 - 最后刷新时间 > 间隔时间，那么就检查文件是否被修改了。
		if(lastRefreshTime == null
				|| now - lastRefreshTime.longValue() - refreshInterval > 0) {
			// 保存最新的刷新时间
			baseNameIntervalCache.put(baseName, new Long(now));

			cleanAllModifiedBundleCache(baseName, defaultLocale, resourceLocale, getClassLoader(extInfo), extInfo);
		}
	}

	/**
	 * 根据basename 得到全部的可能的文件，然后察看是否被修改了，<br>
	 * 如果修改，则清空缓存。
	 * @param baseName
	 * @param defaultLocale
	 * @return
	 */
	private void cleanAllModifiedBundleCache(String baseName, Locale defaultLocale,
														Locale resourceLocale,
														final ClassLoader loader, Map extInfo) {

		boolean rootChange = false;
		if(isFileModified(loader, baseName, extInfo)) {

			rootChange = true;
			removeCacheObject(loader, baseName);
		}

		Vector names = calculateBundleNames(baseName, resourceLocale);
		boolean parentNameChange = false;

		for(int i = 0, size = names.size(); i < size; i++) {

			String name = (String) names.get(i);

			if(rootChange || parentNameChange || isFileModified(loader, name, extInfo)) {

				parentNameChange = true;
				removeCacheObject(loader, name);
			}
		}

		names = calculateBundleNames(baseName, defaultLocale);
		parentNameChange = false;

		for(int i = 0, size = names.size(); i < size; i++) {

			String name = (String) names.get(i);
			if(rootChange || parentNameChange || isFileModified(loader, name, extInfo)) {

				removeCacheObject(loader, name);
			}
		}

	}

	/**
	 * 清空缓存里的一个数据。
	 * @param loader
	 * @param baseName
	 * @param locale
	 */
	private void removeCacheObject(final ClassLoader loader, String baseName) {
		cacheKey.setKeyValues(loader, baseName);
		cacheList.remove(cacheKey);
		cacheKey.clear();
	}

	/**
	 * 判断 properties 文件是否被修改了。
	 * @param loader
	 * @param bundleName
	 * @return
	 */
	private boolean isFileModified(final ClassLoader loader, String bundleName, Map extInfo) {
		try {

			final String resName = bundleName.replace('.', '/') + getFileExtName(extInfo);
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

	private void putResourceInCache(String bundleName, Object value, Map extInfo) {
		//we use a static shared cacheKey but we use the lock in cacheList since
		//the key is only used to interact with cacheList.
		synchronized (cacheList) {
			cacheKey.setKeyValues(getClassLoader(extInfo), bundleName);
			cacheList.put(cacheKey.clone(), value);
			cacheKey.clear();

			baseNameIntervalCache.put(bundleName, new Long(System.currentTimeMillis()));
		}
	}

	private Vector calculateBundleNames(String baseName, Locale locale) {
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

	/**
	 * 除非在单元测试中，否则不建议从外部直接调用这个方法。
	 *
	 */
	public void clearCache() {

		synchronized (cacheList) {
			cacheList.clear();
			baseNameIntervalCache.clear();
			baseNameModifyCache.clear();
		}
	}

	private void throwMissingResourceException(String baseName, Locale resourceLocale) throws MissingResourceException {
		throw new MissingResourceException("Can't find bundle for base name "
				+ baseName + ", locale " + resourceLocale, baseName + "_" + resourceLocale, "");
	}

	private static final class ResourceCacheKey implements Cloneable {
        private SoftReference loaderRef;
        private String searchName;
        private int hashCodeCache;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            try {
                final ResourceCacheKey otherEntry = (ResourceCacheKey)other;
                //quick check to see if they are not equal
                if (hashCodeCache != otherEntry.hashCodeCache) {
                    return false;
                }
                //are the names the same?
                if (!searchName.equals(otherEntry.searchName)) {
                    return false;
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

        public void setKeyValues(ClassLoader loader, String searchName) {
            this.searchName = searchName;
            hashCodeCache = searchName.hashCode();

            if (loader == null) {
                this.loaderRef = null;
            } else {
                loaderRef = new SoftReference(loader);
                hashCodeCache ^= loader.hashCode();
            }
        }

        public void clear() {
            setKeyValues(null, "");
        }
    }

	private final class ResourceHolder
	{
		private ReloadableResource current;
		private ResourceHolder parent;

		public ResourceHolder(ReloadableResource resource) {
			current = resource;
		}

		public ResourceHolder getParent() {
			return parent;
		}

		public void setParent(ResourceHolder parent) {
			this.parent = parent;
		}

		public ReloadableResource getCurrent() {
			return current;
		}

		public void setCurrent(ReloadableResource current) {
			this.current = current;
		}

	}
	
	private ClassLoader getClassLoader(Map extInfo) {
		ClassLoader loader = (ClassLoader) extInfo.get(MAP_CLASS_LOADER_KEY);
		if(loader == null) {
			// 如果没有设置 resourceClassLoader，那么就使用当前线程的 ClassLoader，
			// 以确保能够正确地载入资源。
			loader = Thread.currentThread().getContextClassLoader();
			extInfo.put(MAP_CLASS_LOADER_KEY, loader);
		}
		return loader;
	}
	
	private String getFileExtName(Map extInfo) {
		String value = (String) extInfo.get(MAP_FILE_EXT_NAME_KEY);
		if(value == null) {
			value = DEFAULT_FILE_EXT_NAME;
			extInfo.put(MAP_FILE_EXT_NAME_KEY, value);
		}
		return value;
	}
	
	private Class getReloadableResource(Map extInfo) {
		Class resource = (Class) extInfo.get(MAP_RELOADABLE_RESOURCE_KEY);
		if(resource == null) {
			resource = ReloadablePropertyResource.class;
			extInfo.put(MAP_RELOADABLE_RESOURCE_KEY, resource);
		}
		return resource;
	}
	
	private String getEncoding(Map extInfo) {
		return (String) extInfo.get(MAP_ENCODING_KEY);
	}
	
	
	public long getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(long refreshInterval) {
		this.refreshInterval = refreshInterval;
	}


}
