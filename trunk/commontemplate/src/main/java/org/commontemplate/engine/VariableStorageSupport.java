package org.commontemplate.engine;

import java.util.Iterator;
import java.util.Map;

import org.commontemplate.config.Keywords;
import org.commontemplate.core.DefinedException;
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
	
	public VariableStorageSupport(Keywords keywords) {
		Assert.assertNotNull(keywords);
		this.keywords = keywords;
	}
	
	// 断言 -----

	protected void assertVariables(Map model) throws VariableException {
		if (model != null && model.size() > 0) {
			for (Iterator iterator = model.keySet().iterator(); iterator.hasNext();) {
				String name = (String)iterator.next();
				assertVariableName(name);
				if (isDefinedVariable(name)) 
					throw new DefinedException(name + " 已经定义!", name);
			}
		}
	}

	protected void assertVariableName(String var) throws VariableException {
		if (var == null) 
			throw new VariableException("变量名不能为空!", null);
		
		if (! TypeUtils.isNamed(var)) 
			throw new VariableException(var + " 不符合命名规范!", var);
		
		if (keywords.isKeyword(var)) 
			throw new VariableException("变量名不能为关键字: " + var, var);
	}

}
