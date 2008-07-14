package org.commontemplate.tools.debugger.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import org.commontemplate.core.Element;
import org.commontemplate.core.Template;
import org.commontemplate.standard.debug.Breakpoint;
import org.commontemplate.standard.debug.BreakpointEvent;
import org.commontemplate.standard.debug.BreakpointListener;
import org.commontemplate.standard.debug.DebugManager;
import org.commontemplate.tools.swing.ImageFactory;
import org.commontemplate.tools.swing.MenuBuilder;
import org.commontemplate.util.I18nMessages;

/**
 * 模板编辑面板
 *
 * @author liangfei0201@163.com
 *
 */
public class TemplateEditorPane extends JTextArea implements Border, BreakpointListener {

	// TODO 未监听BreakpointEvent在有断点去除时更新

	private static final long serialVersionUID = 1L;

	private final JPopupMenu templateViewMenu;

	private final JPopupMenu templateLineMenu;

	private final DefaultHighlighter highlighter = new DefaultHighlighter();

	private final DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
			Color.decode("0x669900"));

	private Object highlight = null;

	private PropertiesDialog propertiesDialog;

	public TemplateEditorPane(JFrame frame) {
		super();
		propertiesDialog = new PropertiesDialog(frame);
		setHighlighter(highlighter);
		templateViewMenu = new JPopupMenu();
		MenuBuilder.buildReadonlyTextMenu(this, templateViewMenu);
		templateViewMenu.add(new JPopupMenu.Separator());
		final JMenuItem propertiesItem = new JMenuItem(I18nMessages
				.getMessage("TemplateEditorPane.properties.menu.item"));
		propertiesItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				propertiesDialog.showTemplate(template, element);
			}
		});
		templateViewMenu.add(propertiesItem);

		templateLineMenu = new JPopupMenu();
		final TemplateLineMenuItem toggleBreakpoint = new TemplateLineMenuItem(
				I18nMessages
						.getMessage("TemplatePane.toggle.breakpoint.menu.item"));
		toggleBreakpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int line = toggleBreakpoint.getLine();
				if (template != null && line >= 0) {
					DebugManager.getInstance().addBreakpoint(
							new Breakpoint(template.getName(), line));
					TemplateEditorPane.this.repaint();
				}
			}
		});
		templateLineMenu.add(toggleBreakpoint);
		final TemplateLineMenuItem disableBreakpoint = new TemplateLineMenuItem(
				I18nMessages
						.getMessage("TemplatePane.disable.breakpoint.menu.item"));
		disableBreakpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int line = toggleBreakpoint.getLine();
				if (template != null && line >= 0) {
					DebugManager.getInstance().removeBreakpoint(
							new Breakpoint(template.getName(), line));
					TemplateEditorPane.this.repaint();
				}
			}
		});
		templateLineMenu.add(disableBreakpoint);

		this.setAutoscrolls(true);
		this.setBorder(this);
		this.setEditable(false);
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (template == null)
					return;
				if (me.getModifiers() == MouseEvent.META_MASK) {
					int x = me.getX();
					int y = me.getY();
					if (x < 30) {
						int line = TemplateEditorPane.this.getYLine(me.getY());
						if (line >= 0 && line < TemplateEditorPane.this.getLineCount()) {
							Breakpoint breakpoint = new Breakpoint(template
									.getName(), line);
							boolean isEable = DebugManager.getInstance()
									.hasBreakpoint(breakpoint);
							toggleBreakpoint.setEnabled(! isEable);
							toggleBreakpoint.setLine(line);
							disableBreakpoint.setEnabled(isEable);
							disableBreakpoint.setLine(line);
							templateLineMenu
									.show(TemplateEditorPane.this, x, y);
						}
					} else {
						propertiesItem.setEnabled(template != null);
					}
				} else if (me.getClickCount() >= 2 && me.getX() < 30) {
					int line = TemplateEditorPane.this.getYLine(me.getY());
					if (line >= 0 && line < TemplateEditorPane.this.getLineCount()) {
						DebugManager.getInstance().changeBreakpoint(
								new Breakpoint(template.getName(), line));
						TemplateEditorPane.this.repaint();
					}
				}
			}
		});
		DebugManager.getInstance().addBreakpointListener(this);
	}

	private Template template;

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		if (template == null)
			return;
		this.template = template;
		setText(template.getSource());
	}

	private Element element;

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		if (element == null) {
			removeElement();
			return;
		}
		Template template = element.getTemplate();
		if (template != null)
			setTemplate(template);
		this.element = element;
		int begin = element.getLocation().getBegin().getOffset();
		int end = element.getLocation().getEnd().getOffset();
		try {
			highlight = highlighter.addHighlight(begin, end, painter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		this.scrollRectToVisible(getHighlightRectangle());
	}

	public void removeElement() {
		this.element = null;
		if (highlight != null)
			highlighter.removeHighlight(highlight);
	}

	private Rectangle getHighlightRectangle() {
		super.getColumnWidth();
		Insets margin = super.getMargin();
		int height = getRowHeight();
		int width = getColumnWidth();
		int row = element.getLocation().getBegin().getLine();
		int col = element.getLocation().getBegin().getColumn();
		return new Rectangle(width * col + margin.left, height * row
				+ margin.right, width, height);
	}

	/**
	 * 获取Y轴坐标所在行
	 *
	 * @param y
	 *            Y轴坐标
	 * @return 行号
	 */
	private int getYLine(int y) {
		int top = getLineTopMargin();
		int height = getRowHeight();
		int loc = y - top;
		if (loc <= 0)
			return 0;
		int mod = loc % height;
		int line = (loc - mod) / height;
		if (mod > 0)
			line++;
		return line;
	}

	/**
	 * 获取行Y轴坐标
	 *
	 * @param 行号
	 * @return Y轴坐标
	 */
	private int getLineY(int line) {
		int top = getLineTopMargin();
		int height = getRowHeight();
		return line * height + top;
	}

	private int getLineTopMargin() {
		FontMetrics fontMetrics = getFontMetrics(getFont());
		Insets margin = super.getMargin();
		return fontMetrics.getAscent() + margin.top;
	}

	private final Image breakpointImage = ImageFactory.getImage(this.getClass()
			.getPackage().getName().replace('.', '/')
			+ "/breakpoint.gif");

	public Insets getBorderInsets(Component c) {
		return new Insets(0, 30, 0, 0);
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		if (width < 30)
			return;
		int lineX = x + 11;
		g.setColor(Color.decode("0xECE9D8"));
		g.fillRect(x, y, 10, height);
		g.setColor(Color.decode("0xDCDCDC"));
		g.drawLine(x + 29, 0, x + 29, height);
		g.setColor(Color.GRAY);
		TemplateEditorPane templatePane = (TemplateEditorPane) c;
		g.setFont(c.getFont());
		Collection breakpoints = null;
		Template template = templatePane.getTemplate();
		if (template != null)
			breakpoints = DebugManager.getInstance().getBreakpoints(
					template.getName());
		for (int line = 0, count = templatePane.getLineCount(); line < count; line++) {
			int lineY = templatePane.getLineY(line);
			if (breakpoints != null && isBreakpoint(breakpoints, line))
				g.drawImage(breakpointImage, x, lineY - 9, c);
			g.drawString(String.valueOf(line + 1), lineX, lineY);
		}
	}

	private boolean isBreakpoint(Collection breakpoints, int line) {
		for (Iterator iterator = breakpoints.iterator(); iterator.hasNext();) {
			Breakpoint breakpoint = (Breakpoint) iterator.next();
			if (breakpoint.getLine() == line)
				return true;
		}
		return false;
	}

	public boolean isBorderOpaque() {
		return false;
	}


	public void onBreakpointAdded(BreakpointEvent event) {
		repaint();
	}

	public void onBreakpointRemoved(BreakpointEvent event) {
		repaint();
	}

	protected void finalize() throws Throwable {
		super.finalize();
		DebugManager.getInstance().removeBreakpointListener(this);
	}

}