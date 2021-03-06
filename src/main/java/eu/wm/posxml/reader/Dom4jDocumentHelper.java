package eu.wm.posxml.reader;

import java.io.StringReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

/**
 * Helper for methods with dom4j documents
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public final class Dom4jDocumentHelper {

  public static final String MESSAGE_ENCODING = "UTF-8";

  private Dom4jDocumentHelper() {
    // avoid instance init
  }
  
  /**
   * Get dom4j Document from a XML string
   * 
   * @param message The message to be parsed
   * @param namespaces Map of non-standard namespace uris to be used in format prefix --> namespace.
   */
  public static Document getDocument(String message) throws DocumentException {
    SAXReader xmlReader = new SAXReader();

    InputSource inputSource = new InputSource(new StringReader(message));
    inputSource.setEncoding(MESSAGE_ENCODING);

    return xmlReader.read(inputSource);
  }

  /**
   * Get List of dom4j nodes selected by XPath from given node
   * 
   * @param mandatory If list is empty, throws an exception
   * @throws NoSuchFieldException If the field is set mandatory but no value exists.
   */
  public static List<Node> getNodes(Node node, String xpath) {
    XPath xpathSelector = DocumentHelper.createXPath(xpath);
    return xpathSelector.selectNodes(node);
  }

  /**
   * Get the value of an element
   * 
   * @return Returns String value or null, if the value is an empty string or doesn't exist
   */
  public static String getValue(Node node, String XPath) {
    String value = node.valueOf(XPath);
    if(StringUtils.isEmpty(value)) {
      return null;
    }
    return value;
  }

}