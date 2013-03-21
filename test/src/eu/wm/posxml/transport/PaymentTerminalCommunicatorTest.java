package eu.wm.posxml.transport;

import eu.wm.posxml.JUnitTestHelper;
import eu.wm.posxml.composer.PosXMLComposer;
import eu.wm.posxml.domain.Card;
import eu.wm.posxml.domain.GetParametersRequest;
import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.ReadCardRequest;
import eu.wm.posxml.domain.ReadCardResponse;
import eu.wm.posxml.domain.ReadIDCardRequest;
import eu.wm.posxml.domain.TransactionMagStripeRequest;
import eu.wm.posxml.domain.TransactionRequest;
import eu.wm.posxml.reader.PosXMLReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test communication layer
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PaymentTerminalCommunicatorTest {

  private static String terminalAddress = JUnitTestHelper.getProperty(JUnitTestHelper.PROPERTY__TERMINAL_ADDRESS);
  private static int timeout  = 60000;

  @Before
  public void setUp() throws InterruptedException {
    Thread.sleep(2000);
  }
  
  @Test
  public void sendNullMessage() {
    try {
      PaymentTerminalCommunicator.sendMessage(null, null, 0);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // normal behavior
    }
  }

  @Test
  public void idcardRequest() throws DocumentException {
    ReadIDCardRequest request = new ReadIDCardRequest();
    request.setTimeout(60);
    request.setVerifyPIN(0);
    request.setLanguage("et");

    Document doc = PosXMLComposer.composeXml(request);
    String response = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    PosXMLDomainObject responseObject = PosXMLReader.readXml(response);
    Assert.assertNotNull(responseObject);
  }

  @Test
  public void getParameters() throws DocumentException {
    Document doc = PosXMLComposer.composeXml(new GetParametersRequest());
    String xml = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    PosXMLDomainObject responseObject = PosXMLReader.readXml(xml);
    Assert.assertNotNull(responseObject);
  }

  @Test
  public void getParametersPlain() {
    String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><GetParametersRequest></GetParametersRequest></PosXML>";
    String response = PaymentTerminalCommunicator.sendMessage(request, terminalAddress, timeout);
    Assert.assertNotNull(response);
  }
  
  @Test
  public void readCardData() throws DocumentException {
    ReadCardRequest cardRequest = new ReadCardRequest();
    cardRequest.setTimeout(60);
    cardRequest.setLanguage("et");

    Document doc = PosXMLComposer.composeXml(cardRequest);
    String xml = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    PosXMLDomainObject responseObject = PosXMLReader.readXml(xml);
    Assert.assertNotNull(responseObject);
  }
  
  @Test
  public void transactionRequest() throws DocumentException {
    TransactionRequest request = new TransactionRequest();
    request.setAmount(10000);
    request.setAttendantID("12345");
    request.setCardholderPresent(1);
    request.setCurrencyName("EUR");
    request.setPrintReceipt(2);
    request.setTransactionID("123233");
    Document doc = PosXMLComposer.composeXml(request);
    String responseXML = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    PosXMLDomainObject responseObject = PosXMLReader.readXml(responseXML);
    Assert.assertNotNull(responseObject);
  }
  
  @Test
  public void transactionRequestManual() throws DocumentException {
    TransactionRequest request = new TransactionRequest();
    request.setAmount(10000);
    request.setAttendantID("12345");
    request.setCardholderPresent(1);
    request.setCurrencyName("EUR");
    request.setPrintReceipt(2);
    request.setTransactionID("123233");
    request.setManual(1);
    
    Document doc = PosXMLComposer.composeXml(request);
    String responseXML = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    PosXMLDomainObject responseObject = PosXMLReader.readXml(responseXML);
    Assert.assertNotNull(responseObject);
  }

  @Test
  public void transactionMagStripeRequest() throws DocumentException, InterruptedException {
    // read card data
    ReadCardRequest cardRequest = new ReadCardRequest();
    cardRequest.setTimeout(60);
    cardRequest.setLanguage("et");

    Document doc = PosXMLComposer.composeXml(cardRequest);
    String xml = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    PosXMLDomainObject responseObject = PosXMLReader.readXml(xml);
    Assert.assertNotNull(responseObject);
    Assert.assertTrue(responseObject instanceof ReadCardResponse);
    ReadCardResponse cardData = (ReadCardResponse) responseObject;
    Assert.assertNotNull(cardData.getCard());
    Card card = cardData.getCard();
    
    // sleep a bit before sending another request to terminal
    Thread.sleep(1000);
    
    TransactionMagStripeRequest request = new TransactionMagStripeRequest();
    request.setAmount(10000);
    request.setCurrencyName("EUR");
    request.setPan(card.getPan());
    request.setExpires(card.getExpires());
    request.setPrintReceipt(2);
    request.setCardPresent(1);
    request.setCardholderPresent(1);
    request.setTransactionID("123233");
    doc = PosXMLComposer.composeXml(request);
    String responseXML = PaymentTerminalCommunicator.sendMessage(doc.asXML(), terminalAddress, timeout);
    responseObject = PosXMLReader.readXml(responseXML);
    Assert.assertNotNull(responseObject);
  }
}
