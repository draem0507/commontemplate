package org.commontemplate.tools.debugger.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.I18nMessages;
import org.commontemplate.util.swing.TextPopupMenu;

public class ContextPane extends JSplitPane {

	private static final long serialVersionUID = 1L;

	private JTree contextTree;

	private JTextField variableType;

	private JTextArea variableView;

	private JPopupMenu contextTreePopupMenu;

	public ContextPane() {
		super();
		createContextPane();
	}

	private void createContextPane() {
		final JMenuItem modifyItem = new JMenuItem(I18nMessages
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
		});

		final JMenuItem copyItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.menu.item"));
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TreePath path = contextTree.getSelectionPath();
				if (path != null) {
					VariableTreeNode node = (VariableTreeNode) path
							.getLastPathComponent();
					if (node != null) {
						StringSelection stringSelection = new StringSelection(
								node.getName());
						Toolkit.getDefaultToolkit().getSystemClipboard()
								.setContents(stringSelection, stringSelection);
					}
				}
			}
		});

		final JMenuItem copyAllItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.all.menu.item"));
		copyAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TreePath path = contextTree.getSelectionPath();
				if (path != null) {
					VariableTreeNode node = (VariableTreeNode) path
							.getLastPathComponent();
					if (node != null) {
						StringSelection stringSelection = new StringSelection(
								node.getAllName());
						Toolkit.getDefaultToolkit().getSystemClipboard()
								.setContents(stringSelection, stringSelection);
						return;
					}
				}
				StringSelection stringSelection = new StringSelection(
						((VariableTreeNode) contextTree.getModel().getRoot())
								.getAllName());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
						stringSelection, stringSelection);
			}
		});

		contextTreePopupMenu = new JPopupMenu();
		/* TODO 未实现，暂时屏蔽
		contextTreePopupMenu.add(modifyItem);
		contextTreePopupMenu.add(newItem);
		contextTreePopupMenu.add(new JPopupMenu.Separator());
		*/
		contextTreePopupMenu.add(copyItem);
		contextTreePopupMenu.add(copyAllItem);

		variableType = new JTextField();
		TextPopupMenu.bindCopy(variableType);
		variableType.setEditable(false);
		variableType.setBackground(Color.WHITE);

		variableView = new JTextArea();
		TextPopupMenu.bindCopy(variableView);
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
		contextTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		contextTree.addTreeWillExpandListener(new TreeWillExpandListener(){

			public void treeWillCollapse(TreeExpansionEvent event)
					throws ExpandVetoException {
				// TODO Auto-generated method stub

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
		contextTree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getModifiers() == MouseEvent.META_MASK) {
					int x = me.getX();
					int y = me.getY();
					int selRow = contextTree.getRowForLocation(x, y);
					contextTree.setSelectionRow(selRow == -1 ? 0 : selRow);
					TreePath path = contextTree.getSelectionPath();
					modifyItem.setEnabled(false);
					newItem.setEnabled(false);
					if (path != null) {
						VariableTreeNode node = (VariableTreeNode) path
								.getLastPathComponent();
						if (node != null) {
							modifyItem.setEnabled(node.isModifiable());
							newItem.setEnabled(node.isAppendable());
						}
					}
					contextTreePopupMenu.show(contextTree, x, y);
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
		this.setDividerLocation(300);
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
