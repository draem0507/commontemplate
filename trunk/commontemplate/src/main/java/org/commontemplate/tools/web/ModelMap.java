package org.commontemplate.tools.web;

import java.util.Enumeration;
import java.util.Vector;

import org.commontemplate.util.ClassUtils;
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
		if (model == null) 
			throw new java.lang.NullPointerException("model == null!");
		
		this.model = model;
	}
	
	private Vector vet = null;
	
	protected Enumeration getNames() {
		if (vet == null) { // 非线程安全
			try {
				Vector v = new Vector();
				v.addAll(ClassUtils.getObjectAllPropertyNames(model));
				vet = v;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return vet.elements();
	}

	protected Object getValue(Object key) {
		try {
			return ClassUtils.getObjectProperty(model, (String)key);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
