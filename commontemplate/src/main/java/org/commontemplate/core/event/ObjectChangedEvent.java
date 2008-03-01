package org.commontemplate.core.event;

/**
 * 对象仓库中的对象值改变事件
 * 
 * @see org.commontemplate.core.ObjectStorage
 * @author liangfei0201@163.com
 *
 */
public class ObjectChangedEvent extends ValueChangedEvent {

	private static final long serialVersionUID = 1L;
	
	public ObjectChangedEvent(Object source, String type, String name, Object oldValue,
			Object newValue) {
		super(source, name, oldValue, newValue);
		this.type = type;
	}

	private final String type;

	public String getType() {
		return type;
	}

}
