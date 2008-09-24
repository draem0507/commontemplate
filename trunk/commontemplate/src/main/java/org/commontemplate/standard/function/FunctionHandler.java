package org.commontemplate.standard.function;

/**
 * 扩展函数接口
 *
 * @author liangfei0201@163.com
 *
 */
public interface FunctionHandler {

	public Object doFunction(Object bean, Object argument) throws Exception;

}
