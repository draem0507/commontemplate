package integration;

import org.commontemplate.standard.property.number.NumberToChinesePropertyHandler;

public class Test {

	public static void main(String[] args) {
		try {
			String num = (String) new NumberToChinesePropertyHandler().doProperty(new Integer(8020003));
			System.out.println(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
