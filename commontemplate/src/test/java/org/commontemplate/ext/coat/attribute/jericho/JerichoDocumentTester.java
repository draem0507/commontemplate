/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.io.InputStreamReader;
import java.util.*;

import org.commontemplate.ext.coat.attribute.Segment;

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
	 * Test method for {@link org.commontemplate.ext.coat.attribute.jericho.JerichoDocument#getTopSegments()}.
	 */
	public void testGetTopSegments() {
		assertNotNull(document.getTopSegments());
//		Segment[] segments = document.getTopSegments();
		List segements = document.getTopSegments();
//		for(int i=0;i<segments.length;i++){
		for(Iterator i = segements.iterator();i.hasNext();){
			Segment s = (Segment) i.next();
			assertNotNull(s);
			assertNotNull(s.getText());
		}
	}

}
