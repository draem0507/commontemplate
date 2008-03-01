package org.commontemplate.core.event;

import org.commontemplate.core.LocalContext;

/**
 * 上下文栈压栈事件
 * 
 * @see org.commontemplate.core.LocalContextStack#pushLocalContext()
 * @author liangfei0201@163.com
 *
 */
public class LocalContextPushedEvent extends LocalContextStackEvent {

	private static final long serialVersionUID = 1L;

	public LocalContextPushedEvent(Object source, LocalContext previous, LocalContext current) {
		super(source, previous, current);
	}

}