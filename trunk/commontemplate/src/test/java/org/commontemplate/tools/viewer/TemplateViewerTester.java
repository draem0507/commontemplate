package org.commontemplate.tools.viewer;

import java.io.File;


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
			viewer.view(new File("C:\\Documents and Settings\\lf\\桌面\\test\\test_xml.ctl"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
