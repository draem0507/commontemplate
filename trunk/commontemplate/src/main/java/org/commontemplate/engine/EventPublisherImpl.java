package org.commontemplate.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.commontemplate.core.Context;
import org.commontemplate.core.Event;
import org.commontemplate.core.EventListener;
import org.commontemplate.core.EventPublisher;

/**
 * 事件发布器
 *
 * @author liangfei0201@163.com
 *
 */
final class EventPublisherImpl implements EventPublisher, Serializable {

	private static final long serialVersionUID = 1L;

	private final EventListener eventListener;

	private final Context context;

	EventPublisherImpl(EventListener eventListener, Context context) {
		this.eventListener = eventListener;
		this.context = context;
	}

	public void publishEvent(Event event) {
		event.setContext(context);
		fireEvent(event, eventListener);
		for (int i = 0; i < eventListeners.size(); i ++) {
			EventListener listener;
			try {
				listener = (EventListener)eventListeners.get(i);
			} catch (IndexOutOfBoundsException e) { // 乐观并发
				break;
			}
			fireEvent(event, listener);
		}
	}

	private void fireEvent(Event event, EventListener listener) {
		try {
			if (listener != null)
				listener.onEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final List eventListeners = new ArrayList();

	public void addEventListener(EventListener listener) {
		eventListeners.add(listener);
	}

	public void clearEventListeners() {
		eventListeners.clear();
	}

	public void removeEventListener(EventListener listener) {
		eventListeners.remove(listener);
	}

}
