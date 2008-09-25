package org.commontemplate.tools.javaxscript;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

import org.commontemplate.core.Context;
import org.commontemplate.engine.Engine;
import org.commontemplate.util.IOUtils;

/**
 * 模板引擎适配：Engine -&gt; ScriptEngine
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateScriptEngine extends AbstractScriptEngine implements Compilable {

	public static final String ENGINE_RELEASED = "javax.script.engine.released";

	public static final String ENGINE_VENDOR = "javax.script.engine.vendor";

	private final ScriptEngineFactory factory;

	private final Engine engine;

	public CommonTemplateScriptEngine(ScriptEngineFactory factory, Engine engine) {
		this.factory = factory;
		this.engine = engine;
		super.context = new CommonTemplateScriptContext(engine.createContext(new StringWriter()));
	}

	public Bindings createBindings() {
		return new CommonTemplateBindings(engine.createContext(new StringWriter()));
	}

	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		if (! (context instanceof CommonTemplateScriptContext))
			throw new UnsupportedOperationException("不支持非CommonTemplateScriptContext: " + context.getClass().getName());
		Context ctx = ((CommonTemplateScriptContext)context).getContext();
		engine.parseTemplate(script).render(ctx);
		return ctx.getOut().toString();
	}

	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		if (! (context instanceof CommonTemplateScriptContext))
			throw new UnsupportedOperationException("不支持非CommonTemplateScriptContext: " + context.getClass().getName());
		Context ctx = ((CommonTemplateScriptContext)context).getContext();
		try {
			engine.parseTemplate(IOUtils.readToString(reader)).render(ctx);
		} catch (IOException e) {
			throw new ScriptException(e);
		}
		return ctx.getOut().toString();
	}

	public ScriptEngineFactory getFactory() {
		return factory;
	}

	public CompiledScript compile(String script) throws ScriptException {
		return new CommonTemplateCompiledScript(this, engine.parseTemplate(script));
	}

	public CompiledScript compile(Reader script) throws ScriptException {
		try {
			return new CommonTemplateCompiledScript(this, engine.parseTemplate(IOUtils.readToString(script)));
		} catch (IOException e) {
			throw new ScriptException(e);
		}
	}

}
