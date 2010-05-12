package eu.wm.posxml.domain;

import java.util.Date;

/**
 * IDCard XML element
 * @author Tanel Käär (tanelk@webmedia.ee)
 */
public class IDCard implements PosXMLDomainObject {

  /**
   * @see eu.wm.posxml.domain.PosXMLDomainObject#getFieldOrder()
   */
  public String[] getFieldOrder() {
    return new String[]{"surname","firstName","middleName","gender","iDCode","cardNumber","validFrom","validTo","citizenship","placeOfBirth","dateOfBirth","residencePermitType","comment1","comment2","comment3","comment4"};
  }

  @PosXMLField(mandatory=true, length=64)
  private String surname;
  @PosXMLField(mandatory=true, length=64)
  private String firstName;
  @PosXMLField(length=64)
  private String middleName;
  @PosXMLField(length=1)
  private String gender;
  @PosXMLField(mandatory=true, length=11)
  private String iDCode;
  @PosXMLField(mandatory=true, length=8)
  private String cardNumber;
  @PosXMLField(mandatory=true, pattern="dd.MM.yyyy")
  private Date validFrom;
  @PosXMLField(mandatory=true, pattern="dd.MM.yyyy")
  private Date validTo;
  @PosXMLField(length=3)
  private String citizenship;
  @PosXMLField(length=64)
  private String placeOfBirth;
  @PosXMLField(pattern="dd.MM.yyyy")
  private String dateOfBirth;
  @PosXMLField(length=64)
  private String residencePermitType;
  @PosXMLField(length=128)
  private String comment1;
  @PosXMLField(length=128)
  private String comment2;
  @PosXMLField(length=128)
  private String comment3;
  @PosXMLField(length=128)
  private String comment4;

  public String getSurname() {
    return surname;
  }
  public void setSurname(String surname) {
    this.surname = surname;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getMiddleName() {
    return middleName;
  }
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public String getiDCode() {
    return iDCode;
  }
  public void setiDCode(String iDCode) {
    this.iDCode = iDCode;
  }
  public String getCardNumber() {
    return cardNumber;
  }
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
  public Date getValidFrom() {
    return validFrom;
  }
  public void setValidFrom(Date validFrom) {
    this.validFrom = validFrom;
  }
  public Date getValidTo() {
    return validTo;
  }
  public void setValidTo(Date validTo) {
    this.validTo = validTo;
  }
  public String getCitizenship() {
    return citizenship;
  }
  public void setCitizenship(String citizenship) {
    this.citizenship = citizenship;
  }
  public String getPlaceOfBirth() {
    return placeOfBirth;
  }
  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
  public String getDateOfBirth() {
    return dateOfBirth;
  }
  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  public String getResidencePermitType() {
    return residencePermitType;
  }
  public void setResidencePermitType(String residencePermitType) {
    this.residencePermitType = residencePermitType;
  }
  public String getComment1() {
    return comment1;
  }
  public void setComment1(String comment1) {
    this.comment1 = comment1;
  }
  public String getComment2() {
    return comment2;
  }
  public void setComment2(String comment2) {
    this.comment2 = comment2;
  }
  public String getComment3() {
    return comment3;
  }
  public void setComment3(String comment3) {
    this.comment3 = comment3;
  }
  public String getComment4() {
    return comment4;
  }
  public void setComment4(String comment4) {
    this.comment4 = comment4;
  }
}
