package org.commontemplate.tools.web;

import java.util.Enumeration;
import java.util.Vector;

import org.commontemplate.util.Assert;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.MapSupport;

/**
 * Bean对象的属性Map封装，识别getter方法作为集合.
 * (非线程安全)
 *
 * @author liangfei0201@163.com
 *
 */
public class ModelMap extends MapSupport {

	private static final long serialVersionUID = 1L;

	private final Object model;

	public ModelMap(Object model) {
		Assert.assertNotNull(model, "ModelMap.model.required");
		this.model = model;
	}

	private Vector vet = null;

	protected Enumeration getNames() {
		if (vet == null) { // 非线程安全
			try {
				Vector v = new Vector();
				v.addAll(BeanUtils.getPropertyNames(model.getClass()));
				vet = v;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return vet.elements();
	}

	protected Object getValue(Object key) {
		try {
			return BeanUtils.getProperty(model, (String)key);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
