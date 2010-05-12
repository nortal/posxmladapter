package eu.wm.posxml.domain;

/**
 * Abstract response XML element containing ReturnCode element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public abstract class AbstractResponse implements PosXMLResponse {

  @PosXMLField(length = 6, mandatory = true)
  private Integer returnCode;

  public void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

  public Integer getReturnCode() {
    return returnCode;
  }

}
