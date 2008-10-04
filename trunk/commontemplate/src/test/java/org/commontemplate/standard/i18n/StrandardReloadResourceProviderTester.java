package org.commontemplate.standard.i18n;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import junit.framework.TestCase;
/**
 * StrandardReloadResourceProvider 的测试。
 * @author YanRong
 *
 */
public class StrandardReloadResourceProviderTester extends TestCase {

	private static final int EXT_WAIT_TIMES = 1000;
	private ReloadableResourceProvider resourceProvider;
	private Locale locale = Locale.CHINESE;
	
	private static final String KEY = "key"; 
	private static final String KEY2 = "key2";
	
	private static final String OLD_CONTENT = "old_content"; 
	private static final String NEW_CONTENT_1 = "new_content_1";
	private static final String NEW_CONTENT_2 = "new_content_2";
	private static final String NEW_CONTENT_3 = "UTF-8编码的内容3";
	
	private static final String ROOT_PROPERTY_FILE = "integration/i18n/StrandardReloadResourceProviderTester.properties";
	private static final String ZH_PROPERTY_FILE = "integration/i18n/StrandardReloadResourceProviderTester_zh.properties";
	
	private static final String BASE_NAME = "integration/i18n/StrandardReloadResourceProviderTester";
	private static final String SUB_BASE_NAME = "integration/i18n/StrandardReloadResourceProviderTester_zh";
	
	private Map extInfoMap = new HashMap();
	
	protected void setUp() throws Exception {
		
		resourceProvider = new StandardReloadableResourceProvider();		
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
		
		createResourceFile(ZH_PROPERTY_FILE, KEY2, OLD_CONTENT);
		
		createResourceFile(ROOT_PROPERTY_FILE, KEY, OLD_CONTENT);		
		assertEquals(OLD_CONTENT, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
		
		createResourceFile(ROOT_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		assertEquals(OLD_CONTENT, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
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
		assertEquals(NEW_CONTENT_1, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
				
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);		
		assertEquals(NEW_CONTENT_2, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
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
		assertEquals(NEW_CONTENT_1, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
				
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);		
		assertEquals(NEW_CONTENT_2, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
		
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		assertEquals(OLD_CONTENT, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
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
			
		assertEquals(NEW_CONTENT_1, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
		
		// 修改 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		assertEquals(NEW_CONTENT_1, resourceProvider.getObject(BASE_NAME, locale, KEY, extInfoMap));
		
		// 修改 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, OLD_CONTENT);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES);
		assertEquals(OLD_CONTENT, resourceProvider.getObject(SUB_BASE_NAME, locale, KEY, extInfoMap));
	}
	
	public void testReloadParentProperties() throws Exception{
		
		// 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, OLD_CONTENT);
		// 子properties
		createResourceFile(ZH_PROPERTY_FILE, KEY, NEW_CONTENT_1);
		assertEquals(OLD_CONTENT, resourceProvider.getObject(BASE_NAME, locale, KEY2, extInfoMap));
		
		// 修改父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, NEW_CONTENT_2);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES + 2000);
		assertEquals(NEW_CONTENT_2, resourceProvider.getObject(BASE_NAME, locale, KEY2, extInfoMap));
	}
	
	public void testReloadPropertiesWithEncoding() throws Exception {
		
		// 父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, OLD_CONTENT);
		
		// 设置编码格式为 utf-8
		extInfoMap.put(StandardReloadableResourceProvider.MAP_ENCODING_KEY, "UTF-8");
		
		assertEquals(OLD_CONTENT, resourceProvider.getObject(BASE_NAME, locale, KEY2, extInfoMap));
		
		// 修改父properties
		createResourceFile(ROOT_PROPERTY_FILE, KEY2, NEW_CONTENT_3);
		Thread.sleep(((StandardReloadableResourceProvider) resourceProvider).getRefreshInterval() + EXT_WAIT_TIMES + 2000);
		assertEquals(NEW_CONTENT_3, resourceProvider.getObject(BASE_NAME, locale, KEY2, extInfoMap));
	}
		
	private void createResourceFile(String fileName, String key, String content) throws Exception {
		OutputStreamWriter os = new OutputStreamWriter(
				new FileOutputStream(StrandardReloadResourceProviderTester.class.getClassLoader().getResource(fileName).getFile()), 
				"UTF-8");
		try {
			os.write(key + "=" + content);
		} finally {
			if(os != null) {
				os.close();
			}
		}
	}
	
}
