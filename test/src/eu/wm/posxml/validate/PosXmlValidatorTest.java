package eu.wm.posxml.validate;

import eu.wm.posxml.domain.Card;
import eu.wm.posxml.domain.CardData;
import eu.wm.posxml.domain.PosXMLDomainObject;
import eu.wm.posxml.domain.TransactionResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test PosXmlValidator functions
 * 
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class PosXmlValidatorTest {

  @Test
  public void validateSimple() {
    String validationResult = PosXmlValidator.validate((PosXMLDomainObject)null);
    Assert.assertEquals("Validation failed: object is null!", validationResult);
    Card card = new Card();
    // test string length
    card.setCardName("too long card name (over 40 symbols).....");
    card.setServiceCode(1234);
    validationResult = PosXmlValidator.validate(card);
    Assert.assertEquals("Field /Card/pan is mandatory!\nField /Card/cardName maximum length is 40!\nField /Card/expires is mandatory!\nField /Card/serviceCode maximum length is 3!\n", validationResult);
    
    // make object valid
    card.setCardName("Card name exactly 40 symbols____________");
    card.setExpires("01/70");
    card.setPan("p");
    card.setServiceCode(999);
    Assert.assertNull(PosXmlValidator.validate(card));
  }
  
  @Test
  public void validateComplex() {
    TransactionResponse transactionResponse = new TransactionResponse();
    CardData cardData = new CardData();
    cardData.setPhysicalType(10);
    transactionResponse.setCardData(cardData);
    String validationResult = PosXmlValidator.validate(transactionResponse);
    Assert.assertNotNull(validationResult);
    Assert.assertTrue(validationResult.contains("Field /TransactionResponse/cardData/physicalType maximum length is 1!"));
  }
}
