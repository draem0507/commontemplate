/**
 *
 */
package org.commontemplate.ext.coat.attribute.jericho;

import java.io.IOException;
import java.io.Reader;

import org.commontemplate.ext.coat.attribute.AbstractAttributeFilter;
import org.commontemplate.ext.coat.attribute.Document;

import au.id.jericho.lib.html.Source;

/**
 * @author GL
 * @since 2008-4-5 上午01:55:21
 */
public class JerichoAttributeCoatFilter extends AbstractAttributeFilter {

	protected Document getDocument(Reader reader) throws IOException {
		Source source = new Source(reader);
		return new JerichoDocument(source);
	}

}
