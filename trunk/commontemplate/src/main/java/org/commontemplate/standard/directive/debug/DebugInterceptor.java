package org.commontemplate.standard.directive.debug;

import org.commontemplate.config.RenderingInterceptor;
import org.commontemplate.config.Rendition;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;

public class DebugInterceptor implements RenderingInterceptor, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String STEP_STATUS = "____STEP_STATUS____";

	public static final String STEP_OVER_KEY = "____STEP_OVER____";

	public void intercept(Rendition rendition) {
		Context context = rendition.getContext();
		if (context.isDebug()
			&& context.getRootLocalContext().getBooleanStatus(STEP_STATUS)
			&& context.getProperty(STEP_OVER_KEY) != Boolean.TRUE) {
			try {
				context.getOut().flush();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			DebugFrame lock = DebugFrame.getDebugFrame(context);
			Element element= rendition.getElement();
			lock.initDebugFrame(element);
			synchronized (lock) {
				while (lock.getStatus() == DebugFrame.STEPPING) { // 守护
					try {
						lock.wait(); // 中断当前线程, 进入调试界面
					} catch (Exception e) {
						// e.printStackTrace();
					}
				}
				if (lock.getStatus() == DebugFrame.STEP_OVER) {
					if (element instanceof BlockDirective) {
						context.pushLocalContext();
						try {
							context.putProperty(STEP_OVER_KEY, Boolean.TRUE);
							rendition.doRender();
							return;
						} finally {
							context.popLocalContext();
						}
					}
				} else if (lock.getStatus() == DebugFrame.STEP_RETURN) {
					context.putProperty(STEP_OVER_KEY, Boolean.TRUE);
				} else if (lock.getStatus() == DebugFrame.RESUME) {
					context.getRootLocalContext().setBooleanStatus(STEP_STATUS, false);
				} else if (lock.getStatus() == DebugFrame.TERMINATE) {
					throw new StopException();
				}
				rendition.doRender();
			}
		} else {
			rendition.doRender();
		}
	}

}
