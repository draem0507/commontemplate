package org.commontemplate.config;

import java.io.IOException;

import org.commontemplate.core.Resource;

/**
 * Resource新旧比较器
 * 
 * @author liangfei0201@163.com
 * 
 */
public interface ResourceComparator {

	/**
	 * 比较两个Resource的新旧 <p/> 主要用于热加载时比较，返回true的数表示需重新加载，否则不重新加载。
	 * 
	 * @param oldSource
	 *            被比较的模板源
	 * @param newSource
	 *            比较的模板源2
	 * @return 是否已修改
	 * @throws IOException
	 *             读取Resource信息出错时抛出
	 */
	public boolean isModified(Resource oldSource, Resource newSource)
			throws IOException;

}
