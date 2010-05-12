package eu.wm.posxml.reader;

import eu.wm.posxml.reader.Dom4jDocumentHelper;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test helper methods
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class Dom4jDocumentHelperTest {

  @Test
  public void getValue() throws DocumentException {
    Document doc = Dom4jDocumentHelper.getDocument("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<PosXML version=\"6.0.2\"><Error><ReturnCode>123</ReturnCode></Error></PosXML>");
    List<Node> nodes = Dom4jDocumentHelper.getNodes(doc, "/PosXML/Error/ReturnCode");
    Assert.assertEquals(1, nodes.size());
    Assert.assertEquals("123", Dom4jDocumentHelper.getValue(nodes.get(0), "text()"));
    Assert.assertNull(Dom4jDocumentHelper.getValue(nodes.get(0), "nonExistingChild"));
  }
}