package org.commontemplate.standard.directive.debug;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.commontemplate.core.VariableStorage;

class VariableTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 5248571865408340124L;

	private String name;

	private Object value;

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

	public void setValue(Object value) {
		if (isLeaf()) {
			this.value = value;
		}
	}

	public void appendChild(String name, Object value) {
		if (value instanceof VariableStorage) {
			((VariableStorage)value).putVariable(name, value);
		} else if (value instanceof Map) {
			((Map)value).put(name, value);
		}
	}

	public boolean isModifiable() {
		return false; // TODO 未实现检测父级节点是否有当前节点的setter方法
	}

	public boolean isAppendable() {
		return (value instanceof VariableStorage) || (value instanceof Map);
	}

	public String getAllName() {
		return getAllName(0);
	}

	public String getAllName(int level) {
		if (getChildCount() == 0)
			return name;
		level += 1;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < getChildCount(); i ++) {
			VariableTreeNode child = (VariableTreeNode) getChildAt(i);
			buf.append('\n');
			for (int j = 0; j < level; j ++) {
				buf.append('\t');
			}
			buf.append(child.getAllName(level));
		}
		return name + buf.toString();
	}

}
