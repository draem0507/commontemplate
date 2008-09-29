package org.commontemplate.core;

/**
 * 变量决策者
 *
 * @author liangfei0201@163.com
 *
 */
public interface VariableResolver {

	/**
	 * 查找变量的值.
	 * <p/>
	 * 在LocalContext中：若在当前上下文中未定义, 则递归向上级上下文查找, 直到根上下文也未定义, 抛出异常.
	 *
	 * @param name
	 *            变量名
	 * @return 变量的值
	 * @throws InvaildVariableNameException
	 *             当传入非法变量名时抛出
	 * @throws UndefinedException
	 *             当变量未定义时抛出
	 */
	public abstract Object getVariable(String name) throws InvaildVariableNameException, UndefinedException;

}
