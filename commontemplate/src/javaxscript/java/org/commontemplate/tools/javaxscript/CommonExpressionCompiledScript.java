package org.commontemplate.tools.javaxscript;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.commontemplate.core.Context;
import org.commontemplate.core.Expression;

public class CommonExpressionCompiledScript extends CompiledScript {

	private final ScriptEngine scriptEngine;

	private final Expression expression;

	public CommonExpressionCompiledScript(ScriptEngine scriptEngine, Expression expression) {
		this.scriptEngine = scriptEngine;
		this.expression = expression;
	}

	@Override
	public Object eval(ScriptContext context) throws ScriptException {
		if (! (context instanceof CommonTemplateScriptContext))
			throw new UnsupportedOperationException("不支持非CommonTemplateScriptContext: " + context.getClass().getName());
		Context ctx = ((CommonTemplateScriptContext)context).getContext();
		return expression.evaluate(ctx);
	}

	@Override
	public ScriptEngine getEngine() {
		return scriptEngine;
	}

}
