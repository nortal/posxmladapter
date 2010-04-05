package eu.wm.posxml.domain;

/**
 * Error XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class Error implements PosXMLDomainObject {

  public static final String[] fieldOrder = new String[]{"ReturnCode"};
  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return fieldOrder;
  }
  
  @PosXMLField(length = 6, mandatory = true)
  private String returnCode;

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

}
