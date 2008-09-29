package org.commontemplate.engine;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.commontemplate.config.Keywords;
import org.commontemplate.config.ScopeHandler;
import org.commontemplate.core.BaseContext;
import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;

class Scope implements Map {

	private final Map scopeHandlers;

	private final Keywords keywords;

	private final Context context;

	private final int level;

	Scope(Map scopeHandlers, Keywords keywords, Context context, int level) {
		this.scopeHandlers = scopeHandlers;
		this.keywords = keywords;
		this.context = context;
		this.level = level;
	}

	private LocalContext local() {
		if (level == 0)
			return context.getCurrentLocalContext();
		LocalContext local = context.getCurrentLocalContext();
		for (int i = 0; i < level && local != null; i ++) {
			local = local.getParentLocalContext();
		}
		return local;
	}

	public Object get(Object key) {
		if (keywords.getParentKeyword().equals(key))
			return new Scope(scopeHandlers, keywords, context, level + 1);
		if (keywords.getCurrentKeyword().equals(key))
			return this;
		if (scopeHandlers != null) {
			ScopeHandler scopeHandler = (ScopeHandler)scopeHandlers.get(key);
			if (scopeHandler != null) {
				return scopeHandler.getScopeVariable(context, level);
			}
		}
		BaseContext local = local();
		if (local == null)
			return context.getGlobalContext().get(key);
		return local.get(key);
	}

	public boolean isEmpty() {
		return context.isEmpty();
	}

	public Set keySet() {
		return context.keySet();
	}

	public Object put(Object key, Object value) {
		return context.put(key, value);
	}

	public void putAll(Map t) {
		context.putAll(t);
	}

	public Object remove(Object key) {
		return context.remove(key);
	}

	public int size() {
		return context.size();
	}

	public Collection values() {
		return context.values();
	}

	public void clear() {
		context.clear();
	}

	public boolean containsKey(Object key) {
		return context.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return context.containsValue(value);
	}

	public Set entrySet() {
		return context.entrySet();
	}

}
