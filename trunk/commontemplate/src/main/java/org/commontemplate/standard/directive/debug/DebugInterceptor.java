package org.commontemplate.standard.directive.debug;

import java.io.IOException;

import org.commontemplate.config.RenderInterceptor;
import org.commontemplate.config.Rendition;
import org.commontemplate.core.BlockDirective;

public class DebugInterceptor implements RenderInterceptor, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String STEP_STATUS = "____STEP_STATUS____";

	public static final String STEP_OVER_KEY = "____STEP_OVER____";

	public void intercept(Rendition rendition) {
		if (rendition.getContext().isDebug()
				&& rendition.getContext().getRootLocalContext().getBooleanStatus(STEP_STATUS)
				&& rendition.getContext().getProperty(STEP_OVER_KEY) != Boolean.TRUE) {
			try {
				rendition.getContext().getOut().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			final DebugLock lock = new DebugLock();
			DebugFrame.showDebugFrame(rendition.getContext(), rendition.getElement(), lock);
			synchronized (lock) {
				try {
					while (lock.getStatus() == DebugLock.STEPPING) // 守护
						lock.wait(30000); // 中断当前线程, 进入调试界面
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (lock.getStatus() == DebugLock.STEP_OVER) {
				if (rendition.getElement() instanceof BlockDirective) {
					rendition.getContext().pushLocalContext();
					try {
						rendition.getContext().putProperty(STEP_OVER_KEY, Boolean.TRUE);
						rendition.doRender();
					} finally {
						rendition.getContext().popLocalContext();
					}
					return;
				}
			} else if (lock.getStatus() == DebugLock.STEP_RETURN) {
				rendition.getContext().putProperty(STEP_OVER_KEY, Boolean.TRUE);
			} else if (lock.getStatus() == DebugLock.RESUME) {
				rendition.getContext().getRootLocalContext().setBooleanStatus(STEP_STATUS, false);
			} else if (lock.getStatus() == DebugLock.TERMINATE) {
				throw new StopException();
			}
		}
		rendition.doRender();
	}

}
