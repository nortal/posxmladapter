package eu.wm.posxml.domain;

public class SetParametersRequest extends AbstractRequest {
  @PosXMLField(name = "XMLECRName")
  private String xmlEcrName;

  @Override
  public String[] getFieldOrder() {
    return new String[] { "xmlEcrName" };
  }

  public String getXmlEcrName() {
    return xmlEcrName;
  }

  public void setXmlEcrName(String xmlEcrName) {
    this.xmlEcrName = xmlEcrName;
  }

}
