package org.commontemplate.standard.directive;

import java.util.Map;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;

public class StandardDirectiveHandlerProvider implements DirectiveHandlerProvider {

	private Map directiveHandlers;

	public void setDirectiveHandlers(Map directiveHandlers) {
		this.directiveHandlers = directiveHandlers;
	}

	private DirectiveHandler defaultDirectiveHandler;

	public void setDefaultLineDirectiveHandler(
			DirectiveHandler defaultDirectiveHandler) {
		this.defaultDirectiveHandler = defaultDirectiveHandler;
	}

	private BlockDirectiveHandler defaultBlockDirectiveHandler;

	public void setDefaultBlockDirectiveHandler(
			BlockDirectiveHandler defaultBlockDirectiveHandler) {
		this.defaultBlockDirectiveHandler = defaultBlockDirectiveHandler;
	}

	private String defaultBlockDirectiveSuffix = ".block";

	public void setDefaultBlockDirectiveSuffix(String defaultBlockDirectiveSuffix) {
		this.defaultBlockDirectiveSuffix = defaultBlockDirectiveSuffix;
	}

	public DirectiveHandler getDirectiveHandler(String name) {
		DirectiveHandler directiveHandler = (DirectiveHandler) directiveHandlers.get(name);
		if (directiveHandler != null)
			return directiveHandler;

		if (name != null && name.length() > 0
				&& name.endsWith(defaultBlockDirectiveSuffix))
			return defaultBlockDirectiveHandler;

		return defaultDirectiveHandler;
	}

}