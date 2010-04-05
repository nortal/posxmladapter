package eu.wm.posxml.domain;

/**
 * CardData XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class CardData extends Card {

  private static final String[] fieldOrder = new String[] { "physicalType", "cardName", "pan", "expires",
                                                           "serviceCode", "al", "aid", "cryptedPan" };

  @Override
  public String[] getFieldOrder() {
    return fieldOrder;
  }

  @PosXMLField(mandatory = true, length = 1)
  private Integer physicalType;
  @PosXMLField(length = 32, name = "AL")
  private String al;
  @PosXMLField(length = 32, name = "AID")
  private String aid;
  @PosXMLField(length = 32)
  private String cryptedPan;

  public Integer getPhysicalType() {
    return physicalType;
  }

  public void setPhysicalType(Integer physicalType) {
    this.physicalType = physicalType;
  }

  public String getAl() {
    return al;
  }

  public void setAl(String al) {
    this.al = al;
  }

  public String getAid() {
    return aid;
  }

  public void setAid(String aid) {
    this.aid = aid;
  }

  public String getCryptedPan() {
    return cryptedPan;
  }

  public void setCryptedPan(String cryptedPan) {
    this.cryptedPan = cryptedPan;
  }
}
