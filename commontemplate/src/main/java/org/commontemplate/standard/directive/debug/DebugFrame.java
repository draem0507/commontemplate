package org.commontemplate.standard.directive.debug;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.I18nMessages;
import org.commontemplate.util.TypeUtils;
import org.commontemplate.util.swing.ImageFactory;
import org.commontemplate.util.swing.TextPopupMenu;

public class DebugFrame implements ActionListener, WindowListener {

	// ---- 实例管理 ----

	public static void showDebugFrame(final Context context,
			final Element element, final DebugLock lock) {
		DebugFrame.getDebugFrame().initDebugFrame(context, element, lock);
	}

	private static ThreadLocal local = new ThreadLocal();

	private static DebugFrame getDebugFrame() {
		DebugFrame debugFrame = (DebugFrame) local.get();
		if (debugFrame == null) {
			debugFrame = new DebugFrame();
			local.set(debugFrame);
		}
		return debugFrame;
	}

	// ---- 静态结构 ----

	static {
		try {
			// 设置swing样式为当前系统风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String ICON_PATH = DebugFrame.class.getPackage().getName().replace('.', '/') + "/";

	private Image getImage(String icon) {
		return ImageFactory.getImage(ICON_PATH + icon);
	}

	private Icon getIcon(String icon) {
		return ImageFactory.getIcon(ICON_PATH + icon);
	}

	private final JFrame frame;

	private JButton stepInto, stepOver, stepReturn, resume, terminate;

	private JMenuItem stepIntoItem, stepOverItem, stepReturnItem, resumeItem,
			terminateItem;

	private JTextField templateNameBox;

	private JTextField elementBox;

	private JLabel templateView;

	private JTree contextTree;

	private JTextField variableType;

	private JTextArea variableView;

	private JPopupMenu contextTreePopupMenu;

	private JPopupMenu templateViewMenu;

	private DebugFrame() {
		frame = new JFrame(I18nMessages.getMessage("DebugFrame.title")
				+ " (http://www.commontemplate.org)");
		frame.setIconImage(getImage("debug.gif"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);// 在屏幕居中显示
		frame.getRootPane().setFocusable(true);
		frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.addWindowListener(this);

		JSplitPane horizontalPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				createTemplatePane(), createContextPane());
		horizontalPane.setDividerLocation(500);
		horizontalPane.setOneTouchExpandable(true);
		frame.getContentPane().add(createToolBar(), BorderLayout.NORTH);
		frame.getContentPane().add(horizontalPane, BorderLayout.CENTER);
	}

	private JComponent createToolBar() {
		JToolBar buttonPane = new JToolBar();
		buttonPane.setMargin(new Insets(0, 10, 0, 0));

		stepInto = new JButton(getIcon("stepinto.gif"));
		stepInto.setToolTipText(I18nMessages
				.getMessage("DebugFrame.step.into.button")
				+ " (ALT+F5)");
		stepInto.setMnemonic(KeyEvent.VK_F5);
		stepInto.addActionListener(this);
		buttonPane.add(stepInto);

		stepOver = new JButton(getIcon("stepover.gif"));
		stepOver.setToolTipText(I18nMessages
				.getMessage("DebugFrame.step.over.button")
				+ " (ALT+F6)");
		stepOver.setMnemonic(KeyEvent.VK_F6);
		stepOver.addActionListener(this);
		buttonPane.add(stepOver);

		stepReturn = new JButton(getIcon("stepreturn.gif"));
		stepReturn.setToolTipText(I18nMessages
				.getMessage("DebugFrame.step.return.button")
				+ " (ALT+F7)");
		stepReturn.setMnemonic(KeyEvent.VK_F7);
		stepReturn.addActionListener(this);
		buttonPane.add(stepReturn);

		resume = new JButton(getIcon("resume.gif"));
		resume.setToolTipText(I18nMessages
				.getMessage("DebugFrame.resume.button")
				+ " (ALT+F8)");
		resume.setMnemonic(KeyEvent.VK_F8);
		resume.addActionListener(this);
		buttonPane.add(resume);

		terminate = new JButton(getIcon("terminate.gif"));
		terminate.setToolTipText(I18nMessages
				.getMessage("DebugFrame.terminate.button")
				+ " (ALT+F9)");
		terminate.setMnemonic(KeyEvent.VK_F9);
		terminate.addActionListener(this);
		buttonPane.add(terminate);

		return buttonPane;
	}

	private JComponent createTemplatePane() {
		stepIntoItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.step.into.button")
				+ " (ALT+F5)", getIcon("stepinto.gif"));
		stepIntoItem.addActionListener(this);

		stepOverItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.step.over.button")
				+ " (ALT+F6)", getIcon("stepover.gif"));
		stepOverItem.addActionListener(this);

		stepReturnItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.step.return.button")
				+ " (ALT+F7)", getIcon("stepreturn.gif"));
		stepReturnItem.addActionListener(this);

		resumeItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.resume.button")
				+ " (ALT+F8)", getIcon("resume.gif"));
		resumeItem.addActionListener(this);

		terminateItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.terminate.button")
				+ " (ALT+F9)", getIcon("terminate.gif"));
		terminateItem.addActionListener(this);

		final JMenuItem copyItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.menu.item"));
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String elementText;
				if (element == null)
					elementText = "";
				else if (element instanceof BlockDirective)
					elementText = element.getSignature();
				else
					elementText = element.getCanonicalForm();
				StringSelection stringSelection = new StringSelection(
						elementText);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
						stringSelection, stringSelection);
			}
		});

		final JMenuItem copyAllItem = new JMenuItem(I18nMessages
				.getMessage("DebugFrame.copy.all.menu.item"));
		copyAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StringSelection stringSelection = new StringSelection(
						template == null ? "" : template.getSource());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
						stringSelection, stringSelection);
			}
		});

		templateViewMenu = new JPopupMenu();
		templateViewMenu.add(stepIntoItem);
		templateViewMenu.add(stepOverItem);
		templateViewMenu.add(stepReturnItem);
		templateViewMenu.add(resumeItem);
		templateViewMenu.add(terminateItem);
		templateViewMenu.add(new JPopupMenu.Separator());
		templateViewMenu.add(copyItem);
		templateViewMenu.add(copyAllItem);

		templateNameBox = new JTextField();
		TextPopupMenu.bindCopy(templateNameBox);
		templateNameBox.setEditable(false);
		templateNameBox.setBackground(Color.WHITE);

		templateView = new JLabel();
		templateView.setBounds(40, 100, 400, 400);
		templateView.setOpaque(true);
		templateView.setBackground(Color.WHITE);
		templateView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getModifiers() == MouseEvent.META_MASK) {
					int x = me.getX();
					int y = me.getY();
					boolean enableDebug = (lock != null);
					stepIntoItem.setEnabled(enableDebug);
					stepOverItem.setEnabled(enableDebug);
					stepReturnItem.setEnabled(enableDebug);
					resumeItem.setEnabled(enableDebug);
					terminateItem.setEnabled(enableDebug);
					copyItem.setEnabled(enableDebug);
					templateViewMenu.show(templateView, x, y);
				}
			}
		});

		elementBox = new JTextField();
		TextPopupMenu.bindCopy(elementBox);
		elementBox.setEditable(false);
		elementBox.setBackground(Color.WHITE);

		JScrollPane templateBox = new JScrollPane();
		templateBox
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		templateBox.getViewport().setView(templateView);
		templateBox.getViewport().setBackground(Color.white);

		JPanel templatePane = new JPanel();
		templatePane.setLayout(new BorderLayout());
		templatePane.add(templateNameBox, BorderLayout.NORTH);
		templatePane.add(templateBox, BorderLayout.CENTER);
		templatePane.add(elementBox, BorderLayout.SOUTH);

		return templatePane;
	}

	private JComponent createContextPane() {
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
		contextTreePopupMenu.add(modifyItem);
		contextTreePopupMenu.add(newItem);
		contextTreePopupMenu.add(new JPopupMenu.Separator());
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

		JSplitPane contextPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				contextBox, variablePane);
		contextPane.setDividerLocation(400);
		contextPane.setOneTouchExpandable(true);

		return contextPane;
	}

	// ---- 动态结构 ----

	private DebugLock lock;

	private void initDebugFrame(final Context context, final Element element,
			final DebugLock lock) {
		this.lock = lock;
		initTemplatePane(context.getCurrentTemplate(), element);
		initContextPane(context);
		openFrame();
	}

	private Template template;

	private Element element;

	private void initTemplatePane(Template template, Element element) {
		this.template = template;
		this.element = element;

		String elementText;
		if (element instanceof BlockDirective)
			elementText = element.getSignature();
		else
			elementText = element.getCanonicalForm();
		elementText = elementText.replaceAll("\n", "\\\\n");
		elementText = elementText.replaceAll("\r", "\\\\r");
		elementText = elementText.replaceAll("\t", "\\\\t");
		elementText = elementText.replaceAll("\f", "\\\\f");
		elementText = elementText.replaceAll("\b", "\\\\b");

		elementBox.setText(element.getType() + ": " + elementText);
		templateNameBox.setText(template.getEncoding() + ": "
				+ template.getName());

		String tmp = template.getSource();

		// 设置高亮显示指令
		StringBuffer buf = new StringBuffer(tmp);
		int begin = element.getLocation().getBegin().getOffset();
		buf.insert(begin, "\1");
		int end = element.getLocation().getEnd().getOffset();
		buf.insert(end + 1, "\2");
		tmp = escapeHtml(buf.toString());
		tmp = tmp.replaceAll("\1", "<font color=white bgcolor=blue>");
		tmp = tmp.replaceAll("\2", "</font>");
		templateView.setText(tmp);
	}

	private String escapeHtml(String html) {
		html = html.replaceAll("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		html = html.replaceAll("\n", "<br>");
		html = html.replaceAll(" ", "&nbsp;");
		html = html.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return "<html>" + html + "</html>";
	}

	private void initContextPane(Context context) {
		VariableTreeNode root = new VariableTreeNode("variables", context);
		buildContextTree(root, context.getExistedVariables());
		contextTree.setModel(new DefaultTreeModel(root));
	}

	private void buildContextTree(VariableTreeNode root, Object obj) {
		if (obj == null) {
			return;
		} else if (obj.getClass().isArray()) {
			Object[] arr = (Object[]) obj;
			for (int i = 0, n = arr.length; i < n; i++) {
				String name = "[" + i + "]";
				Object value = arr[i];
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				if (value != null && !TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
			}
		} else if (obj instanceof Collection) {
			Collection coll = (Collection) obj;
			int i = 0;
			for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
				String name = "[" + i + "]";
				Object value = iterator.next();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				if (value != null && !TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
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
				if (value != null && !TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
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
				if (value != null && !TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
			}
		}
	}

	// ---- 事件处理 ----

	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		if (button == resume || button == terminate) {
			closeFrame();
		} else {
			disableToolbar();
		}
		int status;
		if (button == stepInto || button == stepIntoItem) {
			status = DebugLock.STEP_INTO;
		} else if (button == stepOver || button == stepOverItem) {
			status = DebugLock.STEP_OVER;
		} else if (button == stepReturn || button == stepReturnItem) {
			status = DebugLock.STEP_RETURN;
		} else if (button == resume || button == resumeItem) {
			status = DebugLock.RESUME;
		} else {
			status = DebugLock.TERMINATE;
		}
		releaseLock(status);
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		if (lock != null) {
			String title = I18nMessages.getMessage("DebugFrame.close.title");
			String message = I18nMessages
					.getMessage("DebugFrame.close.message");
			String[] buttons = new String[] {
					I18nMessages.getMessage("DebugFrame.close.resume"),
					I18nMessages.getMessage("DebugFrame.close.terminate"),
					I18nMessages.getMessage("DebugFrame.close.cancel") };
			int i = JOptionPane.showOptionDialog(frame, message, title,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, buttons, buttons[0]);
			if (i == 0 || i == 1) {
				closeFrame();
				releaseLock(i == 0 ? DebugLock.RESUME : DebugLock.TERMINATE);
			}
		} else {
			closeFrame();
		}
	}

	private void enableToolbar() {
		stepInto.setEnabled(true);
		stepOver.setEnabled(true);
		stepReturn.setEnabled(true);
		resume.setEnabled(true);
		terminate.setEnabled(true);
	}

	private void disableToolbar() {
		stepInto.setEnabled(false);
		stepOver.setEnabled(false);
		stepReturn.setEnabled(false);
		resume.setEnabled(false);
		terminate.setEnabled(false);
		elementBox.setText("");
		variableType.setText("");
		variableView.setText("");
		templateView.setText(template == null ? "" : escapeHtml(template
				.getSource()));
	}

	private void openFrame() {
		enableToolbar();
		enableToolbar();
		frame.show();
	}

	private void closeFrame() {
		disableToolbar();
		frame.dispose();
		local.set(null);
	}

	private void releaseLock(int status) {
		if (lock != null) {
			synchronized (lock) {
				lock.setStatus(status);
				lock.notifyAll();
			}
			lock = null;
		}
	}

}
