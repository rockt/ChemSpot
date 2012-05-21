

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class ReferenceAnnotation extends BaseAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ReferenceAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ReferenceAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ReferenceAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ReferenceAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ReferenceAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: externalReference

  /** getter for externalReference - gets 
   * @generated */
  public ExternalReference getExternalReference() {
    if (ReferenceAnnotation_Type.featOkTst && ((ReferenceAnnotation_Type)jcasType).casFeat_externalReference == null)
      jcasType.jcas.throwFeatMissing("externalReference", "org.u_compare.shared.ReferenceAnnotation");
    return (ExternalReference)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ReferenceAnnotation_Type)jcasType).casFeatCode_externalReference)));}
    
  /** setter for externalReference - sets  
   * @generated */
  public void setExternalReference(ExternalReference v) {
    if (ReferenceAnnotation_Type.featOkTst && ((ReferenceAnnotation_Type)jcasType).casFeat_externalReference == null)
      jcasType.jcas.throwFeatMissing("externalReference", "org.u_compare.shared.ReferenceAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ReferenceAnnotation_Type)jcasType).casFeatCode_externalReference, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    