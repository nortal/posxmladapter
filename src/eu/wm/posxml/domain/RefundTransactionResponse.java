package eu.wm.posxml.domain;

/**
 * RefundTransactionResponse XMl element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class RefundTransactionResponse extends TransactionResponse {

  private static final String[] fieldOrder = new String[] { "returnCode", "transactionData", "cardData",
                                                           "terminalData", "additionalInfo", "reason", "merchReceipt",
                                                           "custReceipt" };

  /**
   * @see eu.wm.posxml.domain.TransactionResponse#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return fieldOrder;
  }
}
