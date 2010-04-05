package eu.wm.posxml.domain;

/**
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class ReverseTransactionRequest extends RefundTransactionRequest {

  private static final String[] fieldOrder = new String[] { "transactionID", "transactionSTAN", "reverseAmount",
                                                           "currencyName", "partialReversal", "lastFourDigits",
                                                           "forcedAction", "printReceipt", "returnReceipts" };
  /**
   * @see eu.wm.posxml.domain.RefundTransactionRequest#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return fieldOrder;
  }
  
  @PosXMLField(length = 12)
  private Integer reverseAmount;
  @PosXMLField(length = 3)
  private String currencyName;
  @PosXMLField(length = 1)
  private Integer partialReversal;
  @PosXMLField(length = 4)
  private Integer lastFourDigits;
  @PosXMLField(length = 1)
  private Integer forcedAction;

  public Integer getReverseAmount() {
    return reverseAmount;
  }

  public void setReverseAmount(Integer reverseAmount) {
    this.reverseAmount = reverseAmount;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public Integer getPartialReversal() {
    return partialReversal;
  }

  public void setPartialReversal(Integer partialReversal) {
    this.partialReversal = partialReversal;
  }

  public Integer getLastFourDigits() {
    return lastFourDigits;
  }

  public void setLastFourDigits(Integer lastFourDigits) {
    this.lastFourDigits = lastFourDigits;
  }

  public Integer getForcedAction() {
    return forcedAction;
  }

  public void setForcedAction(Integer forcedAction) {
    this.forcedAction = forcedAction;
  }
}
