package org.commontemplate.standard.i18n;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Vector;

import sun.misc.SoftCache;
/**
 * 处理可重载的国际化资源的标准实现。实现了 ReloadResourceProvider 接口。<br>
 * 使用者可以根据需要实现自定义的可重载的实现，具体标准请参考 ReloadResourceProvider 接口。
 * @author YanRong
 * @see ReloadResourceProvider
 */
public class StandardReloadResourceProvider implements ReloadResourceProvider {

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

    private Locale resourceLocale = null;

    private ClassLoader resourceClassLoader;

    private String resourceBaseName;
    /** 可以处理的文件的名字，默认是 .properties 文件 */
    private String fileExtName = ".properties";
    /**
     * 处理资源文件的类的名字，默认是 ReloadablePropertyResource 类
     * @see ReloadProviderResource
     * @see ReloadablePropertyResource
     */
    private String resolveReloadResource = ReloadablePropertyResource.class.getName();

    /**
     * 指定编码的字符集。
     */
    private String encoding = null;

    /**
	 * 刷新间隔的时间
	 */
	private long refreshInterval;

    public StandardReloadResourceProvider(String baseName, Locale locale, ClassLoader loader) {
    	resourceBaseName = baseName;
    	resourceLocale = locale;
    	resourceClassLoader = loader;
    }

    public StandardReloadResourceProvider(String baseName, Locale locale) {
    	new StandardReloadResourceProvider(baseName, locale,
    			Thread.currentThread().getClass().getClassLoader());
    }

    public StandardReloadResourceProvider(String baseName) {
    	new StandardReloadResourceProvider(baseName, Locale.getDefault(),
    			Thread.currentThread().getClass().getClassLoader());
    }

    public StandardReloadResourceProvider() {}

    public String getString(String key) {
		return (String) getObject(key);
	}

	public Object getObject(String key) {

		if(resourceClassLoader == null) {
			// 如果没有设置 resourceClassLoader，那么就使用当前线程的 ClassLoader，
			// 以确保能够正确地载入资源。
			resourceClassLoader = Thread.currentThread().getContextClassLoader();
		}
		if(resourceClassLoader == null || resourceLocale == null || resourceBaseName == null) {
			throw new NullPointerException();
		}

		ResourceHolder resourceHolder = getResource(resourceBaseName);
		ReloadProviderResource resource = resourceHolder.getCurrent();

		Object value = resource.handleGetObject(key);
		if(value == null) {
			if(resourceHolder.getParent() != null) {
				resource = resourceHolder.getParent().getCurrent();
				value = resource.handleGetObject(key);
			}
		}
		if(value == null) {
			throwMissingResourceException(key);
		}
		return value;
	}

	private ResourceHolder getResource(String baseName) {

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

		Object lookup = findResourceInCache(baseName, bundleName, defaultLocale);
		if (lookup == NOTFOUND) {
			throwMissingResourceException(baseName);
		} else if (lookup != null) {
			return (ResourceHolder) lookup;
		}

		Object parent = NOTFOUND;

		try {
			//locate the root bundle and work toward the desired child
			Object root = findResource(baseName, baseName, defaultLocale, NOTFOUND, NOTFOUND);
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
							baseName, defaultLocale, parent, NOTFOUND);
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
							baseName, defaultLocale, parent, NOTFOUND);
					if (lookup != null) {
						parent = lookup;
					}
				}
			}

		} catch (Exception e) {

			throwMissingResourceException(baseName);
		} catch (Error e) {

			throw e;
		}
		if (parent == NOTFOUND) {
			throwMissingResourceException(baseName);
		}
		return (ResourceHolder) parent;
	}

	private Object findResource(String bundleName, String baseName, Locale defaultLocale,
			Object parent, final Object NOTFOUND) {

		ResourceHolder result;
		result = findResourceInCache(baseName, bundleName, defaultLocale);
		if(result != null) {
			return result;
		}

		synchronized (cacheList) {
			//try loading the bundle via the class loader
			ReloadProviderResource resource = loadResource(bundleName);
			if (resource != null) {
				result = new ResourceHolder(resource);
				if (parent != NOTFOUND) {
					result.setParent((ResourceHolder) parent);
				}
				// bundle.setLocale(baseName, bundleName);
				putResourceInCache(bundleName, result);

			} else {
				putResourceInCache(bundleName, parent);
			}
		}
		return result;
	}

	private ReloadProviderResource loadResource(String bundleName) {
		// Search for class file using class loader
		try {
			Class resourceClass;
			if (resourceClassLoader != null) {
				resourceClass = resourceClassLoader.loadClass(bundleName);
			} else {
				resourceClass = Class.forName(bundleName);
			}
			if (ReloadProviderResource.class.isAssignableFrom(resourceClass)) {
				Object classResource = resourceClass.newInstance();

				return (ReloadProviderResource) classResource;
			}
		} catch (Exception e) {
		} catch (LinkageError e) {
		}

		// Next search for a Properties file.
		final String resName = bundleName.replace('.', '/') + fileExtName;
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
				Class clazz = resourceClassLoader.loadClass(resolveReloadResource);
				ReloadablePropertyResource resource = (ReloadablePropertyResource) clazz.newInstance();
				resource.loadFromURL(url, encoding);
				return resource;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				url = null;
			}
		}
		return null;
	}

	private ResourceHolder findResourceInCache(String baseName, String bundleName, Locale defaultLocale) {

		synchronized (cacheList) {
			// 清空缓存的方法
			cleanCacheProcess(baseName, defaultLocale);

			cacheKey.setKeyValues(resourceClassLoader, bundleName);
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
	private void cleanCacheProcess(String baseName, Locale defaultLocale) {

		Long lastRefreshTime = (Long) baseNameIntervalCache.get(baseName);
 		long now = System.currentTimeMillis();

		// 如果当前时间 - 最后刷新时间 > 间隔时间，那么就检查文件是否被修改了。
		if(lastRefreshTime == null
				|| now - lastRefreshTime.longValue() - refreshInterval > 0) {
			// 保存最新的刷新时间
			baseNameIntervalCache.put(baseName, new Long(now));

			cleanAllModifiedBundleCache(baseName, defaultLocale, resourceClassLoader);
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
														final ClassLoader loader) {

		boolean rootChange = false;
		if(isFileModified(loader, baseName)) {

			rootChange = true;
			removeCacheObject(loader, baseName);
		}

		Vector names = calculateBundleNames(baseName, resourceLocale);
		boolean parentNameChange = false;

		for(int i = 0, size = names.size(); i < size; i++) {

			String name = (String) names.get(i);

			if(rootChange || parentNameChange || isFileModified(loader, name)) {

				parentNameChange = true;
				removeCacheObject(loader, name);
			}
		}

		names = calculateBundleNames(baseName, defaultLocale);
		parentNameChange = false;

		for(int i = 0, size = names.size(); i < size; i++) {

			String name = (String) names.get(i);
			if(rootChange || parentNameChange || isFileModified(loader, name)) {

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
	private boolean isFileModified(final ClassLoader loader, String bundleName) {
		try {

			final String resName = bundleName.replace('.', '/') + fileExtName;
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

	private void putResourceInCache(String bundleName, Object value) {
		//we use a static shared cacheKey but we use the lock in cacheList since
		//the key is only used to interact with cacheList.
		synchronized (cacheList) {
			cacheKey.setKeyValues(resourceClassLoader, bundleName);
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

	private void throwMissingResourceException(String baseName) throws MissingResourceException {
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
		private ReloadProviderResource current;
		private ResourceHolder parent;

		public ResourceHolder(ReloadProviderResource resource) {
			current = resource;
		}

		public ResourceHolder getParent() {
			return parent;
		}

		public void setParent(ResourceHolder parent) {
			this.parent = parent;
		}

		public ReloadProviderResource getCurrent() {
			return current;
		}

		public void setCurrent(ReloadProviderResource current) {
			this.current = current;
		}

	}

	public Locale getResourceLocale() {
		return resourceLocale;
	}

	public void setResourceLocale(Locale resourceLocale) {
		this.resourceLocale = resourceLocale;
	}

	public ClassLoader getResourceClassLoader() {
		return resourceClassLoader;
	}

	public void setResourceClassLoader(ClassLoader resourceClassLoader) {
		this.resourceClassLoader = resourceClassLoader;
	}

	public String getResourceBaseName() {
		return resourceBaseName;
	}

	public void setResourceBaseName(String resourceBaseName) {
		this.resourceBaseName = resourceBaseName;
	}

	public String getFileExtName() {
		return fileExtName;
	}

	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}

	public String getResolveReloadResource() {
		return resolveReloadResource;
	}

	public void setResolveReloadResource(String resolveReloadResource) {
		this.resolveReloadResource = resolveReloadResource;
	}

	public long getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(long refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
