package eu.wm.posxml.service;

import eu.wm.posxml.api.PosXmlClient;
import eu.wm.posxml.api.QueryResult;
import eu.wm.posxml.api.QueryResult.QueryResultCode;
import eu.wm.posxml.domain.ReadCardRequest;
import eu.wm.posxml.domain.TransactionRequest;
import eu.wm.posxml.domain.TransactionResponse;
import eu.wm.posxml.transport.PaymentTerminalCommunicator;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test PosXMLQueryService
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
@PrepareForTest(PosXmlClient.class)
@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("eu.wm.posxml.transport.PaymentTerminalCommunicator")
public class PosXMLQueryServiceTest {

  private final PosXmlClient service = new PosXmlClient();
  
  @Before
  public void setUp() {
    PowerMock.resetAll();
    service.setValidateRequest(true);
    service.setValidateResponse(true);
  }
  
  @Test
  public void queryInvalidRequest() {
    ReadCardRequest readCardRequest = new ReadCardRequest();
    // set timeout grater than allowed
    readCardRequest.setTimeout(1000);
    
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest><Timeout>1000</Timeout></ReadCardRequest></PosXML>", "url", 10000, 60000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><Error><ReturnCode>123</ReturnCode></Error></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(readCardRequest, "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.REQUEST_VALIDATION_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNull(result.getRequestXML());
    Assert.assertNull(result.getResponseXML());
    
    // don't validate
    service.setValidateRequest(false);
    result = service.query(readCardRequest, "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_ERROR, result.getQueryResultCode());
    
    PowerMock.verifyAll();
  }

  @Test
  public void queryTransportError() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 10000, 60000)).andThrow(new RuntimeException("message"));
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.TRANSPORT_ERROR, result.getQueryResultCode());
    Assert.assertNotNull(result.getException());
    Assert.assertEquals("message", result.getComment());
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNull(result.getResponseXML());
    PowerMock.verifyAll();
  }

  @Test
  public void queryResponseParseError() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 10000, 60000)).andReturn("someNonXmlResponse");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_PARSE_ERROR, result.getQueryResultCode());
    Assert.assertNotNull(result.getException());
    Assert.assertNotNull(result.getComment());
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
    PowerMock.verifyAll();
  }

  @Test
  public void queryResponseParseErrorWrongType() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 10000, 60000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><Card></Card></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_PARSE_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNotNull(result.getComment());
    PowerMock.verifyAll();
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
  }

  @Test
  public void queryResponseValidateError() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 10000, 60000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><TransactionResponse></TransactionResponse></PosXML>");
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 10000, 60000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><TransactionResponse></TransactionResponse></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_VALIDATION_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNotNull(result.getComment());
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
    
    service.setValidateResponse(false);
    result = service.query(new ReadCardRequest(), "url");
    Assert.assertEquals(QueryResultCode.SUCCESS, result.getQueryResultCode());
    Assert.assertNotNull(result.getPosXMLResponse());
    Assert.assertTrue(result.getPosXMLResponse() instanceof TransactionResponse);
    PowerMock.verifyAll();
  }

  @Test
  public void queryResponseParseErrorMessage() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 10000, 60000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"7.2.0\"><Error><ReturnCode>123</ReturnCode></Error></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url");
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNotNull(result.getComment());
    PowerMock.verifyAll();
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
  }

  @Test
  public void invalidTransactionTest() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
    		"<PosXML version=\"7.2.0\">" +
    		"<TransactionRequest>" +
    		"<Amount>3225</Amount>" +
    		"<CurrencyName>EUR</CurrencyName>" +
    		"<TransactionID>13-94598</TransactionID>" +
    		"<PrintReceipt>1</PrintReceipt>" +
    		"<Timeout>60</Timeout>" +
    		"<CardholderPresent>1</CardholderPresent>" +
    		"</TransactionRequest>" +
    		"</PosXML>",
                                                            "url",
                                                            10000,
                                                            60000)).andReturn(
                                                                              "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                                                                              "<PosXML version=\"7.2.0\">" +
                                                                              "<TransactionResponse>" +
                                                                              "<ReturnCode>0</ReturnCode>" +
                                                                              "<Reason></Reason>" +
                                                                              "<TransactionData>" +
                                                                              "<Type>T1</Type>" +
                                                                              "<CardAction>2</CardAction>" +
                                                                              "<AuthorizationNo>      </AuthorizationNo>" +
                                                                              "<HostAnswer>---</HostAnswer>" +
                                                                              "<SignatureRequired>0</SignatureRequired>" +
                                                                              "<PINVerified>0</PINVerified>" +
                                                                              "<TransactionCompletion>NOTIFICATION</TransactionCompletion>" +
                                                                              "</TransactionData>" +
                                                                              "<CardData>" +
                                                                              "<Expires>**/**</Expires>" +
                                                                              "</CardData>" +
                                                                              "<TerminalData>" +
                                                                              "<SerNo>ICT220-01T1003F-12036CT71263522 IPP320-71134136</SerNo>" +
                                                                              "<ID>ICT00210</ID>" +
                                                                              "<Name>Hambapolikliinik II korrus</Name>" +
                                                                              "<Address>Raekoja plats 6,51003 Tartu EST</Address>" +
                                                                              "<MerchantRegNo>90001478</MerchantRegNo>" +
                                                                              "<PayDesk>122</PayDesk>" +
                                                                              "<PrinterStatus>2</PrinterStatus>" +
                                                                              "</TerminalData>" +
                                                                              "</TransactionResponse>" +
                                                                              "</PosXML>");
    PowerMock.replayAll();
    TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setAmount(3225);
    transactionRequest.setCurrencyName("EUR");
    transactionRequest.setTransactionID("13-94598");
    transactionRequest.setPrintReceipt(1);
    transactionRequest.setTimeout(60);
    transactionRequest.setCardholderPresent(1);
    QueryResult result = service.query(transactionRequest, "url");
    PowerMock.verifyAll();
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_VALIDATION_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNotNull("Transaction failed", result.getComment());
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
  }
}
