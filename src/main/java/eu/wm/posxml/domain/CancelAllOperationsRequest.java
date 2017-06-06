package eu.wm.posxml.domain;

/**
 * CancelAllOperationsRequest XML elment
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class CancelAllOperationsRequest extends AbstractRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[]{};
  }

}
