package eu.wm.posxml.composer;

import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import eu.wm.posxml.helper.DomainObjectHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

/**
 * Compose XML messages from PosXML domain objects
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXMLComposer {

  /**
   * Convert the domain object into dom4j document
   * @param object The element to convert into XML.
   * @return Returns a XML document.
   */
  public static Document composeXml(PosXMLDomainObject object) {
    Document doc = DocumentFactory.getInstance().createDocument();
    Element root = doc.addElement(object.getClass().getSimpleName());

    // get all fields
    composeObject(object, root);
    return doc;

  }

  /**
   * Compose PosXMLDomainObject into XML recursively
   * @param object The object to read.
   * @param parent Parent xml object to append elements to.
   */
  private static void composeObject(PosXMLDomainObject object, Element parent) {
    for(String name : object.getFieldOrder()) {
      Object value = DomainObjectHelper.getFieldValue(object, name);
      if(value == null) {
        continue;
      }
      
      PosXMLField field = DomainObjectHelper.getField(object.getClass(), name);
      String childName = DomainObjectHelper.formatName(name, field);
      Element child = parent.addElement(childName);
      String formattedValue = null;
      if(value instanceof PosXMLDomainObject) {
        composeObject((PosXMLDomainObject) value, child);
      }
      else if(field != null && !StringUtils.isEmpty(field.pattern())){
        formattedValue = formatValue(value, field.pattern());
      }
      else {
        formattedValue = value.toString(); 
      }
      if(formattedValue != null) { 
        child.setText(formattedValue);
      }
    }
  }

  /**
   * Format given value with given format
   */
  private static String formatValue(Object value, String format) {
    if(value instanceof Date) {
      return new SimpleDateFormat(format).format((Date)value);
    }
    return value.toString();
  }
}
