package org.commontemplate.standard.directive.debug;

import javax.swing.tree.DefaultMutableTreeNode;

class VariableTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 5248571865408340124L;

	private final String name;

	private final Object value;

	public VariableTreeNode(String name, Object value) {
		super(name);
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Class getType() {
		return value == null ? null : value.getClass();
	}

	public Object getValue() {
		return value;
	}

}
