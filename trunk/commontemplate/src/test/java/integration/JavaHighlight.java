package integration;

import java.io.IOException;

import org.commontemplate.standard.directive.filter.code.JavaCodeFilter;

public class JavaHighlight {

	public static void main(String[] args) {
		try {
			System.out.println(new JavaCodeFilter().filter(FileContentReader.getResultContent("integration/java.html")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
