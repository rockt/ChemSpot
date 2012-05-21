

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class MatchedPair extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(MatchedPair.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MatchedPair() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public MatchedPair(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public MatchedPair(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: goldAnnotation

  /** getter for goldAnnotation - gets 
   * @generated */
  public TOP getGoldAnnotation() {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_goldAnnotation == null)
      jcasType.jcas.throwFeatMissing("goldAnnotation", "org.u_compare.shared.comparable.MatchedPair");
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_goldAnnotation)));}
    
  /** setter for goldAnnotation - sets  
   * @generated */
  public void setGoldAnnotation(TOP v) {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_goldAnnotation == null)
      jcasType.jcas.throwFeatMissing("goldAnnotation", "org.u_compare.shared.comparable.MatchedPair");
    jcasType.ll_cas.ll_setRefValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_goldAnnotation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: testAnnotation

  /** getter for testAnnotation - gets 
   * @generated */
  public TOP getTestAnnotation() {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_testAnnotation == null)
      jcasType.jcas.throwFeatMissing("testAnnotation", "org.u_compare.shared.comparable.MatchedPair");
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_testAnnotation)));}
    
  /** setter for testAnnotation - sets  
   * @generated */
  public void setTestAnnotation(TOP v) {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_testAnnotation == null)
      jcasType.jcas.throwFeatMissing("testAnnotation", "org.u_compare.shared.comparable.MatchedPair");
    jcasType.ll_cas.ll_setRefValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_testAnnotation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: goldLabel

  /** getter for goldLabel - gets U-Compare will show this label along with the assumed gold standard annotation linked from this MatchedPair instance.
   * @generated */
  public String getGoldLabel() {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_goldLabel == null)
      jcasType.jcas.throwFeatMissing("goldLabel", "org.u_compare.shared.comparable.MatchedPair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_goldLabel);}
    
  /** setter for goldLabel - sets U-Compare will show this label along with the assumed gold standard annotation linked from this MatchedPair instance. 
   * @generated */
  public void setGoldLabel(String v) {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_goldLabel == null)
      jcasType.jcas.throwFeatMissing("goldLabel", "org.u_compare.shared.comparable.MatchedPair");
    jcasType.ll_cas.ll_setStringValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_goldLabel, v);}    
   
    
  //*--------------*
  //* Feature: testLabel

  /** getter for testLabel - gets U-Compare will show this label along with the assumed test annotation linked from this MatchedPair instance.
   * @generated */
  public String getTestLabel() {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_testLabel == null)
      jcasType.jcas.throwFeatMissing("testLabel", "org.u_compare.shared.comparable.MatchedPair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_testLabel);}
    
  /** setter for testLabel - sets U-Compare will show this label along with the assumed test annotation linked from this MatchedPair instance. 
   * @generated */
  public void setTestLabel(String v) {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_testLabel == null)
      jcasType.jcas.throwFeatMissing("testLabel", "org.u_compare.shared.comparable.MatchedPair");
    jcasType.ll_cas.ll_setStringValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_testLabel, v);}    
   
    
  //*--------------*
  //* Feature: matchLabel

  /** getter for matchLabel - gets U-Compare will show this label as a explanatory label of this MatchedPair instance.
   * @generated */
  public String getMatchLabel() {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_matchLabel == null)
      jcasType.jcas.throwFeatMissing("matchLabel", "org.u_compare.shared.comparable.MatchedPair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_matchLabel);}
    
  /** setter for matchLabel - sets U-Compare will show this label as a explanatory label of this MatchedPair instance. 
   * @generated */
  public void setMatchLabel(String v) {
    if (MatchedPair_Type.featOkTst && ((MatchedPair_Type)jcasType).casFeat_matchLabel == null)
      jcasType.jcas.throwFeatMissing("matchLabel", "org.u_compare.shared.comparable.MatchedPair");
    jcasType.ll_cas.ll_setStringValue(addr, ((MatchedPair_Type)jcasType).casFeatCode_matchLabel, v);}    
  }

    