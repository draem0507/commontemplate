package org.commontemplate.core.event;

/**
 * 状态存储器中的状态值改变事件
 * 
 * @see org.commontemplate.core.StatusStorage
 * @author liangfei0201@163.com
 *
 */
public class StatusChangedEvent extends ValueChangedEvent {

	private static final long serialVersionUID = 1L;

	public StatusChangedEvent(Object source, String statusName,
			Object oldStatusValue, Object newStatusValue) {
		super(source, statusName, oldStatusValue, newStatusValue);
	}

}
