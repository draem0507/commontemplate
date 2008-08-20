package org.commontemplate.standard.debug;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import org.commontemplate.config.RenderingInterceptor;
import org.commontemplate.config.Rendition;
import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.Location;

/**
 * 调试拦截器
 *
 * @author linagfei0201@163.com
 *
 */
public class DebugInterceptor implements RenderingInterceptor, Serializable {

	private static final long serialVersionUID = 1L;

	private static final String BREAKPOINT_OVER_STATUS = "____BREAKPOINT_OVER_STATUS____";

	private static final String STEP_STATUS = "____STEP_STATUS____";

	private static final String STEP_OVER_KEY = "____STEP_OVER____";

	public void setDebugListener(DebugListener debugListener) {
		DebugManager.getInstance().addDebugListener(debugListener);
	}

	public void intercept(Rendition rendition) {
		// 获取调试器
		final DebugManager debugManager = DebugManager.getInstance();
		// 获取当前上下文
		final Context context = rendition.getContext();
		final Element element = rendition.getElement();
		final Template template = element.getTemplate();
		// 判断是否需要单步运行
		if (! debugManager.isDebugListenerEmpty() // 前置条件：调试器存在
				&& context.isDebug() // 前置条件：配置中开启了调试模式
					&& ((context.getRootLocalContext().getBooleanStatus(STEP_STATUS) // 运行在调试状态中
							&& context.getProperty(STEP_OVER_KEY) != Boolean.TRUE) // 非跳跃状态
					|| (! context.getRootLocalContext().getBooleanStatus(STEP_STATUS) // 运行在非调试状态中
							&& ! context.getRootLocalContext().getBooleanStatus(BREAKPOINT_OVER_STATUS)
							&& isBreakpoint(template.getName(), element)))) { // 当前模板元素在断点位置
			// 构造执行过程封装体
			final ExecutionImpl execution = new ExecutionImpl(rendition, Thread.currentThread().getName());
			// 挂起
			synchronized (execution) {
				// 添加挂起过程
				debugManager.addSuspendedExecution(execution);
				// 守护挂起状态
				while (execution.isSuspended()) {
					try {
						// 挂起当前线程, 等待调试界面notify通知.
						execution.wait();
					} catch (InterruptedException e) {
						 // 忽略意外中断, 恢复挂起状态
					}
				}
				// 移除挂起过程
				debugManager.removeSuspendedExecution(execution);
				// 设置运行状态
				if (execution.getStatus() == ExecutionImpl.STEP_OVER) {
					context.getRootLocalContext().setBooleanStatus(STEP_STATUS, true);
					if (element instanceof BlockDirective) {
						context.pushLocalContext();
						try {
							context.putProperty(STEP_OVER_KEY, Boolean.TRUE);
							rendition.doRender(); // 注：doRender必需在wait恢复之后运行
							return;
						} finally {
							context.popLocalContext();
						}
					}
				} else if (execution.getStatus() == ExecutionImpl.STEP_RETURN) {
					context.getRootLocalContext().setBooleanStatus(STEP_STATUS, true);
					context.putProperty(STEP_OVER_KEY, Boolean.TRUE);
				} else if (execution.getStatus() == ExecutionImpl.RESUME) {
					context.getRootLocalContext().setBooleanStatus(STEP_STATUS, false);
				} else if (execution.getStatus() == ExecutionImpl.RESUME_ALL) {
					context.getRootLocalContext().setBooleanStatus(STEP_STATUS, false);
					context.getRootLocalContext().setBooleanStatus(BREAKPOINT_OVER_STATUS, true);
				} else if (execution.getStatus() == ExecutionImpl.TERMINATE) {
					throw new TerminateException();
				} else {
					context.getRootLocalContext().setBooleanStatus(STEP_STATUS, true);
				}
				rendition.doRender(); // 注：doRender必需在wait恢复之后运行
			}
		} else {
			// 正常运行
			rendition.doRender();
		}
	}

	// 判断模板位置是否为处于断点
	private boolean isBreakpoint(String templateName, Element element) {
		Location location = element.getLocation();
		String src = null;
		try {
			src = element.getSource();
		} catch (IOException e) {
			// ignore
		}
		final DebugManager debugManager = DebugManager.getInstance();
		final Collection breakpoints = debugManager.getBreakpoints();
		for (Iterator iterator = breakpoints.iterator(); iterator.hasNext();) {
			Breakpoint breakpoint = (Breakpoint) iterator.next();
			if (templateName.equals(breakpoint.getTemplateName())) {
				int line = breakpoint.getLine();
				int beginLine = location.getBegin().getLine();
				int endLine = location.getEnd().getLine();
				if (endLine > beginLine && src != null
						&& ! "\n".equals(src) && ! "\r\n".equals(src)
						&& (src.startsWith("\n") || src.startsWith("\r\n")))
					beginLine ++;
				if (endLine > beginLine
						&& location.getEnd().getColumn() == 0)
					endLine --;
				if (beginLine <= line && endLine >= line)
					return true;
			}
		}
		return false;
	}

}
