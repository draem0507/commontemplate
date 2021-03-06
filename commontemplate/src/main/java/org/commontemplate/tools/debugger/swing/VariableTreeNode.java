package org.commontemplate.tools.debugger.swing;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.commontemplate.core.VariableStorage;

/**
 * 变量节点
 *
 * @author liangfei0201@163.com
 *
 */
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
			((VariableStorage) value).putVariable(name, value);
		} else if (value instanceof Map) {
			((Map) value).put(name, value);
		}
	}

	public boolean isLeaf() {
		return (value == null || value.getClass().isPrimitive()
				|| value instanceof Boolean || value instanceof Character
				|| value instanceof Number || value instanceof String);
	}

	public boolean isModifiable() {
		return false; // TODO 未实现检测父级节点是否有当前节点的setter方法
	}

	public boolean isAppendable() {
		return (value instanceof VariableStorage) || (value instanceof Map);
	}

}
