package eu.wm.posxml.domain;

/**
 * ReadCardRequest XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class ReadCardRequest implements PosXMLRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[] { "timeout", "language", "amount", "currencyName" };
  }

  @PosXMLField(length = 3)
  private Integer timeout;
  @PosXMLField(length = 2)
  private String language;
  @PosXMLField(length = 12)
  private Integer amount;
  @PosXMLField(length = 3)
  private String currencyName;

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

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  @Override
  public String validate() {
    return null;
  }
}
