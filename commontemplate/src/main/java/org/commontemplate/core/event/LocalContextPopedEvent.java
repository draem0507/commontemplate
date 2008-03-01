package org.commontemplate.core.event;

import org.commontemplate.core.LocalContext;

/**
 * 上下文栈弹栈事件
 * 
 * @see org.commontemplate.core.LocalContextStack#popLocalContext()
 * @author liangfei0201@163.com
 *
 */
public class LocalContextPopedEvent extends LocalContextStackEvent {

	private static final long serialVersionUID = 1L;

	public LocalContextPopedEvent(Object source, LocalContext previous, LocalContext current) {
		super(source, previous, current);
	}

}
