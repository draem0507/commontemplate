/**
 *
 */
package org.commontemplate.ext.coat.jericho;

import java.io.*;

import org.commontemplate.ext.coat.common.*;

import au.id.jericho.lib.html.Source;

/**
 * @author GL
 * @since 2008-4-5 上午01:55:21
 */
public class JerichoAttributeCoatFilter extends AbstractAttributeFilter{

	protected Document getDocument(Reader reader) throws IOException {
		Source source = new Source(reader);
		return new JerichoDocument(source);
	}

}
