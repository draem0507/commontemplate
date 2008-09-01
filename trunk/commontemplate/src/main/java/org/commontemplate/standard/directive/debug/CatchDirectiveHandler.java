package org.commontemplate.standard.directive.debug;

import java.util.List;
import java.util.Map.Entry;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.MiddleBlockDirectiveHandlerSupport;
import org.commontemplate.util.Assert;
import org.commontemplate.util.ClassUtils;
import org.commontemplate.util.I18nExceptionFactory;

public class CatchDirectiveHandler extends MiddleBlockDirectiveHandlerSupport {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_EXCEPTION_NAME = "exception";

	public void doRender(Context context, String directiveName, Object param, List innerElements) throws Exception {
		LocalContext superLocalContext = context.getParentLocalContext();
		Assert.assertNotNull(superLocalContext.getStatus(TryDirectiveHandler.TRY_STATUS), "CatchDirectiveHandler.location.error");
		Object exception = superLocalContext.getStatus(TryDirectiveHandler.TRY_STATUS);
		if (exception != null) {
			String var = null;
			Class cls;
			if (param == null) {
				cls = null;
			} else if (param instanceof Entry) {
				Entry entry = (Entry)param;
				var = (String)entry.getKey();
				cls = getExceptionClass(entry.getValue());
			} else {
				cls = getExceptionClass(param);
			}
			if (cls == null
					|| cls.isAssignableFrom(exception.getClass())) {
				superLocalContext.setStatus(TryDirectiveHandler.TRY_STATUS, null);
				context.putVariable((var == null ? DEFAULT_EXCEPTION_NAME : var), exception);
				DirectiveUtils.renderAll(innerElements, context);
			}
		}
	}

	private Class getExceptionClass(Object param) throws ClassNotFoundException {
		if (param instanceof String) {
			return ClassUtils.forName((String)param);
		} else if (param instanceof Class) {
			return (Class)param;
		} else {
			throw I18nExceptionFactory.createIllegalArgumentException("CatchDirectiveHandler.parameter.error");
		}
	}

}

