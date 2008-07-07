package org.commontemplate.tools.swing;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class MenuList extends JList {

	private static final long serialVersionUID = 1L;

	public MenuList() {
		super();
		init();
	}

	public MenuList(ListModel dataModel) {
		super(dataModel);
		init();
	}

	public MenuList(Object[] listData) {
		super(listData);
		init();
	}

	public MenuList(Vector listData) {
		super(listData);
		init();
	}

	private void init() {

	}

}
