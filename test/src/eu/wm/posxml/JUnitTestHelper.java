package eu.wm.posxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class JUnitTestHelper {

  private static final Properties properties = new Properties();
  public static final String PROPERTY__TERMINAL_ADDRESS = "test.terminal.address"; 
  
  static {
    InputStream fileStream = JUnitTestHelper.class.getResourceAsStream("/test.properties");
    if(fileStream == null) {
      throw new IllegalStateException("Unable to find resource test.properties from classpath");
    }
    try {
      properties.load(fileStream);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load test.properties from classpath", e);
    }
    finally {
      try {
        fileStream.close();
      } catch (IOException e) {
        throw new IllegalStateException("Error closing file stream!", e);        
      }
    }
  }
  
  /**
   * Get test property by key
   * @return property value or null
   */
  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
}
