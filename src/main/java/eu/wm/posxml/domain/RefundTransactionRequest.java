package eu.wm.posxml.domain;

/**
 * RefundTransactionRequest XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class RefundTransactionRequest implements PosXMLRequest {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  @Override
  public String[] getFieldOrder() {
    return new String[] { "transactionID", "transactionSTAN", "printReceipt", "returnReceipts" };
  }

  @PosXMLField(length = 16)
  private String transactionID;
  @PosXMLField(length = 6)
  private Integer transactionSTAN;
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

  @Override
  public String validate() {
    return null;
  }

}
