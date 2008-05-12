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
			throw new VariableException("变量名不能为空!", null);

		if (! TypeUtils.isNamed(name))
			throw new VariableException(name + " 不符合命名规范!", name);

		if (keywords.isKeyword(name))
			throw new VariableException("变量名不能为关键字: " + name, name);
	}

	protected void assertAllVariableNames(Map model) throws VariableException {
		if (model != null && model.size() > 0) {
			for (Iterator iterator = model.keySet().iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if (! (obj instanceof String))
					throw new VariableException("变量名必需为String类型!", String.valueOf(obj));
				String name = (String)obj;
				assertVariableName(name);
			}
		}
	}

}
