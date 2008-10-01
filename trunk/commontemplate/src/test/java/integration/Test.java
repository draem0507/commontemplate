package integration;


public class Test {

	public static void main(String[] args) {
		try {
			String str = "aa\naa\n\n";
			System.out.println(str.lastIndexOf('\n', str.length() - 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
