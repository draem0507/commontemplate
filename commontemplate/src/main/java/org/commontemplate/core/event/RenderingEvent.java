package org.commontemplate.core.event;

/**
 * 当模板元素渲染开始时触发此事件
 * 
 * @see org.commontemplate.core.Element#render(org.commontemplate.core.Context)
 * @author liangfei0201@163.com
 * 
 */
public class RenderingEvent extends RenderEvent {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造事件
	 * 
	 * @param source 发起事件的模板/模板元素
	 */
	public RenderingEvent(Object source) {
		super(source);
	}

}