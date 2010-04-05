package eu.wm.posxml.domain;

/**
 * Common fields for different transaction requests 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public abstract class AbstractTransactionRequest implements PosXMLDomainObject {

  @PosXMLField(length = 12, mandatory = true)
  private Integer amount;
  @PosXMLField(length = 3, mandatory = true)
  private String currencyName;
  @PosXMLField(length = 16)
  private String transactionID;
  @PosXMLField(length = 8)
  private String attendantID;
  @PosXMLField(length = 1)
  private Integer printReceipt;
  @PosXMLField(length = 2)
  private Integer returnReceipts;
  @PosXMLField(length = 1)
  private Integer preAuthorisation;
  @PosXMLField(length = 1)
  private Integer cardPresent;
  @PosXMLField(length = 1)
  private Integer cardholderPresent;

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

  public String getAttendantID() {
    return attendantID;
  }

  public void setAttendantID(String attendantID) {
    this.attendantID = attendantID;
  }

  public Integer getPrintReceipt() {
    return printReceipt;
  }

  public void setPrintReceipt(Integer printReceipt) {
    this.printReceipt = printReceipt;
  }

  public Integer getReturnReceipts() {
    return returnReceipts;
  }

  public void setReturnReceipts(Integer returnReceipts) {
    this.returnReceipts = returnReceipts;
  }

  public Integer getPreAuthorisation() {
    return preAuthorisation;
  }

  public void setPreAuthorisation(Integer preAuthorisation) {
    this.preAuthorisation = preAuthorisation;
  }
  public Integer getCardPresent() {
    return cardPresent;
  }

  public void setCardPresent(Integer cardPresent) {
    this.cardPresent = cardPresent;
  }

  public Integer getCardholderPresent() {
    return cardholderPresent;
  }

  public void setCardholderPresent(Integer cardholderPresent) {
    this.cardholderPresent = cardholderPresent;
  }
}
