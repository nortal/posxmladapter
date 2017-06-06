package eu.wm.posxml.validate;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import eu.wm.posxml.domain.AbstractRequest;
import eu.wm.posxml.domain.AbstractResponse;
import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import eu.wm.posxml.helper.DomainObjectHelper;

/**
 * Validate PosXML objects
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public final class PosXmlValidator {

  private PosXmlValidator() {
    // to avoid instance init
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
    if(errors.length() == 0) {
      return null;
    }
    // validate request
    if(object instanceof AbstractRequest) {
      String error = ((AbstractRequest) object).validate();
      if(StringUtils.isNotEmpty(error)) {
        errors.append(error).append("\n");
      }
    }
    // validate response
    if(object instanceof AbstractResponse) {
      String error = ((AbstractResponse) object).validate();
      if(StringUtils.isNotEmpty(error)) {
        errors.append(error).append("\n");
      }
    }
    return errors.toString();
  }

  /**
   * Validate PosXMLDomainObject recursively
   */
  private static void validateObject(PosXMLDomainObject object, String location, StringBuilder errors) {
    String[] fieldOrder = object.getFieldOrder();
    if(ArrayUtils.isEmpty(fieldOrder)) {
      return;
    }
    for(String fieldName : fieldOrder) {
      PosXMLField field = DomainObjectHelper.getField(object.getClass(), fieldName);
      Object value = DomainObjectHelper.getFieldValue(object, fieldName);
      // no such field in model
      if(field == null) {
        continue;
      }
      else if(field.mandatory() && value == null) {
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
