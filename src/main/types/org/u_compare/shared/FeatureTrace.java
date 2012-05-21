

/* First created by JCasGen Thu Jul 14 11:57:11 CEST 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** For system use. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class FeatureTrace extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FeatureTrace.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FeatureTrace() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FeatureTrace(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FeatureTrace(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: outcome

  /** getter for outcome - gets 
   * @generated */
  public TOP getOutcome() {
    if (FeatureTrace_Type.featOkTst && ((FeatureTrace_Type)jcasType).casFeat_outcome == null)
      jcasType.jcas.throwFeatMissing("outcome", "org.u_compare.shared.FeatureTrace");
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_outcome)));}
    
  /** setter for outcome - sets  
   * @generated */
  public void setOutcome(TOP v) {
    if (FeatureTrace_Type.featOkTst && ((FeatureTrace_Type)jcasType).casFeat_outcome == null)
      jcasType.jcas.throwFeatMissing("outcome", "org.u_compare.shared.FeatureTrace");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_outcome, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: nbests

  /** getter for nbests - gets 
   * @generated */
  public FSArray getNbests() {
    if (FeatureTrace_Type.featOkTst && ((FeatureTrace_Type)jcasType).casFeat_nbests == null)
      jcasType.jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_nbests)));}
    
  /** setter for nbests - sets  
   * @generated */
  public void setNbests(FSArray v) {
    if (FeatureTrace_Type.featOkTst && ((FeatureTrace_Type)jcasType).casFeat_nbests == null)
      jcasType.jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_nbests, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for nbests - gets an indexed value - 
   * @generated */
  public FSArray getNbests(int i) {
    if (FeatureTrace_Type.featOkTst && ((FeatureTrace_Type)jcasType).casFeat_nbests == null)
      jcasType.jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_nbests), i);
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_nbests), i)));}

  /** indexed setter for nbests - sets an indexed value - 
   * @generated */
  public void setNbests(int i, FSArray v) { 
    if (FeatureTrace_Type.featOkTst && ((FeatureTrace_Type)jcasType).casFeat_nbests == null)
      jcasType.jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_nbests), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureTrace_Type)jcasType).casFeatCode_nbests), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    