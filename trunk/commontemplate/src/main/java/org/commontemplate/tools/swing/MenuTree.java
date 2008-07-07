package org.commontemplate.tools.swing;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class MenuTree extends JTree {

	private static final long serialVersionUID = 1L;

	public MenuTree() {
		super();
		init();
	}

	public MenuTree(Hashtable value) {
		super(value);
		init();
	}

	public MenuTree(Object[] value) {
		super(value);
		init();
	}

	public MenuTree(TreeModel newModel) {
		super(newModel);
		init();
	}

	public MenuTree(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
		init();
	}

	public MenuTree(TreeNode root) {
		super(root);
		init();
	}

	public MenuTree(Vector value) {
		super(value);
		init();
	}

	private void init() {

	}

}
