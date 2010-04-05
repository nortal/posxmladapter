package eu.wm.posxml.reader;

import eu.wm.posxml.domain.Card;
import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.TransactionResponse;
import java.util.Calendar;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test XML parsing into {@link PosXMLDomainObject}
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXMLReaderTest {

  @Test
  public void testSimpleObject() throws DocumentException {
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Card><Pan>pan</Pan><CardName>cardName</CardName><Expires>01/70</Expires><ServiceCode>123</ServiceCode></Card>";
    Card card = (Card) PosXMLReader.readXml(xml);
    Assert.assertNotNull(card);
    Assert.assertEquals("pan", card.getPan());
    Assert.assertEquals("cardName", card.getCardName());
    Assert.assertEquals(Integer.valueOf(123), card.getServiceCode());
    Assert.assertNotNull(card.getExpires());
    Calendar cal = Calendar.getInstance();
    cal.setTime(card.getExpires());
    Assert.assertEquals(1970, cal.get(Calendar.YEAR));
    Assert.assertEquals(0, cal.get(Calendar.MONTH));
  }
  
  @Test
  public void testComplexObject() throws DocumentException {
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<TransactionResponse><ReturnCode>89</ReturnCode><Reason>reason</Reason><TransactionData><TransactionID>transactionID</TransactionID><Amount>6000</Amount><AdditionalAmount>1000</AdditionalAmount><CurrencyName>EEK</CurrencyName><Datetime>01.01.1970 02:00:00</Datetime><DatetimeRev>01.01.1970 02:00:00</DatetimeRev><Stan>3</Stan><StanRev>33</StanRev><Type>123</Type><CardAction>cardAction</CardAction><AuthorizationNo>authorizationNo</AuthorizationNo><HostAnswer>hostAnswer</HostAnswer><SecAmount>10</SecAmount><SecAdditionalAmount>1</SecAdditionalAmount><SecCurrencyName>EUR</SecCurrencyName><SignatureRequired>0</SignatureRequired><PINVerified>1</PINVerified></TransactionData><CardData><PhysicalType>3</PhysicalType><CardName>card name</CardName><Pan>pan</Pan><Expires>01/70</Expires><ServiceCode>4</ServiceCode><AL>AL string</AL><AID>aidString</AID><CryptedPan>crypted Pan</CryptedPan></CardData><TerminalData><ID>id</ID><SerNo>serNo</SerNo><Name>name</Name><Address>address</Address><MerchantRegNo>merchantRegNo</MerchantRegNo><PayDesk>payDesk</PayDesk><VerificationResult>verificationResult</VerificationResult><ApplicationCryptogramm>applicationCryptogramm</ApplicationCryptogramm><CID>cid</CID><TSI>tsi</TSI><CVMR>cvmr</CVMR><IAD>iad</IAD><UPN>upn</UPN><ATC>atc</ATC><AIP>aip</AIP><TT>tt</TT><PrinterStatus>3</PrinterStatus></TerminalData><AdditionalInfo>additional info</AdditionalInfo><MerchReceipt>merchReceipt</MerchReceipt><CustReceipt>custReceipt</CustReceipt></TransactionResponse>";
    TransactionResponse transactionResponse = (TransactionResponse) PosXMLReader.readXml(xml);
    Assert.assertNotNull(transactionResponse);
    
    Calendar cal = Calendar.getInstance();
    // test some card value fields
    Assert.assertNotNull(transactionResponse.getCardData());
    Assert.assertEquals("pan", transactionResponse.getCardData().getPan());
    cal.setTime(transactionResponse.getCardData().getExpires());
    Assert.assertEquals(1970, cal.get(Calendar.YEAR));
    Assert.assertEquals(0, cal.get(Calendar.MONTH));
    Assert.assertEquals(Integer.valueOf(3), transactionResponse.getCardData().getPhysicalType());
    
    // test some terminalData fields
    Assert.assertNotNull(transactionResponse.getTerminalData());
    Assert.assertEquals("address", transactionResponse.getTerminalData().getAddress());
    Assert.assertEquals("upn", transactionResponse.getTerminalData().getUpn());
    
    // test some transactionData fields
    Assert.assertNotNull(transactionResponse.getTransactionData());
    Assert.assertEquals(Integer.valueOf(1000), transactionResponse.getTransactionData().getAdditionalAmount());
    Assert.assertEquals("authorizationNo", transactionResponse.getTransactionData().getAuthorizationNo());
    cal.setTime(transactionResponse.getTransactionData().getDatetime());
    Assert.assertEquals(1970, cal.get(Calendar.YEAR));
    Assert.assertEquals(0, cal.get(Calendar.MONTH));
    Assert.assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    Assert.assertEquals(Integer.valueOf(1), transactionResponse.getTransactionData().getPinVerified());
  }
}
