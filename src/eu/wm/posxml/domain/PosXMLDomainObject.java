package eu.wm.posxml.domain;


/**
 * Interface for posXML domain objects
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public interface PosXMLDomainObject {
  
  /**
   * Get correct field order in XML documents
   */
  public String[] getFieldOrder();
}
