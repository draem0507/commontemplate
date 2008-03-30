package org.commontemplate.util;

import java.io.Serializable;

/**
 * 资源条目, 任意资源的封装类
 *
 * @author liangfei0201@163.com
 *
 */
public final class ResourceEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object resource = null;

	public final Object get() {
		return resource;
	}

	public final void set(Object resource) {
		this.resource = resource;
	}

}