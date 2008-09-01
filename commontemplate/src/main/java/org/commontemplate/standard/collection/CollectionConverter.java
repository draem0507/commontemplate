package org.commontemplate.standard.collection;

import java.util.Collection;

/**
 * 集合转换器.
 * 用于向$for指令提供迭代数据.
 *
 * @see org.commontemplate.standard.directive.iteration.ForeachDirectiveHandler
 * @author liangfei0201@163.com
 *
 */
public interface CollectionConverter {

	/**
	 * 将对象转换成集合.<br>
	 * 集合实现类至少要实现iterator()和size()方法.<br>
	 * 集合可继承<code>org.commontemplate.util.CollectionSupport</code>进行实现.<br>
	 *
	 * @see org.commontemplate.util.CollectionSupport
	 * @param obj 对象
	 * @return 集合
	 */
	Collection convert(Object obj);

}
