

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** The annotation set provides a means for arbitrarily categorizing or clustering groups of annotations. Annotations can be associated with multiple annotation groups. Examples of use include, defining Gold Standard annotation sets, and delineating between the use of different parameters during annotation, among others. Each annotation set is associated with a unique ID, a name and a description.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPAnnotationSet extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPAnnotationSet.class);
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
  protected CCPAnnotationSet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPAnnotationSet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPAnnotationSet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotationSetID

  /** getter for annotationSetID - gets An integer uniquely identifying a particular annotation set.
   * @generated */
  public int getAnnotationSetID() {
    if (CCPAnnotationSet_Type.featOkTst && ((CCPAnnotationSet_Type)jcasType).casFeat_annotationSetID == null)
      jcasType.jcas.throwFeatMissing("annotationSetID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPAnnotationSet_Type)jcasType).casFeatCode_annotationSetID);}
    
  /** setter for annotationSetID - sets An integer uniquely identifying a particular annotation set. 
   * @generated */
  public void setAnnotationSetID(int v) {
    if (CCPAnnotationSet_Type.featOkTst && ((CCPAnnotationSet_Type)jcasType).casFeat_annotationSetID == null)
      jcasType.jcas.throwFeatMissing("annotationSetID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPAnnotationSet_Type)jcasType).casFeatCode_annotationSetID, v);}    
   
    
  //*--------------*
  //* Feature: annotationSetName

  /** getter for annotationSetName - gets The name of the annotation set.
   * @generated */
  public String getAnnotationSetName() {
    if (CCPAnnotationSet_Type.featOkTst && ((CCPAnnotationSet_Type)jcasType).casFeat_annotationSetName == null)
      jcasType.jcas.throwFeatMissing("annotationSetName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPAnnotationSet_Type)jcasType).casFeatCode_annotationSetName);}
    
  /** setter for annotationSetName - sets The name of the annotation set. 
   * @generated */
  public void setAnnotationSetName(String v) {
    if (CCPAnnotationSet_Type.featOkTst && ((CCPAnnotationSet_Type)jcasType).casFeat_annotationSetName == null)
      jcasType.jcas.throwFeatMissing("annotationSetName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPAnnotationSet_Type)jcasType).casFeatCode_annotationSetName, v);}    
   
    
  //*--------------*
  //* Feature: annotationSetDescription

  /** getter for annotationSetDescription - gets A textual description of an annotation set.
   * @generated */
  public String getAnnotationSetDescription() {
    if (CCPAnnotationSet_Type.featOkTst && ((CCPAnnotationSet_Type)jcasType).casFeat_annotationSetDescription == null)
      jcasType.jcas.throwFeatMissing("annotationSetDescription", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPAnnotationSet_Type)jcasType).casFeatCode_annotationSetDescription);}
    
  /** setter for annotationSetDescription - sets A textual description of an annotation set. 
   * @generated */
  public void setAnnotationSetDescription(String v) {
    if (CCPAnnotationSet_Type.featOkTst && ((CCPAnnotationSet_Type)jcasType).casFeat_annotationSetDescription == null)
      jcasType.jcas.throwFeatMissing("annotationSetDescription", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPAnnotationSet_Type)jcasType).casFeatCode_annotationSetDescription, v);}    
  }

    