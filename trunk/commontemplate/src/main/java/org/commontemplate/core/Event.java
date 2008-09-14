package org.commontemplate.core;

/**
 * 事件信息
 *
 * @see org.commontemplate.core.EventListener#onEvent(Event)
 * @see org.commontemplate.core.EventPublisher#publishEvent(Event)
 * @author liangfei0201@163.com
 *
 */
public abstract class Event extends java.util.EventObject {

	private Context context;

	private long timestamp;

	/**
	 * 事件信息
	 *
	 * @param source
	 *            事件的发起者
	 */
	public Event(Object source) {
		super(source);
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * 设置上下文
	 *
	 * @param context
	 *            上下文
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 事件发布的上下文
	 *
	 * @return 上下文
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * 获取事件发布时的时间戳
	 *
	 * @return 时间戳
	 */
	public long getTimestamp() {
		return timestamp;
	}

}
