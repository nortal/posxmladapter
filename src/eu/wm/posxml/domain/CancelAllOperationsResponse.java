package eu.wm.posxml.domain;

/**
 * CancelAllOperationsResponse XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class CancelAllOperationsResponse extends AbstractResponse {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return new String[]{"returnCode"};
  }
}
