

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;


/** THIS TYPE WILL BE CHANGED TO INHERIT APACHE UIMA ANNOTATIONMETADATA TYPE, WHEN IT RELEASED. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class AnnotationMetadata extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AnnotationMetadata.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnnotationMetadata() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnnotationMetadata(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnnotationMetadata(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: confidence

  /** getter for confidence - gets Range of confidence value should be between 0.0 (lowest) and 1.0 (highest).
   * @generated */
  public float getConfidence() {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "org.u_compare.shared.AnnotationMetadata");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_confidence);}
    
  /** setter for confidence - sets Range of confidence value should be between 0.0 (lowest) and 1.0 (highest). 
   * @generated */
  public void setConfidence(float v) {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "org.u_compare.shared.AnnotationMetadata");
    jcasType.ll_cas.ll_setFloatValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_confidence, v);}    
  }

    