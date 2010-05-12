package eu.wm.posxml.domain;

/**
 * TransactionMagStripeRequest XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TransactionMagStripeRequest extends AbstractTransactionRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return new String[] { "pan", "expires", "serviceCode", "amount", "currencyName", "transactionID",
                         "preAuthorisation", "attendantID", "printReceipt", "returnReceipts", "cardPresent",
                         "cardholderPresent" };
  }

  @PosXMLField(length = 20, mandatory = true)
  private String pan;
  @PosXMLField(mandatory = true, length = 5)
  private String expires;
  @PosXMLField(length = 3, mandatory = true)
  private Integer serviceCode;

  public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
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
