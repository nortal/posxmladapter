package eu.wm.posxml.service;

import eu.wm.posxml.composer.PosXMLComposer;
import eu.wm.posxml.domain.Error;
import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.PosXMLRequest;
import eu.wm.posxml.domain.PosXMLResponse;
import eu.wm.posxml.reader.PosXMLReader;
import eu.wm.posxml.service.QueryResult.QueryResultCode;
import eu.wm.posxml.transport.PaymentTerminalCommunicator;
import eu.wm.posxml.validate.PosXmlValidator;
import org.apache.commons.lang.Validate;

/**
 * Helper class to make PosXML queries
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXMLQueryService {
  
  private boolean validateRequest = false;
  private boolean validateResponse = false;

  /**
   * Send PosXML request to payment terminal.
   * @param request PosXML request object.
   * @param terminalAddress Payment terminal address.
   * @param connectionTimeout Timeout in milliseconds until a connection is established. A value of 0 means infinite timeout.
   * @param socketTimeout Socket timeout in milliseconds which is the timeout for waiting for data. A value of 0 means infinite timeout.
   * @return Returns query result object, which contains information about the process and the resulting object if the query was successful.
   */
  public QueryResult query(PosXMLRequest request, String terminalAddress, int connectionTimeout, int socketTimeout) {
    Validate.notNull(request, "Request must not be null!");
    try {
      // validate request
      if(validateRequest) {
        QueryResult result = validateRequest(request);
        if(result != null) {
          return result;
        }      
      }
      // transform request to XML
      String requestXML;
      try {
        requestXML = PosXMLComposer.composeXml(request).asXML();
      } catch (Exception e) {
        return parseException(QueryResultCode.REQUEST_COMPOSE_ERROR, null, null, e);
      }
      
      // execute query
      String responseXML;
      try {
        responseXML = PaymentTerminalCommunicator.sendMessage(requestXML, terminalAddress, connectionTimeout, socketTimeout);
      } catch (Exception e) {
        return parseException(QueryResultCode.TRANSPORT_ERROR, requestXML, null, e);
      }
      
      // parse response
      PosXMLResponse response;
      try {
        PosXMLDomainObject object = PosXMLReader.readXml(responseXML);
        QueryResult result = checkWrongResult(object);
        if(result != null) {
          result.setRequestXML(requestXML);
          result.setResponseXml(responseXML);
          return result;
        }
        response = (PosXMLResponse) object;
      }
      catch (Exception e) {
        return parseException(QueryResultCode.RESPONSE_PARSE_ERROR, requestXML, responseXML, e);
      }
      
      // validate response
      if(validateResponse) {
        QueryResult result = validateResponse(response);
        if(result != null) {
          result.setRequestXML(requestXML);
          result.setResponseXml(responseXML);
          return result;
        }
      }
      
      QueryResult result = new QueryResult();
      result.setRequestXML(requestXML);
      result.setResponseXml(responseXML);
      result.setPosXMLResponse(response);
      // check for result type
      if(response instanceof Error) {
        result.setComment("Query resulted in PosXML error!");
        result.setError(true);
        result.setQueryResultCode(QueryResultCode.RESPONSE_ERROR);
      }
      
      return result;
    } catch (RuntimeException e) {
      return parseException(QueryResultCode.SYSTEM_ERROR, null, null, e);
    }
  }

  /**
   * Check if the object is instance of PosXMLResponse
   * @return Returns null or QueryResult, if the object is not instance of PosXMLResponse
   */
  private QueryResult checkWrongResult(PosXMLDomainObject object) {
    if(object instanceof PosXMLResponse) {
      return null;
    }
    QueryResult result = new QueryResult();
    result.setComment("Response object (" + object.getClass().getName() + ")is not instance of " + PosXMLResponse.class.getName());
    result.setError(true);
    result.setQueryResultCode(QueryResultCode.RESPONSE_PARSE_ERROR);
    return result;
  }

  /**
   * Parse an exception to QueryResult
   * @param queryResultCode Query error type
   */
  private QueryResult parseException(QueryResultCode queryResultCode, String requestXML, String responseXML, Exception e) {
    QueryResult result = new QueryResult();
    result.setComment(e.getMessage());
    result.setError(true);
    result.setException(e);
    result.setQueryResultCode(queryResultCode);
    result.setRequestXML(requestXML);
    result.setResponseXml(responseXML);
    return result;
  }

  /**
   * Validate PosXMLDomainObject
   * @return Returns null or QueryResult when the object validation failed
   */
  private QueryResult validate(PosXMLDomainObject object) {
    String comment = PosXmlValidator.validate(object);    
    if(comment == null) {
      return null;
    }
    QueryResult result = new QueryResult();
    result.setComment(comment);
    result.setError(true);
    return result;
  }

  /**
   * Validate request
   * @return Returns null or QueryResult when the object validation failed
   */
  private QueryResult validateRequest(PosXMLDomainObject object) {
    QueryResult result = validate(object);
    if(result != null) {
      result.setQueryResultCode(QueryResultCode.REQUEST_VALIDATION_ERROR);
    }
    return result;
  }

  /**
   * Validate response
   * @return Returns null or QueryResult when the object validation failed
   */
  private QueryResult validateResponse(PosXMLDomainObject object) {
    QueryResult result = validate(object);
    if(result == null) {
      return null;
    }
    result.setQueryResultCode(QueryResultCode.RESPONSE_VALIDATION_ERROR);
    return result;
  }

  public void setValidateRequest(boolean validateRequest) {
    this.validateRequest = validateRequest;
  }

  public void setValidateResponse(boolean validateResponse) {
    this.validateResponse = validateResponse;
  }
}
