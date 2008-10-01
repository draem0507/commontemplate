package org.commontemplate.tools.viewer;

import java.io.File;
import java.io.IOException;

import org.commontemplate.tools.generator.TemplateGenerator;

public class Main {

	public static void main(String[] args) {
		try {
			if (args == null || args.length == 0) {
				MainUI.showSettings();
			} else if (args.length == 1) {
				run("", args[0]);
			} else if (args.length == 2) {
				run(args[0], args[1]);
			}
		} catch (Throwable e) {
			MainUI.showException(e);
		}
	}

	public static void run(String command, String file) throws Exception {
		File sourceFile = getSourceFile(file);
		TemplateGenerator templateGenerator = new TemplateGenerator(sourceFile);
		if (sourceFile.isDirectory()) {
			new DirectoryGeneratorUI(templateGenerator).generate(sourceFile);
		} else {
			if ("d".equalsIgnoreCase(command)) {
				templateGenerator.generateFile(sourceFile, templateGenerator.getDefaultTargetFile(sourceFile), null, null, true, true);
			} else if ("v".equalsIgnoreCase(command)) {
				templateGenerator.generateFile(sourceFile, templateGenerator.getDefaultTargetFile(sourceFile), null, null, false, true);
			} else {
				new FileGeneratorUI(templateGenerator).generate(sourceFile);
			}
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

}
