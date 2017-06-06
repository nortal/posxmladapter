package eu.wm.posxml.transport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.Validate;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper for communicating with payment terminals using XML messages
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PaymentTerminalCommunicator {

  private static final String MESSAGE_ENCODING = "UTF-8";
  private static final Logger LOGGER = LogManager.getLogger(PaymentTerminalCommunicator.class);
  
  private PaymentTerminalCommunicator() {
    // avoid instance initialization
  }

  /**
   * Send XML message to payment terminal.
   * @param message PosXML message. 
   * @param terminalAddress Terminal address.
   * @param connectionTimeout Timeout in milliseconds until a connection is etablished. A value of 0 means infinite timeout.
   * @param socketTimeout Socket timeout in milliseconds which is the timeout for waiting for data. A value of 0 means infinite timeout.
   * @return Returns response message
   * @throws IllegalStateException if message sending fails.
   */
  public static String sendMessage(String message, String terminalAddress, int connectionTimeout, int socketTimeout) {
    Validate.notNull(message, "Message must not be null!");
    Validate.notNull(terminalAddress, "Terminal address must not be null!");

    // execute request
    HttpResponse response = sendRequest(message, terminalAddress, connectionTimeout, socketTimeout);

    // check response status
    checkErrors(response);

    // read response
    String body = readResponse(response);
    if (body != null) {
    	body = body.trim();
    }
    return body;
  }

  /**
   * Send request to payment terminal
   * @throws IllegalStateException if sending fails.
   */
  private static HttpResponse sendRequest(String message, String terminalAddress, int connectionTimeout, int socketTimeout) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Sending request\n" + message);
    }
    RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT)
            .setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectionTimeout)
            .build();
    HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();

    HttpPost postMethod = new HttpPost(terminalAddress);
    try {
      postMethod.setEntity(new StringEntity(message, ContentType.TEXT_HTML.withCharset(MESSAGE_ENCODING)));
      return httpClient.execute(postMethod);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException("Unsupported encoding: " + MESSAGE_ENCODING, e);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to send message to terminal!", e);
    }
  }

  /**
   * Check errors in executed {@link PostMethod}
   */
  private static void checkErrors(HttpResponse response) {
    if (response.getStatusLine().getStatusCode() >= 400) {
      StringBuilder error = new StringBuilder("HTTP post returned error code: ");
      error.append(response.getStatusLine().getStatusCode());
      if (response.getStatusLine().getReasonPhrase() != null) {
        error.append("\n");
        error.append(response.getStatusLine().getReasonPhrase());
      }
      throw new IllegalStateException(error.toString());
    }
  }

  /**
   * Read response from postMethod
   */
  private static String readResponse(HttpResponse response) {
    InputStream inputStream = null;
    try {
      inputStream = response.getEntity().getContent();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[4096];
      while (true) {
        int read = inputStream.read(buffer);
        if (read == -1) {
          break;
        }
        outputStream.write(buffer, 0, read);
      }
      String body = new String(outputStream.toByteArray(), MESSAGE_ENCODING);
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Received response\n" + body);
      }
      return body;
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
