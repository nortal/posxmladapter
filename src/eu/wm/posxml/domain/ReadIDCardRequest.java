package eu.wm.posxml.domain;

/**
 * ReadIDCardRequest XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class ReadIDCardRequest extends AbstractRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[]{"timeout","language","verifyPIN"};
  }

  @PosXMLField(length=3)
  private Integer timeout;
  @PosXMLField(length=2)
  private String language;
  @PosXMLField(length=1)
  private Integer verifyPIN;
  public Integer getTimeout() {
    return timeout;
  }
  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }
  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }
  public Integer getVerifyPIN() {
    return verifyPIN;
  }
  public void setVerifyPIN(Integer verifyPIN) {
    this.verifyPIN = verifyPIN;
  }
}
