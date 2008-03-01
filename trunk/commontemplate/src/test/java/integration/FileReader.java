package integration;

import java.io.IOException;
import java.io.InputStream;

public class FileReader {
	
	public static String getResultContent(String resultFile) throws IOException {
		StringBuffer result = new StringBuffer();
		InputStream input = null;
		try {
			input = FileReader.class.getClassLoader().getResourceAsStream(resultFile);
			byte[] buffer = new byte[8];
			int n;
			while (-1 != (n = input.read(buffer))) {
				for (int i = 0; i < n; i ++) {
					result.append((char)buffer[i]);
				}
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return result.toString();
	}

}
