/**
 *
 */
package org.commontemplate.ext.coat.jericho;

import java.io.InputStreamReader;

import org.commontemplate.ext.coat.common.Segment;

import junit.framework.TestCase;
import au.id.jericho.lib.html.Source;

/**
 * @author GL
 * @since 2008-4-5 上午02:04:10
 */
public class JerichoDocumentTester extends TestCase {

	JerichoDocument document;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		Source source = new Source(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("integration/ext/coat/test01.ctl"),"UTF-8"));
		document = new JerichoDocument(source);
		super.setUp();
	}

	/**
	 * Test method for {@link org.commontemplate.ext.coat.jericho.JerichoDocument#getTopSegments()}.
	 */
	public void testGetTopSegments() {
		assertNotNull(document.getTopSegments());
		Segment[] segments = document.getTopSegments();
		for(int i=0;i<segments.length;i++){
			assertNotNull(segments[i]);
			assertNotNull(segments[i].getText());
		}
	}

}
