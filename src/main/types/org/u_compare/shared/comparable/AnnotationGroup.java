

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.*;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class AnnotationGroup extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AnnotationGroup.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnnotationGroup() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnnotationGroup(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnnotationGroup(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: types

  /** getter for types - gets 
   * @generated */
  public StringArray getTypes() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_types)));}
    
  /** setter for types - sets  
   * @generated */
  public void setTypes(StringArray v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_types, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for types - gets an indexed value - 
   * @generated */
  public String getTypes(int i) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_types), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_types), i);}

  /** indexed setter for types - sets an indexed value - 
   * @generated */
  public void setTypes(int i, String v) { 
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_types), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_types), i, v);}
   
    
  //*--------------*
  //* Feature: annotations

  /** getter for annotations - gets 
   * @generated */
  public FSList getAnnotations() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_annotations == null)
      jcasType.jcas.throwFeatMissing("annotations", "org.u_compare.shared.comparable.AnnotationGroup");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_annotations)));}
    
  /** setter for annotations - sets  
   * @generated */
  public void setAnnotations(FSList v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_annotations == null)
      jcasType.jcas.throwFeatMissing("annotations", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_annotations, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: derivingAnnotationGroups

  /** getter for derivingAnnotationGroups - gets List of AnnotationGroups which are used to produce this AnnotationGroup.
   * @generated */
  public FSList getDerivingAnnotationGroups() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_derivingAnnotationGroups == null)
      jcasType.jcas.throwFeatMissing("derivingAnnotationGroups", "org.u_compare.shared.comparable.AnnotationGroup");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroups)));}
    
  /** setter for derivingAnnotationGroups - sets List of AnnotationGroups which are used to produce this AnnotationGroup. 
   * @generated */
  public void setDerivingAnnotationGroups(FSList v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_derivingAnnotationGroups == null)
      jcasType.jcas.throwFeatMissing("derivingAnnotationGroups", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroups, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: componentID

  /** getter for componentID - gets 
   * @generated */
  public String getComponentID() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_componentID == null)
      jcasType.jcas.throwFeatMissing("componentID", "org.u_compare.shared.comparable.AnnotationGroup");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_componentID);}
    
  /** setter for componentID - sets  
   * @generated */
  public void setComponentID(String v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_componentID == null)
      jcasType.jcas.throwFeatMissing("componentID", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_componentID, v);}    
   
    
  //*--------------*
  //* Feature: latest

  /** getter for latest - gets 
   * @generated */
  public boolean getLatest() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_latest == null)
      jcasType.jcas.throwFeatMissing("latest", "org.u_compare.shared.comparable.AnnotationGroup");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_latest);}
    
  /** setter for latest - sets  
   * @generated */
  public void setLatest(boolean v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_latest == null)
      jcasType.jcas.throwFeatMissing("latest", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_latest, v);}    
   
    
  //*--------------*
  //* Feature: superTypes

  /** getter for superTypes - gets 
   * @generated */
  public StringArray getSuperTypes() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_superTypes == null)
      jcasType.jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_superTypes)));}
    
  /** setter for superTypes - sets  
   * @generated */
  public void setSuperTypes(StringArray v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_superTypes == null)
      jcasType.jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_superTypes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for superTypes - gets an indexed value - 
   * @generated */
  public String getSuperTypes(int i) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_superTypes == null)
      jcasType.jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_superTypes), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_superTypes), i);}

  /** indexed setter for superTypes - sets an indexed value - 
   * @generated */
  public void setSuperTypes(int i, String v) { 
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_superTypes == null)
      jcasType.jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_superTypes), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_superTypes), i, v);}
   
    
  //*--------------*
  //* Feature: derivingAnnotationGroupTypes

  /** getter for derivingAnnotationGroupTypes - gets Array of StringArray, where types used in each corresponding deriving annotation group are stored.
   * @generated */
  public FSArray getDerivingAnnotationGroupTypes() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_derivingAnnotationGroupTypes == null)
      jcasType.jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroupTypes)));}
    
  /** setter for derivingAnnotationGroupTypes - sets Array of StringArray, where types used in each corresponding deriving annotation group are stored. 
   * @generated */
  public void setDerivingAnnotationGroupTypes(FSArray v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_derivingAnnotationGroupTypes == null)
      jcasType.jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroupTypes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for derivingAnnotationGroupTypes - gets an indexed value - Array of StringArray, where types used in each corresponding deriving annotation group are stored.
   * @generated */
  public StringArray getDerivingAnnotationGroupTypes(int i) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_derivingAnnotationGroupTypes == null)
      jcasType.jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroupTypes), i);
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroupTypes), i)));}

  /** indexed setter for derivingAnnotationGroupTypes - sets an indexed value - Array of StringArray, where types used in each corresponding deriving annotation group are stored.
   * @generated */
  public void setDerivingAnnotationGroupTypes(int i, StringArray v) { 
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_derivingAnnotationGroupTypes == null)
      jcasType.jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroupTypes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_derivingAnnotationGroupTypes), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: collectionReaderGenerated

  /** getter for collectionReaderGenerated - gets 
   * @generated */
  public boolean getCollectionReaderGenerated() {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_collectionReaderGenerated == null)
      jcasType.jcas.throwFeatMissing("collectionReaderGenerated", "org.u_compare.shared.comparable.AnnotationGroup");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_collectionReaderGenerated);}
    
  /** setter for collectionReaderGenerated - sets  
   * @generated */
  public void setCollectionReaderGenerated(boolean v) {
    if (AnnotationGroup_Type.featOkTst && ((AnnotationGroup_Type)jcasType).casFeat_collectionReaderGenerated == null)
      jcasType.jcas.throwFeatMissing("collectionReaderGenerated", "org.u_compare.shared.comparable.AnnotationGroup");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((AnnotationGroup_Type)jcasType).casFeatCode_collectionReaderGenerated, v);}    
  }

    