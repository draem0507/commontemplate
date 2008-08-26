package org.commontemplate.standard.context;

import org.commontemplate.config.ContextInitializer;
import org.commontemplate.core.Context;
import org.commontemplate.standard.directive.extend.ExtendDirectiveHandler;

/**
 * 自动继承模板
 *
 * @author liangfei0201@163.com
 *
 */
public class AutoExtendInitializer implements ContextInitializer {

	private String autoExtends;

	public void setAutoExtends(String autoExtends) {
		this.autoExtends = autoExtends;
	}

	public void initialize(Context context) {
		// TODO 未实现
		if (autoExtends != null) {
			context.putProperty(ExtendDirectiveHandler.EXTEND_INNER_PROPERTY, Boolean.TRUE);
		}
	}

}
