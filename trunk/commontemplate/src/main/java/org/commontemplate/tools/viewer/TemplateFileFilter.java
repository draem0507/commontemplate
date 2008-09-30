package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.FileFilter;

public class TemplateFileFilter implements FileFilter {

	private String suffix;

	public TemplateFileFilter(String suffix) {
		this.suffix = filterSuffix(suffix);
	}

	private static String filterSuffix(String suffix) {
		if (suffix == null)
			return ".ctl";
		suffix = suffix.trim();
		if (suffix.length() == 0)
			return ".ctl";
		if (! suffix.startsWith("."))
			return "." + suffix;
		return suffix;
	}

	public boolean accept(File file) {
		return file != null && ! file.isHidden()
				&& (file.isDirectory() || file.getName().endsWith(suffix));
	}

}
