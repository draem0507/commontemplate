package integration;

import org.commontemplate.core.Expression;

public class Test {

	public static void main(String[] args) {
		try {
			Expression expression = TestEngineProvider.getTestEngine().parseExpression("'x' & 2 * 4");
			expression.evaluate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
