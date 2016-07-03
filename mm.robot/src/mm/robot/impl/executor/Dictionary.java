package mm.robot.impl.executor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Dictionary {

	private final Properties prop;
	
	public Dictionary(String dictionary) throws IOException {
		super();
		prop = loadDictionary(dictionary);
	}

	Properties getProp() {
		return prop;
	}

	private Properties loadDictionary(String dictionary) throws IOException {
		Properties prop = new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream(dictionary);
		prop.load(stream);
		return prop;
	}
}
