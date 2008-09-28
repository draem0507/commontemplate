package org.commontemplate.core;

import java.util.List;

public interface ElementStack {

	public void pushElement(Element element);

	public void popElement();

	public Element getCurrentElement();

	public Element findElement(String name);

	public List getElementStackValues();

	public void clearElements();

}
