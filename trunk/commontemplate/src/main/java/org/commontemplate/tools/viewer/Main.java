package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.IOException;

public class Main {

	private static final TemplateGenerator generator = new TemplateGenerator();

	private static TemplateViewer viewer = new TemplateViewer(generator);

	private static final TemplateDebugger debugger = new TemplateDebugger(viewer);

	public static void main(String[] args) {
		try {
			if (args == null || args.length == 0) {
				MainUI.showSettings();
			} else if (args.length == 1) {
				run("v", args[0]);
			} else if (args.length == 2) {
				run(args[0], args[1]);
			}
		} catch (Exception e) {
			MainUI.showException(e);
		}
	}

	public static void run(String command, String file) throws Exception {
		File sourceFile = getSourceFile(file);
		if ("d".equalsIgnoreCase(command)) {
			debug(sourceFile);
		} else if ("g".equalsIgnoreCase(command)) {
			generate(sourceFile);
		} else {
			view(sourceFile);
		}
	}

	public static File getSourceFile(String sourcePath) throws Exception {
		// 前置条件
		if (sourcePath == null || sourcePath.length() == 0)
			throw new IOException("source file path is null!");
		File sourceFile = new File(sourcePath).getCanonicalFile(); // 源文件
		if (! sourceFile.exists())
			throw new IOException("source file not exists! path:" + sourcePath);
		return sourceFile;
	}

	public static void view(File sourceFile) throws Exception {
		viewer.view(sourceFile);
	}

	public static void generate(File sourceFile) throws Exception {
		generator.generate(sourceFile);
	}

	public static void debug(File sourceFile) throws Exception {
		debugger.debug(sourceFile);
	}

}
