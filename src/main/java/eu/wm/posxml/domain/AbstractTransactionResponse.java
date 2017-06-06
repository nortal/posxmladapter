package eu.wm.posxml.domain;

/**
 * Common TransactionResponse XML elements
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public abstract class AbstractTransactionResponse extends AbstractResponse {

  @PosXMLField(length = 256, mandatory = true)
  private String reason;
  @PosXMLField(mandatory = true)
  private TransactionData transactionData;
  @PosXMLField(length = 1, mandatory = true)
  private CardData cardData;
  @PosXMLField(mandatory = true)
  private TerminalData terminalData;
  @PosXMLField(length = 256)
  private String additionalInfo;
  @PosXMLField(length = 4096)
  private String merchReceipt;
  @PosXMLField(length = 4096)
  private String custReceipt;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public TransactionData getTransactionData() {
    return transactionData;
  }

  public void setTransactionData(TransactionData transactionData) {
    this.transactionData = transactionData;
  }

  public CardData getCardData() {
    return cardData;
  }

  public void setCardData(CardData cardData) {
    this.cardData = cardData;
  }

  public TerminalData getTerminalData() {
    return terminalData;
  }

  public void setTerminalData(TerminalData terminalData) {
    this.terminalData = terminalData;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public String getMerchReceipt() {
    return merchReceipt;
  }

  public void setMerchReceipt(String merchReceipt) {
    this.merchReceipt = merchReceipt;
  }

  public String getCustReceipt() {
    return custReceipt;
  }

  public void setCustReceipt(String custReceipt) {
    this.custReceipt = custReceipt;
  }
}
