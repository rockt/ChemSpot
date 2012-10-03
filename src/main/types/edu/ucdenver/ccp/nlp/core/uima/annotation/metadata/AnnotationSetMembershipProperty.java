

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation.metadata;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet;


/** A metadata property for declaring AnnotationSet membership. This will eventually replace the annotationSets field that is currently part of the CCPTextAnnotation class.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class AnnotationSetMembershipProperty extends AnnotationMetadataProperty {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnnotationSetMembershipProperty.class);
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
  protected AnnotationSetMembershipProperty() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnnotationSetMembershipProperty(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnnotationSetMembershipProperty(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotationSet

  /** getter for annotationSet - gets 
   * @generated */
  public CCPAnnotationSet getAnnotationSet() {
    if (AnnotationSetMembershipProperty_Type.featOkTst && ((AnnotationSetMembershipProperty_Type)jcasType).casFeat_annotationSet == null)
      jcasType.jcas.throwFeatMissing("annotationSet", "edu.ucdenver.ccp.nlp.core.uima.annotation.metadata.AnnotationSetMembershipProperty");
    return (CCPAnnotationSet)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationSetMembershipProperty_Type)jcasType).casFeatCode_annotationSet)));}
    
  /** setter for annotationSet - sets  
   * @generated */
  public void setAnnotationSet(CCPAnnotationSet v) {
    if (AnnotationSetMembershipProperty_Type.featOkTst && ((AnnotationSetMembershipProperty_Type)jcasType).casFeat_annotationSet == null)
      jcasType.jcas.throwFeatMissing("annotationSet", "edu.ucdenver.ccp.nlp.core.uima.annotation.metadata.AnnotationSetMembershipProperty");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationSetMembershipProperty_Type)jcasType).casFeatCode_annotationSet, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    