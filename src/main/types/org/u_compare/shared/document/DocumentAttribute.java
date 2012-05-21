

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.document;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.u_compare.shared.ExternalReference;
import org.apache.uima.jcas.cas.TOP;


/** Abstract type for document attributes, which appear in the document tags. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class DocumentAttribute extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DocumentAttribute.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DocumentAttribute() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocumentAttribute(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocumentAttribute(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: attributeName

  /** getter for attributeName - gets 
   * @generated */
  public ExternalReference getAttributeName() {
    if (DocumentAttribute_Type.featOkTst && ((DocumentAttribute_Type)jcasType).casFeat_attributeName == null)
      jcasType.jcas.throwFeatMissing("attributeName", "org.u_compare.shared.document.DocumentAttribute");
    return (ExternalReference)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentAttribute_Type)jcasType).casFeatCode_attributeName)));}
    
  /** setter for attributeName - sets  
   * @generated */
  public void setAttributeName(ExternalReference v) {
    if (DocumentAttribute_Type.featOkTst && ((DocumentAttribute_Type)jcasType).casFeat_attributeName == null)
      jcasType.jcas.throwFeatMissing("attributeName", "org.u_compare.shared.document.DocumentAttribute");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocumentAttribute_Type)jcasType).casFeatCode_attributeName, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    