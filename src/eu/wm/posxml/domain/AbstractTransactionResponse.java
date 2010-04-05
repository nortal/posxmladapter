package eu.wm.posxml.domain;

/**
 * Common TransactionResponse XML elements
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public abstract class AbstractTransactionResponse implements PosXMLDomainObject {

  @PosXMLField(length = 6, mandatory = true)
  private Integer returnCode;
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

  public Integer getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(Integer returnCode) {
    this.returnCode = returnCode;
  }

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
