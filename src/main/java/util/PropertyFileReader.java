package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	private Properties prop;
	public PropertyFileReader(String filePath) {
		File file = new File(filePath);
		FileInputStream input;
		try {
			input = new FileInputStream(file);
			prop = new Properties();
			prop.load(input);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getValue(String key) {
		return prop.getProperty(key);
	}
}
