package org.commontemplate.core.event;

import org.commontemplate.core.Element;

public class ElementPushedEvent extends ElementStackEvent {

	private static final long serialVersionUID = 1L;

	public ElementPushedEvent(Object source, Element previous, Element current) {
		super(source, previous, current);
	}

}