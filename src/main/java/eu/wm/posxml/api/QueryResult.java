package eu.wm.posxml.api;

import eu.wm.posxml.domain.PosXMLResponse;

/**
 * PosXML query response container
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class QueryResult {

  public static enum QueryResultCode {
    /**
     * Request is not valid
     */
    REQUEST_VALIDATION_ERROR,
    /**
     * Error composing XML from domain object
     */
    REQUEST_COMPOSE_ERROR,
    /**
     * Error executing query
     */
    TRANSPORT_ERROR,
    /**
     * Error parsing response XML to domain object
     */
    RESPONSE_PARSE_ERROR,
    /**
     * Response is not valid
     */
    RESPONSE_VALIDATION_ERROR,
    /**
     * Response contained error message 
     */
    RESPONSE_ERROR,
    /**
     * Uncategorized runtime exception
     */
    SYSTEM_ERROR,
    /**
     * Query resulted with correct response
     */
    SUCCESS;
  }
  private PosXMLResponse posXMLResponse;
  private Exception exception;
  private String comment;
  private boolean error = false;
  private QueryResultCode queryResultCode = QueryResultCode.SUCCESS;
  private String requestXML;
  private String responseXML;
  public PosXMLResponse getPosXMLResponse() {
    return posXMLResponse;
  }
  public void setPosXMLResponse(PosXMLResponse posXMLResponse) {
    this.posXMLResponse = posXMLResponse;
  }
  public Exception getException() {
    return exception;
  }
  public void setException(Exception exception) {
    this.exception = exception;
  }
  public boolean isError() {
    return error;
  }
  public void setError(boolean error) {
    this.error = error;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public QueryResultCode getQueryResultCode() {
    return queryResultCode;
  }
  public void setQueryResultCode(QueryResultCode queryResultCode) {
    this.queryResultCode = queryResultCode;
  }
  public String getRequestXML() {
    return requestXML;
  }
  public void setRequestXML(String requestXML) {
    this.requestXML = requestXML;
  }
  public String getResponseXML() {
    return responseXML;
  }
  public void setResponseXml(String responseXML) {
    this.responseXML = responseXML;
  }
}
