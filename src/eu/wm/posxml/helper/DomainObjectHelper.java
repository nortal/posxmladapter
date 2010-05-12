package eu.wm.posxml.helper;

import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLField;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Helper for converting bean filed names to XML element names
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public final class DomainObjectHelper {

  private DomainObjectHelper() {
    // to avoid instance init
  }
  
  private static final Logger LOGGER = Logger.getLogger(DomainObjectHelper.class);
  
  private static final Set<Class<?>> ALLOWED_TYPES = new HashSet<Class<?>>();
  
  static {
    ALLOWED_TYPES.add(String.class);
    ALLOWED_TYPES.add(Integer.class);
    ALLOWED_TYPES.add(Date.class);
    ALLOWED_TYPES.add(BigDecimal.class);
  }

  /**
   * Format field name
   */
  public static String formatName(String name, PosXMLField field) {
    if(name == null) {
      return null;
    }
    if(field != null && !StringUtils.isEmpty(field.name())) {
      return field.name();
    }
    return StringUtils.capitalize(name);
  }
  
  /**
   * Format field name
   */
  public static String formatName(String name) {
    return formatName(name, null);
  }

  /**
   * Get PosXMLField annotation from given objects named field.
   * @param clazz Class to get field from.
   * @param name Field name.
   * @return Returns found annotation or null.
   */
  @SuppressWarnings("unchecked")
  public static PosXMLField getField(Class<? extends PosXMLDomainObject> clazz, String name) {
    if(clazz == null || name == null) {
      return null;
    }
    try {
      return clazz.getDeclaredField(name).getAnnotation(PosXMLField.class);
    } catch (SecurityException e) {
      throw new IllegalStateException("Error accessing class field: " + clazz.getName() + "." + name);
    } catch (NoSuchFieldException e) {
      Class<?> superClass = clazz.getSuperclass();
      if(isDomainObject(superClass)) {
        return getField((Class<? extends PosXMLDomainObject>) superClass, name);
      }
      return null;
    }
  }
  
  /**
   * Check whether a field value is of allowed type.
   */
  public static boolean typeAllowed(Class<?> clazz) {
    if(clazz == null) {
      return false;
    }
    return isDomainObject(clazz) || DomainObjectHelper.ALLOWED_TYPES.contains(clazz);
  }

  /**
   * Returns if given class implements {@link PosXMLDomainObject} interface.
   */
  public static boolean isDomainObject(Class<?> clazz) {
    return PosXMLDomainObject.class.isAssignableFrom(clazz);
  }

  /**
   * Read field value from an object by field name.
   * @return Returns field value or null, if retrieving the value fails.
   */
  public static Object getFieldValue(Object object, String fieldName) {
    if(object == null || fieldName == null) {
      return null;
    }
    PropertyDescriptor descriptor;
    try {
      descriptor = PropertyUtils.getPropertyDescriptor(object, fieldName);
    } catch (Exception e) {
      return null;
    }
    if(descriptor == null) {
      return null;
    }
    if(!typeAllowed(descriptor.getPropertyType())) {
      return null;
    }
    
    Method readMethod = descriptor.getReadMethod();
    if(readMethod == null) {
      return null;
    }

    try {
      return readMethod.invoke(object);
    } catch (Exception e) {
      LOGGER.error("Error getting field value: " + fieldName, e);
      return null;
    }
  }
}
