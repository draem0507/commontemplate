package org.commontemplate.standard.listener;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.commontemplate.core.Event;
import org.commontemplate.core.EventListener;

public class EventListenerChain implements EventListener, Serializable {

	private static final long serialVersionUID = 1L;

	private List eventListeners;

	public void setEventListeners(List eventListeners) {
		this.eventListeners = eventListeners;
	}

	private List asynchronousEventListeners;

	public void setAsynchronousEventListeners(
			List asynchronousEventListeners) {
		this.asynchronousEventListeners = asynchronousEventListeners;
	}

	public void onEvent(final Event event) {
		// 异步监听器
		if (asynchronousEventListeners != null && asynchronousEventListeners.size() > 0) {
			for (Iterator iterator = asynchronousEventListeners.iterator(); iterator
					.hasNext();) {
				final EventListener eventListener = (EventListener) iterator
						.next();
				if (eventListener != null) {
					new Thread() { // FIXME 异步处理不完善，应该放入一个队列中
						public void run() {
							eventListener.onEvent(event);
						}
					}.start();
				}
			}
		}

		// 同步监听器
		if (eventListeners != null && eventListeners.size() > 0) {
			for (Iterator iterator = eventListeners.iterator(); iterator
					.hasNext();) {
				EventListener eventListener = (EventListener) iterator
						.next();
				if (eventListener != null)
					eventListener.onEvent(event);
			}
		}
	}

}