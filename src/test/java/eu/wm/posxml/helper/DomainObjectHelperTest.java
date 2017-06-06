package eu.wm.posxml.helper;

import eu.wm.posxml.domain.Card;
import eu.wm.posxml.domain.CardData;
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
  public void formatSimpleName() {
    Assert.assertNull(DomainObjectHelper.formatName(null));
    Assert.assertEquals("CamelCaseName", DomainObjectHelper.formatName("camelCaseName"));
  }

  @Test
  public void formatFieldName() {
    String name = "serviceCode";
    Assert.assertEquals("ServiceCode", DomainObjectHelper.formatName(name, DomainObjectHelper.getField(Card.class, name)));
  }

  @Test
  public void typeAllowed() {
    Assert.assertTrue(DomainObjectHelper.typeAllowed(String.class));
    Assert.assertTrue(DomainObjectHelper.typeAllowed(Date.class));
    Assert.assertTrue(DomainObjectHelper.typeAllowed(Card.class));
    Assert.assertFalse(DomainObjectHelper.typeAllowed(Boolean.class));
    Assert.assertFalse(DomainObjectHelper.typeAllowed(null));
  }

  @Test
  public void getField() {
    Assert.assertNotNull(DomainObjectHelper.getField(Card.class, "pan"));
    Assert.assertNull(DomainObjectHelper.getField(Card.class, "nonExistingField"));
    Assert.assertNull(DomainObjectHelper.getField(Card.class, null));
    Assert.assertNull(DomainObjectHelper.getField(null, "nonExistingField"));
  }

  
  @Test
  public void getInheritedField() {
    Assert.assertNotNull(DomainObjectHelper.getField(Card.class, "serviceCode"));
    Assert.assertNotNull(DomainObjectHelper.getField(CardData.class, "serviceCode"));
  }
  
  @Test
  public void getFieldValue() {
    Card card = new Card();
    card.setCardName("cardName");
    Assert.assertEquals(card.getCardName(), DomainObjectHelper.getFieldValue(card, "cardName"));
    Assert.assertNull(DomainObjectHelper.getFieldValue(card, "pan"));
    Assert.assertNull(DomainObjectHelper.getFieldValue(card, "nonExistingField"));
    Assert.assertNull(DomainObjectHelper.getFieldValue(card, null));
    Assert.assertNull(DomainObjectHelper.getFieldValue(null, "nonExistingField"));
  }
}
