package eu.wm.posxml.composer;

import eu.wm.posxml.domain.Card;
import eu.wm.posxml.domain.CardData;
import eu.wm.posxml.domain.TerminalData;
import eu.wm.posxml.domain.TransactionData;
import eu.wm.posxml.domain.TransactionResponse;
import java.util.Date;
import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test PosXML composer
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXMLComposerTest {

  @Test
  public void testSimpleObject() {
    Card card = new Card();
    card.setCardName("cardName");
    card.setExpires(new Date(0));
    card.setPan("pan");
    card.setServiceCode(123);
    
    Document doc = PosXMLComposer.composeXml(card);
    String result = doc.asXML();
    Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Card><Pan>pan</Pan><CardName>cardName</CardName><Expires>01/70</Expires><ServiceCode>123</ServiceCode></Card>", result);
  }

  @Test
  public void testComplexObject() {
    TransactionResponse transactionResponse = new TransactionResponse();
    transactionResponse.setAdditionalInfo("additional info");
    
    CardData cardData = new CardData();
    cardData.setAid("aidString");
    cardData.setAl("AL string");
    cardData.setCardName("card name");
    cardData.setCryptedPan("crypted Pan");
    cardData.setExpires(new Date(0));
    cardData.setPan("pan");
    cardData.setPhysicalType(3);
    cardData.setServiceCode(4);
    
    transactionResponse.setCardData(cardData);
    transactionResponse.setCustReceipt("custReceipt");
    transactionResponse.setMerchReceipt("merchReceipt");
    transactionResponse.setReason("reason");
    transactionResponse.setReturnCode(89);
    
    TerminalData terminalData = new TerminalData();
    terminalData.setAddress("address");
    terminalData.setAip("aip");
    terminalData.setApplicationCryptogramm("applicationCryptogramm");
    terminalData.setAtc("atc");
    terminalData.setCid("cid");
    terminalData.setCvmr("cvmr");
    terminalData.setIad("iad");
    terminalData.setId("id");
    terminalData.setMerchantRegNo("merchantRegNo");
    terminalData.setName("name");
    terminalData.setPayDesk("payDesk");
    terminalData.setPrinterStatus("3");
    terminalData.setSerNo("serNo");
    terminalData.setTsi("tsi");
    terminalData.setTt("tt");
    terminalData.setUpn("upn");
    terminalData.setVerificationResult("verificationResult");
    
    transactionResponse.setTerminalData(terminalData);
    
    TransactionData transactionData = new TransactionData();
    transactionData.setAdditionalAmount(1000);
    transactionData.setAmount(6000);
    transactionData.setAuthorizationNo("authorizationNo");
    transactionData.setCardAction("cardAction");
    transactionData.setCurrencyName("EEK");
    transactionData.setDatetime(new Date(0));
    transactionData.setDatetimeRev(new Date(0));
    transactionData.setHostAnswer("hostAnswer");
    transactionData.setPinVerified(1);
    transactionData.setSecAdditionalAmount(1);
    transactionData.setSecAmount(10);
    transactionData.setSecCurrencyName("EUR");
    transactionData.setSignatureRequired(0);
    transactionData.setStan(3);
    transactionData.setStanRev(33);
    transactionData.setTransactionID("transactionID");
    transactionData.setType("123");
    
    transactionResponse.setTransactionData(transactionData);
    
    String result = PosXMLComposer.composeXml(transactionResponse).asXML();
    String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<TransactionResponse><ReturnCode>89</ReturnCode><Reason>reason</Reason><TransactionData><TransactionID>transactionID</TransactionID><Amount>6000</Amount><AdditionalAmount>1000</AdditionalAmount><CurrencyName>EEK</CurrencyName><Datetime>01.01.1970 02:00:00</Datetime><DatetimeRev>01.01.1970 02:00:00</DatetimeRev><Stan>3</Stan><StanRev>33</StanRev><Type>123</Type><CardAction>cardAction</CardAction><AuthorizationNo>authorizationNo</AuthorizationNo><HostAnswer>hostAnswer</HostAnswer><SecAmount>10</SecAmount><SecAdditionalAmount>1</SecAdditionalAmount><SecCurrencyName>EUR</SecCurrencyName><SignatureRequired>0</SignatureRequired><PINVerified>1</PINVerified></TransactionData><CardData><PhysicalType>3</PhysicalType><CardName>card name</CardName><Pan>pan</Pan><Expires>01/70</Expires><ServiceCode>4</ServiceCode><AL>AL string</AL><AID>aidString</AID><CryptedPan>crypted Pan</CryptedPan></CardData><TerminalData><ID>id</ID><SerNo>serNo</SerNo><Name>name</Name><Address>address</Address><MerchantRegNo>merchantRegNo</MerchantRegNo><PayDesk>payDesk</PayDesk><VerificationResult>verificationResult</VerificationResult><ApplicationCryptogramm>applicationCryptogramm</ApplicationCryptogramm><CID>cid</CID><TSI>tsi</TSI><CVMR>cvmr</CVMR><IAD>iad</IAD><UPN>upn</UPN><ATC>atc</ATC><AIP>aip</AIP><TT>tt</TT><PrinterStatus>3</PrinterStatus></TerminalData><AdditionalInfo>additional info</AdditionalInfo><MerchReceipt>merchReceipt</MerchReceipt><CustReceipt>custReceipt</CustReceipt></TransactionResponse>";
    Assert.assertEquals(expected, result);
  }
}
