

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.document;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Assumed to be used to correspond to an attribute name-value pair (CDATA in case of DTD). <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class DocumentValueAttribute extends DocumentAttribute {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DocumentValueAttribute.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DocumentValueAttribute() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocumentValueAttribute(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocumentValueAttribute(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (DocumentValueAttribute_Type.featOkTst && ((DocumentValueAttribute_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.u_compare.shared.document.DocumentValueAttribute");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocumentValueAttribute_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (DocumentValueAttribute_Type.featOkTst && ((DocumentValueAttribute_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.u_compare.shared.document.DocumentValueAttribute");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocumentValueAttribute_Type)jcasType).casFeatCode_value, v);}    
  }

    