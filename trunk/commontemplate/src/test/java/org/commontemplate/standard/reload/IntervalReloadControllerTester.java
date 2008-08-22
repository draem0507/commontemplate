package org.commontemplate.standard.reload;

import junit.framework.TestCase;
/**
 * IntervalReloadController的测试。
 * @author Yan Rong
 *
 */
public class IntervalReloadControllerTester extends TestCase {

	private IntervalReloadController reloadController;
	private String templateName = "test";
	private long interval = 3000;
	
	public void setUp() {
		
		reloadController = new IntervalReloadController();
	}
	
	/**
	 * 测试shouldReload方法。
	 * @condition
	 * 条件<br>
	 * 参数为模板的名字。
	 * @result
	 * 结果<br>
	 * 如果两次的间隔时间小于 interval(3000) ，那么就返回 false，
	 * 否则返回 true.
	 * @throws Exception
	 */
	public void testShouldReload() throws InterruptedException{	
		
		// 设置检查时间
		reloadController.setModificationCheckInterval(interval);
		
		boolean b = reloadController.shouldReload(templateName);
		
		assertTrue(b);
		
		// 延迟
		Thread.sleep(100);
		
		b = reloadController.shouldReload(templateName);
		
		assertFalse(b);
		
		// 延迟
		Thread.sleep(3001);
		
		b = reloadController.shouldReload(templateName);
		
		assertTrue(b);
	}
}
