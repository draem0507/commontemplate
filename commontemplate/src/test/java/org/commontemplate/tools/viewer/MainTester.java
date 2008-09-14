package org.commontemplate.tools.viewer;

public class MainTester {

	public static void main(String[] args) {
		try {
			/*new Thread(new Runnable() {
				public void run() {
					try {
						Main.run("d", "F:\\CommonTemplate\\test\\test_yaml.ctl");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();*/
			Main.run("v", "F:\\CommonTemplate\\test\\xxx.ctl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
