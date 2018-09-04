package persistencia.conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class RWProperties {
	private static final String FILE = "sql/dbConfig.properties";
	
	public static String getValue(String key) throws IOException {
		Properties properties  = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(FILE);
			properties.load(inputStream);
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null) inputStream.close();
		}
		return properties.getProperty(key);
	}
	
	public static void writeValue(String key, String value) throws IOException{
		FileInputStream in = new FileInputStream(FILE);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(FILE);
		props.setProperty(key, value);
		props.store(out, null);
		out.close();
	}
}
