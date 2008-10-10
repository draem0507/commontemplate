package org.commontemplate.config;

/**
 * 中间嵌套指令处理器, 如: elseif, else等. (实现类应保证线程安全，及友好的序列化)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class MiddleBlockDirectiveHandler extends BlockDirectiveHandler {

	private static final long serialVersionUID = 1L;
	
}
