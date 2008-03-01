package org.commontemplate.config;

/**
 * 特殊的一元操作符
 * 
 * @author liangfei0201@163.com
 * 
 */
public abstract class SpecialUnaryOperatorHandler implements
		UnaryOperatorHandler {

	/**
	 * 一元操作符操作数延迟求值 <p/> doEvaluate的operand参数将会以LazyOperand的实例传入<br/>
	 * 可以用((LazyOperand)operand).evaluate()取真实值<br/>
	 * 
	 * @see org.commontemplate.config.LazyOperand
	 * @return 是否延迟求值
	 * 
	 */
	public boolean isOperandLazy() {
		return false;
	}

	/**
	 * 一元操作符操作数变量字面化 <p/> 当操作数为无引号字符串时，将不作为变量取值，而作为String传入
	 * 
	 * @return 是否字面化
	 * 
	 */
	public boolean isOperandNamed() {
		return false;
	}

	/**
	 * 一元操作符操作数函数字面化 <p/>
	 * 当操作数为函数时，将不作为函数运行，而作为org.commontemplate.util.Function传入
	 * 
	 * @return 函数是否字面化
	 */
	public boolean isOperandFunctioned() {
		return false;
	}

}
