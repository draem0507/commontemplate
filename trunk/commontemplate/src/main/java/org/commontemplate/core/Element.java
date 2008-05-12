package org.commontemplate.core;

import java.io.Serializable;

import org.commontemplate.util.Location;

/**
 * 模板组成元素 <p/> (线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public abstract class Element implements Node, Serializable {

	/**
	 * 获取模板元素在模板中的位置
	 *
	 * @return 元素在模板中的位置
	 */
	public abstract Location getLocation();

	/**
	 * 获取指令签名信息, 只包含"$xxx{yyy}", 不包含内部块信息等.
	 *
	 * @return 指令签名信息
	 */
	public abstract String getSignature();

	/**
	 * 返回模板元素的标准组成, 同getCanonicalForm()
	 *
	 * @return 模板元素的标准组成
	 */
	public String toString() {
		return getCanonicalForm();
	}

}
