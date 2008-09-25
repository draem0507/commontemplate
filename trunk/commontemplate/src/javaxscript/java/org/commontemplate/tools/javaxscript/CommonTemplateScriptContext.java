package org.commontemplate.tools.javaxscript;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;

import org.commontemplate.core.BaseContext;
import org.commontemplate.core.Context;
import org.commontemplate.core.Template;

/**
 * 运行上下文适配: Context -&gt; ScriptContext
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateScriptContext implements ScriptContext {

	private final Context context;

	private final List<Integer> scopes;

	public CommonTemplateScriptContext(Context context) {
		this.context = context;
		this.scopes = new ArrayList<Integer>();
		this.scopes.add(ScriptContext.GLOBAL_SCOPE);
		this.scopes.add(ScriptContext.ENGINE_SCOPE);
	}

	public Context getContext() {
		return context;
	}

	private BaseContext getContext(int scope) {
		if (scope == ScriptContext.GLOBAL_SCOPE) {
            return context.getGlobalContext();
        } else if (scope == ScriptContext.ENGINE_SCOPE) {
            return context;
        } else {
            throw new IllegalArgumentException("Invalid scope value.");
        }
	}

	public Object getAttribute(String name) {
		return context.getProperty(name);
	}

	public Object getAttribute(String name, int scope) {
		return getContext(scope).getProperty(name);
	}

	public Object removeAttribute(String name, int scope) {
		BaseContext ctx = getContext(scope);
		Object old = ctx.getProperty(name);
		ctx.removeProperty(name);
		return old;
	}

	public void setAttribute(String name, Object value, int scope) {
		getContext(scope).putProperty(name, value);
	}

	public Bindings getBindings(int scope) {
		return new CommonTemplateBindings(getContext(scope));
	}

	public void setBindings(Bindings bindings, int scope) {
		getContext(scope).putAll(bindings);
	}

	public int getAttributesScope(String name) {
		if (context.getGlobalContext().isVariableContained(name))
			return ScriptContext.GLOBAL_SCOPE;
		return ScriptContext.ENGINE_SCOPE;
	}

	public List<Integer> getScopes() {
		return scopes;
	}

	public Reader getReader() {
		Template template = context.getCurrentTemplate();
		if (template != null) {
			try {
				return template.getReader();
			} catch (IOException e) {
				return null;
			}
		}
		return null;
	}

	public void setReader(Reader reader) {
		// Unsupported
	}

	public Writer getWriter() {
		return context.getOut();
	}

	public void setWriter(Writer writer) {
		// Unsupported
	}

	public Writer getErrorWriter() {
		return context.getOut();
	}

	public void setErrorWriter(Writer writer) {
		// Unsupported
	}

}
