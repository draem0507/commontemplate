package integration;


public class Test {

	public static void main(String[] args) {
		try {
			System.out.println("<html xxx=\"yyy & zzz\"></html>".replaceAll("(\")[^\"]*\"", "<font>$0</font>"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
