package integration;

import org.commontemplate.standard.directive.filter.escape.XmlEscapeFilter;

public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(new XmlEscapeFilter().filter("<html xxx=\"yyy & zzz\"></html>"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
