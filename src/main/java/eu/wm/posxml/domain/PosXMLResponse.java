package eu.wm.posxml.domain;

/**
 * Marker interface for PosXML responses
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public interface PosXMLResponse extends PosXMLDomainObject {

  /**
   * Object specific validation rules
   */
  public String validate();

}
