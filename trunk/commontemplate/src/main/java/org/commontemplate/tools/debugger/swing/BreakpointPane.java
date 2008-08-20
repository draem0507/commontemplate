package org.commontemplate.tools.debugger.swing;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.commontemplate.standard.debug.Breakpoint;
import org.commontemplate.standard.debug.BreakpointEvent;
import org.commontemplate.standard.debug.BreakpointListener;
import org.commontemplate.standard.debug.DebugManager;
import org.commontemplate.tools.swing.MenuBuilder;

/**
 * 断点面板，显示所以已注册的断点
 *
 * @author liangfei0201@163.com
 *
 */
public class BreakpointPane extends JScrollPane implements BreakpointListener {

	// TODO 未实现断点的删除/禁用等功能, 以及复制功能

	private static final long serialVersionUID = 1L;

	private final JTree breakpointTree = new JTree();

	public BreakpointPane() {
		MenuBuilder.buildReadonlyTreeMenu(breakpointTree);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getViewport().setView(breakpointTree);
		this.getViewport().setBackground(Color.white);
		buildBreakpointTree();
		DebugManager.getInstance().addBreakpointListener(this);
	}

	public void onBreakpointAdded(BreakpointEvent event) {
		buildBreakpointTree();
	}

	public void onBreakpointRemoved(BreakpointEvent event) {
		buildBreakpointTree();
	}

	private void buildBreakpointTree() {
		Map map = new TreeMap();
		for (Iterator iterator = DebugManager.getInstance().getBreakpoints().iterator();iterator.hasNext();) {
			Breakpoint breakpoint = (Breakpoint)iterator.next();
			Set set = (Set)map.get(breakpoint.getTemplateName());
			if (set == null) {
				set = new TreeSet();
				map.put(breakpoint.getTemplateName(), set);
			}
			set.add(new Integer(breakpoint.getLine()));
		}

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("breakpoints");
		for (Iterator mapIterator = map.entrySet().iterator(); mapIterator.hasNext();) {
			Map.Entry entry = (Map.Entry)mapIterator.next();
			DefaultMutableTreeNode templateNode = new DefaultMutableTreeNode(entry.getKey());
			rootNode.add(templateNode);
			for (Iterator setIterator = ((Set)entry.getValue()).iterator(); setIterator.hasNext();) {
				Integer line = (Integer)setIterator.next();
				DefaultMutableTreeNode lineNode = new DefaultMutableTreeNode("line: " + (line.intValue() + 1));
				templateNode.add(lineNode);
			}
		}
		breakpointTree.setModel(new DefaultTreeModel(rootNode));
	}

	protected void finalize() throws Throwable {
		super.finalize();
		DebugManager.getInstance().removeBreakpointListener(this);
	}

}
