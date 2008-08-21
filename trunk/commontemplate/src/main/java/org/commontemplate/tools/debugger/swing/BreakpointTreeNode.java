package org.commontemplate.tools.debugger.swing;

import javax.swing.tree.DefaultMutableTreeNode;

public class BreakpointTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;

	private final String templateName;

	private final int line;

	public BreakpointTreeNode() {
		super("Breakpoints");
		this.templateName = null;
		this.line = -1;
	}

	public BreakpointTreeNode(String templateName) {
		super(templateName);
		this.templateName = (templateName == null ? "" : templateName);
		this.line = -1;
	}

	public BreakpointTreeNode(String templateName, int line) {
		super("line:" + (line + 1));
		this.templateName = (templateName == null ? "" : templateName);
		this.line = line;
	}

	public String getTemplateName() {
		return templateName;
	}

	public int getLine() {
		return line;
	}

}
