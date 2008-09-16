package concurrent;

import junit.framework.TestCase;

public class ConcurrentTester extends TestCase {

	public void testConcurrent() {
		Thread th = new Thread(new Runnable(){
			public void run() {
			}
		});
	}

}
