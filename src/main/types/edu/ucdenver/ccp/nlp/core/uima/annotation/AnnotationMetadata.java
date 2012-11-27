

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import edu.ucdenver.ccp.nlp.core.uima.annotation.metadata.AnnotationMetadataProperty;


/** A class to store annotation metadata, provenance, etc.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class AnnotationMetadata extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnnotationMetadata.class);
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
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: confidence

  /** getter for confidence - gets 
   * @generated */
  public float getConfidence() {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_confidence);}
    
  /** setter for confidence - sets  
   * @generated */
  public void setConfidence(float v) {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    jcasType.ll_cas.ll_setFloatValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_confidence, v);}    
   
    
  //*--------------*
  //* Feature: metadataProperties

  /** getter for metadataProperties - gets 
   * @generated */
  public FSArray getMetadataProperties() {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_metadataProperties == null)
      jcasType.jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_metadataProperties)));}
    
  /** setter for metadataProperties - sets  
   * @generated */
  public void setMetadataProperties(FSArray v) {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_metadataProperties == null)
      jcasType.jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_metadataProperties, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for metadataProperties - gets an indexed value - 
   * @generated */
  public AnnotationMetadataProperty getMetadataProperties(int i) {
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_metadataProperties == null)
      jcasType.jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_metadataProperties), i);
    return (AnnotationMetadataProperty)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_metadataProperties), i)));}

  /** indexed setter for metadataProperties - sets an indexed value - 
   * @generated */
  public void setMetadataProperties(int i, AnnotationMetadataProperty v) { 
    if (AnnotationMetadata_Type.featOkTst && ((AnnotationMetadata_Type)jcasType).casFeat_metadataProperties == null)
      jcasType.jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_metadataProperties), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationMetadata_Type)jcasType).casFeatCode_metadataProperties), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    