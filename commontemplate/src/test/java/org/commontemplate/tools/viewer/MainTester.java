package org.commontemplate.tools.viewer;

public class MainTester {

	public static void main(String[] args) {
		try {
			/*new Thread(new Runnable() {
				public void run() {
					try {
						Main.run("d", "C:\\Documents and Settings\\lf\\桌面\\test\\test_yaml.ctl");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();*/
			Main.run("d", "C:\\Documents and Settings\\lf\\桌面\\test\\test_xml.ctl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
