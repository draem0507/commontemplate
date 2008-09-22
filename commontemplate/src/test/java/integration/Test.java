package integration;


public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(Class.forName("java.util.concurrent.ConcurrentHashMap").newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
