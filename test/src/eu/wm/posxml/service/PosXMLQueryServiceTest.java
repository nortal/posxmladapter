package eu.wm.posxml.service;

import eu.wm.posxml.domain.ReadCardRequest;
import eu.wm.posxml.domain.TransactionResponse;
import eu.wm.posxml.service.QueryResult.QueryResultCode;
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
@PrepareForTest(PosXMLQueryService.class)
@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("eu.wm.posxml.transport.PaymentTerminalCommunicator")
public class PosXMLQueryServiceTest {

  private PosXMLQueryService service = new PosXMLQueryService();
  
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
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest><Timeout>1000</Timeout></ReadCardRequest></PosXML>", "url", 60000, 10000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><Error><ReturnCode>123</ReturnCode></Error></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(readCardRequest, "url", 10000, 60000);
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.REQUEST_VALIDATION_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNull(result.getRequestXML());
    Assert.assertNull(result.getResponseXML());
    
    // don't validate
    service.setValidateRequest(false);
    result = service.query(readCardRequest, "url", 10000, 60000);
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_ERROR, result.getQueryResultCode());
    
    PowerMock.verifyAll();
  }

  @Test
  public void queryTransportError() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 60000, 10000)).andThrow(new RuntimeException("message"));
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url", 10000, 60000);
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
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 60000, 10000)).andReturn("someNonXmlResponse");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url", 10000, 60000);
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
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 60000, 10000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><Card></Card></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url", 10000, 60000);
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
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 60000, 10000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><TransactionResponse></TransactionResponse></PosXML>");
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 60000, 10000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><TransactionResponse></TransactionResponse></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url", 10000, 60000);
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_VALIDATION_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNotNull(result.getComment());
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
    
    service.setValidateResponse(false);
    result = service.query(new ReadCardRequest(), "url", 10000, 60000);
    Assert.assertEquals(QueryResultCode.SUCCESS, result.getQueryResultCode());
    Assert.assertNotNull(result.getPosXMLResponse());
    Assert.assertTrue(result.getPosXMLResponse() instanceof TransactionResponse);
    PowerMock.verifyAll();
  }

  @Test
  public void queryResponseParseErrorMessage() {
    PowerMock.mockStatic(PaymentTerminalCommunicator.class);
    EasyMock.expect(PaymentTerminalCommunicator.sendMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><ReadCardRequest></ReadCardRequest></PosXML>", "url", 60000, 10000)).andReturn("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><Error><ReturnCode>123</ReturnCode></Error></PosXML>");
    PowerMock.replayAll();
    QueryResult result = service.query(new ReadCardRequest(), "url", 10000, 60000);
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isError());
    Assert.assertEquals(QueryResultCode.RESPONSE_ERROR, result.getQueryResultCode());
    Assert.assertNull(result.getException());
    Assert.assertNotNull(result.getComment());
    PowerMock.verifyAll();
    Assert.assertNotNull(result.getRequestXML());
    Assert.assertNotNull(result.getResponseXML());
  }
}
