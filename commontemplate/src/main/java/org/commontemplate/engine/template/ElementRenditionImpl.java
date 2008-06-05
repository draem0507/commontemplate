package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.RenderInterceptor;
import org.commontemplate.config.Rendition;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.util.Assert;

final class ElementRenditionImpl implements Rendition {

	private final Element element;

	private final Context context;

	private final List elementInterceptors;

	private final int index;

	ElementRenditionImpl(Element element, Context context, List elementInterceptors) {
		Assert.assertNotEmpty(elementInterceptors);
		this.element = element;
		this.context = context;
		this.elementInterceptors = elementInterceptors;
		this.index = 0;
	}

	private ElementRenditionImpl(Element element, Context context, List elementInterceptors, int index) {
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
		try {
			if (index < elementInterceptors.size()) {
				RenderInterceptor elementInterceptor= (RenderInterceptor)elementInterceptors.get(index);
				if (elementInterceptor != null) {
					elementInterceptor.intercept(new ElementRenditionImpl(element, context, elementInterceptors, index + 1));
					return;
				}
			}
		} catch (Throwable e) { // 不影响正常运行
			//e.printStackTrace();
		}
		element.render(context);
	}

}
