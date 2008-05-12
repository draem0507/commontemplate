package integration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileContentReader {

	public static String getResultContent(String resultFile) throws IOException {
		StringBuffer result = new StringBuffer();
		Reader reader = null;
		try {
			reader = new InputStreamReader(FileContentReader.class.getClassLoader().getResourceAsStream(resultFile), "UTF-8");
			char[] buffer = new char[8];
			int len;
			while (-1 != (len = reader.read(buffer))) {
				result.append(buffer, 0, len);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result.toString();
	}

}
