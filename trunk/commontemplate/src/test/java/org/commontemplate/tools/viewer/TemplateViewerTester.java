package org.commontemplate.tools.viewer;

import java.io.File;

import org.commontemplate.tools.generator.TemplateGenerator;

public class TemplateViewerTester {

	public static void main(String[] args) {
		final TemplateViewer viewer = new TemplateViewer(new TemplateGenerator());
		/*new Thread(new Runnable() {
			public void run() {
				try {
					viewer.view(new File("C:\\Documents and Settings\\lf\\桌面\\test\\test_json.ctl"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();*/
		try {
			viewer.view(new File("C:\\Documents and Settings\\lf\\桌面\\test\\test_yaml.ctl"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}