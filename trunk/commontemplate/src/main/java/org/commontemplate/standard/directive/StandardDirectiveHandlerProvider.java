package org.commontemplate.standard.directive;

import java.io.Serializable;
import java.util.Map;

import org.commontemplate.config.BlockDirectiveHandler;
import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;

public class StandardDirectiveHandlerProvider implements DirectiveHandlerProvider, Serializable {

	private static final long serialVersionUID = 1L;

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

	public static final String DEFAULT_BLOCK_DIRECTIVE_SUFFIX = ".begin";

	private String defaultBlockDirectiveSuffix = DEFAULT_BLOCK_DIRECTIVE_SUFFIX;

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