package eu.wm.posxml.domain;

import java.util.Date;

/**
 * TransactionMagStripeRequest XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TransactionMagStripeRequest extends AbstractTransactionRequest {

  private static final String[] fieldOrder = new String[] { "pan", "expires", "serviceCode", "amount", "currencyName",
                                                           "transactionID", "preAuthorisation", "attendantID",
                                                           "printReceipt", "returnReceipts", "cardPresent",
                                                           "cardholderPresent" };

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return fieldOrder;
  }

  @PosXMLField(length = 20, mandatory = true)
  protected String pan;
  @PosXMLField(mandatory = true, pattern = "MM/yy")
  protected Date expires;
  @PosXMLField(length = 3, mandatory = true)
  protected Integer serviceCode;

  public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
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
}
