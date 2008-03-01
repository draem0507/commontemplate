package integration;

import org.commontemplate.standard.operator.number.NumberDivideOperatorHandler;

public class Test {
	
	public static void main(String[] args) {
		try {
			Integer result = (Integer) new NumberDivideOperatorHandler().doEvaluate(new Integer(6), new Integer(2));
			System.out.println("result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
