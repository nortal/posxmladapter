package eu.wm.posxml.domain;

/**
 * GetParametersRequest XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class GetParametersRequest implements PosXMLRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return new String[]{};
  }

}
