package org.commontemplate.standard.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.config.Cache;
import org.commontemplate.config.CacheException;
import org.commontemplate.util.Assert;

/**
 * 多策略缓存，软引用加最近最少使用原则缓存，相当于SoftCache与LruCache的组合
 *
 * 声明: 此类引自<a href="http://www.freemarker.org">FreeMarker</a>的freemarker.cache.MruCacheStorage
 *
 */
public class MruCache extends Cache {
	private final MruEntry strongHead = new MruEntry();
	private final MruEntry softHead = new MruEntry();
	{
		softHead.linkAfter(strongHead);
	}
	private final Map map = new HashMap();
	private final ReferenceQueue refQueue = new ReferenceQueue();
	private int maxStrongSize;
	private int maxSoftSize;
	private int strongSize = 0;
	private int softSize = 0;

	/**
	 * 同时设置强缓存及弱缓存
	 *
	 * @param maxSize
	 *            缓存大小
	 */
	public void setMaxSize(int maxSize) {
		setMaxStrongSize(maxSize);
		setMaxSoftSize(maxSize);
	}

	/**
	 * 设置最大强缓存
	 *
	 * @param maxStrongSize
	 *            the maximum number of strongly referenced templates
	 */
	public void setMaxStrongSize(int maxStrongSize) {
		Assert.assertTrue(maxStrongSize >= 0, "MruCache.max.strong.size");
		this.maxStrongSize = maxStrongSize;
	}

	/**
	 * 设置最大弱缓存
	 *
	 * @param maxSoftSize
	 *            the maximum number of softly referenced templates
	 */
	public void setMaxSoftSize(int maxSoftSize) {
		Assert.assertTrue(maxStrongSize >= 0, "MruCache.max.soft.size");
		this.maxSoftSize = maxSoftSize;
	}

	public Object get(Object key) throws CacheException {
		removeClearedReferences();
		MruEntry entry = (MruEntry) map.get(key);
		if (entry == null) {
			return null;
		}
		relinkEntryAfterStrongHead(entry, null);
		Object value = entry.getValue();
		if (value instanceof MruReference) {
			// This can only happen with maxStrongSize == 0
			return ((MruReference) value).get();
		}
		return value;
	}

	public void put(Object key, Object value) throws CacheException {
		removeClearedReferences();
		MruEntry entry = (MruEntry) map.get(key);
		if (entry == null) {
			entry = new MruEntry(key, value);
			map.put(key, entry);
			linkAfterStrongHead(entry);
		} else {
			relinkEntryAfterStrongHead(entry, value);
		}

	}

	public void remove(Object key) throws CacheException {
		removeClearedReferences();
		removeInternal(key);
	}

	private void removeInternal(Object key) {
		MruEntry entry = (MruEntry) map.remove(key);
		if (entry != null) {
			unlinkEntryAndInspectIfSoft(entry);
		}
	}

	public void clear() throws CacheException {
		strongHead.makeHead();
		softHead.linkAfter(strongHead);
		map.clear();
		strongSize = softSize = 0;
		// Quick refQueue processing
		while (refQueue.poll() != null)
			;
	}

	private void relinkEntryAfterStrongHead(MruEntry entry, Object newValue) {
		if (unlinkEntryAndInspectIfSoft(entry) && newValue == null) {
			// Turn soft reference into strong reference, unless is was cleared
			MruReference mref = (MruReference) entry.getValue();
			Object strongValue = mref.get();
			if (strongValue != null) {
				entry.setValue(strongValue);
				linkAfterStrongHead(entry);
			} else {
				map.remove(mref.getKey());
			}
		} else {
			if (newValue != null) {
				entry.setValue(newValue);
			}
			linkAfterStrongHead(entry);
		}
	}

	private void linkAfterStrongHead(MruEntry entry) {
		entry.linkAfter(strongHead);
		if (strongSize == maxStrongSize) {
			// softHead.previous is LRU strong entry
			MruEntry lruStrong = softHead.getPrevious();
			// Attila: This is equaivalent to maxStrongSize != 0
			// DD: But entry.linkAfter(strongHead) was just excuted above, so
			// lruStrong != strongHead is true even if maxStrongSize == 0.
			if (lruStrong != strongHead) {
				lruStrong.unlink();
				if (maxSoftSize > 0) {
					lruStrong.linkAfter(softHead);
					lruStrong.setValue(new MruReference(lruStrong, refQueue));
					if (softSize == maxSoftSize) {
						// List is circular, so strongHead.previous is LRU soft
						// entry
						MruEntry lruSoft = strongHead.getPrevious();
						lruSoft.unlink();
						map.remove(lruSoft.getKey());
					} else {
						++softSize;
					}
				} else {
					map.remove(lruStrong.getKey());
				}
			}
		} else {
			++strongSize;
		}
	}

	private boolean unlinkEntryAndInspectIfSoft(MruEntry entry) {
		entry.unlink();
		if (entry.getValue() instanceof MruReference) {
			--softSize;
			return true;
		} else {
			--strongSize;
			return false;
		}
	}

	private void removeClearedReferences() {
		for (;;) {
			MruReference ref = (MruReference) refQueue.poll();
			if (ref == null) {
				break;
			}
			removeInternal(ref.getKey());
		}
	}

	private static final class MruEntry {
		private MruEntry prev;
		private MruEntry next;
		private final Object key;
		private Object value;

		/**
		 * Used solely to construct the head element
		 */
		MruEntry() {
			makeHead();
			key = value = null;
		}

		MruEntry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		Object getKey() {
			return key;
		}

		Object getValue() {
			return value;
		}

		void setValue(Object value) {
			this.value = value;
		}

		MruEntry getPrevious() {
			return prev;
		}

		void linkAfter(MruEntry entry) {
			next = entry.next;
			entry.next = this;
			prev = entry;
			next.prev = this;
		}

		void unlink() {
			next.prev = prev;
			prev.next = next;
			prev = null;
			next = null;
		}

		void makeHead() {
			prev = next = this;
		}
	}

	private static final class MruReference extends SoftReference {
		private final Object key;

		MruReference(MruEntry entry, ReferenceQueue queue) {
			super(entry.getValue(), queue);
			this.key = entry.getKey();
		}

		Object getKey() {
			return key;
		}
	}

}