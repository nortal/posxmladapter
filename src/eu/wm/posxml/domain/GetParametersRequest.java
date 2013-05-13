package eu.wm.posxml.domain;

/**
 * GetParametersRequest XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class GetParametersRequest extends AbstractRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[]{};
  }

}
