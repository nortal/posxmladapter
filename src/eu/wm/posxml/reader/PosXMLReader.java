package eu.wm.posxml.reader;

import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import eu.wm.posxml.helper.Dom4jDocumentHelper;
import eu.wm.posxml.helper.DomainObjectHelper;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

/**
 * Helper to read XML into objects 
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXMLReader {

  private static final Logger LOGGER = Logger.getLogger(PosXMLReader.class);
  
  /**
   * Read xml string into PosXMLDomainObject
   * @throws DocumentException Thrown when parsing of xml fails.
   */
  @SuppressWarnings("unchecked")
  public static PosXMLDomainObject readXml(String xml) throws DocumentException {
    Document doc = Dom4jDocumentHelper.getDocument(xml);
    
    // match root element and root object
    String className = PosXMLDomainObject.class.getPackage().getName() + "." + doc.getRootElement().getName(); 
    Class<? extends PosXMLDomainObject> rootClass = null; 
    try {
      rootClass = (Class<? extends PosXMLDomainObject>) Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Unable to load mapped class: " + className, e); 
    }
    PosXMLDomainObject instance = createInstance(rootClass);
    
    readElement(doc.getRootElement(), instance);
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
  @SuppressWarnings("unchecked")
  private static void readElement(Node element, PosXMLDomainObject bean) {
    for(String name : bean.getFieldOrder()) {
      PropertyDescriptor descriptor;
      try {
        descriptor = PropertyUtils.getPropertyDescriptor(bean, name);
      } catch (Exception e) {
        LOGGER.error("Error reading field information: " + name, e);
        continue;
      }
      if(!DomainObjectHelper.typeAllowed(descriptor.getPropertyType())) {
        LOGGER.debug("Field " + name + " " + descriptor.getPropertyType()+ " is not supported!");
        continue;
      }
      Method writeMethod = descriptor.getWriteMethod();
      if(writeMethod == null) {
        LOGGER.debug("Field " + name + " has no write method!");
        continue;
      }
      PosXMLField field = DomainObjectHelper.getField(bean.getClass(), name);
      String elementName = DomainObjectHelper.formatName(name, field);
      List<Node> nodes = Dom4jDocumentHelper.getNodes(element, elementName);
      if(nodes.isEmpty()) {
        continue;
      }
      Node childNode = nodes.get(0);
      Object converted = null;
      if(DomainObjectHelper.isDomainObject(descriptor.getPropertyType())) {
        PosXMLDomainObject child = createInstance((Class<? extends PosXMLDomainObject>) descriptor.getPropertyType());
        converted = child;
        readElement(childNode, child);
      }
      else {
        String value = Dom4jDocumentHelper.getValue(childNode, "text()");
        if(value == null) {
          continue;
        }
        converted = convertValue(value, descriptor.getPropertyType(), field);
      }
      // set field value
      try {
        writeMethod.invoke(bean, converted);
      } catch (Exception e) {
        throw new IllegalArgumentException("Error setting field value: " + bean.getClass() + "#" + writeMethod.getName(), e);
      }
    }
  }

  /**
   * Convert a String value to required type
   */
  private static Object convertValue(String value, Class<?> clazz, PosXMLField field) {
    if(value == null) {
      return null;
    }
    if(clazz == String.class) {
      return value;
    }
    if(clazz == Integer.class) {
      return Integer.valueOf(value);
    }
    if(clazz == Date.class) {
      if(field == null || StringUtils.isEmpty(field.pattern())) {
        throw new IllegalArgumentException("Date field has to be annotated with PosXMLField(pattern)!");
      }
      try {
        return new SimpleDateFormat(field.pattern()).parse(value);
      } catch (ParseException e) {
        throw new IllegalArgumentException("Date not in correct format! Excpected " + field.pattern() + ", got " + value);
      }
    }
    throw new IllegalArgumentException("Unsupported field type: " + clazz.getName());
  }

}
