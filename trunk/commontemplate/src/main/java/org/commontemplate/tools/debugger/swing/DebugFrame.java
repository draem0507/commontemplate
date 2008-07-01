package org.commontemplate.tools.debugger.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.commontemplate.standard.debug.Execution;
import org.commontemplate.util.I18nMessages;
import org.commontemplate.util.swing.ImageFactory;

public class DebugFrame implements ActionListener, WindowListener, ListSelectionListener {

	private static DebugFrame debugFrame;

	public static synchronized DebugFrame getDebugFrame() {
		return debugFrame;
	}

	public static synchronized DebugFrame createDebugFrame() {
		if (debugFrame == null)
			debugFrame = new DebugFrame();
		return debugFrame;
	}

	public static synchronized void removeDebugFrame() {
		debugFrame = null;
	}

	// ---- 静态结构 ----

	static {
		try {
			// 启用图形UI
			// System.setProperty("java.awt.headless", "ture");
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

	private final TemplatePane templatePane = new TemplatePane();

	private final ContextPane contextPane = new ContextPane();

	private final JButton stepInto, stepOver, stepReturn, resume, resumeAll, terminate;

	private final DefaultListModel executionModel = new DefaultListModel();

	private final JList executionList = new JList(executionModel);

	private Execution execution;

	public DebugFrame() {
		frame = new JFrame(I18nMessages.getMessage("DebugFrame.title")
				+ " (http://www.commontemplate.org)");
		frame.setIconImage(getImage("debug.gif"));
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


		executionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		executionList.addListSelectionListener(this);
		JScrollPane executionBox = new JScrollPane();
		executionBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		executionBox.getViewport().setView(executionList);
		executionBox.getViewport().setBackground(Color.white);

		JTabbedPane tabPanel = new JTabbedPane();
		tabPanel.add("Threads", executionBox);
		tabPanel.add("Breakpoints", new BreakpointPane());

		JSplitPane horizontalPane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				templatePane, contextPane);
		horizontalPane.setDividerLocation(500);
		horizontalPane.setOneTouchExpandable(true);

		JSplitPane verticalPane = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT,
				tabPanel, horizontalPane);
		verticalPane.setDividerLocation(100);
		verticalPane.setOneTouchExpandable(true);

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

		resumeAll = new JButton(getIcon("resume_all.gif"));
		resumeAll.setToolTipText(I18nMessages
				.getMessage("DebugFrame.resume.all.button")
				+ " (ALT+F9)");
		resumeAll.setMnemonic(KeyEvent.VK_F9);
		resumeAll.addActionListener(this);
		buttonPane.add(resumeAll);

		terminate = new JButton(getIcon("terminate.gif"));
		terminate.setToolTipText(I18nMessages
				.getMessage("DebugFrame.terminate.button")
				+ " (ALT+F10)");
		terminate.setMnemonic(KeyEvent.VK_F10);
		terminate.addActionListener(this);
		buttonPane.add(terminate);

		frame.getContentPane().add(buttonPane, BorderLayout.NORTH);
		frame.getContentPane().add(verticalPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	// ---- 动态结构 ----

	public void addExecution(final Execution execution) {
		if (execution == null)
			return;
		synchronized (executionModel) {
			if (executionModel.indexOf(execution) < 0) {
				executionModel.addElement(execution);
				if (executionList.getSelectedIndex() < 0)
					executionList.setSelectedValue(execution, true);
			}
		}
	}

	public void removeExecution(final Execution execution) {
		synchronized (executionModel) {
			executionModel.removeElement(execution);
		}
	}

	private void initDebugFrame(Execution execution) {
		if (execution == null) {
			this.execution = null;
			disableToolbar();
		} else {
			this.execution = execution;
			enableToolbar();
			frame.setVisible(true);
		}
	}

	// ---- 事件处理 ----

	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		disableToolbar();
		if (execution != null) {
			if (button == stepInto) {
				execution.stepInto();
			} else if (button == stepOver) {
				execution.stepOver();
			} else if (button == stepReturn) {
				execution.stepReturn();
			} else if (button == resume) {
				execution.resume();
			} else if (button == resumeAll) {
				execution.resumeAll();
			}  else {
				execution.terminate();
			}
			removeExecution(execution);
		}
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
		if (executionModel.size() != 0) {
			String title = I18nMessages.getMessage("DebugFrame.close.title");
			String message = I18nMessages
					.getMessage("DebugFrame.close.message");
			String[] buttons = new String[] {
					I18nMessages.getMessage("DebugFrame.close.resume"),
					I18nMessages.getMessage("DebugFrame.close.terminate"),
					I18nMessages.getMessage("DebugFrame.close.cancel") };
			int ch = JOptionPane.showOptionDialog(frame, message, title,
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, buttons, buttons[0]);
			if (ch == 0 || ch == 1) {
				disableToolbar();
				for (Enumeration enumeration = executionModel.elements(); enumeration.hasMoreElements();) {
					Execution exec = (Execution)enumeration.nextElement();
					if (ch == 0)
						exec.resumeAll();
					else
						exec.terminate();
					removeExecution(exec);
				}
				executionModel.clear();
				removeDebugFrame();
				frame.dispose();
			}
		} else {
			disableToolbar();
			executionModel.clear();
			removeDebugFrame();
			frame.dispose();
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		initDebugFrame((Execution)executionList.getSelectedValue());
	}

	private synchronized void enableToolbar() {
		stepInto.setEnabled(true);
		stepOver.setEnabled(true);
		stepReturn.setEnabled(true);
		resume.setEnabled(true);
		resumeAll.setEnabled(true);
		terminate.setEnabled(true);
		templatePane.setElement(execution.getElement());
		contextPane.initContextPane(execution.getContext());
	}

	private synchronized void disableToolbar() {
		stepInto.setEnabled(false);
		stepOver.setEnabled(false);
		stepReturn.setEnabled(false);
		resume.setEnabled(false);
		resumeAll.setEnabled(false);
		terminate.setEnabled(false);
		contextPane.clearContextPane();
		templatePane.removeElement();
	}

}
