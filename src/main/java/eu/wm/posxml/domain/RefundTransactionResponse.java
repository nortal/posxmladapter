package eu.wm.posxml.domain;

/**
 * RefundTransactionResponse XMl element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class RefundTransactionResponse extends TransactionResponse {

  /**
   * @see eu.wm.posxml.domain.TransactionResponse#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[] { "returnCode", "transactionData", "cardData", "terminalData", "additionalInfo", "reason",
                         "merchReceipt", "custReceipt" };
  }
}
