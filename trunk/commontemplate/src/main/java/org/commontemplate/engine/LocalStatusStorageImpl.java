package org.commontemplate.engine;

import java.util.HashMap;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.StatusStorage;
import org.commontemplate.core.event.StatusChangedEvent;

/**
 * 局部状态存储器实现
 * 
 * @author liangfei0201@163.com
 *
 */
final class LocalStatusStorageImpl implements StatusStorage {
	
	private final Context context;
	
	LocalStatusStorageImpl(Context context) {
		this.context = context;
	}
	
	private final Map statusContainer = new HashMap();

	public Object getStatus(String index) {
		return statusContainer.get(index);
	}

	public void setStatus(String index, Object value) {
		Object old = statusContainer.get(index);
		statusContainer.put(index, value);
		statusChanged(index, old, value);
	}

	public void removeStatus(String index) {
		Object old = statusContainer.get(index);
		statusContainer.remove(index);
		statusChanged(index, old, null);
	}
	
	private void statusChanged(String name, Object oldValue, Object newValue) {
		if ((newValue == null && oldValue != null)
				|| (newValue != null && ! newValue.equals(oldValue))) {
			context.publishEvent(new StatusChangedEvent(this, name, oldValue, newValue));
		}
	}

	public void clearStatus() {
		statusContainer.clear();
	}

}
