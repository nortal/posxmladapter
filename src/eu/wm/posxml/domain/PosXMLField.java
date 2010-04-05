package eu.wm.posxml.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describe posXML field attributes
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface PosXMLField {

  /**
   * Maximum element length in characters / numbers / ...
   */
  int length() default -1;
  /**
   * Whether the element is mandatory.<br/>Default is false.
   */
  boolean mandatory() default false;
  /**
   * Format to parse element value to / from.
   */
  String pattern() default "";
  /**
   * If specified, the field will be mapped to given XML element name.
   */
  String name() default "";
}
