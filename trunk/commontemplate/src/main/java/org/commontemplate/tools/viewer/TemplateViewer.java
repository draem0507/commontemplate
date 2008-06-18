package org.commontemplate.tools.viewer;

import java.io.File;

import org.commontemplate.tools.generator.TemplateGenerator;
import org.commontemplate.util.UrlCleaner;

public class TemplateViewer {

	private final TemplateGenerator generator;

	public TemplateViewer(TemplateGenerator generator) {
		this.generator = generator;
	}

	public void view(File sourceFile) throws Exception {
		String target = generator.generate(sourceFile);
		Runtime.getRuntime().exec("cmd /c start " + UrlCleaner.cleanWindowsPath(target));
	}

}
