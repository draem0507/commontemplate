package integration;

import org.commontemplate.util.ClassUtils;

public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(ClassUtils.forName("boolean"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
