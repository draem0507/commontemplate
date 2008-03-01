package org.commontemplate.config;

/**
 * 指令处理器决策者
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface DirectiveHandlerProvider {

	/**
	 * 通过指令名得到相应指令
	 * 
	 * @param name
	 *            指令名
	 * @return DirectiveHandler - 指令名所对应的指令
	 */
	public DirectiveHandler getDirectiveHandler(String name);

}
