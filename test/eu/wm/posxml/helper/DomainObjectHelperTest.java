package eu.wm.posxml.helper;

import eu.wm.posxml.domain.Card;
import eu.wm.posxml.domain.CardData;
import eu.wm.posxml.domain.PosXMLField;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test posXML helper
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class DomainObjectHelperTest {

  @Test
  public void testFormatSimpleName() {
    Assert.assertEquals("CamelCaseName", DomainObjectHelper.formatName("camelCaseName"));
  }

  @Test
  public void testFormatFieldName() {
    String name = "serviceCode";
    Assert.assertEquals("ServiceCode", DomainObjectHelper.formatName(name, DomainObjectHelper.getField(Card.class, name)));
  }

  @Test
  public void testTypeAllowed() {
    Assert.assertTrue(DomainObjectHelper.typeAllowed(String.class));
    Assert.assertTrue(DomainObjectHelper.typeAllowed(Date.class));
    Assert.assertTrue(DomainObjectHelper.typeAllowed(Card.class));
    Assert.assertFalse(DomainObjectHelper.typeAllowed(Boolean.class));
  }
  
  @Test
  public void testGetInheritedField() {
    PosXMLField field = DomainObjectHelper.getField(Card.class, "serviceCode");
    Assert.assertNotNull(field);
    field = DomainObjectHelper.getField(CardData.class, "serviceCode");
    Assert.assertNotNull(field);
  }
}
