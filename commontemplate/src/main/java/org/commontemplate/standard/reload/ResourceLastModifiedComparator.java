package org.commontemplate.standard.reload;

import java.io.IOException;
import java.io.Serializable;

import org.commontemplate.config.ResourceComparator;
import org.commontemplate.core.Resource;

/**
 * 模板源最后修改时间比较器
 *
 * @author liangfei0201@163.com
 *
 */
public class ResourceLastModifiedComparator implements ResourceComparator, Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isModified(Resource oldSource, Resource newSource) throws IOException {
		return oldSource.getLastModified() - newSource.getLastModified() < 0;
	}

}