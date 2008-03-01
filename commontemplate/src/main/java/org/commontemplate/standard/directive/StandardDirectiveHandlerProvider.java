package org.commontemplate.standard.directive;

import java.util.Map;

import org.commontemplate.config.DirectiveHandler;
import org.commontemplate.config.DirectiveHandlerProvider;
import org.commontemplate.config.LineDirectiveHandler;
import org.commontemplate.config.BlockDirectiveHandler;

public class StandardDirectiveHandlerProvider implements DirectiveHandlerProvider {

	private Map directiveHandlers;

	public void setDirectiveHandlers(Map directiveHandlers) {
		this.directiveHandlers = directiveHandlers;
	}

	private LineDirectiveHandler defaultLineDirectiveHandler;

	public void setDefaultLineDirectiveHandler(
			LineDirectiveHandler defaultLineDirectiveHandler) {
		this.defaultLineDirectiveHandler = defaultLineDirectiveHandler;
	}

	private BlockDirectiveHandler defaultBlockDirectiveHandler;

	public void setDefaultBlockDirectiveHandler(
			BlockDirectiveHandler defaultBlockDirectiveHandler) {
		this.defaultBlockDirectiveHandler = defaultBlockDirectiveHandler;
	}
	
	private String defaultBlockDirectiveRegex = ".*_block";

	public void setDefaultBlockDirectiveRegex(String defaultBlockDirectiveRegex) {
		this.defaultBlockDirectiveRegex = defaultBlockDirectiveRegex;
	}

	public DirectiveHandler getDirectiveHandler(String name) {
		DirectiveHandler directiveHandler = (DirectiveHandler) directiveHandlers.get(name);
		if (directiveHandler != null)
			return directiveHandler;
		
		if (name != null && name.length() > 0 
				&& name.matches(defaultBlockDirectiveRegex)) 
			return defaultBlockDirectiveHandler;
		
		return defaultLineDirectiveHandler;
	}

}