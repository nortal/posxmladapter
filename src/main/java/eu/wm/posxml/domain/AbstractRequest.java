package eu.wm.posxml.domain;

/**
 * Abstract request class which defines no validation by default.
 * @author Tanel Käär (Tanel.Kaar@nortal.com)
 */
public abstract class AbstractRequest implements PosXMLRequest {


  @Override
  public String validate() {
    return null;
  }

}
