package org.commontemplate.core.event;

import org.commontemplate.core.Element;

public class ElementPopedEvent extends ElementStackEvent {

	private static final long serialVersionUID = 1L;

	public ElementPopedEvent(Object source, Element previous, Element current) {
		super(source, previous, current);
	}

}