package org.commontemplate.engine;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.StatusStorage;
import org.commontemplate.util.TypeUtils;

/**
 * 全局状态存储器实现
 *
 * @author liangfei0201@163.com
 *
 */
final class GlobalStatusStorageImpl implements StatusStorage {

	private Map statusContainer = new HashMap();

	GlobalStatusStorageImpl() {

	}

	public synchronized void setStatus(String index, Object value) {
		statusContainer.put(index, value);
	}

	public synchronized Object getStatus(String index) {
		return statusContainer.get(index);
	}

	public synchronized void removeStatus(String index) {
		statusContainer.remove(index);
	}

	public Map getStatuses() {
		return statusContainer;
	}

	public synchronized void clearStatuses() {
		statusContainer.clear();
	}

	public boolean getBooleanStatus(String name) {
		return TypeUtils.isTrue(getStatus(name));
	}

	public void setBooleanStatus(String name, boolean value) {
		setStatus(name, Boolean.valueOf(value));
	}

}
