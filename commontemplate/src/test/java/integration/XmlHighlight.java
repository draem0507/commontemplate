package integration;

import java.io.IOException;

import org.commontemplate.standard.directive.filter.code.XmlCodeFilter;

public class XmlHighlight {

	public static void main(String[] args) {
		try {
			System.out.println(new XmlCodeFilter().filter(FileContentReader.getResultContent("integration/xml.html")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
