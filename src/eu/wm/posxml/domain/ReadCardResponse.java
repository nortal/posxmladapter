package eu.wm.posxml.domain;

/**
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class ReadCardResponse extends AbstractResponse {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[]{"returnCode","card"};
  }
  private Card card;
  public void setCard(Card card) {
    this.card = card;
  }
  public Card getCard() {
    return card;
  }
}
