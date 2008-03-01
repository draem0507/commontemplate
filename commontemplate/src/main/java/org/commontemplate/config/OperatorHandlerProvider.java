package org.commontemplate.config;

/**
 * 操作符处理器供给者
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface OperatorHandlerProvider {

	/**
	 * 检测是否存在相应一元操作符处理器
	 * 
	 * @param name
	 *            一元操作符名称
	 * @return 是否存在相应一元操作符处理器
	 */
	public boolean hasUnaryOperatorHandler(String name);

	/**
	 * 获取相应名称的一元操作符处理器
	 * 
	 * @param name
	 *            一元操作符名称
	 * @return 一元操作符处理器
	 */
	public UnaryOperatorHandler getUnaryOperatorHandler(String name);

	/**
	 * 获取一元操作符的优先级
	 * 
	 * @param name
	 *            一元操作符名称
	 * @return 优先级
	 */
	public int getUnaryOperatorPriority(String name);

	/**
	 * 检测是否存在相应二元操作符处理器
	 * 
	 * @param name
	 *            二元操作符名称
	 * @return 是否存在相应二元操作符处理器
	 */
	public boolean hasBinaryOperatorHandler(String name);

	/**
	 * 获取相应名称的二元操作符处理器
	 * 
	 * @param name
	 *            二元操作符名称
	 * @return 二元操作符处理器
	 */
	public BinaryOperatorHandler getBinaryOperatorHandler(String name);

	/**
	 * 获取二元操作符的优先级
	 * 
	 * @param name
	 *            二元操作符名称
	 * @return 优先级
	 */
	public int getBinaryOperatorPriority(String name);

}