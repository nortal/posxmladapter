package eu.wm.posxml.validate;

import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import eu.wm.posxml.helper.DomainObjectHelper;
import eu.wm.posxml.reader.PosXMLReader;
import org.dom4j.DocumentException;

/**
 * Validate PosXML objects
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXmlValidator {

  /**
   * Parse xml into PosXMLDomainObject and validate it
   */
  public static String validate(String xml) {
    PosXMLDomainObject object;
    try {
      object = PosXMLReader.readXml(xml);
    } catch (DocumentException e) {
      return "Error parsing XML into PosXMLDomainObject: " + e.getMessage();
    }
    return validate(object);
  }

  /**
   * Validate domain object against required data and data length.
   * @param object The object to be validated
   * @return Returns null, if the object is valid or string of found errors.
   */
  public static String validate(PosXMLDomainObject object) {
    if(object == null) {
      return "Validation failed: object is null!";
    }
    String location = "/" + object.getClass().getSimpleName() + "/"; 
    StringBuilder errors = new StringBuilder();
    
    validateObject(object, location, errors);
    return errors.length() > 0 ? errors.toString() : null;
  }

  /**
   * Validate PosXMLDomainObject recursively
   */
  private static void validateObject(PosXMLDomainObject object, String location, StringBuilder errors) {
    for(String fieldName : object.getFieldOrder()) {
      PosXMLField field = DomainObjectHelper.getField(object.getClass(), fieldName);
      Object value = DomainObjectHelper.getFieldValue(object, fieldName);
      if(field.mandatory() && value == null) {
        errors.append("Field " + location + fieldName + " is mandatory!\n");
      }
      if(value == null) {
        continue;
      }
      if(DomainObjectHelper.isDomainObject(value.getClass())) {
        validateObject((PosXMLDomainObject) value, location + fieldName + "/", errors);
        continue;
      }
      if(field.length() > -1 && !validateLength(value, field.length())) {
        errors.append("Field " + location + fieldName + " maximum length is " + field.length() + "!\n");
      }
    }
  }

  /**
   * Validate field length
   */
  private static boolean validateLength(Object value, int length) {
    if(value == null) {
      return true;
    }
    if(value instanceof String) {
      return ((String)value).length() <= length;
    }
    if(value instanceof Integer) {
      return Math.abs(((Integer)value).intValue()) < Math.pow(10, length);
    }
    return true;
  }
}
