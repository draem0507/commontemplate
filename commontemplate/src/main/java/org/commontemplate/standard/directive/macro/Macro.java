package org.commontemplate.standard.directive.macro;

import java.util.List;
import java.util.Map;

import org.commontemplate.core.Context;
import org.commontemplate.core.RenderingException;
import org.commontemplate.standard.directive.DirectiveUtils;
import org.commontemplate.standard.directive.ParameterUtils;

/**
 * 宏信息
 *
 * @author liangfei0201@163.com
 *
 */
public final class Macro {

	public static final String NAMESPACE_TYPE = "macro.namespace";

	private final String namespace;

	private final String name;

	private final List elements;

	public Macro(List elements, String name) {
		this(elements, name, null);
	}

	public Macro(List elements, String name, String namespace) {
		this.namespace = namespace;
		this.name = name;
		this.elements = elements;
	}

	public void render(Context context, Object param, List innerElements) throws RenderingException {
		Map variables = ParameterUtils.getParameters(param);
		context.pushLocalContext(variables);
		context.putProperty(InnerDirectiveHandler.INNER_BLOCK, innerElements);
		if (namespace != null)
			context.putProperty(NAMESPACE_TYPE, namespace);
		try {
			DirectiveUtils.renderAll(elements, context);
		} catch (ReturnException e) {
			// ignore
		} finally {
			context.popLocalContext();
		}
	}

	public String getNamespace() {
		return namespace;
	}

	public String getName() {
		return name;
	}

	public List getElements() {
		return elements;
	}

}
