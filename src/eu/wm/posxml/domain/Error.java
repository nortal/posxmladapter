package eu.wm.posxml.domain;

/**
 * Error XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class Error extends AbstractResponse {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return new String[]{"returnCode"};
  }
  
}
