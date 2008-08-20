package org.commontemplate.tools.debugger.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.commontemplate.core.Context;
import org.commontemplate.tools.swing.MenuBuilder;
import org.commontemplate.tools.swing.MenuTextArea;
import org.commontemplate.tools.swing.MenuTextField;
import org.commontemplate.util.BeanUtils;

public class ContextPane extends JSplitPane {

	private static final long serialVersionUID = 1L;

	private JTree contextTree;

	private MenuTextField variableType;

	private MenuTextArea variableView;

	public ContextPane() {
		super();
		createContextPane();
	}

	private void createContextPane() {
		/*final JMenuItem modifyItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.modify.variable.menu.item"));
		modifyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO 未实现
			}
		});

		final JMenuItem newItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.new.variable.menu.item"));
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO 未实现
			}
		});*/

		variableType = new MenuTextField();
		variableType.setEditable(false);
		variableType.setBackground(Color.WHITE);

		variableView = new MenuTextArea();
		variableView.setEditable(false);
		variableView.setBackground(Color.WHITE);
		variableView.setLineWrap(true);
		variableView.setWrapStyleWord(true);

		JScrollPane variableBox = new JScrollPane();
		variableBox
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		variableBox.getViewport().setView(variableView);
		variableBox.getViewport().setBackground(Color.white);

		JPanel variablePane = new JPanel();
		variablePane.setLayout(new BorderLayout());
		variablePane.add(variableType, BorderLayout.NORTH);
		variablePane.add(variableBox, BorderLayout.CENTER);

		contextTree = new JTree();
		MenuBuilder.buildReadonlyTreeMenu(contextTree);
		contextTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		contextTree.addTreeWillExpandListener(new TreeWillExpandListener(){

			public void treeWillCollapse(TreeExpansionEvent event)
					throws ExpandVetoException {
			}

			public void treeWillExpand(TreeExpansionEvent event)
					throws ExpandVetoException {
				TreePath path = event.getPath();
				if (path != null) {
					VariableTreeNode node = (VariableTreeNode) path
							.getLastPathComponent();
					if (node != null) {
						buildContextTree(node);
					}
				}
			}

		});
		contextTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = contextTree.getSelectionPath();
				if (path != null) {
					VariableTreeNode node = (VariableTreeNode) path
							.getLastPathComponent();
					if (node != null) {
						if (node.isRoot()) {
							variableType.setText("");
							variableView.setText("");
						} else {
							Class type = node.getType();
							variableType.setText(type == null ? "null" : type
									.getName());
							variableView.setText(String
									.valueOf(node.getValue()));
						}
					}
				}
			}
		});

		JScrollPane contextBox = new JScrollPane();
		contextBox
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contextBox.getViewport().setView(contextTree);
		contextBox.getViewport().setBackground(Color.white);

		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(contextBox, JSplitPane.TOP);
		this.add(variablePane, JSplitPane.BOTTOM);
		this.setDividerLocation(250);
		this.setOneTouchExpandable(true);
	}


	public void initContextPane(Context context) {
		if (context == null)
			return;
		VariableTreeNode root = new VariableTreeNode("variables", context.getExistedVariables());
		buildContextTree(root);
		contextTree.setModel(new DefaultTreeModel(root));
	}

	private void buildContextTree(VariableTreeNode root) {
		if (root == null)
			return;
		Object obj = root.getValue();
		if (obj == null) {
			return;
		} else if (obj.getClass().isArray()) {
			Object[] arr = (Object[]) obj;
			for (int i = 0, n = arr.length; i < n; i++) {
				String name = "[" + i + "]";
				Object value = arr[i];
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
			}
		} else if (obj instanceof Collection) {
			Collection coll = (Collection) obj;
			int i = 0;
			for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
				String name = "[" + i + "]";
				Object value = iterator.next();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				i++;
			}
		} else if (obj instanceof Map) {
			Map map = (Map) obj;
			for (Iterator iterator = map.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String name = String.valueOf(entry.getKey());
				Object value = entry.getValue();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
			}
		} else {
			Map map = BeanUtils.getProperties(obj);
			for (Iterator iterator = map.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String name = String.valueOf(entry.getKey());
				Object value = entry.getValue();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
			}
		}
	}

	public void clearContextPane() {
		variableType.setText("");
		variableView.setText("");
	}

}
