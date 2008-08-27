package org.commontemplate.standard.i18n;

import java.io.FileWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import junit.framework.TestCase;
/**
 * ReloadablePropertiesResourceBundleProvider 的测试。
 * @author YanRong
 *
 */
public class ReloadablePropertiesResourceBundleProviderTester extends TestCase {

	private ResourceBundleProvider resourceBundleProvider;
	private Locale locale = Locale.CHINESE;
	
	private static final String KEY = "key"; 
	private static final String KEY2 = "key2";
	
	private static final String OLD_CONTENT = "old_content"; 
	private static final String NEW_CONTENT_1 = "new_content_1";
	private static final String NEW_CONTENT_2 = "new_content_2";
	
	private static final String ROOT_PROPERTY_FILE = "integration/i18n/ReloadablePropertiesResourceBundleProviderTester.properties";
	private static final String ZH_PROPERTY_FILE = "integration/i18n/ReloadablePropertiesResourceBundleProviderTester_zh.properties";
	
	private static final String BASE_NAME = "integration/i18n/ReloadablePropertiesResourceBundleProviderTester";
	private static final String SUB_BASE_NAME = "integration/i18n/ReloadablePropertiesResourceBundleProviderTester_zh";
	
	protected void setUp() throws Exception {
		
		resourceBundleProvider = new ReloadablePropertiesResourceBundleProvider();
		
		((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).setBasename(BASE_NAME);
		((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).setRefreshInterval(3000);
		((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).clearCache();
		
		
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
		ResourceBundle bundle = resourceBundleProvider.getResourceBundle(locale);		
		assertEquals(OLD_CONTENT, bundle.getString(KEY));
		
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(OLD_CONTENT, bundle.getString(KEY));
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
		ResourceBundle bundle = resourceBundleProvider.getResourceBundle(locale);		
		assertEquals(NEW_CONTENT_1, bundle.getString(KEY));
				
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(
				((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).getRefreshInterval()
					+100);		
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(NEW_CONTENT_2, bundle.getString(KEY));
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
		ResourceBundle bundle = resourceBundleProvider.getResourceBundle(locale);		
		assertEquals(NEW_CONTENT_1, bundle.getString(KEY));
				
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(
				((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).getRefreshInterval()
					+100);		
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(NEW_CONTENT_2, bundle.getString(KEY));
		
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);
		Thread.sleep(
				((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).getRefreshInterval()
					+100);
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(OLD_CONTENT, bundle.getString(KEY));
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
		
		ResourceBundle bundle = resourceBundleProvider.getResourceBundle(locale);		
		assertEquals(NEW_CONTENT_1, bundle.getString(KEY));
		
		// 修改 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(
				((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).getRefreshInterval()
					+100);
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(NEW_CONTENT_1, bundle.getString(KEY));
		
		// 修改 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);
		Thread.sleep(
				((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).getRefreshInterval()
					+100);
		((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).setBasename(SUB_BASE_NAME);
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(OLD_CONTENT, bundle.getString(KEY));
	}
	
	public void testReloadParentProperties() throws Exception{
		
		// 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, OLD_CONTENT);
		// 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		ResourceBundle bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(OLD_CONTENT, bundle.getString(KEY2));
		
		// 修改父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, NEW_CONTENT_2);
		Thread.sleep(
				((ReloadablePropertiesResourceBundleProvider) resourceBundleProvider).getRefreshInterval()
					+100);
		bundle = resourceBundleProvider.getResourceBundle(locale);
		assertEquals(NEW_CONTENT_2, bundle.getString(KEY2));
	}
		
	private void createResourceFile(String fileName, String key, String content) throws Exception {
		FileWriter file = null;
		
		try {
			file = new FileWriter(ReloadablePropertiesResourceBundleProviderTester.class.getClassLoader().getResource(fileName).getFile());
			file.write(key + "=" + content); 
		} finally {
			if(file != null) {
				file.close();
			}
		}
	}
	
}
