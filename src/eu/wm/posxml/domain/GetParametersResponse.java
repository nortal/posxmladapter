package eu.wm.posxml.domain;

import java.math.BigDecimal;

/**
 * GetParametersResponse XML element
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class GetParametersResponse implements PosXMLResponse {

  public String[] getFieldOrder() {
    return new String[] { "showLogo", "showText1", "showText2", "showText3", "merchantInputTimeout", "waitCardTimeout",
                         "pinInputTimeout", "customerInputTimeout", "magNotUsed", "dukptInitialized", "emvDukptUsed",
                         "emvOnlinePinOnly", "cdaSupported", "pinpadEmulation", "unattEmulation", "deProtoVer",
                         "customPrint", "minCustomerAmount", "terminalOwnerCountry", "terminalID", "merchantID",
                         "merchantRegNo", "payDesk", "cardAcceptorID", "terminalIndex", "terminalVersion",
                         "countryCodeStr", "currencyExpChar", "acquiringInstitutionID", "idCardEnabled",
                         "amountBefore", "debugEnabled", "terminalOwnerAddress", "terminalOwnerName", "tipPercent",
                         "preAuthPercent", "attendantIDUsed", "terminalDiscountEnabled", "xmlDiscountEnabled",
                         "refundActionEnabled", "supportedLanguages", "terminalLanguages", "terminalCurrentLanguage",
                         "terminalPrintLanguage", "cardAcceptorBusinessCode", "cardAcceptorNameLocation",
                         "transactionTimeStr", "printerTerminalOwnerName", "printerTerminalOwnerAddress",
                         "panStarsMask", "currencyCodeStr", "currencyNameStr", "secondCurrencyCodeStr",
                         "seconCurrencyNameStr", "secondaryCurrencyRate", "printerHeader", "printerFooter",
                         "ethernetUsed", "ipAddressMode", "localIPAddress", "gatewayIPAddress", "subnetMask",
                         "pingEnabled", "hostMode", "hostX25", "hostIPAddressA", "hostIPPortA", "hostIPAddressT",
                         "hostIPPortT", "hostSSLUsed", "hostType", "dwlMode", "dwlIPAddress", "dwlIpPort", "xmlMode",
                         "xmlIpPort", "logTCPIPAddress", "logTCPIPPort", "printerMode", "serPrintDataRate",
                         "hostPhonePrefix", "hostPhoneNumber" };
  }

  @PosXMLField(length = 15)
  private String showLogo;
  @PosXMLField(mandatory = true, length = 32)
  private String showText1;
  @PosXMLField(length = 32)
  private String showText2;
  @PosXMLField(length = 32)
  private String showText3;
  @PosXMLField(length = 3)
  private Integer merchantInputTimeout;
  @PosXMLField(length = 3)
  private Integer waitCardTimeout;
  @PosXMLField(length = 3, name = "PINInputTimeout")
  private Integer pinInputTimeout;
  @PosXMLField(length = 3)
  private Integer customerInputTimeout;
  @PosXMLField(length = 1)
  private Integer magNotUsed;
  @PosXMLField(length = 1, name = "DUKPTInitialized")
  private Integer dukptInitialized;
  @PosXMLField(length = 1, name = "EMVDUKPTUsed")
  private Integer emvDukptUsed;
  @PosXMLField(length = 1, name = "EMVOnlinePINOnly")
  private Integer emvOnlinePinOnly;
  @PosXMLField(length = 1, name = "CDASupported")
  private Integer cdaSupported;
  @PosXMLField(length = 1)
  private Integer pinpadEmulation;
  @PosXMLField(length = 1)
  private Integer unattEmulation;
  @PosXMLField(length = 1, name = "DEProtoVer")
  private Integer deProtoVer;
  @PosXMLField(length = 95)
  private String customPrint;
  @PosXMLField(length = 12)
  private Integer minCustomerAmount;
  @PosXMLField(length = 2)
  private String terminalOwnerCountry;
  @PosXMLField(length = 8)
  private String terminalID;
  @PosXMLField(length = 10)
  private String merchantID;
  @PosXMLField(length = 8)
  private String merchantRegNo;
  @PosXMLField(length = 3)
  private String payDesk;
  @PosXMLField(length = 15)
  private String cardAcceptorID;
  @PosXMLField(length = 10)
  private String terminalIndex;
  @PosXMLField(length = 1)
  private String terminalVersion;
  @PosXMLField(length = 3)
  private String countryCodeStr;
  @PosXMLField(length = 2)
  private Integer currencyExpChar;
  @PosXMLField(length = 11)
  private String acquiringInstitutionID;
  @PosXMLField(length = 1, name = "IDCardEnabled")
  private Integer idCardEnabled;
  @PosXMLField(length = 1)
  private Integer amountBefore;
  @PosXMLField(length = 1)
  private Integer debugEnabled;
  @PosXMLField(length = 12)
  private String terminalOwnerAddress;
  @PosXMLField(length = 25)
  private String terminalOwnerName;
  @PosXMLField(length = 2)
  private Integer tipPercent;
  @PosXMLField(length = 2)
  private Integer preAuthPercent;
  @PosXMLField(length = 1)
  private Integer attendantIDUsed;
  @PosXMLField(length = 1)
  private Integer terminalDiscountEnabled;
  @PosXMLField(length = 1, name = "XMLDiscountEnabled")
  private Integer xmlDiscountEnabled;
  @PosXMLField(length = 1)
  private Integer refundActionEnabled;
  @PosXMLField(length = 20)
  private String supportedLanguages;
  @PosXMLField(length = 20)
  private String terminalLanguages;
  @PosXMLField(length = 2)
  private String terminalCurrentLanguage;
  @PosXMLField(length = 2)
  private String terminalPrintLanguage;
  @PosXMLField(length = 4)
  private String cardAcceptorBusinessCode;
  @PosXMLField(length = 40)
  private String cardAcceptorNameLocation;
  @PosXMLField(length = 4)
  private Integer transactionTimeStr;
  @PosXMLField(length = 32)
  private String printerTerminalOwnerName;
  @PosXMLField(length = 32)
  private String printerTerminalOwnerAddress;
  @PosXMLField(length = 6, name = "PANStarsMask")
  private String panStarsMask;
  @PosXMLField(length = 3)
  private String currencyCodeStr;
  @PosXMLField(length = 3)
  private String currencyNameStr;
  @PosXMLField(length = 3)
  private String secondCurrencyCodeStr;
  @PosXMLField(length = 3)
  private String seconCurrencyNameStr;
  @PosXMLField(length = 8)
  private BigDecimal secondaryCurrencyRate;
  @PosXMLField(length = 128)
  private String printerHeader;
  @PosXMLField(length = 128)
  private String printerFooter;
  @PosXMLField(length = 1)
  private Integer ethernetUsed;
  @PosXMLField(length = 1, name = "IPAddressMode")
  private Integer ipAddressMode;
  @PosXMLField(length = 23)
  private String localIPAddress;
  @PosXMLField(length = 23)
  private String gatewayIPAddress;
  @PosXMLField(length = 23)
  private String subnetMask;
  @PosXMLField(length = 1)
  private Integer pingEnabled;
  @PosXMLField(length = 1)
  private Integer hostMode;
  @PosXMLField(length = 1)
  private Integer hostX25;
  @PosXMLField(length = 23)
  private String hostIPAddressA;
  @PosXMLField(length = 5)
  private Integer hostIPPortA;
  @PosXMLField(length = 23)
  private String hostIPAddressT;
  @PosXMLField(length = 5)
  private Integer hostIPPortT;
  @PosXMLField(length = 1)
  private Integer hostSSLUsed;
  @PosXMLField
  private String hostType;
  @PosXMLField(length = 1, name = "DWLMode")
  private Integer dwlMode;
  @PosXMLField(length = 23, name = "DWLIPAddress")
  private String dwlIPAddress;
  @PosXMLField(length = 5, name = "DWLIPPort")
  private Integer dwlIpPort;
  @PosXMLField(length = 1, name = "XMLMode")
  private Integer xmlMode;
  @PosXMLField(length = 5, name = "XMLIPPort")
  private Integer xmlIpPort;
  @PosXMLField(length = 23)
  private String logTCPIPAddress;
  @PosXMLField(length = 5)
  private Integer logTCPIPPort;
  @PosXMLField(length = 1)
  private Integer printerMode;
  @PosXMLField(length = 5)
  private Integer serPrintDataRate;
  @PosXMLField(length = 4)
  private String hostPhonePrefix;
  @PosXMLField(length = 23)
  private String hostPhoneNumber;
  public String getShowLogo() {
    return showLogo;
  }
  public void setShowLogo(String showLogo) {
    this.showLogo = showLogo;
  }
  public String getShowText1() {
    return showText1;
  }
  public void setShowText1(String showText1) {
    this.showText1 = showText1;
  }
  public String getShowText2() {
    return showText2;
  }
  public void setShowText2(String showText2) {
    this.showText2 = showText2;
  }
  public String getShowText3() {
    return showText3;
  }
  public void setShowText3(String showText3) {
    this.showText3 = showText3;
  }
  public Integer getMerchantInputTimeout() {
    return merchantInputTimeout;
  }
  public void setMerchantInputTimeout(Integer merchantInputTimeout) {
    this.merchantInputTimeout = merchantInputTimeout;
  }
  public Integer getWaitCardTimeout() {
    return waitCardTimeout;
  }
  public void setWaitCardTimeout(Integer waitCardTimeout) {
    this.waitCardTimeout = waitCardTimeout;
  }
  public Integer getPinInputTimeout() {
    return pinInputTimeout;
  }
  public void setPinInputTimeout(Integer pinInputTimeout) {
    this.pinInputTimeout = pinInputTimeout;
  }
  public Integer getCustomerInputTimeout() {
    return customerInputTimeout;
  }
  public void setCustomerInputTimeout(Integer customerInputTimeout) {
    this.customerInputTimeout = customerInputTimeout;
  }
  public Integer getMagNotUsed() {
    return magNotUsed;
  }
  public void setMagNotUsed(Integer magNotUsed) {
    this.magNotUsed = magNotUsed;
  }
  public Integer getDukptInitialized() {
    return dukptInitialized;
  }
  public void setDukptInitialized(Integer dukptInitialized) {
    this.dukptInitialized = dukptInitialized;
  }
  public Integer getEmvDukptUsed() {
    return emvDukptUsed;
  }
  public void setEmvDukptUsed(Integer emvDukptUsed) {
    this.emvDukptUsed = emvDukptUsed;
  }
  public Integer getEmvOnlinePinOnly() {
    return emvOnlinePinOnly;
  }
  public void setEmvOnlinePinOnly(Integer emvOnlinePinOnly) {
    this.emvOnlinePinOnly = emvOnlinePinOnly;
  }
  public Integer getCdaSupported() {
    return cdaSupported;
  }
  public void setCdaSupported(Integer cdaSupported) {
    this.cdaSupported = cdaSupported;
  }
  public Integer getPinpadEmulation() {
    return pinpadEmulation;
  }
  public void setPinpadEmulation(Integer pinpadEmulation) {
    this.pinpadEmulation = pinpadEmulation;
  }
  public Integer getUnattEmulation() {
    return unattEmulation;
  }
  public void setUnattEmulation(Integer unattEmulation) {
    this.unattEmulation = unattEmulation;
  }
  public Integer getDeProtoVer() {
    return deProtoVer;
  }
  public void setDeProtoVer(Integer deProtoVer) {
    this.deProtoVer = deProtoVer;
  }
  public String getCustomPrint() {
    return customPrint;
  }
  public void setCustomPrint(String customPrint) {
    this.customPrint = customPrint;
  }
  public Integer getMinCustomerAmount() {
    return minCustomerAmount;
  }
  public void setMinCustomerAmount(Integer minCustomerAmount) {
    this.minCustomerAmount = minCustomerAmount;
  }
  public String getTerminalOwnerCountry() {
    return terminalOwnerCountry;
  }
  public void setTerminalOwnerCountry(String terminalOwnerCountry) {
    this.terminalOwnerCountry = terminalOwnerCountry;
  }
  public String getTerminalID() {
    return terminalID;
  }
  public void setTerminalID(String terminalID) {
    this.terminalID = terminalID;
  }
  public String getMerchantID() {
    return merchantID;
  }
  public void setMerchantID(String merchantID) {
    this.merchantID = merchantID;
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
  public String getCardAcceptorID() {
    return cardAcceptorID;
  }
  public void setCardAcceptorID(String cardAcceptorID) {
    this.cardAcceptorID = cardAcceptorID;
  }
  public String getTerminalIndex() {
    return terminalIndex;
  }
  public void setTerminalIndex(String terminalIndex) {
    this.terminalIndex = terminalIndex;
  }
  public String getTerminalVersion() {
    return terminalVersion;
  }
  public void setTerminalVersion(String terminalVersion) {
    this.terminalVersion = terminalVersion;
  }
  public String getCountryCodeStr() {
    return countryCodeStr;
  }
  public void setCountryCodeStr(String countryCodeStr) {
    this.countryCodeStr = countryCodeStr;
  }
  public Integer getCurrencyExpChar() {
    return currencyExpChar;
  }
  public void setCurrencyExpChar(Integer currencyExpChar) {
    this.currencyExpChar = currencyExpChar;
  }
  public String getAcquiringInstitutionID() {
    return acquiringInstitutionID;
  }
  public void setAcquiringInstitutionID(String acquiringInstitutionID) {
    this.acquiringInstitutionID = acquiringInstitutionID;
  }
  public Integer getIdCardEnabled() {
    return idCardEnabled;
  }
  public void setIdCardEnabled(Integer idCardEnabled) {
    this.idCardEnabled = idCardEnabled;
  }
  public Integer getAmountBefore() {
    return amountBefore;
  }
  public void setAmountBefore(Integer amountBefore) {
    this.amountBefore = amountBefore;
  }
  public Integer getDebugEnabled() {
    return debugEnabled;
  }
  public void setDebugEnabled(Integer debugEnabled) {
    this.debugEnabled = debugEnabled;
  }
  public String getTerminalOwnerAddress() {
    return terminalOwnerAddress;
  }
  public void setTerminalOwnerAddress(String terminalOwnerAddress) {
    this.terminalOwnerAddress = terminalOwnerAddress;
  }
  public String getTerminalOwnerName() {
    return terminalOwnerName;
  }
  public void setTerminalOwnerName(String terminalOwnerName) {
    this.terminalOwnerName = terminalOwnerName;
  }
  public Integer getTipPercent() {
    return tipPercent;
  }
  public void setTipPercent(Integer tipPercent) {
    this.tipPercent = tipPercent;
  }
  public Integer getPreAuthPercent() {
    return preAuthPercent;
  }
  public void setPreAuthPercent(Integer preAuthPercent) {
    this.preAuthPercent = preAuthPercent;
  }
  public Integer getAttendantIDUsed() {
    return attendantIDUsed;
  }
  public void setAttendantIDUsed(Integer attendantIDUsed) {
    this.attendantIDUsed = attendantIDUsed;
  }
  public Integer getTerminalDiscountEnabled() {
    return terminalDiscountEnabled;
  }
  public void setTerminalDiscountEnabled(Integer terminalDiscountEnabled) {
    this.terminalDiscountEnabled = terminalDiscountEnabled;
  }
  public Integer getXmlDiscountEnabled() {
    return xmlDiscountEnabled;
  }
  public void setXmlDiscountEnabled(Integer xmlDiscountEnabled) {
    this.xmlDiscountEnabled = xmlDiscountEnabled;
  }
  public Integer getRefundActionEnabled() {
    return refundActionEnabled;
  }
  public void setRefundActionEnabled(Integer refundActionEnabled) {
    this.refundActionEnabled = refundActionEnabled;
  }
  public String getSupportedLanguages() {
    return supportedLanguages;
  }
  public void setSupportedLanguages(String supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
  }
  public String getTerminalLanguages() {
    return terminalLanguages;
  }
  public void setTerminalLanguages(String terminalLanguages) {
    this.terminalLanguages = terminalLanguages;
  }
  public String getTerminalCurrentLanguage() {
    return terminalCurrentLanguage;
  }
  public void setTerminalCurrentLanguage(String terminalCurrentLanguage) {
    this.terminalCurrentLanguage = terminalCurrentLanguage;
  }
  public String getTerminalPrintLanguage() {
    return terminalPrintLanguage;
  }
  public void setTerminalPrintLanguage(String terminalPrintLanguage) {
    this.terminalPrintLanguage = terminalPrintLanguage;
  }
  public String getCardAcceptorBusinessCode() {
    return cardAcceptorBusinessCode;
  }
  public void setCardAcceptorBusinessCode(String cardAcceptorBusinessCode) {
    this.cardAcceptorBusinessCode = cardAcceptorBusinessCode;
  }
  public String getCardAcceptorNameLocation() {
    return cardAcceptorNameLocation;
  }
  public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
    this.cardAcceptorNameLocation = cardAcceptorNameLocation;
  }
  public Integer getTransactionTimeStr() {
    return transactionTimeStr;
  }
  public void setTransactionTimeStr(Integer transactionTimeStr) {
    this.transactionTimeStr = transactionTimeStr;
  }
  public String getPrinterTerminalOwnerName() {
    return printerTerminalOwnerName;
  }
  public void setPrinterTerminalOwnerName(String printerTerminalOwnerName) {
    this.printerTerminalOwnerName = printerTerminalOwnerName;
  }
  public String getPrinterTerminalOwnerAddress() {
    return printerTerminalOwnerAddress;
  }
  public void setPrinterTerminalOwnerAddress(String printerTerminalOwnerAddress) {
    this.printerTerminalOwnerAddress = printerTerminalOwnerAddress;
  }
  public String getPanStarsMask() {
    return panStarsMask;
  }
  public void setPanStarsMask(String panStarsMask) {
    this.panStarsMask = panStarsMask;
  }
  public String getCurrencyCodeStr() {
    return currencyCodeStr;
  }
  public void setCurrencyCodeStr(String currencyCodeStr) {
    this.currencyCodeStr = currencyCodeStr;
  }
  public String getCurrencyNameStr() {
    return currencyNameStr;
  }
  public void setCurrencyNameStr(String currencyNameStr) {
    this.currencyNameStr = currencyNameStr;
  }
  public String getSecondCurrencyCodeStr() {
    return secondCurrencyCodeStr;
  }
  public void setSecondCurrencyCodeStr(String secondCurrencyCodeStr) {
    this.secondCurrencyCodeStr = secondCurrencyCodeStr;
  }
  public String getSeconCurrencyNameStr() {
    return seconCurrencyNameStr;
  }
  public void setSeconCurrencyNameStr(String seconCurrencyNameStr) {
    this.seconCurrencyNameStr = seconCurrencyNameStr;
  }
  public BigDecimal getSecondaryCurrencyRate() {
    return secondaryCurrencyRate;
  }
  public void setSecondaryCurrencyRate(BigDecimal secondaryCurrencyRate) {
    this.secondaryCurrencyRate = secondaryCurrencyRate;
  }
  public String getPrinterHeader() {
    return printerHeader;
  }
  public void setPrinterHeader(String printerHeader) {
    this.printerHeader = printerHeader;
  }
  public String getPrinterFooter() {
    return printerFooter;
  }
  public void setPrinterFooter(String printerFooter) {
    this.printerFooter = printerFooter;
  }
  public Integer getEthernetUsed() {
    return ethernetUsed;
  }
  public void setEthernetUsed(Integer ethernetUsed) {
    this.ethernetUsed = ethernetUsed;
  }
  public Integer getIpAddressMode() {
    return ipAddressMode;
  }
  public void setIpAddressMode(Integer ipAddressMode) {
    this.ipAddressMode = ipAddressMode;
  }
  public String getLocalIPAddress() {
    return localIPAddress;
  }
  public void setLocalIPAddress(String localIPAddress) {
    this.localIPAddress = localIPAddress;
  }
  public String getGatewayIPAddress() {
    return gatewayIPAddress;
  }
  public void setGatewayIPAddress(String gatewayIPAddress) {
    this.gatewayIPAddress = gatewayIPAddress;
  }
  public String getSubnetMask() {
    return subnetMask;
  }
  public void setSubnetMask(String subnetMask) {
    this.subnetMask = subnetMask;
  }
  public Integer getPingEnabled() {
    return pingEnabled;
  }
  public void setPingEnabled(Integer pingEnabled) {
    this.pingEnabled = pingEnabled;
  }
  public Integer getHostMode() {
    return hostMode;
  }
  public void setHostMode(Integer hostMode) {
    this.hostMode = hostMode;
  }
  public Integer getHostX25() {
    return hostX25;
  }
  public void setHostX25(Integer hostX25) {
    this.hostX25 = hostX25;
  }
  public String getHostIPAddressA() {
    return hostIPAddressA;
  }
  public void setHostIPAddressA(String hostIPAddressA) {
    this.hostIPAddressA = hostIPAddressA;
  }
  public Integer getHostIPPortA() {
    return hostIPPortA;
  }
  public void setHostIPPortA(Integer hostIPPortA) {
    this.hostIPPortA = hostIPPortA;
  }
  public String getHostIPAddressT() {
    return hostIPAddressT;
  }
  public void setHostIPAddressT(String hostIPAddressT) {
    this.hostIPAddressT = hostIPAddressT;
  }
  public Integer getHostIPPortT() {
    return hostIPPortT;
  }
  public void setHostIPPortT(Integer hostIPPortT) {
    this.hostIPPortT = hostIPPortT;
  }
  public Integer getHostSSLUsed() {
    return hostSSLUsed;
  }
  public void setHostSSLUsed(Integer hostSSLUsed) {
    this.hostSSLUsed = hostSSLUsed;
  }
  public String getHostType() {
    return hostType;
  }
  public void setHostType(String hostType) {
    this.hostType = hostType;
  }
  public Integer getDwlMode() {
    return dwlMode;
  }
  public void setDwlMode(Integer dwlMode) {
    this.dwlMode = dwlMode;
  }
  public String getDwlIPAddress() {
    return dwlIPAddress;
  }
  public void setDwlIPAddress(String dwlIPAddress) {
    this.dwlIPAddress = dwlIPAddress;
  }
  public Integer getDwlIpPort() {
    return dwlIpPort;
  }
  public void setDwlIpPort(Integer dwlIpPort) {
    this.dwlIpPort = dwlIpPort;
  }
  public Integer getXmlMode() {
    return xmlMode;
  }
  public void setXmlMode(Integer xmlMode) {
    this.xmlMode = xmlMode;
  }
  public Integer getXmlIpPort() {
    return xmlIpPort;
  }
  public void setXmlIpPort(Integer xmlIpPort) {
    this.xmlIpPort = xmlIpPort;
  }
  public String getLogTCPIPAddress() {
    return logTCPIPAddress;
  }
  public void setLogTCPIPAddress(String logTCPIPAddress) {
    this.logTCPIPAddress = logTCPIPAddress;
  }
  public Integer getLogTCPIPPort() {
    return logTCPIPPort;
  }
  public void setLogTCPIPPort(Integer logTCPIPPort) {
    this.logTCPIPPort = logTCPIPPort;
  }
  public Integer getPrinterMode() {
    return printerMode;
  }
  public void setPrinterMode(Integer printerMode) {
    this.printerMode = printerMode;
  }
  public Integer getSerPrintDataRate() {
    return serPrintDataRate;
  }
  public void setSerPrintDataRate(Integer serPrintDataRate) {
    this.serPrintDataRate = serPrintDataRate;
  }
  public String getHostPhonePrefix() {
    return hostPhonePrefix;
  }
  public void setHostPhonePrefix(String hostPhonePrefix) {
    this.hostPhonePrefix = hostPhonePrefix;
  }
  public String getHostPhoneNumber() {
    return hostPhoneNumber;
  }
  public void setHostPhoneNumber(String hostPhoneNumber) {
    this.hostPhoneNumber = hostPhoneNumber;
  }
}
