package eu.wm.posxml.domain;

/**
 * FinishTransactionRequest XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class FinishTransactionRequest extends AbstractRequest {
  @Override
  public String[] getFieldOrder() {
    return new String[] { "transactionID", "transactionSTAN", "finishAmount", "amount", "tipAmount", "currencyName",
                         "printReceipt", "returnReceipts" };
  }

  @PosXMLField(length = 16)
  private String transactionID;
  @PosXMLField(length = 6)
  private Integer transactionSTAN;
  @PosXMLField(length = 12)
  private Integer finishAmount;
  @PosXMLField(length = 12)
  private Integer amount;
  @PosXMLField(length = 12, name = "TIPAmount")
  private Integer tipAmount;
  @PosXMLField(length = 3)
  private String currencyName;
  @PosXMLField(length = 1)
  private Integer printReceipt;
  @PosXMLField(length = 2)
  private Integer returnReceipts;
  public String getTransactionID() {
    return transactionID;
  }
  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }
  public Integer getTransactionSTAN() {
    return transactionSTAN;
  }
  public void setTransactionSTAN(Integer transactionSTAN) {
    this.transactionSTAN = transactionSTAN;
  }
  public Integer getFinishAmount() {
    return finishAmount;
  }
  public void setFinishAmount(Integer finishAmount) {
    this.finishAmount = finishAmount;
  }
  public Integer getAmount() {
    return amount;
  }
  public void setAmount(Integer amount) {
    this.amount = amount;
  }
  public Integer getTipAmount() {
    return tipAmount;
  }
  public void setTipAmount(Integer tipAmount) {
    this.tipAmount = tipAmount;
  }
  public String getCurrencyName() {
    return currencyName;
  }
  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
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
}
