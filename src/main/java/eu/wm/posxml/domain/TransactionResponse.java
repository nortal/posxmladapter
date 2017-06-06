package eu.wm.posxml.domain;

/**
 * TransactionResponse XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TransactionResponse extends AbstractTransactionResponse {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[] { "returnCode", "reason", "transactionData", "cardData", "terminalData", "additionalInfo",
                         "merchReceipt", "custReceipt" };
  }

  @Override
  public String validate() {
    if(getTransactionData() == null || getTransactionData().getTransactionCompletion() == null) {
      return null;
    }
    // Transaction status for failed transactions is "NOTIFICATION"
    return "NOTIFICATION".equals(getTransactionData().getTransactionCompletion()) ? "Transaction failed" : null;
  }

}
