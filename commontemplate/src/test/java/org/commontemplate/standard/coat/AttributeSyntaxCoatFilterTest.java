package org.commontemplate.standard.coat;

import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.commontemplate.tools.PropertiesConfigurationLoader;
import org.commontemplate.util.IOUtils;

/**
 * @author GL
 * @since 2008-4-5 上午03:26:00
 */
public class AttributeSyntaxCoatFilterTest extends TestCase {

	private AttributeSyntaxCoatFilter coatFilter;

	protected void setUp() throws Exception {
		coatFilter = new AttributeSyntaxCoatFilter();
		coatFilter.setDirectiveHandlerProvider(PropertiesConfigurationLoader
				.loadStandardConfiguration().getDirectiveHandlerProvider());
	}

	public void testFilter() throws Exception {
		long start = System.currentTimeMillis();
		Reader filted = coatFilter.filter(new InputStreamReader(getClass()
				.getClassLoader().getResourceAsStream(
						"integration/ext/coat/test01.ctl"), "UTF-8"));
		String expect = IOUtils.readToString(new InputStreamReader(getClass()
				.getClassLoader().getResourceAsStream(
						"integration/ext/coat/test01.out"), "UTF-8"));
		String actual = IOUtils.readToString(filted);
		assertEquals(expect, actual);
		long end = System.currentTimeMillis();
		long time = end - start;
		assertTrue(time < 500);
	}

}
