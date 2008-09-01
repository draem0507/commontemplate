package org.commontemplate.config;

import java.io.IOException;

import org.commontemplate.core.Resource;
import org.commontemplate.core.Template;

/**
 * Resource新旧比较器
 *
 * @author liangfei0201@163.com
 *
 */
public interface ResourceComparator {

	/**
	 * 比较Template和Resource的新旧
	 * <p/>
	 * 主要用于热加载时比较，返回true的数表示需重新加载，否则不重新加载。
	 *
	 * @param template
	 *            已加载的模板
	 * @param reource
	 *            待比较的模板源
	 * @return 是否已修改
	 * @throws IOException
	 *             读取Resource信息出错时抛出
	 */
	public boolean isModified(Template template, Resource reource)
			throws IOException;

}
