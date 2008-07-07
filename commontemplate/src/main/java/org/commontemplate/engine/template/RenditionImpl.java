package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.RenderingInterceptor;
import org.commontemplate.config.Rendition;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.util.Assert;

final class RenditionImpl implements Rendition {

	private final Element element;

	private final Context context;

	private final List elementInterceptors;

	private final int index;

	RenditionImpl(Element element, Context context, List elementInterceptors) {
		Assert.assertNotEmpty(elementInterceptors);
		this.element = element;
		this.context = context;
		this.elementInterceptors = elementInterceptors;
		this.index = 0;
	}

	private RenditionImpl(Element element, Context context, List elementInterceptors, int index) {
		this.element = element;
		this.context = context;
		this.elementInterceptors = elementInterceptors;
		this.index = index;
	}

	/**
	 * 获取待渲染模块元素
	 *
	 * @return 待渲染模块元素
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * 获取待渲染上下文
	 *
	 * @return 待渲染上下文
	 */
	public Context getContext() {
		return context;
	}

	public boolean isRendered() {
		return false;
	}

	/**
	 * 执行渲染过程或调用上一拦截器
	 */
	public void doRender() {
		// 查找下一个不为空的拦截器
		RenderingInterceptor elementInterceptor = null;
		int i = index;
		if (elementInterceptors != null) {
			while (elementInterceptor == null && i < elementInterceptors.size()) {
				elementInterceptor = (RenderingInterceptor)elementInterceptors.get(i);
				i ++;
			}
		}
		// 如果存在下一个不为空的拦截器， 则执行它
		if (elementInterceptor != null) {
			elementInterceptor.intercept(new RenditionImpl(element, context, elementInterceptors, i));
		} else { // 否则运行正常逻辑
			if (element instanceof BlockDirectiveProxy)
				((BlockDirectiveProxy)element).getTarget().doRender(context);
			else if (element instanceof DirectiveProxy)
				((DirectiveProxy)element).getTarget().doRender(context);
			else if (element instanceof TextProxy)
				((TextProxy)element).getTarget().doRender(context);
			else if (element instanceof CommentProxy)
				((CommentProxy)element).getTarget().doRender(context);
		}
	}

}
