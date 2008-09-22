package org.commontemplate.tools.viewer;

import java.io.File;

import org.commontemplate.standard.debug.Breakpoint;
import org.commontemplate.standard.debug.DebugManager;
import org.commontemplate.util.UrlUtils;

public class TemplateDebugger {

	private final TemplateGenerator templateGenerator;

	public TemplateDebugger(TemplateGenerator templateGenerator) {
		this.templateGenerator = templateGenerator;
	}

	public void debug(File sourceFile) throws Exception {
		String sourcePath = sourceFile.getCanonicalPath();
		sourcePath = UrlUtils.cleanUrl(sourcePath);
		DebugManager.getInstance().addBreakpoint(new Breakpoint(sourcePath, 0));
		templateGenerator.generateDefault(sourceFile);
	}

}