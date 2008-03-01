package integration;

public class Bank {
	
	public String queryAll() {
		return "query:All";
	}
	
	public String queryById(int i) {
		return "query:" + i;
	}

}
