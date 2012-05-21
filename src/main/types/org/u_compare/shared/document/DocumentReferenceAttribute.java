

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.document;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.u_compare.shared.ReferenceAnnotation;


/** Assumed to be used to correspond to an attribute name-id pair (IDREF in case of DTD). <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class DocumentReferenceAttribute extends DocumentAttribute {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DocumentReferenceAttribute.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DocumentReferenceAttribute() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocumentReferenceAttribute(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocumentReferenceAttribute(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: referredAnnotation

  /** getter for referredAnnotation - gets 
   * @generated */
  public ReferenceAnnotation getReferredAnnotation() {
    if (DocumentReferenceAttribute_Type.featOkTst && ((DocumentReferenceAttribute_Type)jcasType).casFeat_referredAnnotation == null)
      jcasType.jcas.throwFeatMissing("referredAnnotation", "org.u_compare.shared.document.DocumentReferenceAttribute");
    return (ReferenceAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentReferenceAttribute_Type)jcasType).casFeatCode_referredAnnotation)));}
    
  /** setter for referredAnnotation - sets  
   * @generated */
  public void setReferredAnnotation(ReferenceAnnotation v) {
    if (DocumentReferenceAttribute_Type.featOkTst && ((DocumentReferenceAttribute_Type)jcasType).casFeat_referredAnnotation == null)
      jcasType.jcas.throwFeatMissing("referredAnnotation", "org.u_compare.shared.document.DocumentReferenceAttribute");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocumentReferenceAttribute_Type)jcasType).casFeatCode_referredAnnotation, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    