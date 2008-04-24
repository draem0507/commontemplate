package org.commontemplate.standard.directive.debug;

import java.io.IOException;

import org.commontemplate.config.ElementInterceptor;
import org.commontemplate.config.ElementRendition;
import org.commontemplate.core.BlockDirective;

public class DebugInterceptor implements ElementInterceptor {

	private static final String STEP_OVER_KEY = "____STEP_OVER____";

	public void intercept(ElementRendition rendition) {
		if (rendition.getContext().isDebugMode()
				&& rendition.getContext().isStep()
				&& rendition.getContext().lookupObject(STEP_OVER_KEY) != Boolean.TRUE) {
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
						rendition.getContext().putObject(STEP_OVER_KEY, Boolean.TRUE);
						rendition.doRender();
					} finally {
						rendition.getContext().popLocalContext();
					}
					return;
				}
			} else if (lock.getStatus() == DebugLock.STEP_RETURN) {
				rendition.getContext().putObject(STEP_OVER_KEY, Boolean.TRUE);
			} else if (lock.getStatus() == DebugLock.RESUME) {
				rendition.getContext().setStep(false);
			} else if (lock.getStatus() == DebugLock.TERMINATE) {
				throw new StopException();
			}
		}
		rendition.doRender();
	}

}
