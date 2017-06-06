package eu.wm.posxml.domain;

/**
 * TransactionRequest xml element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TransactionRequest extends AbstractTransactionRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[] { "amount", "currencyName", "transactionID", "attendantID", "printReceipt", "returnReceipts",
                         "preAuthorisation", "partialAuthorization", "discountEnabled", "lastFourDigits", "timeout",
                         "language", "manual", "cardPresent", "cardholderPresent" };
  }

  @PosXMLField(length = 1)
  private String partialAuthorization;
  @PosXMLField(length = 1)
  private String discountEnabled;
  @PosXMLField(length = 4)
  private Integer lastFourDigits;
  @PosXMLField(length = 3)
  private Integer timeout;
  @PosXMLField(length = 2)
  private String language;
  @PosXMLField(length = 1)
  private Integer manual;

  public String getPartialAuthorization() {
    return partialAuthorization;
  }

  public void setPartialAuthorization(String partialAuthorization) {
    this.partialAuthorization = partialAuthorization;
  }

  public String getDiscountEnabled() {
    return discountEnabled;
  }

  public void setDiscountEnabled(String discountEnabled) {
    this.discountEnabled = discountEnabled;
  }

  public Integer getLastFourDigits() {
    return lastFourDigits;
  }

  public void setLastFourDigits(Integer lastFourDigits) {
    this.lastFourDigits = lastFourDigits;
  }

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

  public Integer getManual() {
    return manual;
  }

  public void setManual(Integer manual) {
    this.manual = manual;
  }
}
