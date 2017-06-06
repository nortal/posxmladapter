package eu.wm.posxml.domain;

/**
 * IDCardResponse XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class IDCardResponse extends AbstractResponse {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[]{"returnCode","idCard"};
  }
  
  @PosXMLField(name="IDCard")
  private IDCard idCard;
  public IDCard getIdCard() {
    return idCard;
  }
  public void setIdCard(IDCard idCard) {
    this.idCard = idCard;
  }
}
