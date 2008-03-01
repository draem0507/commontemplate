package integration;

public class PrimitiveObject {
	
	public String test1(int i) {
		return "hello:" + i;
	}
	
	public String test2(int i) {
		return "hello:" + i;
	}
	
	public String test2(String i) {
		return "hello2:" + i;
	}
	
	public String test3(int i, String n) {
		return "hello:" + i + "," + n;
	}

}
