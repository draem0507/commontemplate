package org.commontemplate.core;

/**
 * 全局上下文 <p/> (线程安全) <p/> LocalContext的lookupVariable和lookupObject，
 * 在搜索到根LocalContext均未找到值时，会继续向上搜索GlobalContext.
 * 
 * @see org.commontemplate.core.ContextFactory#getGlobalContext()
 * @see org.commontemplate.core.Context#getGlobalContext()
 * @author liangfei0201@163.com
 * 
 */
public abstract class GlobalContext extends BaseContext {

	private static final long serialVersionUID = 1L;
	
}