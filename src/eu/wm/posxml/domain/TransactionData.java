package eu.wm.posxml.domain;

import java.util.Date;

/**
 * TransactionData XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TransactionData implements PosXMLDomainObject {

  private static final String[] fieldOrder = new String[] { "transactionID", "amount", "additionalAmount",
                                                           "currencyName", "datetime", "datetimeRev", "stan",
                                                           "stanRev", "type", "cardAction", "authorizationNo",
                                                           "hostAnswer", "secAmount", "secAdditionalAmount",
                                                           "secCurrencyName", "signatureRequired", "pinVerified" };

  @PosXMLField(mandatory = true, length = 16)
  private String transactionID;
  @PosXMLField(mandatory = true, length = 12)
  private Integer amount;
  @PosXMLField(mandatory = true, length = 12)
  private Integer additionalAmount;
  @PosXMLField(mandatory = true, length = 3)
  private String currencyName;
  @PosXMLField(mandatory = true, pattern = "dd.MM.yyyy HH:mm:ss")
  private Date datetime;
  @PosXMLField(pattern = "dd.MM.yyyy HH:mm:ss")
  private Date datetimeRev;
  @PosXMLField(mandatory = true, length = 6)
  private Integer stan;
  @PosXMLField(length = 6)
  private Integer stanRev;
  @PosXMLField(mandatory = true, length = 2)
  private String type;
  @PosXMLField(mandatory = true, length = 1)
  private String cardAction;
  @PosXMLField(mandatory = true, length = 6)
  private String authorizationNo;
  private String hostAnswer;
  @PosXMLField(mandatory = true, length = 12)
  private Integer secAmount;
  @PosXMLField(length = 12)
  private Integer secAdditionalAmount;
  @PosXMLField(length = 3)
  private String secCurrencyName;
  @PosXMLField(mandatory = true, length = 1)
  private Integer signatureRequired;
  @PosXMLField(name = "PINVerified", mandatory = true, length = 1)
  private Integer pinVerified;

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return fieldOrder;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Integer getAdditionalAmount() {
    return additionalAmount;
  }

  public void setAdditionalAmount(Integer additionalAmount) {
    this.additionalAmount = additionalAmount;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }

  public Date getDatetimeRev() {
    return datetimeRev;
  }

  public void setDatetimeRev(Date datetimeRev) {
    this.datetimeRev = datetimeRev;
  }

  public Integer getStan() {
    return stan;
  }

  public void setStan(Integer stan) {
    this.stan = stan;
  }

  public Integer getStanRev() {
    return stanRev;
  }

  public void setStanRev(Integer stanRev) {
    this.stanRev = stanRev;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCardAction() {
    return cardAction;
  }

  public void setCardAction(String cardAction) {
    this.cardAction = cardAction;
  }

  public String getAuthorizationNo() {
    return authorizationNo;
  }

  public void setAuthorizationNo(String authorizationNo) {
    this.authorizationNo = authorizationNo;
  }

  public String getHostAnswer() {
    return hostAnswer;
  }

  public void setHostAnswer(String hostAnswer) {
    this.hostAnswer = hostAnswer;
  }

  public Integer getSecAmount() {
    return secAmount;
  }

  public void setSecAmount(Integer secAmount) {
    this.secAmount = secAmount;
  }

  public Integer getSecAdditionalAmount() {
    return secAdditionalAmount;
  }

  public void setSecAdditionalAmount(Integer secAdditionalAmount) {
    this.secAdditionalAmount = secAdditionalAmount;
  }

  public String getSecCurrencyName() {
    return secCurrencyName;
  }

  public void setSecCurrencyName(String secCurrencyName) {
    this.secCurrencyName = secCurrencyName;
  }

  public Integer getSignatureRequired() {
    return signatureRequired;
  }

  public void setSignatureRequired(Integer signatureRequired) {
    this.signatureRequired = signatureRequired;
  }

  public Integer getPinVerified() {
    return pinVerified;
  }

  public void setPinVerified(Integer pinVerified) {
    this.pinVerified = pinVerified;
  }

}
