package org.commontemplate.core;

import java.util.Map;

/**
 * 变量存储器
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface VariableStorage extends VariableResolver {

	/**
	 * 判断变量是否已在当前上下文定义
	 * 
	 * @param name
	 *            变量名
	 * @return 是否已定义
	 * @throws VariableException
	 *             当传入非法变量名时抛出
	 */
	public boolean isDefinedVariable(String name) throws VariableException;

	/**
	 * 在当前上下文定义局部变量
	 * 
	 * @param name
	 *            变量名
	 * @throws DefinedException
	 *             当变量已定义时抛出
	 * @throws VariableException
	 *             当传入非法变量名时抛出
	 */
	public void defineVariable(String name) throws DefinedException,
			VariableException;

	/**
	 * 在当前上下文定义局部变量, 并初始化值.
	 * 
	 * @param name
	 *            变量名
	 * @param value
	 *            初始化值
	 * @throws DefinedException
	 *             当变量已定义时抛出
	 * @throws VariableException
	 *             当传入非法变量名时抛出
	 */
	public void defineVariable(String name, Object value)
			throws DefinedException, VariableException;

	/**
	 * 在当前上下文定义只读局部变量, 并初始化值.
	 * 
	 * @param name
	 *            变量名
	 * @param value
	 *            初始化值
	 * @throws DefinedException
	 *             当变量已定义时抛出
	 * @throws VariableException
	 *             当传入非法变量名时抛出
	 */
	public void defineReadonlyVariable(String name, Object value)
			throws DefinedException, VariableException;

	/**
	 * 批量定义变量集
	 * 
	 * @param variables
	 *            变量集
	 * @throws DefinedException
	 *             当变量已定义时抛出
	 * @throws VariableException
	 *             当传入非法变量名时抛出
	 */
	public void defineAllVariables(Map variables) throws DefinedException,
			VariableException;
	
	/**
	 * 定义变量别名
	 * 
	 * @param alias 别名
	 * @param name 变量名
	 * 
	 * @throws VariableException 当传入非法变量名时抛出
	 */
	public void defineVariableAlias(String alias, String name) throws VariableException;
	
	/**
	 * 移除变量别名
	 * 
	 * @param alias 别名
	 * @throws VariableException 当传入非法变量名时抛出
	 */
	public void removeVariableAlias(String alias) throws VariableException;

	/**
	 * 给已定义变量赋值. 若在当前上下文中未定义, 则递归向上级上下文查找, 直到根上下文也未定义, 抛出异常.
	 * 
	 * @param name
	 *            变量名
	 * @param value
	 *            所赋值
	 * @throws UndefinedException
	 *             当变量未定义时抛出
	 * @throws VariableException
	 *             当传入非法变量名或变量只读时抛出
	 */
	public void assignVariable(String name, Object value)
			throws UndefinedException, VariableException;

	/**
	 * 获取当前区域所有已定义变量
	 * 
	 * @return 所有已定义变量, 此Map是只读的
	 */
	public Map getDefinedVariables();

	/**
	 * 锁定变量容器，即：使容器变量只读
	 */
	public void lockVariables();

	/**
	 * 解锁变量容器，即：使容器变量可写
	 */
	public void unlockVariables();

	/**
	 * 清除当前上下文中的局部变量
	 * 
	 * @param name
	 *            变量名
	 * @throws UndefinedException
	 *             当变量未定义时抛出
	 * @throws VariableException
	 *             当传入非法变量名时抛出
	 */
	public void removeVariable(String name) throws UndefinedException,
			VariableException;

	/**
	 * 清空当前上下文中所有局部变量定义
	 */
	public void clearVariables();

}
