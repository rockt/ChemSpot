

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** The annotator object contains information which is used to determine who/what generated an annotation.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPAnnotator extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPAnnotator.class);
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
  protected CCPAnnotator() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPAnnotator(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPAnnotator(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotatorID

  /** getter for annotatorID - gets This Integer should be a unique ID for a particular annotator.
   * @generated */
  public int getAnnotatorID() {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_annotatorID == null)
      jcasType.jcas.throwFeatMissing("annotatorID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_annotatorID);}
    
  /** setter for annotatorID - sets This Integer should be a unique ID for a particular annotator. 
   * @generated */
  public void setAnnotatorID(int v) {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_annotatorID == null)
      jcasType.jcas.throwFeatMissing("annotatorID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_annotatorID, v);}    
   
    
  //*--------------*
  //* Feature: firstName

  /** getter for firstName - gets The first name of the annotator. Use of this field is optional as the annotator ID is primarily used for determining the source of an annotation.
   * @generated */
  public String getFirstName() {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_firstName == null)
      jcasType.jcas.throwFeatMissing("firstName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_firstName);}
    
  /** setter for firstName - sets The first name of the annotator. Use of this field is optional as the annotator ID is primarily used for determining the source of an annotation. 
   * @generated */
  public void setFirstName(String v) {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_firstName == null)
      jcasType.jcas.throwFeatMissing("firstName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_firstName, v);}    
   
    
  //*--------------*
  //* Feature: lastName

  /** getter for lastName - gets The last name of the annotator. Use of this field is optional as the annotator ID is primarily used for determining the source of an annotation.
   * @generated */
  public String getLastName() {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_lastName == null)
      jcasType.jcas.throwFeatMissing("lastName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_lastName);}
    
  /** setter for lastName - sets The last name of the annotator. Use of this field is optional as the annotator ID is primarily used for determining the source of an annotation. 
   * @generated */
  public void setLastName(String v) {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_lastName == null)
      jcasType.jcas.throwFeatMissing("lastName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_lastName, v);}    
   
    
  //*--------------*
  //* Feature: affiliation

  /** getter for affiliation - gets The affiliation of the annotator. Use of this field is optional as the annotator ID is primarily used for determining the source of an annotation.
   * @generated */
  public String getAffiliation() {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_affiliation == null)
      jcasType.jcas.throwFeatMissing("affiliation", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_affiliation);}
    
  /** setter for affiliation - sets The affiliation of the annotator. Use of this field is optional as the annotator ID is primarily used for determining the source of an annotation. 
   * @generated */
  public void setAffiliation(String v) {
    if (CCPAnnotator_Type.featOkTst && ((CCPAnnotator_Type)jcasType).casFeat_affiliation == null)
      jcasType.jcas.throwFeatMissing("affiliation", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPAnnotator_Type)jcasType).casFeatCode_affiliation, v);}    
  }

    