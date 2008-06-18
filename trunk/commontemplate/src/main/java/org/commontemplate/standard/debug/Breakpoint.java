package org.commontemplate.standard.debug;

import java.io.Serializable;

/**
 * 断点信息 (不变类, 线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public final class Breakpoint implements Serializable, Comparable {

	private static final long serialVersionUID = 1L;

	private final String templateName;

	private final int line;

	public Breakpoint(String templateName, int line) {
		this.templateName = templateName;
		this.line = line;
	}

	/**
	 * 获取断点所在模板名称
	 *
	 * @return 模板名称
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 获取断点行
	 *
	 * @return 断点行
	 */
	public int getLine() {
		return line;
	}

	public int hashCode() {
		return templateName.hashCode() + 31 * line;
	}

	public boolean equals(Object o) {
		if (o instanceof Breakpoint) {
			Breakpoint b = (Breakpoint) o;
			return b.templateName.equals(templateName) && b.line == line;
		}
		return false;
	}

	public int compareTo(Object o) {
		Breakpoint b = (Breakpoint) o;
		int r = templateName.compareTo(b.templateName);
		return r == 0 ? line - b.line : r;
	}

	public String toString() {
		return templateName + ":" + line;
	}
}
