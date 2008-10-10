package org.commontemplate.core;

import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 表达式 (线程安全)
 *
 * @see org.commontemplate.core.Directive#getExpression()
 * @author liangfei0201@163.com
 *
 */
public abstract class Expression implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	 * 接收访问者, 并带领访问者遍历整个树 (前序遍历)
	 *
	 * @param visitor 访问者
	 */

	public void accept(ExpressionVisitor visitor) {
		accept(visitor, true);
	}

	/**
	 * 接收访问者, 并带领访问者遍历整个树 (前序遍历)<br>
	 * 通常直接使用accept(Visitor visitor)<br>
	 *
	 * @see org.commontemplate.core.Expression#accept(ExpressionVisitor)
	 * @param visitor 访问者
	 * @param isEnter 是否为入口, 在入口处忽略StopVisitException
	 */
	public abstract void accept(ExpressionVisitor visitor, boolean isEnter);

	/**
	 * 获取表达式符的名称
	 *
	 * @return 表达式符的名称
	 */
	public abstract String getName();

	/**
	 * 获取表达式符的标准组成
	 *
	 * @return 表达式符的标准组成
	 */
	public abstract String getSource();

	/**
	 * 获取表达式符在表达式中的位置
	 *
	 * @return 表达式符在表达式中的位置
	 */
	public abstract Location getLocation();

	/**
	 * 返回表达式符的标准组成, 同getCanonicalForm()
	 *
	 * @return 表达式符的标准组成
	 */
	public String toString() {
		return getSource();
	}

}
