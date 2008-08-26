package org.commontemplate.standard.context;

import java.util.Iterator;
import java.util.List;

import org.commontemplate.config.ContextInitializer;
import org.commontemplate.core.Context;

/**
 * 自动嵌入模板
 *
 * @author liangfei0201@163.com
 *
 */
public class AutoEmbedInitializer implements ContextInitializer {

	// TODO 待考虑与自动include的顺序

	private List autoEmbeds;

	public void setAutoEmbeds(List autoEmbeds) {
		this.autoEmbeds = autoEmbeds;
	}

	public void initialize(Context context) {
		if (autoEmbeds != null && autoEmbeds.size() > 0) {
			for (Iterator iterator = autoEmbeds.iterator(); iterator.hasNext();) {
				String templateName = (String)iterator.next();
				if (templateName != null) {
					try {
						context.getTemplate(templateName).render(context);
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
