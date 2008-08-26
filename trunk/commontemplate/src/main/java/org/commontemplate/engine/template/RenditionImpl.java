package org.commontemplate.engine.template;

import java.util.List;

import org.commontemplate.config.RenderInterceptor;
import org.commontemplate.config.Rendition;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.Assert;

final class RenditionImpl extends Rendition {

	private final Element element;

	private final Template template;

	private final Context context;

	private final List renderInterceptors;

	private final int index;

	RenditionImpl(Element element, Context context, List renderInterceptors) {
		this(element, (element == null ? null : element.getTemplate()), context, renderInterceptors);
	}

	RenditionImpl(Template template, Context context, List renderInterceptors) {
		this(null, template, context, renderInterceptors);
	}

	private RenditionImpl(Element element, Template template, Context context, List renderInterceptors) {
		this(element, template, context, renderInterceptors, 0);
		Assert.assertNotEmpty(renderInterceptors);
	}

	private RenditionImpl(Element element, Template template, Context context, List renderInterceptors, int index) {
		this.element = element;
		this.template = template;
		this.context = context;
		this.renderInterceptors = renderInterceptors;
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

	public Template getTemplate() {
		return template;
	}

	/**
	 * 获取待渲染上下文
	 *
	 * @return 待渲染上下文
	 */
	public Context getContext() {
		return context;
	}

	private boolean rendered;

	public boolean isRendered() {
		return rendered;
	}

	/**
	 * 执行渲染过程或调用上一拦截器
	 */
	public void doRender() {
		// 查找下一个不为空的拦截器
		RenderInterceptor elementInterceptor = null;
		int i = index;
		if (renderInterceptors != null) {
			while (elementInterceptor == null && i < renderInterceptors.size()) {
				elementInterceptor = (RenderInterceptor)renderInterceptors.get(i);
				i ++;
			}
		}
		// 如果存在下一个不为空的拦截器， 则执行它
		if (elementInterceptor != null) {
			elementInterceptor.intercept(new RenditionImpl(element, template, context, renderInterceptors, i));
		} else { // 否则运行正常逻辑
			rendered = true;
			if (element == null) {
				Assert.assertNotNull(template, "被拦截的模板代理为空!"); // TODO 未国际化
				((TemplateProxy)template).getTarget().doRender(context);
			} else {
				if (element instanceof BlockDirectiveProxy)
					((BlockDirectiveProxy)element).getTarget().doRender(context);
				else if (element instanceof DirectiveProxy)
					((DirectiveProxy)element).getTarget().doRender(context);
				else if (element instanceof TextProxy)
					((TextProxy)element).getTarget().doRender(context);
				else if (element instanceof CommentProxy)
					((CommentProxy)element).getTarget().doRender(context);
				else
					Assert.fail("模板元素代理类型出错!"); // TODO 未国际化
			}
		}
	}

}
