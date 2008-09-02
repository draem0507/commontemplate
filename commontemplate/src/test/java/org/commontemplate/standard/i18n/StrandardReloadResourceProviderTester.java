package org.commontemplate.standard.i18n;

import java.io.FileWriter;
import java.util.Locale;

import junit.framework.TestCase;
/**
 * StrandardReloadResourceProvider 的测试。
 * @author YanRong
 *
 */
public class StrandardReloadResourceProviderTester extends TestCase {

	private static final int EXT_WAIT_TIMES = 1000;
	private ReloadResourceProvider resourceProvider;
	private Locale locale = Locale.CHINESE;
	
	private static final String KEY = "key"; 
	private static final String KEY2 = "key2";
	
	private static final String OLD_CONTENT = "old_content"; 
	private static final String NEW_CONTENT_1 = "new_content_1";
	private static final String NEW_CONTENT_2 = "new_content_2";
	
	private static final String ROOT_PROPERTY_FILE = "integration/i18n/StrandardReloadResourceProviderTester.properties";
	private static final String ZH_PROPERTY_FILE = "integration/i18n/StrandardReloadResourceProviderTester_zh.properties";
	
	private static final String BASE_NAME = "integration/i18n/StrandardReloadResourceProviderTester";
	private static final String SUB_BASE_NAME = "integration/i18n/StrandardReloadResourceProviderTester_zh";
	
	protected void setUp() throws Exception {
		
		resourceProvider = new StandardReloadResourceProvider();
		resourceProvider.setResourceLocale(locale);
		resourceProvider.setResourceBaseName(BASE_NAME);
		resourceProvider.setRefreshInterval(3000);
		resourceProvider.clearCache();
		
		super.setUp();
	}
	
	/**
	 * 重载属性文件失败的测试。<br>
	 * @condition
	 * 条件<br>
	 * 读取属性文件的间隔时间小于设定刷新时间<br>
	 * @result
	 * 结果<br>
	 * 读取的是没有刷新的属性文件
	 * @throws Exception
	 */
	public void testReloadFail() throws Exception{
		
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);		
		assertEquals(OLD_CONTENT, resourceProvider.getString(KEY));
		
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		assertEquals(OLD_CONTENT, resourceProvider.getString(KEY));
	}
	
	/**
	 * 重载属性文件成功的测试。<br>
	 * @condition
	 * 条件<br>
	 * 读取属性文件的间隔时间大于设定刷新时间<br>
	 * @result
	 * 结果<br>
	 * 读取的是刷新后的属性文件
	 * @throws Exception
	 */
	public void testReloadSuccess() throws Exception{
		
		createResourceFile(ROOT_PROPERTY_FILE, KEY, OLD_CONTENT);
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		assertEquals(NEW_CONTENT_1, resourceProvider.getString(KEY));
				
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);		
		assertEquals(NEW_CONTENT_2, resourceProvider.getString(KEY));
	}
	
	/**
	 * 重载属性文件成功的测试。<br>
	 * @condition
	 * 条件<br>
	 * 读取属性文件的间隔时间大于设定刷新时间<br>
	 * @result
	 * 结果<br>
	 * 读取的是刷新后的属性文件
	 * @throws Exception
	 */
	public void testReloadSuccess2() throws Exception{
		
		createResourceFile(ROOT_PROPERTY_FILE, KEY, OLD_CONTENT);
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		assertEquals(NEW_CONTENT_1, resourceProvider.getString(KEY));
				
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);		
		assertEquals(NEW_CONTENT_2, resourceProvider.getString(KEY));
		
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);
		Thread.sleep(((StandardReloadResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		assertEquals(OLD_CONTENT, resourceProvider.getString(KEY));
	}
	
	/**
	 * 重载属性文件成功的测试，测试当存在2个属性文件的情况。<br>
	 * @condition
	 * 条件<br>
	 * 读取属性文件的间隔时间大于设定刷新时间<br>
	 * @result
	 * 结果<br>
	 * 读取的是刷新后的属性文件
	 * @throws Exception
	 */
	public void testReloadSuccess3() throws Exception {
		// 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY, OLD_CONTENT);
		// 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
			
		assertEquals(NEW_CONTENT_1, resourceProvider.getString(KEY));
		
		// 修改 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		assertEquals(NEW_CONTENT_1, resourceProvider.getString(KEY));
		
		// 修改 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);
		Thread.sleep(((StandardReloadResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		resourceProvider.setResourceBaseName(SUB_BASE_NAME);
		assertEquals(OLD_CONTENT, resourceProvider.getString(KEY));
	}
	
	public void testReloadParentProperties() throws Exception{
		
		// 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, OLD_CONTENT);
		// 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		assertEquals(OLD_CONTENT, resourceProvider.getString(KEY2));
		
		// 修改父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		assertEquals(NEW_CONTENT_2, resourceProvider.getString(KEY2));
	}
		
	private void createResourceFile(String fileName, String key, String content) throws Exception {
		FileWriter file = null;
		
		try {
			file = new FileWriter(StrandardReloadResourceProviderTester.class.getClassLoader().getResource(fileName).getFile());
			file.write(key + "=" + content); 
		} finally {
			if(file != null) {
				file.close();
			}
		}
	}
	
}
