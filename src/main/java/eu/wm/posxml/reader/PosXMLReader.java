package eu.wm.posxml.reader;

import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import eu.wm.posxml.helper.DomainObjectHelper;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * Helper to read XML into objects 
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public final class PosXMLReader {

  private static final Logger LOGGER = LogManager.getLogger(PosXMLReader.class);
  
  private PosXMLReader() {
    // to avoid instance init
  }
  
  /**
   * Read xml string into PosXMLDomainObject
   * @throws DocumentException Thrown when parsing of xml fails.
   */
  @SuppressWarnings("unchecked")
  public static PosXMLDomainObject readXml(String xml) throws DocumentException {
    if(xml == null) {
      return null;
    }
    String xml_ = xml.replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
    Document doc = Dom4jDocumentHelper.getDocument(xml_);
    
    // get child
    List<Node> children = Dom4jDocumentHelper.getNodes(doc.getRootElement(), "/node()/node()");
    if(children.isEmpty()) {
      throw new IllegalArgumentException("Missing child node under " + doc.getRootElement().getName());
    }
    Node root = null;
    for(Node node: children) {
      if(node instanceof Element) {
        root = node;
        break;
      }
    }
    if(root == null) {
      throw new IllegalArgumentException("Missing child node under " + doc.getRootElement().getName());
    }
    // match root element and root object
    String className = PosXMLDomainObject.class.getPackage().getName() + "." + root.getName(); 
    Class<? extends PosXMLDomainObject> rootClass = null; 
    try {
      rootClass = (Class<? extends PosXMLDomainObject>) Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Unable to load mapped class: " + className, e); 
    }
    PosXMLDomainObject instance = createInstance(rootClass);
    
    readElement(root, instance);
    return instance;
  }

  /**
   * Create new instance of PosXMLDomainObject
   */
  private static PosXMLDomainObject createInstance(Class<? extends PosXMLDomainObject> rootClass) {
    try {
      return rootClass.newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to create instance: " + rootClass.getName(), e); 
    }
  }

  /**
   * Read PosXMLDomainObject content from xml
   */
  private static void readElement(Node element, PosXMLDomainObject bean) {
    String[] fieldOrder = bean.getFieldOrder();
    if(ArrayUtils.isEmpty(fieldOrder)) {
      return;
    }
    for(String name : fieldOrder) {
      PropertyDescriptor descriptor;
      try {
        descriptor = PropertyUtils.getPropertyDescriptor(bean, name);
      } catch (Exception e) {
        LOGGER.error("Error reading field information: " + name, e);
        continue;
      }
      if(!DomainObjectHelper.typeAllowed(descriptor.getPropertyType()) || 
          descriptor.getWriteMethod() == null) {
        continue;
      }

      PosXMLField field = DomainObjectHelper.getField(bean.getClass(), name);
      String elementName = DomainObjectHelper.formatName(name, field);
      List<Node> nodes = Dom4jDocumentHelper.getNodes(element, elementName);
      if(nodes.isEmpty()) {
        continue;
      }
      Object converted = readValue(nodes.get(0), descriptor, field);
      if(converted == null) {
        continue;
      }
      // set field value
      try {
        descriptor.getWriteMethod().invoke(bean, converted);
      } catch (Exception e) {
        throw new IllegalArgumentException("Error setting field value: " + bean.getClass() + "#" + descriptor.getWriteMethod().getName(), e);
      }
    }
  }
  
  /**
   * Read value from XML
   * @return Returns the value or null
   */
  @SuppressWarnings("unchecked")
  private static Object readValue(Node childNode, PropertyDescriptor descriptor, PosXMLField field) {
    Object converted = null;
    if(DomainObjectHelper.isDomainObject(descriptor.getPropertyType())) {
      PosXMLDomainObject child = createInstance((Class<? extends PosXMLDomainObject>) descriptor.getPropertyType());
      converted = child;
      readElement(childNode, child);
    }
    else {
      String value = Dom4jDocumentHelper.getValue(childNode, "text()");
      if(value == null) {
        return null;
      }
      converted = convertValue(value, descriptor.getPropertyType(), field);
    }
    return converted;
  }

  /**
   * Convert a String value to required type
   */
  private static Object convertValue(String value, Class<?> clazz, PosXMLField field) {
    if(StringUtils.isEmpty(value)) {
      return null;
    }
    if(clazz == String.class) {
      return value;
    }
    if(clazz == Integer.class) {
      return Integer.valueOf(value);
    }
    if(clazz == BigDecimal.class) {
      return new BigDecimal(value.replace(",", "."));
    }
    if(clazz == Date.class) {
      if(field == null || StringUtils.isEmpty(field.pattern())) {
        throw new IllegalArgumentException("Date field has to be annotated with PosXMLField(pattern)!");
      }
      try {
        return new SimpleDateFormat(field.pattern(), Locale.getDefault()).parse(value);
      } catch (ParseException e) {
        throw new IllegalArgumentException("Date not in correct format! Excpected " + field.pattern() + ", got " + value, e);
      }
    }
    throw new IllegalArgumentException("Unsupported field type: " + clazz.getName());
  }

}
