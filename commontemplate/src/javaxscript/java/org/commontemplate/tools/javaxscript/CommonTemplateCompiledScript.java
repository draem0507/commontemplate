package org.commontemplate.tools.javaxscript;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.commontemplate.core.Context;
import org.commontemplate.core.Template;

/**
 * 编译后模板表示形态适配: Template -&gt; CompiledScript
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateCompiledScript extends CompiledScript {

	private final ScriptEngine scriptEngine;

	private final Template template;

	public CommonTemplateCompiledScript(ScriptEngine scriptEngine, Template template) {
		this.scriptEngine = scriptEngine;
		this.template = template;
	}

	public Template getTemplate() {
		return template;
	}

	@Override
	public Object eval(ScriptContext context) throws ScriptException {
		if (! (context instanceof CommonTemplateScriptContext))
			throw new UnsupportedOperationException("不支持非CommonTemplateScriptContext: " + context.getClass().getName());
		Context ctx = ((CommonTemplateScriptContext)context).getContext();
		template.render(ctx);
		return ctx.getOut().toString();
	}

	@Override
	public ScriptEngine getEngine() {
		return scriptEngine;
	}

}
