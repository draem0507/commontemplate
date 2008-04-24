package org.commontemplate.standard.directive.debug;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import org.commontemplate.core.BlockDirective;
import org.commontemplate.core.Context;
import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.util.BeanUtils;
import org.commontemplate.util.TypeUtils;

class DebugFrame implements ActionListener, WindowListener {

	// ---- 实例管理 ----

	public static void showDebugFrame(final Context context, final Element element, final DebugLock lock) {
		DebugFrame.getDebugFrame().initDebugFrame(context, element, lock);
	}

	private static ThreadLocal local = new ThreadLocal();

	private static DebugFrame getDebugFrame() {
		DebugFrame debugFrame = (DebugFrame)local.get();
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

	private final JFrame frame;

	private JButton stepInto, stepOver, stepReturn, resume, terminate;

	private JTextField elementBox;

	private JLabel templateView;

	private JTree contextTree;

	private DebugFrame() {
		frame = new JFrame("Common Template Debugging");
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(DebugFrame.class.getClassLoader().getResource(ICON_PATH + "debug.gif")));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
				createTemplatePane(),
				createContextPane());
		horizontalPane.setDividerLocation(500);
		frame.getContentPane().add(createToolBar(), BorderLayout.NORTH);
		frame.getContentPane().add(horizontalPane, BorderLayout.CENTER);
	}

	private static final String ICON_PATH = DebugFrame.class.getPackage().getName().replace('.', '/') + "/";

	private JComponent createToolBar() {
		JToolBar buttonPane = new JToolBar();
		buttonPane.setMargin(new Insets(0,10,0,0));

		stepInto = new JButton(new ImageIcon(DebugFrame.class.getClassLoader().getResource(ICON_PATH + "stepinto.gif")));
		stepInto.setToolTipText("Step Into (ALT+F5)");
		stepInto.setMnemonic(KeyEvent.VK_F5);
		stepInto.addActionListener(this);
		buttonPane.add(stepInto);

		stepOver = new JButton(new ImageIcon(DebugFrame.class.getClassLoader().getResource(ICON_PATH + "stepover.gif")));
		stepOver.setToolTipText("Step Over (ALT+F6)");
		stepOver.setMnemonic(KeyEvent.VK_F6);
		stepOver.addActionListener(this);
		buttonPane.add(stepOver);

		stepReturn = new JButton(new ImageIcon(DebugFrame.class.getClassLoader().getResource(ICON_PATH + "stepreturn.gif")));
		stepReturn.setToolTipText("Step Return (ALT+F7)");
		stepReturn.setMnemonic(KeyEvent.VK_F7);
		stepReturn.addActionListener(this);
		buttonPane.add(stepReturn);

		resume = new JButton(new ImageIcon(DebugFrame.class.getClassLoader().getResource(ICON_PATH + "resume.gif")));
		resume.setToolTipText("Resume (ALT+F8)");
		resume.setMnemonic(KeyEvent.VK_F8);
		resume.addActionListener(this);
		buttonPane.add(resume);

		terminate = new JButton(new ImageIcon(DebugFrame.class.getClassLoader().getResource(ICON_PATH + "terminate.gif")));
		terminate.setToolTipText("Terminate (ALT+F9)");
		terminate.setMnemonic(KeyEvent.VK_F9);
		terminate.addActionListener(this);
		buttonPane.add(terminate);

		return buttonPane;
	}

	private JComponent createTemplatePane() {
		elementBox = new JTextField();
		elementBox.setEditable(false);
		elementBox.setBackground(Color.WHITE);

		templateView = new JLabel();
		templateView.setBounds(40, 100, 400, 400);
		templateView.setOpaque(true);
		templateView.setBackground(Color.WHITE);

		JScrollPane templateBox = new JScrollPane();
		templateBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		templateBox.getViewport().setView(templateView);
		templateBox.getViewport().setBackground(Color.white);

		JPanel templatePane = new JPanel();
		templatePane.setLayout(new BorderLayout());
		templatePane.add(elementBox, BorderLayout.NORTH);
		templatePane.add(templateBox, BorderLayout.CENTER);

		return templatePane;
	}

	private JComponent createContextPane() {
		final JTextField variableType = new JTextField();
		variableType.setEditable(false);
		variableType.setBackground(Color.WHITE);

		final JTextArea variableView = new JTextArea();
		variableView.setEditable(false);
		variableView.setBackground(Color.WHITE);
		variableView.setLineWrap(true);
		variableView.setWrapStyleWord(true);

		JScrollPane variableBox = new JScrollPane();
		variableBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		variableBox.getViewport().setView(variableView);
		variableBox.getViewport().setBackground(Color.white);

		JPanel variablePane = new JPanel();
		variablePane.setLayout(new BorderLayout());
		variablePane.add(variableType, BorderLayout.NORTH);
		variablePane.add(variableBox, BorderLayout.CENTER);

		contextTree = new JTree();
		contextTree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getNewLeadSelectionPath();
				if (path != null) {
					VariableTreeNode node = (VariableTreeNode)path.getLastPathComponent();
					if (node != null) {
						if (node.isRoot()) {
							variableType.setText("");
							variableView.setText("");
						} else {
							Class type = node.getType();
							variableType.setText(type == null ? null : type.getName());
							variableView.setText(String.valueOf(node.getValue()));
						}
					}
				}
			}
		});

		JScrollPane contextBox = new JScrollPane();
		contextBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contextBox.getViewport().setView(contextTree);
		contextBox.getViewport().setBackground(Color.white);

		JSplitPane contextPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contextBox, variablePane);
		contextPane.setDividerLocation(400);

		return contextPane;
	}

	// ---- 动态结构 ----

	private DebugLock lock;

	private void initDebugFrame(final Context context, final Element element, final DebugLock lock) {
		this.lock = lock;
		initTemplatePane(context.getCurrentTemplate(), element);
		initContextPane(context);
		openFrame();
	}

	private void initTemplatePane(Template template, Element element) {
		String elementText;
		if (element instanceof BlockDirective)
			elementText = element.getSignature();
		else
			elementText = element.getCanonicalForm();
		elementText = elementText.replaceAll("\n", "\\\\n");
		elementText = elementText.replaceAll("\r", "\\\\r");
		elementText = elementText.replaceAll("\f", "\\\\f");

		if (element instanceof BlockDirective)
			elementBox.setText(element.getSignature());
		else
			elementBox.setText(element.getCanonicalForm());
		elementBox.setText(elementText);

		String tmp = template.getCanonicalForm();
		StringBuffer buf = new StringBuffer(tmp);
		int begin = element.getLocation().getBegin().getOffset();
		buf.insert(begin, "\1");
		int end = element.getLocation().getEnd().getOffset();
		buf.insert(end + 1, "\2");
		tmp = buf.toString();
		tmp = tmp.replaceAll("<", "&lt;");
		tmp = tmp.replaceAll(">", "&gt;");
		tmp = tmp.replaceAll("\n", "<br>");
		tmp = tmp.replaceAll(" ", "&nbsp;");
		tmp = tmp.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		tmp = tmp.replaceAll("\1", "<font color=\"white\" bgcolor=\"blue\">");
		tmp = tmp.replaceAll("\2", "</font>");
		templateView.setText("<html>" + tmp + "</html>");
	}

	private void initContextPane(Context context) {
		VariableTreeNode root = new VariableTreeNode("variables", null);
		buildContextTree(root, context.getDefinedVariables());
		contextTree.setModel(new DefaultTreeModel(root));
	}

	private void buildContextTree(VariableTreeNode root, Object obj) {
		if (obj == null) {
			return;
		} else if (obj.getClass().isArray()) {
			Object[] arr = (Object[])obj;
			for (int i = 0, n = arr.length; i < n; i ++) {
				String name = "[" + i + "]";
				Object value = arr[i];
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				if (value != null && ! TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
			}
		} else if (obj instanceof Collection) {
			Collection coll = (Collection)obj;
			int i = 0;
			for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
				String name = "[" + i + "]";
				Object value = iterator.next();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				if (value != null && ! TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
				i ++;
			}
		} else if (obj instanceof Map) {
			Map map = (Map)obj;
			for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String name = String.valueOf(entry.getKey());
				Object value = entry.getValue();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				if (value != null && ! TypeUtils.isPrimitive(value.getClass())) {
					buildContextTree(thisNode, value);
				}
			}
		} else {
			Map map = BeanUtils.getProperties(obj);
			for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String name = String.valueOf(entry.getKey());
				Object value = entry.getValue();
				VariableTreeNode thisNode = new VariableTreeNode(name, value);
				root.add(thisNode);
				if (value != null && ! TypeUtils.isPrimitive(value.getClass())) {
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
		if (button == stepInto) {
			status = DebugLock.STEP_INTO;
		} else if (button == stepOver) {
			status = DebugLock.STEP_OVER;
		} else if (button == stepReturn) {
			status = DebugLock.STEP_RETURN;
		} else if (button == resume) {
			status = DebugLock.RESUME;
		} else {
			status = DebugLock.TERMINATE;
		}
		releaseLock(status);
	}

	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		if (lock != null) {
			String title = "Close";
			String message = "Debug unfinished! Please choose command after closed.";
			String[] buttons = new String[]{"Resume", "Terminate", "Cancel"};
			int i = JOptionPane.showOptionDialog(frame, message, title,
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,
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
	}

	private void openFrame() {
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
