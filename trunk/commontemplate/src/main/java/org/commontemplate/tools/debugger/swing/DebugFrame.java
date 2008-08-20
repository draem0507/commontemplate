package org.commontemplate.tools.debugger.swing;

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
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.commontemplate.core.Context;
import org.commontemplate.core.LocalContext;
import org.commontemplate.core.OutputFilter;
import org.commontemplate.core.ParsingException;
import org.commontemplate.core.Template;
import org.commontemplate.standard.debug.Execution;
import org.commontemplate.tools.swing.CommonTemplateFrame;
import org.commontemplate.tools.swing.ImageFactory;
import org.commontemplate.util.I18nMessages;

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

	private static final String ICON_PATH = DebugFrame.class.getPackage().getName().replace('.', '/') + "/";

	private Icon getIcon(String icon) {
		return ImageFactory.getIcon(ICON_PATH + icon);
	}

	private final CommonTemplateFrame frame = new CommonTemplateFrame();

	private final TemplateTabPane templatePane = new TemplateTabPane(frame);

	private final ContextPane contextPane = new ContextPane();

	private final OutputPane outputPane = new OutputPane();

	private final JButton stepInto, stepOver, stepReturn, resume, resumeAll, terminate;

	private final DefaultListModel executionModel = new DefaultListModel();

	private final JList executionList = new JList(executionModel);

	private Execution execution;

	public DebugFrame() {
		frame.setTitle(I18nMessages.getMessage("DebugFrame.title"));
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
		frame.setJMenuBar(createMenuBar());

		executionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		executionList.addListSelectionListener(this);
		JScrollPane executionBox = new JScrollPane();
		executionBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		executionBox.getViewport().setView(executionList);
		executionBox.getViewport().setBackground(Color.white);

		JTabbedPane tabThreadPane = new JTabbedPane();
		tabThreadPane.add("Threads", executionBox);
		tabThreadPane.add("Breakpoints", new BreakpointPane());

		JTabbedPane tabContextPane = new JTabbedPane();
		tabContextPane.add("Variables", contextPane);

		JTabbedPane tabOutputPane = new JTabbedPane();
		tabOutputPane.add("Output", outputPane);

		JSplitPane horizontalPane1 = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				tabThreadPane, tabOutputPane);
		horizontalPane1.setDividerLocation(400);
		horizontalPane1.setOneTouchExpandable(true);

		JSplitPane horizontalPane2 = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				templatePane, tabContextPane);
		horizontalPane2.setDividerLocation(500);
		horizontalPane2.setOneTouchExpandable(true);

		JSplitPane verticalPane = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT,
				horizontalPane1, horizontalPane2);
		verticalPane.setDividerLocation(130);
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

	private JMenuItem itemStepInto, itemStepOver, itemStepReturn, itemResume, itemResumeAll, itemTerminate, itemOpen, itemFlush;

	private JCheckBoxMenuItem itemCapture;

	private JMenuBar createMenuBar() {
		JMenuBar mainMenuBar = new JMenuBar();

		JMenu menuFile = new JMenu("文件(F)"); // TODO 未国际化
		mainMenuBar.add(menuFile);
		menuFile.setMnemonic(KeyEvent.VK_F);
			itemOpen = new JMenuItem("打开模板(O)"); // TODO 未国际化
			itemOpen.setMnemonic(KeyEvent.VK_O);
			menuFile.add(itemOpen);
			itemOpen.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String templateName = JOptionPane.showInputDialog(frame,
							"请输入模板名称：(以\"/\"开头表示绝对路径，否则相对于当前模板路径，可以使用\"../\")", "打开模板",
							JOptionPane.QUESTION_MESSAGE); // TODO 未国际化
					if (templateName == null || templateName.length() == 0)
						return;
					if (execution == null
							|| execution.getContext() == null)
						return;
					try {
						Template template = execution.getContext().getTemplate(templateName);
						templatePane.addTemplate(template);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(frame, "加载模板：\"" + templateName + "\"失败，错误信息：" + ex.getMessage()); // TODO 未国际化
					} catch (ParsingException ex) {
						JOptionPane.showMessageDialog(frame, "编译模板：\"" + templateName + "\"失败，错误信息：" + ex.getMessage()); // TODO 未国际化
					}
				}
			});
			menuFile.addSeparator();
			JMenuItem itemExit = new JMenuItem("退出(E)"); // TODO 未国际化
			itemExit.setMnemonic(KeyEvent.VK_E);
			menuFile.add(itemExit);
			itemExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					closeFrame();
				}
			});

		JMenu menuDebug = new JMenu("调试(D)"); // TODO 未国际化
		mainMenuBar.add(menuDebug);
		menuDebug.setMnemonic(KeyEvent.VK_D);
			itemStepInto = new JMenuItem("单步运行(S)"); // TODO 未国际化
			itemStepInto.setMnemonic(KeyEvent.VK_S);
			menuDebug.add(itemStepInto);
			itemStepInto.addActionListener(DebugFrame.this);

			itemStepOver = new JMenuItem("单步块运行(O)"); // TODO 未国际化
			itemStepOver.setMnemonic(KeyEvent.VK_O);
			menuDebug.add(itemStepOver);
			itemStepOver.addActionListener(DebugFrame.this);

			itemStepReturn = new JMenuItem("单步返回运行(E)"); // TODO 未国际化
			itemStepReturn.setMnemonic(KeyEvent.VK_E);
			menuDebug.add(itemStepReturn);
			itemStepReturn.addActionListener(DebugFrame.this);

			itemResume = new JMenuItem("恢复运行(R)"); // TODO 未国际化
			itemResume.setMnemonic(KeyEvent.VK_R);
			menuDebug.add(itemResume);
			itemResume.addActionListener(DebugFrame.this);

			itemResumeAll = new JMenuItem("跳过断点运行(A)"); // TODO 未国际化
			itemResumeAll.setMnemonic(KeyEvent.VK_A);
			menuDebug.add(itemResumeAll);
			itemResumeAll.addActionListener(DebugFrame.this);

			itemTerminate = new JMenuItem("终止运行(T)"); // TODO 未国际化
			itemTerminate.setMnemonic(KeyEvent.VK_T);
			menuDebug.add(itemTerminate);
			itemTerminate.addActionListener(DebugFrame.this);
		JMenu menuOut = new JMenu("输出(O)"); // TODO 未国际化
		mainMenuBar.add(menuOut);
		menuOut.setMnemonic(KeyEvent.VK_P);
			itemFlush = new JMenuItem("刷新输出(F)"); // TODO 未国际化
			itemFlush.setMnemonic(KeyEvent.VK_F);
			menuOut.add(itemFlush);
			itemFlush.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Context context = execution.getContext();
					if (context != null) {
						Writer out = context.getOut();
						if (out != null) {
							try {
								out.flush();
							} catch (IOException ioe) {
								ioe.printStackTrace();
								// ignore
							}
						}
					}
				}
			});

			itemCapture = new JCheckBoxMenuItem("捕获输出(C)"); // TODO 未国际化
			itemCapture.setMnemonic(KeyEvent.VK_C);
			itemCapture.setSelected(true);
			menuOut.add(itemCapture);
			itemCapture.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					captureOutput();
				}
			});

			JMenuItem itemClear = new JMenuItem("清除输出(L)"); // TODO 未国际化
			itemClear.setMnemonic(KeyEvent.VK_L);
			menuOut.add(itemClear);
			itemClear.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					outputPane.clearText();
				}
			});
		return mainMenuBar;
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
			captureOutput();
			frame.setVisible(true);
		}
	}

	private void captureOutput() {
		if (execution == null)
			return;
		Context context = execution.getContext();
		if (context == null)
			return;
		LocalContext root = context.getRootLocalContext();
		if (itemCapture.getModel().isSelected()) {
			OutputFilter outputFilter = root.getOutputFilter();
			if (outputFilter == outputPane)
				return ;
			outputPane.setOutputFilter(outputFilter);
			root.setOutputFilter(outputPane);
		} else {
			OutputFilter outputFilter = root.getOutputFilter();
			if (outputFilter == outputPane) {
				root.setOutputFilter(outputPane.getOutputFilter());
			}
		}
	}

	// ---- 事件处理 ----

	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		disableToolbar();
		if (execution != null) {
			if (button == stepInto || button == itemStepInto) {
				execution.stepInto();
				removeExecution(execution);
			} else if (button == stepOver || button == itemStepOver) {
				execution.stepOver();
				removeExecution(execution);
			} else if (button == stepReturn || button == itemStepReturn) {
				execution.stepReturn();
				removeExecution(execution);
			} else if (button == resume || button == itemResume) {
				execution.resume();
				removeExecution(execution);
			} else if (button == resumeAll || button == itemResumeAll) {
				execution.resumeAll();
				removeExecution(execution);
			} else  if (button == terminate || button == itemTerminate) {
				execution.terminate();
				removeExecution(execution);
			}
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
		closeFrame();
	}

	public void valueChanged(ListSelectionEvent e) {
		initDebugFrame((Execution)executionList.getSelectedValue());
	}

	private synchronized void closeFrame() {
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

	private synchronized void enableToolbar() {
		stepInto.setEnabled(true);
		stepOver.setEnabled(true);
		stepReturn.setEnabled(true);
		resume.setEnabled(true);
		resumeAll.setEnabled(true);
		terminate.setEnabled(true);
		itemStepInto.setEnabled(true);
		itemStepOver.setEnabled(true);
		itemStepReturn.setEnabled(true);
		itemResume.setEnabled(true);
		itemResumeAll.setEnabled(true);
		itemTerminate.setEnabled(true);
		itemOpen.setEnabled(true);
		itemFlush.setEnabled(true);
		if (execution != null) {
			templatePane.setElement(execution.getElement());
			contextPane.initContextPane(execution.getContext());
		}
	}

	private synchronized void disableToolbar() {
		stepInto.setEnabled(false);
		stepOver.setEnabled(false);
		stepReturn.setEnabled(false);
		resume.setEnabled(false);
		resumeAll.setEnabled(false);
		terminate.setEnabled(false);
		itemStepInto.setEnabled(false);
		itemStepOver.setEnabled(false);
		itemStepReturn.setEnabled(false);
		itemResume.setEnabled(false);
		itemResumeAll.setEnabled(false);
		itemTerminate.setEnabled(false);
		itemOpen.setEnabled(false);
		itemFlush.setEnabled(false);
		templatePane.removeElement();
		contextPane.clearContextPane();
	}

}
