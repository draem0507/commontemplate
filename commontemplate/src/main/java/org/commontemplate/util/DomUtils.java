package org.commontemplate.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DomUtils {

	public static Document getDocument(String dataSource) throws Exception {
		return getDocument(new ByteArrayInputStream(dataSource.getBytes()));
	}

	public static Document getDocument(File dataFile) throws Exception {
		return getDocument(new FileInputStream(dataFile));
	}

	public static Document getDocument(InputStream dataInputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(dataInputStream);
		return document;
	}

}
