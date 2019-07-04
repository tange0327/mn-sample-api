package jp.co.softbank.cmeself.sales.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import jp.co.softbank.cmeself.sales.util.TestUtils;

public final class TestUtils {
  
  private TestUtils() {
    
  }
  
  public static String readMessageFromFile(String path) throws IOException {
    String result = null;
    
    try (FileInputStream input = new FileInputStream("src/test/resources/" + path)) {
      result = IOUtils.toString(input, "UTF-8");
    }
    
    return result;
  }
  
  public static String readBodyFile(String path) throws IOException {
    return readMessageFromFile("__files/" + path);
  }
  
  public static String urlEncode(String str) {
    String result = null;
    
    try {
      result = URLEncoder.encode(str, StandardCharsets.UTF_8.name());
      
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Unsupported type", e);
    }
    
    return result;
  }
}
