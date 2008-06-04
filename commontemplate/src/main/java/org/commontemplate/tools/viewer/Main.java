package org.commontemplate.tools.viewer;

public class Main {

	public static void main(String[] args) {
		if (args == null || args.length == 0)
			return;
		TemplateViewer viewer = new TemplateViewer();
		for (int i = 0; i < args.length; i ++) {
			viewer.view(args[0]);
		}
	}

}
