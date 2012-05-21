

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class AnnotationGroupCluster extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AnnotationGroupCluster.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnnotationGroupCluster() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnnotationGroupCluster(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnnotationGroupCluster(JCas jcas) {
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
    if (AnnotationGroupCluster_Type.featOkTst && ((AnnotationGroupCluster_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_types)));}
    
  /** setter for types - sets  
   * @generated */
  public void setTypes(StringArray v) {
    if (AnnotationGroupCluster_Type.featOkTst && ((AnnotationGroupCluster_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_types, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for types - gets an indexed value - 
   * @generated */
  public String getTypes(int i) {
    if (AnnotationGroupCluster_Type.featOkTst && ((AnnotationGroupCluster_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_types), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_types), i);}

  /** indexed setter for types - sets an indexed value - 
   * @generated */
  public void setTypes(int i, String v) { 
    if (AnnotationGroupCluster_Type.featOkTst && ((AnnotationGroupCluster_Type)jcasType).casFeat_types == null)
      jcasType.jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_types), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_types), i, v);}
   
    
  //*--------------*
  //* Feature: annotationGroups

  /** getter for annotationGroups - gets 
   * @generated */
  public FSList getAnnotationGroups() {
    if (AnnotationGroupCluster_Type.featOkTst && ((AnnotationGroupCluster_Type)jcasType).casFeat_annotationGroups == null)
      jcasType.jcas.throwFeatMissing("annotationGroups", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_annotationGroups)));}
    
  /** setter for annotationGroups - sets  
   * @generated */
  public void setAnnotationGroups(FSList v) {
    if (AnnotationGroupCluster_Type.featOkTst && ((AnnotationGroupCluster_Type)jcasType).casFeat_annotationGroups == null)
      jcasType.jcas.throwFeatMissing("annotationGroups", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnnotationGroupCluster_Type)jcasType).casFeatCode_annotationGroups, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    