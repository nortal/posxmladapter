package eu.wm.posxml.composer;

import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import eu.wm.posxml.helper.DomainObjectHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Compose XML messages from PosXML domain objects
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public final class PosXMLComposer {
  public static final String POSXML_VERSION = "7.2.0";

  private PosXMLComposer() {
    // to avoid instance init
  }


  /**
   * Convert the domain object into dom4j document
   * 
   * @param object The element to convert into XML.
   * @return Returns a XML document.
   */
  public static Document composeXml(PosXMLDomainObject object) {
    Validate.notNull(object, "Object to be composed must not be null!");
    Document doc = DocumentFactory.getInstance().createDocument();
    Element root = doc.addElement("PosXML");
    root.addAttribute("version", POSXML_VERSION);

    Element node = root.addElement(object.getClass().getSimpleName());
    // get all fields
    composeObject(object, node);
    return doc;

  }

  /**
   * Compose PosXMLDomainObject into XML recursively
   * 
   * @param object The object to read.
   * @param parent Parent xml object to append elements to.
   */
  private static void composeObject(PosXMLDomainObject object, Element parent) {
    String[] fieldOrder = object.getFieldOrder();
    if(ArrayUtils.isEmpty(fieldOrder)) {
      // add empty content for requests
      parent.setText("");
      return;
    }
    // if no values were present
    boolean childValuePresent = false;
    for (String name : fieldOrder) {
      Object value = DomainObjectHelper.getFieldValue(object, name);
      if (value == null) {
        continue;
      }
      childValuePresent = true;
      PosXMLField field = DomainObjectHelper.getField(object.getClass(), name);
      String childName = DomainObjectHelper.formatName(name, field);
      Element child = parent.addElement(childName);
      String formattedValue = null;
      if (value instanceof PosXMLDomainObject) {
        composeObject((PosXMLDomainObject) value, child);
      } else if (field != null && !StringUtils.isEmpty(field.pattern())) {
        formattedValue = formatValue(value, field.pattern());
      } else {
        formattedValue = value.toString();
      }
      if (formattedValue != null) {
        child.setText(formattedValue);
      }
    }
    // add empty content
    if(!childValuePresent) {
      parent.setText("");
    }
  }

  /**
   * Format given value with given format
   */
  private static String formatValue(Object value, String format) {
    if (value instanceof Date) {
      return new SimpleDateFormat(format, Locale.getDefault()).format((Date) value);
    }
    return value.toString();
  }
}
