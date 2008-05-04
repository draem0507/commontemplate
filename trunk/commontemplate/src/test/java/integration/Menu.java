package integration;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String name;

	private List children;

	public Menu() {

	}

	public Menu(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public Menu addChild(Menu child) {
		if (null == children)
			children = new ArrayList();
		children.add(child);
		return this;
	}

}
