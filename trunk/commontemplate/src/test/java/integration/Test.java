package integration;

public class Test {

	public static void main(String[] args) {
		String name = "/aaa/bbb/ccc/*/ddd/ee.txt";
		int i = name.indexOf("/*/");
		if (i > -1) {
			String path = name.substring(0, i + 1) + name.substring(i + 3);
			System.out.println(path);
			if (i > 0) {
				int j = path.lastIndexOf('/', i - 1);
				while (j > -1) {
					path = path.substring(0, j) + path.substring(i);
					System.out.println(path);
					if (j == 0)
						break;
					i = j;
					j = path.lastIndexOf('/', i - 1);
				}
			}
		}
	}

}
