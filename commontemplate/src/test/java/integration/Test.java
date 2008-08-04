package integration;

import org.commontemplate.util.StringConvertUtils;

public class Test {

	public static void main(String[] args) {
		try {
			String src = "C:\\nxx\txx\\\"xx\\hxx\\uxx\\\"xx";
			String convert = StringConvertUtils.convertLiteral(src);
			String revert = StringConvertUtils.revertLiteral(src);
			System.out.println(convert);
			System.out.println(revert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
