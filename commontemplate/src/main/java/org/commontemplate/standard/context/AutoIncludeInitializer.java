package org.commontemplate.standard.context;

import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.ContextInitializer;
import org.commontemplate.core.Context;

/**
 * 自动包含模板输出
 *
 * @author liangfei0201@163.com
 *
 */
public class AutoIncludeInitializer implements ContextInitializer {

	// TODO 待考虑与自动embed的顺序

	private List autoIncludes;

	public void setAutoIncludes(List autoIncludes) {
		this.autoIncludes = autoIncludes;
	}

	public void initialize(Context context) {
		if (autoIncludes != null && autoIncludes.size() > 0) {
			for (Iterator iterator = autoIncludes.iterator(); iterator.hasNext();) {
				String templateName = (String)iterator.next();
				if (templateName != null) {
					try {
						Context newContext = context.createContext();
						try {
							context.getTemplate(templateName).render(newContext);
						} finally {
							newContext.clear();
						}
					} catch (RuntimeException e) {
						throw e;
					} catch (Exception e) {
						throw new RuntimeException(e.getMessage(), e);
					}
				}
			}
		}
	}

}
