package org.commontemplate.core.event;

import org.commontemplate.core.Event;

/**
 * 值变化事件
 * 
 * @see org.commontemplate.core.Storage
 * @author liangfei0201@163.com
 *
 */
public abstract class ValueChangedEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	private String name;

	private Object newValue;

	private Object oldValue;

	public ValueChangedEvent(Object source, String name,
			Object oldValue, Object newValue) {
		super(source);
		this.name = name;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getName() {
		return name;
	}

	public Object getNewValue() {
		return newValue;
	}

	public Object getOldValue() {
		return oldValue;
	}

}
