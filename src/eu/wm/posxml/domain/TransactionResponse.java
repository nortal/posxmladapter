package eu.wm.posxml.domain;

/**
 * TransactionResponse XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TransactionResponse extends AbstractTransactionResponse {

  private static final String[] fieldOrder = new String[] { "returnCode", "reason", "transactionData", "cardData",
                                                           "terminalData", "additionalInfo", "merchReceipt",
                                                           "custReceipt" };

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return fieldOrder;
  }
}
