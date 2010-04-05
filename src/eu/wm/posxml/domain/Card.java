package eu.wm.posxml.domain;

import java.util.Date;

/**
 * Object that represents card type in XML
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class Card implements PosXMLDomainObject {
  
  private static final String[] fieldOrder = new String[]{"pan","cardName","expires","serviceCode"};

  @PosXMLField(length=20,mandatory=true)
  private String pan;
  @PosXMLField(length=40,mandatory=true)
  private String cardName;
  @PosXMLField(mandatory=true, pattern="MM/yy")
  private Date expires;
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
  public Date getExpires() {
    return expires;
  }
  public void setExpires(Date expires) {
    this.expires = expires;
  }
  public Integer getServiceCode() {
    return serviceCode;
  }
  public void setServiceCode(Integer serviceCode) {
    this.serviceCode = serviceCode;
  }
  public String[] getFieldOrder() {
    return fieldOrder;
  }
}
