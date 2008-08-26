package org.commontemplate.standard.context;

import java.io.Serializable;
import java.util.List;

import org.commontemplate.config.ContextInitializer;
import org.commontemplate.core.Context;

public class ContextInitializerChain implements ContextInitializer, Serializable {

	private static final long serialVersionUID = 1L;

	private List contextInitializers;

	public void setContextInitializers(List contextInitializers) {
		this.contextInitializers = contextInitializers;
	}

	public void initialize(Context context) {
		if (contextInitializers != null && contextInitializers.size() > 0) {
			for (int i = 0, n = contextInitializers.size(); i < n; i++) {
				ContextInitializer contextInitializer = (ContextInitializer)contextInitializers.get(i);
				contextInitializer.initialize(context);
			}
		}
	}

}
