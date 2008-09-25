package org.commontemplate.tools.javaxscript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

import org.commontemplate.Version;
import org.commontemplate.engine.Engine;
import org.commontemplate.tools.PropertiesConfigurationLoader;

/**
 * 模板引擎注册工厂，用于向JDK供给模板引擎信息。
 *
 * @author liangfei0201@163.com
 *
 */
public class CommonTemplateScriptEngineFactory implements ScriptEngineFactory {

	private final List<String> names;

	private final List<String> extensions;

	private final List<String> mimeTypes;

	private final Engine engine;

	public CommonTemplateScriptEngineFactory() {
		this.names = initNames();
		this.extensions = initExtensions();
		this.mimeTypes = initMimeTypes();
		this.engine = initEngine();
	}

	private static List<String> initNames() {
		List<String> names = new ArrayList<String>();
		names.add("commontemplate");
		names.add("cte");
		names.add("ct");
		return Collections.unmodifiableList(names);
	}

	private static List<String> initExtensions() {
		List<String> extensions = new ArrayList<String>();
		extensions.add("ctl");
		extensions.add("ct");
		return Collections.unmodifiableList(extensions);
	}

	private static List<String> initMimeTypes() {
		List<String> mimeTypes = new ArrayList<String>();
		mimeTypes.add("text/ctl");
		mimeTypes.add("text/plain");
		mimeTypes.add("text/html");
		return Collections.unmodifiableList(mimeTypes);
	}

	private static Engine initEngine() {
		try {
			return new Engine(PropertiesConfigurationLoader.loadConfiguration("commontemplate.properties"));
		} catch (Exception e) {
			return new Engine(PropertiesConfigurationLoader.loadStandardConfiguration());
		}
	}

	public ScriptEngine getScriptEngine() {
		return new CommonTemplateScriptEngine(this, engine);
	}

	public String getEngineName() {
		return "commontemplate";
	}

	public String getEngineVersion() {
		return Version.getVersion();
	}

	public String getLanguageName() {
		return "ctl";
	}

	public String getLanguageVersion() {
		return Version.getVersion();
	}

	public List<String> getNames() {
		return names;
	}

	public List<String> getExtensions() {
		return extensions;
	}

	public List<String> getMimeTypes() {
		return mimeTypes;
	}

	public Object getParameter(String key) {
		if (ScriptEngine.NAME.equals(key)) {
			return getEngineName();
		} else if (ScriptEngine.ENGINE.equals(key)) {
			return CommonTemplateScriptEngine.class.getName();
		} else if (ScriptEngine.ENGINE_VERSION.equals(key)) {
			return getEngineVersion();
		} else if (ScriptEngine.LANGUAGE.equals(key)) {
			return getLanguageName();
		} else if (ScriptEngine.LANGUAGE_VERSION.equals(key)) {
			return getLanguageVersion();
		} else if (CommonTemplateScriptEngine.ENGINE_RELEASED.equals(key)) {
			return Version.getReleased();
		} else if (CommonTemplateScriptEngine.ENGINE_VENDOR.equals(key)) {
			return Version.getVendor();
		}
		return null;
	}

	public String getMethodCallSyntax(String obj, String m, String... args) {
		StringBuilder buf = new StringBuilder();
		if (args != null && args.length > 0) {
			boolean first = true;
			for (String arg : args) {
				if (first)
					first = false;
				else
					buf.append(", ");
				buf.append(arg);
			}
		}
		return obj + "." + m + "(" + buf.toString() + ")";
	}

	public String getOutputStatement(String toDisplay) {
		return "${\"" + toDisplay + "\"}";
	}

	public String getProgram(String... statements) {
		StringBuilder buf = new StringBuilder();
		for (String stat : statements) {
			buf.append(stat);
		}
		return buf.toString();
	}

}
