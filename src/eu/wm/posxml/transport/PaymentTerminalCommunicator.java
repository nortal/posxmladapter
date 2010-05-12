package eu.wm.posxml.transport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

/**
 * Helper for communicating with payment terminals using XML messages
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PaymentTerminalCommunicator {

  private static final String MESSAGE_ENCODING = "UTF-8";
  private static final Logger LOGGER = Logger.getLogger(PaymentTerminalCommunicator.class);
  
  private PaymentTerminalCommunicator() {
    // avoid instance initialization
  }

  /**
   * Send XML message to payment terminal.
   * @param message PosXML message. 
   * @param terminalAddress Terminal address.
   * @param httpTimeout Http timeout
   * @return Returns response message
   * @throws IllegalStateException if message sending fails.
   */
  public static String sendMessage(String message, String terminalAddress, int httpTimeout) {
    Validate.notNull(message, "Message must not be null!");
    Validate.notNull(terminalAddress, "Terminal address must not be null!");

    // execute request
    PostMethod postMethod = sendRequest(message, terminalAddress, httpTimeout);

    // check response status
    checkErrors(postMethod);

    // read response
    String response = readResponse(postMethod);
    if (response != null) {
      response = response.trim();
    }
    return response;
  }

  /**
   * Send request to payment terminal
   * @throws IllegalStateException if sending fails.
   */
  private static PostMethod sendRequest(String message, String terminalAddress, int httpTimeout) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Sending request\n" + message);
    }
    HttpClient httpClient = new HttpClient();
    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(httpTimeout);
    httpClient.getHttpConnectionManager().getParams().setSoTimeout(httpTimeout);
    // httpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_0);

    PostMethod postMethod = new PostMethod(terminalAddress);
    try {
      postMethod.setRequestEntity(new StringRequestEntity(message, "text/xml", MESSAGE_ENCODING));
      httpClient.executeMethod(postMethod);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Unsupported encoding: " + MESSAGE_ENCODING, e);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to send message to terminal!", e);
    }
    return postMethod;
  }

  /**
   * Check errors in executed {@link PostMethod}
   */
  private static void checkErrors(PostMethod postMethod) {
    if (postMethod.getStatusCode() != 200) {
      StringBuilder error = new StringBuilder("HTTP post returned error code: ");
      error.append(postMethod.getStatusCode());
      if (postMethod.getStatusLine() != null) {
        error.append("\n");
        error.append(postMethod.getStatusLine());
      }
      if (postMethod.getStatusText() != null) {
        error.append("\n");
        error.append(postMethod.getStatusText());
      }
      throw new IllegalStateException(error.toString());
    }
  }

  /**
   * Read response from postMethod
   */
  private static String readResponse(PostMethod postMethod) {
    InputStream inputStream = null;
    try {
      inputStream = postMethod.getResponseBodyAsStream();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[4096];
      while (true) {
        int read = inputStream.read(buffer);
        if (read == -1) {
          break;
        }
        outputStream.write(buffer, 0, read);
      }
      String response = new String(outputStream.toByteArray(), MESSAGE_ENCODING);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Received response\n" + response);
      }
      return response;
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Unsupported encoding: " + MESSAGE_ENCODING, e);
    } catch (IOException e) {
      throw new IllegalStateException("Error reading response message!", e);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          LOGGER.error("Error closing response input stream!", e);
        }
      }
    }
  }
}
