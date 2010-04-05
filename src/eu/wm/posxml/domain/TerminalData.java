package eu.wm.posxml.domain;

/**
 * TerminalData XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class TerminalData implements PosXMLDomainObject {

  private static final String[] fieldOrder = new String[] { "id", "serNo", "name", "address", "merchantRegNo",
                                                           "payDesk", "verificationResult", "applicationCryptogramm",
                                                           "cid", "tsi", "cvmr", "iad", "upn", "atc", "aip", "tt",
                                                           "printerStatus" };

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return fieldOrder;
  }
  
  @PosXMLField(mandatory = true, length = 8, name = "ID")
  private String id;
  @PosXMLField(mandatory = true, length = 16)
  private String serNo;
  @PosXMLField(mandatory = true, length = 40)
  private String name;
  @PosXMLField(mandatory = true, length = 40)
  private String address;
  @PosXMLField(mandatory = true, length = 8)
  private String merchantRegNo;
  @PosXMLField(mandatory = true, length = 3)
  private String payDesk;
  @PosXMLField(length = 14)
  private String verificationResult;
  @PosXMLField(length = 20)
  private String applicationCryptogramm;
  @PosXMLField(length = 2, name = "CID")
  private String cid;
  @PosXMLField(length = 4, name = "TSI")
  private String tsi;
  @PosXMLField(length = 6, name = "CVMR")
  private String cvmr;
  @PosXMLField(length = 128, name = "IAD")
  private String iad;
  @PosXMLField(length = 8, name = "UPN")
  private String upn;
  @PosXMLField(length = 4, name = "ATC")
  private String atc;
  @PosXMLField(length = 4, name = "AIP")
  private String aip;
  @PosXMLField(length = 1, name = "TT")
  private String tt;
  @PosXMLField(length = 1)
  private String printerStatus;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSerNo() {
    return serNo;
  }

  public void setSerNo(String serNo) {
    this.serNo = serNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMerchantRegNo() {
    return merchantRegNo;
  }

  public void setMerchantRegNo(String merchantRegNo) {
    this.merchantRegNo = merchantRegNo;
  }

  public String getPayDesk() {
    return payDesk;
  }

  public void setPayDesk(String payDesk) {
    this.payDesk = payDesk;
  }

  public String getVerificationResult() {
    return verificationResult;
  }

  public void setVerificationResult(String verificationResult) {
    this.verificationResult = verificationResult;
  }

  public String getApplicationCryptogramm() {
    return applicationCryptogramm;
  }

  public void setApplicationCryptogramm(String applicationCryptogramm) {
    this.applicationCryptogramm = applicationCryptogramm;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getTsi() {
    return tsi;
  }

  public void setTsi(String tsi) {
    this.tsi = tsi;
  }

  public String getCvmr() {
    return cvmr;
  }

  public void setCvmr(String cvmr) {
    this.cvmr = cvmr;
  }

  public String getIad() {
    return iad;
  }

  public void setIad(String iad) {
    this.iad = iad;
  }

  public String getUpn() {
    return upn;
  }

  public void setUpn(String upn) {
    this.upn = upn;
  }

  public String getAtc() {
    return atc;
  }

  public void setAtc(String atc) {
    this.atc = atc;
  }

  public String getAip() {
    return aip;
  }

  public void setAip(String aip) {
    this.aip = aip;
  }

  public String getTt() {
    return tt;
  }

  public void setTt(String tt) {
    this.tt = tt;
  }

  public String getPrinterStatus() {
    return printerStatus;
  }

  public void setPrinterStatus(String printerStatus) {
    this.printerStatus = printerStatus;
  }
}
