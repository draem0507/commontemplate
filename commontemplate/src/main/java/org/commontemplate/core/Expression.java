package org.commontemplate.core;

import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 表达式 (线程安全)
 * 
 * @author liangfei0201@163.com
 * 
 */
public abstract class Expression implements Visitable, Serializable {

	/**
	 * 表达式求值
	 * 
	 * @param context
	 *            上下文
	 * @return 求值结果
	 * @throws EvaluationException
	 *             所有异常均封装成此异常抛出
	 */
	public abstract Object evaluate(VariableResolver context) throws EvaluationException;

	/**
	 * 获取表达式符的名称
	 * 
	 * @return 表达式符的名称
	 */
	public abstract String getName();

	/**
	 * 获取表达式符在表达式中的位置
	 * 
	 * @return 表达式符在表达式中的位置
	 */
	public abstract Location getLocation();
	
	/**
	 * 获取表达式符的标准组成
	 * 
	 * @return 表达式符的标准组成
	 */
	public abstract String getCanonicalForm();

	/**
	 * 返回表达式符的标准组成, 同getCanonicalForm()
	 * 
	 * @return 表达式符的标准组成
	 */
	public String toString() {
		return getCanonicalForm();
	}

}
