package org.commontemplate.engine;

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
final class EventPublisherImpl implements EventPublisher {
	
	private final EventListener eventListener;

	private final Context context;

	EventPublisherImpl(EventListener eventListener, Context context) {
		this.eventListener = eventListener;
		this.context = context;
	}

	public void publishEvent(Event event) {
		event.setContext(context);
		if (eventListener != null)
			eventListener.onEvent(event);
	}

}
