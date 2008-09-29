package org.commontemplate.core;

import java.util.Map;

/**
 * 变量存储器.
 *
 * @author liangfei0201@163.com
 *
 */
public interface VariableStorage extends VariableResolver {

	/**
	 * 在当前上下文设置局部变量.
	 *
	 * @param name
	 *            变量名
	 * @param value
	 *            初始化值
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public void putVariable(String name, Object value)
			throws InvaildVariableNameException;

	/**
	 * 在当前上下文设置局部变量空值
	 *
	 * @param name
	 *            变量名
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public void putNullVariable(String name) throws InvaildVariableNameException;

	/**
	 * 在当前上下文设置只读局部变量.
	 *
	 * @param name
	 *            变量名
	 * @param value
	 *            初始化值
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public void putReadonlyVariable(String name, Object value)
			throws InvaildVariableNameException;

	/**
	 * 批量设置变量.
	 *
	 * @param variables
	 *            变量集
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public void putAllVariables(Map variables) throws InvaildVariableNameException;

	/**
	 * 判断变量是否包含在当前上下文中
	 *
	 * @param name
	 *            变量名
	 * @return 是否包含
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public boolean isVariableContained(String name) throws InvaildVariableNameException;

	/**
	 * 获取当前上下文所有变量
	 *
	 * @return 当前上下文所有变量, 此Map是只读的.
	 */
	public Map getVariables();

	/**
	 * 加入变量别名
	 *
	 * @param alias 别名
	 * @param name 变量名
	 * @throws InvaildVariableNameException 当传入非法变量名时抛出
	 */
	public void addVariableAlias(String alias, String name) throws InvaildVariableNameException;

	/**
	 * 移除变量别名
	 *
	 * @param alias 别名
	 * @throws InvaildVariableNameException 当传入非法变量名时抛出
	 */
	public void removeVariableAlias(String alias) throws InvaildVariableNameException;

	/**
	 * 检测变量容器是否锁定(只读).
	 */
	public boolean isVariablesReadonly();

	/**
	 * 锁定变量容器，即：使容器变量只读
	 * @param readonly 是否只读
	 */
	public void setVariablesReadonly(boolean readonly);

	/**
	 * 移除当前上下文中的局部变量
	 *
	 * @param name
	 *            变量名
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public void removeVariable(String name) throws InvaildVariableNameException;

	/**
	 * 清空当前上下文中所有局部变量定义, 并解除只读锁.
	 */
	public void clearVariables();

	/**
	 * 给已定义变量赋值. 若在当前上下文中未定义, 则递归向上级上下文查找, 直到全局上下文.
	 *
	 * @param name
	 *            变量名
	 * @param value
	 *            所赋值
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名或变量只读时抛出
	 */
	public void setVariable(String name, Object value)
			throws InvaildVariableNameException;

	/**
	 * 判断变量在所有上下文定义是否存在, 递归向上级容器查找.
	 *
	 * @param name
	 *            变量名
	 * @return 是否存在
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public boolean isVariableDefined(String name) throws InvaildVariableNameException;

	/**
	 * 获取所有上下文中存在的变量
	 *
	 * @return 所有上下文中存在的变量, 此Map是只读的.
	 */
	public Map getDefinedVariables();

	/**
	 * 移除存在的变量, 递归向上级容器移除.
	 *
	 * @param name
	 *            变量名
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 */
	public void removeDefinedVariable(String name) throws InvaildVariableNameException;

	/**
	 * 清空所有上下文的存在的变量, 递归向上级容器清空.
	 */
	public void clearDefinedVariables();

}
