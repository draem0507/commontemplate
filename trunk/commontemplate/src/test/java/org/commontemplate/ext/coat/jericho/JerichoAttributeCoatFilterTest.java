/**
 *
 */
package org.commontemplate.ext.coat.jericho;

import java.io.*;

import org.commontemplate.util.IOUtils;

import junit.framework.TestCase;

/**
 * @author GL
 * @since 2008-4-5 上午03:26:00
 */
public class JerichoAttributeCoatFilterTest extends TestCase {
	JerichoAttributeCoatFilter coatFilter;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		coatFilter = new JerichoAttributeCoatFilter();
	}

	/**
	 * Test method for {@link org.commontemplate.ext.coat.common.AbstractAttributeFilter#filter(java.io.Reader)}.
	 * @throws Exception
	 */
	public void testFilter() throws Exception {
		long start = System.currentTimeMillis();
		Reader filted = coatFilter.filter(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("integration/ext/coat/test01.ctl"),"UTF-8"));
		String expect = IOUtils.readToString(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("integration/ext/coat/test01.out"),"UTF-8"));
		assertEquals(expect, IOUtils.readToString(filted));
		long end = System.currentTimeMillis();
		System.out.println("attribute coat cost:"+(end-start) +" ms");
	}

}
