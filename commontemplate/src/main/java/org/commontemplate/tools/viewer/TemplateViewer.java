package org.commontemplate.tools.viewer;

import java.io.File;

import org.commontemplate.util.UrlUtils;

public class TemplateViewer {

	private final TemplateGenerator generator;

	public TemplateViewer(TemplateGenerator generator) {
		this.generator = generator;
	}

	public void view(File sourceFile) throws Exception {
		File targetFile = generator.generateDefault(sourceFile);
		Runtime.getRuntime().exec("cmd /c start " + UrlUtils.cleanWindowsPath(targetFile.getCanonicalPath()));
	}

}
