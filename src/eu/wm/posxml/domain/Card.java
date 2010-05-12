package eu.wm.posxml.domain;


/**
 * Object that represents card type in XML
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class Card implements PosXMLDomainObject {

  public String[] getFieldOrder() {
    return new String[]{"pan","cardName","expires","serviceCode"};
  }

  @PosXMLField(length=20,mandatory=true)
  private String pan;
  @PosXMLField(length=40,mandatory=true)
  private String cardName;
  @PosXMLField(mandatory=true, length=5)
  private String expires;
  @PosXMLField(length=3,mandatory=true)
  private Integer serviceCode;
  public String getPan() {
    return pan;
  }
  public void setPan(String pan) {
    this.pan = pan;
  }
  public String getCardName() {
    return cardName;
  }
  public void setCardName(String cardName) {
    this.cardName = cardName;
  }
  public String getExpires() {
    return expires;
  }
  public void setExpires(String expires) {
    this.expires = expires;
  }
  public Integer getServiceCode() {
    return serviceCode;
  }
  public void setServiceCode(Integer serviceCode) {
    this.serviceCode = serviceCode;
  }
}
