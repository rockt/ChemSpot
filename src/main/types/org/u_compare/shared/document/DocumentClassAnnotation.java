

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.document;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.u_compare.shared.ReferenceAnnotation;


/** Assumed to be used to express an instance of a document tag. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class DocumentClassAnnotation extends DocumentAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DocumentClassAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DocumentClassAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DocumentClassAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DocumentClassAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DocumentClassAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: attributes

  /** getter for attributes - gets 
   * @generated */
  public FSArray getAttributes() {
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.u_compare.shared.document.DocumentClassAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_attributes)));}
    
  /** setter for attributes - sets  
   * @generated */
  public void setAttributes(FSArray v) {
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.u_compare.shared.document.DocumentClassAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_attributes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for attributes - gets an indexed value - 
   * @generated */
  public DocumentAttribute getAttributes(int i) {
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.u_compare.shared.document.DocumentClassAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_attributes), i);
    return (DocumentAttribute)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_attributes), i)));}

  /** indexed setter for attributes - sets an indexed value - 
   * @generated */
  public void setAttributes(int i, DocumentAttribute v) { 
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.u_compare.shared.document.DocumentClassAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_attributes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_attributes), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: elements

  /** getter for elements - gets 
   * @generated */
  public FSArray getElements() {
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.u_compare.shared.document.DocumentClassAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_elements)));}
    
  /** setter for elements - sets  
   * @generated */
  public void setElements(FSArray v) {
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.u_compare.shared.document.DocumentClassAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_elements, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for elements - gets an indexed value - 
   * @generated */
  public ReferenceAnnotation getElements(int i) {
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.u_compare.shared.document.DocumentClassAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_elements), i);
    return (ReferenceAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_elements), i)));}

  /** indexed setter for elements - sets an indexed value - 
   * @generated */
  public void setElements(int i, ReferenceAnnotation v) { 
    if (DocumentClassAnnotation_Type.featOkTst && ((DocumentClassAnnotation_Type)jcasType).casFeat_elements == null)
      jcasType.jcas.throwFeatMissing("elements", "org.u_compare.shared.document.DocumentClassAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_elements), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DocumentClassAnnotation_Type)jcasType).casFeatCode_elements), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    