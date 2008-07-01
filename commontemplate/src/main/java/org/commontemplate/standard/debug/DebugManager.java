package org.commontemplate.standard.debug;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 调试管理器 (单例模式)
 *
 * @author liangfei0201@163.com
 *
 */
public final class DebugManager {

	public static final DebugManager instance = new DebugManager();

	/**
	 * 获取DebugManager单例
	 *
	 * @return DebugManager单例
	 */
	public static DebugManager getInstance() {
		return instance;
	}

	// 单例私有构造子
	private DebugManager() {}

	// 断点集合
	private final Set breakpoints = new HashSet();

	/**
	 * 判断是否包含断点
	 *
	 * @param breakpoint 断点信息
	 * @return 是否包含
	 */
	public boolean hasBreakpoint(Breakpoint breakpoint) {
		synchronized (breakpoints) {
			return breakpoints.contains(breakpoint);
		}
	}

	/**
	 * 变换断点, 如果存在则去除, 不存在则加入.
	 *
	 * @param breakpoint 断点信息
	 */
	public void changeBreakpoint(Breakpoint breakpoint) {
		if (breakpoint == null)
			return;
		if (breakpoint.getTemplateName() == null)
			return;
		if (breakpoint.getLine() < 0)
			return;
		synchronized (breakpoints) {
			if (breakpoints.contains(breakpoint)) {
				breakpoints.remove(breakpoint);
				breakpointRemoved(breakpoint);
			} else {
				breakpoints.add(breakpoint);
				breakpointAdded(breakpoint);
			}
		}
	}

	/**
	 * 添加断点
	 *
	 * @param breakpoint 断点
	 */
	public void addBreakpoint(Breakpoint breakpoint) {
		if (breakpoint == null)
			return;
		if (breakpoint.getTemplateName() == null)
			return;
		if (breakpoint.getLine() < 0)
			return;
		synchronized (breakpoints) {
			if (! breakpoints.contains(breakpoint)) {
				breakpoints.add(breakpoint);
				breakpointAdded(breakpoint);
			}
		}
	}

	/**
	 * 移除断点
	 *
	 * @param breakpoint 断点
	 */
	public void removeBreakpoint(Breakpoint breakpoint) {
		if (breakpoint == null)
			return;
		if (breakpoint.getTemplateName() == null)
			return;
		if (breakpoint.getLine() < 0)
			return;
		synchronized (breakpoints) {
			if (breakpoints.contains(breakpoint)) {
				breakpoints.remove(breakpoint);
				breakpointRemoved(breakpoint);
			}
		}
	}

	/**
	 * 清除所有断点
	 */
	public void clearBreakpoints() {
		synchronized (breakpoints) {
			for (Iterator iterator = breakpoints.iterator(); iterator.hasNext();) {
				Breakpoint breakpoint = (Breakpoint) iterator.next();
				iterator.remove();
				breakpointRemoved(breakpoint);
			}
		}
	}

	/**
	 * 清除指定模板的所有断点
	 *
	 * @param templateName 模板名
	 */
	public void clearBreakpoints(String templateName) {
		if (templateName == null)
			return;
		synchronized (breakpoints) {
			for (Iterator iterator = breakpoints.iterator(); iterator.hasNext();) {
				Breakpoint breakpoint = (Breakpoint) iterator.next();
				if (templateName.equals(breakpoint.getTemplateName())) {
					iterator.remove();
					breakpointRemoved(breakpoint);
				}
			}
		}
	}

	/**
	 * 获取所有断点
	 *
	 * @return 所有断点
	 */
	public Collection getBreakpoints() {
		synchronized (breakpoints) {
			return breakpoints;
		}
	}

	/**
	 * 获取指定模板的所有断点
	 *
	 * @param templateName 模板名
	 * @return 模板的所有断点
	 */
	public Collection getBreakpoints(String templateName) {
		if (templateName == null)
			return Collections.EMPTY_SET;
		synchronized (breakpoints) {
			Set templateBreakpoints = new HashSet();
			for (Iterator iterator = breakpoints.iterator(); iterator.hasNext();) {
				Breakpoint breakpoint = (Breakpoint) iterator.next();
				if (templateName.equals(breakpoint.getTemplateName()))
					templateBreakpoints.add(breakpoint);
			}
			return templateBreakpoints;
		}
	}

	// 断点变化监听器集合
	private final Set breakpointListeners = Collections.synchronizedSet(new HashSet());

	/**
	 * 添加断点变化事件监听器
	 *
	 * @param listener 断点变化事件监听器
	 */
	public void addBreakpointListener(BreakpointListener listener) {
		if (listener != null)
			breakpointListeners.add(listener);
	}

	/**
	 * 移除断点变化事件监听器
	 *
	 * @param listener 断点变化事件监听器
	 */
	public void removeBreakpointListener(BreakpointListener listener) {
		if (listener != null)
			breakpointListeners.remove(listener);
	}

	/**
	 * 清除所有断点变化事件监听器
	 */
	public void clearBreakpointListener() {
		breakpointListeners.clear();
	}

	/**
	 * 判断断点变化事件监听器是否为空
	 *
	 * @return 是否为空
	 */
	public boolean isBreakpointListenerEmpty() {
		return breakpointListeners.isEmpty();
	}

	// 发布断点添加事件
	private void breakpointAdded(Breakpoint breakpoint) {
		for (Iterator iterator = breakpointListeners.iterator(); iterator.hasNext();) {
			BreakpointListener listener = (BreakpointListener)iterator.next();
			listener.onBreakpointAdded(new BreakpointEvent(this, breakpoint));
		}
	}

	// 发布断点移除事件
	private void breakpointRemoved(Breakpoint breakpoint) {
		for (Iterator iterator = breakpointListeners.iterator(); iterator.hasNext();) {
			BreakpointListener listener = (BreakpointListener)iterator.next();
			listener.onBreakpointRemoved(new BreakpointEvent(this, breakpoint));
		}
	}

	// 被挂起的运行过程集合
	private final Set suspendedExecutions = Collections.synchronizedSet(new HashSet());

	/**
	 * 获取所有已挂起的上下文
	 *
	 * @return 已挂起的上下文
	 */
	public Collection getSuspendedExecutions() {
		return Collections.unmodifiableSet(suspendedExecutions);
	}

	/**
	 * 添加挂起运行过程
	 *
	 * @param execution 运行过程
	 */
	public void addSuspendedExecution(Execution execution) {
		suspendedExecutions.add(execution);
		executionSuspended(execution);
	}

	/**
	 * 移除恢复运行过程
	 *
	 * @param execution 运行过程
	 */
	public void removeSuspendedExecution(Execution execution) {
		suspendedExecutions.remove(execution);
		executionExecuted((ExecutionImpl)execution);
	}

	// 发布挂起事件
	private void executionSuspended(Execution execution) {
		DebugEvent event = new DebugEvent(this, execution);
		for (Iterator iterator = debugListeners.iterator(); iterator.hasNext();) {
			DebugListener debugListener = (DebugListener)iterator.next();
			debugListener.onSuspended(event);
		}
	}

	// 发布恢复运行事件
	private void executionExecuted(ExecutionImpl execution) {
		DebugEvent event = new DebugEvent(this, execution);
		for (Iterator iterator = debugListeners.iterator(); iterator.hasNext();) {
			DebugListener debugListener = (DebugListener)iterator.next();
			int s = execution.getStatus();
			if (s == ExecutionImpl.STEP_INTO)
				debugListener.onStepIntoed(event);
			else if (s == ExecutionImpl.STEP_OVER)
				debugListener.onStepOvered(event);
			else if (s == ExecutionImpl.STEP_RETURN)
				debugListener.onStepReturned(event);
			else if (s == ExecutionImpl.RESUME)
				debugListener.onResumed(event);
			else if (s == ExecutionImpl.RESUME_ALL)
				debugListener.onResumeAlled(event);
			else if (s == ExecutionImpl.TERMINATE)
				debugListener.onTerminated(event);
		}
	}

	// 调试监听器集合
	private Set debugListeners = Collections.synchronizedSet(new HashSet());

	/**
	 * 添加调试挂起事件监听器
	 *
	 * @param listener 调试挂起事件监听器
	 */
	public void addDebugListener(DebugListener listener) {
		if (listener != null)
			debugListeners.add(listener);
	}

	/**
	 * 移除调试挂起事件监听器
	 *
	 * @param listener 调试挂起事件监听器
	 */
	public void removeDebugListener(DebugListener listener) {
		if (listener != null)
			debugListeners.remove(listener);
	}

	/**
	 * 清除所有调试挂起事件监听器
	 */
	public void clearDebugListener() {
		debugListeners.clear();
	}

	/**
	 * 判断调试挂起事件监听器是否为空
	 *
	 * @return 是否为空
	 */
	public boolean isDebugListenerEmpty() {
		return debugListeners.isEmpty();
	}
}
