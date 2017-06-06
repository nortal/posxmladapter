package eu.wm.posxml.domain;

/**
 * Marker interface for PosXML requests
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public interface PosXMLRequest extends PosXMLDomainObject {

  /**
   * Object specific validation rules
   */
  public String validate();
}
