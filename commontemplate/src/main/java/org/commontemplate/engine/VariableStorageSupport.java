package org.commontemplate.engine;

import java.util.Iterator;
import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.VariableException;
import org.commontemplate.core.VariableStorage;
import org.commontemplate.util.Assert;
import org.commontemplate.util.TypeUtils;

/**
 * 变量存储实现基类
 *
 * @author liangfei0201@163.com
 *
 */
abstract class VariableStorageSupport implements VariableStorage {

	protected final Keywords keywords;

	VariableStorageSupport(Keywords keywords) {
		Assert.assertNotNull(keywords);
		this.keywords = keywords;
	}

	// 断言 -----

	protected void assertVariableName(String name) throws VariableException {
		if (name == null)
			throw new VariableException(null, "VariableStorageSupport.variable.name.required");

		if (! TypeUtils.isNamed(name))
			throw new VariableException(name, "VariableStorageSupport.invaild.variable.name");

		if (keywords.isKeyword(name))
			throw new VariableException(name, "VariableStorageSupport.variable.name.is.keyword");
	}

	protected void assertAllVariableNames(Map model) throws VariableException {
		if (model != null && model.size() > 0) {
			for (Iterator iterator = model.keySet().iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if (! (obj instanceof String))
					throw new VariableException(String.valueOf(obj), "VariableStorageSupport.variable.type.error");
				String name = (String)obj;
				assertVariableName(name);
			}
		}
	}

}
