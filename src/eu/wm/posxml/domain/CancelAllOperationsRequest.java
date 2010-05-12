package eu.wm.posxml.domain;

/**
 * CancelAllOperationsRequest XML elment
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class CancelAllOperationsRequest implements PosXMLRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return new String[]{};
  }

}
