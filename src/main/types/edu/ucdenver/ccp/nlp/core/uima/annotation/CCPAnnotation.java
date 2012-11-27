

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** The superclass for all CCP annotations.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CCPAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CCPAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotationMetadata

  /** getter for annotationMetadata - gets Stores metadata for an annotation.
   * @generated */
  public AnnotationMetadata getAnnotationMetadata() {
    if (CCPAnnotation_Type.featOkTst && ((CCPAnnotation_Type)jcasType).casFeat_annotationMetadata == null)
      jcasType.jcas.throwFeatMissing("annotationMetadata", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotation");
    return (AnnotationMetadata)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPAnnotation_Type)jcasType).casFeatCode_annotationMetadata)));}
    
  /** setter for annotationMetadata - sets Stores metadata for an annotation. 
   * @generated */
  public void setAnnotationMetadata(AnnotationMetadata v) {
    if (CCPAnnotation_Type.featOkTst && ((CCPAnnotation_Type)jcasType).casFeat_annotationMetadata == null)
      jcasType.jcas.throwFeatMissing("annotationMetadata", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPAnnotation_Type)jcasType).casFeatCode_annotationMetadata, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    