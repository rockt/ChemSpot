

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class ComparisonSet extends AnnotationGroup {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ComparisonSet.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ComparisonSet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ComparisonSet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ComparisonSet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: goldAnnotations

  /** getter for goldAnnotations - gets A set of "gold" annotations. Should be filled by developers.
   * @generated */
  public FSArray getGoldAnnotations() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldAnnotations == null)
      jcasType.jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotations)));}
    
  /** setter for goldAnnotations - sets A set of "gold" annotations. Should be filled by developers. 
   * @generated */
  public void setGoldAnnotations(FSArray v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldAnnotations == null)
      jcasType.jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for goldAnnotations - gets an indexed value - A set of "gold" annotations. Should be filled by developers.
   * @generated */
  public TOP getGoldAnnotations(int i) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldAnnotations == null)
      jcasType.jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotations), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotations), i)));}

  /** indexed setter for goldAnnotations - sets an indexed value - A set of "gold" annotations. Should be filled by developers.
   * @generated */
  public void setGoldAnnotations(int i, TOP v) { 
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldAnnotations == null)
      jcasType.jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotations), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: testAnnotations

  /** getter for testAnnotations - gets A set of "test" annotations. Should be filled by developers.
   * @generated */
  public FSArray getTestAnnotations() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testAnnotations == null)
      jcasType.jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotations)));}
    
  /** setter for testAnnotations - sets A set of "test" annotations. Should be filled by developers. 
   * @generated */
  public void setTestAnnotations(FSArray v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testAnnotations == null)
      jcasType.jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for testAnnotations - gets an indexed value - A set of "test" annotations. Should be filled by developers.
   * @generated */
  public TOP getTestAnnotations(int i) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testAnnotations == null)
      jcasType.jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotations), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotations), i)));}

  /** indexed setter for testAnnotations - sets an indexed value - A set of "test" annotations. Should be filled by developers.
   * @generated */
  public void setTestAnnotations(int i, TOP v) { 
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testAnnotations == null)
      jcasType.jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotations), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: matchedAnnotations

  /** getter for matchedAnnotations - gets A set of annotations, which are recoginized as "matched" through comparison of gold and test annotations. Should be filled by developers.
   * @generated */
  public FSArray getMatchedAnnotations() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_matchedAnnotations == null)
      jcasType.jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchedAnnotations)));}
    
  /** setter for matchedAnnotations - sets A set of annotations, which are recoginized as "matched" through comparison of gold and test annotations. Should be filled by developers. 
   * @generated */
  public void setMatchedAnnotations(FSArray v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_matchedAnnotations == null)
      jcasType.jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchedAnnotations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for matchedAnnotations - gets an indexed value - A set of annotations, which are recoginized as "matched" through comparison of gold and test annotations. Should be filled by developers.
   * @generated */
  public MatchedPair getMatchedAnnotations(int i) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_matchedAnnotations == null)
      jcasType.jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchedAnnotations), i);
    return (MatchedPair)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchedAnnotations), i)));}

  /** indexed setter for matchedAnnotations - sets an indexed value - A set of annotations, which are recoginized as "matched" through comparison of gold and test annotations. Should be filled by developers.
   * @generated */
  public void setMatchedAnnotations(int i, MatchedPair v) { 
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_matchedAnnotations == null)
      jcasType.jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchedAnnotations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchedAnnotations), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: goldAnnotationGroup

  /** getter for goldAnnotationGroup - gets Reference to the original gold AnnotationGroup. Developers should iterate and pick proper annotations from this FSList.
   * @generated */
  public AnnotationGroup getGoldAnnotationGroup() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldAnnotationGroup == null)
      jcasType.jcas.throwFeatMissing("goldAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    return (AnnotationGroup)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotationGroup)));}
    
  /** setter for goldAnnotationGroup - sets Reference to the original gold AnnotationGroup. Developers should iterate and pick proper annotations from this FSList. 
   * @generated */
  public void setGoldAnnotationGroup(AnnotationGroup v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldAnnotationGroup == null)
      jcasType.jcas.throwFeatMissing("goldAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldAnnotationGroup, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: testAnnotationGroup

  /** getter for testAnnotationGroup - gets Reference to the original test AnnotationGroup. Developers should iterate and pick proper annotations from this FSList.
   * @generated */
  public AnnotationGroup getTestAnnotationGroup() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testAnnotationGroup == null)
      jcasType.jcas.throwFeatMissing("testAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    return (AnnotationGroup)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotationGroup)));}
    
  /** setter for testAnnotationGroup - sets Reference to the original test AnnotationGroup. Developers should iterate and pick proper annotations from this FSList. 
   * @generated */
  public void setTestAnnotationGroup(AnnotationGroup v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testAnnotationGroup == null)
      jcasType.jcas.throwFeatMissing("testAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testAnnotationGroup, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: scores

  /** getter for scores - gets A field to store scores for each CAS. This field should have an instance of *ARRAY* which implements get(i) method e.g. StringArray, IntegerArray, FSArray, etc. Evaluator class should be responsible to make consistent order of scores when there is multiple types of scores exist.
   * @generated */
  public TOP getScores() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_scores == null)
      jcasType.jcas.throwFeatMissing("scores", "org.u_compare.shared.comparable.ComparisonSet");
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_scores)));}
    
  /** setter for scores - sets A field to store scores for each CAS. This field should have an instance of *ARRAY* which implements get(i) method e.g. StringArray, IntegerArray, FSArray, etc. Evaluator class should be responsible to make consistent order of scores when there is multiple types of scores exist. 
   * @generated */
  public void setScores(TOP v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_scores == null)
      jcasType.jcas.throwFeatMissing("scores", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_scores, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: fields

  /** getter for fields - gets This field is prepared to store any value which might be used in the evaluation process implemented by the developer, especially values needed when calculate the total score over CASes. U-Compare will not use this field.
   * @generated */
  public TOP getFields() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_fields == null)
      jcasType.jcas.throwFeatMissing("fields", "org.u_compare.shared.comparable.ComparisonSet");
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_fields)));}
    
  /** setter for fields - sets This field is prepared to store any value which might be used in the evaluation process implemented by the developer, especially values needed when calculate the total score over CASes. U-Compare will not use this field. 
   * @generated */
  public void setFields(TOP v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_fields == null)
      jcasType.jcas.throwFeatMissing("fields", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_fields, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: goldCountAdjustment

  /** getter for goldCountAdjustment - gets 
   * @generated */
  public int getGoldCountAdjustment() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldCountAdjustment == null)
      jcasType.jcas.throwFeatMissing("goldCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    return jcasType.ll_cas.ll_getIntValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldCountAdjustment);}
    
  /** setter for goldCountAdjustment - sets  
   * @generated */
  public void setGoldCountAdjustment(int v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_goldCountAdjustment == null)
      jcasType.jcas.throwFeatMissing("goldCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setIntValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_goldCountAdjustment, v);}    
   
    
  //*--------------*
  //* Feature: testCountAdjustment

  /** getter for testCountAdjustment - gets 
   * @generated */
  public int getTestCountAdjustment() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testCountAdjustment == null)
      jcasType.jcas.throwFeatMissing("testCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    return jcasType.ll_cas.ll_getIntValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testCountAdjustment);}
    
  /** setter for testCountAdjustment - sets  
   * @generated */
  public void setTestCountAdjustment(int v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_testCountAdjustment == null)
      jcasType.jcas.throwFeatMissing("testCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setIntValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_testCountAdjustment, v);}    
   
    
  //*--------------*
  //* Feature: matchCountAdjustment

  /** getter for matchCountAdjustment - gets 
   * @generated */
  public int getMatchCountAdjustment() {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_matchCountAdjustment == null)
      jcasType.jcas.throwFeatMissing("matchCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    return jcasType.ll_cas.ll_getIntValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchCountAdjustment);}
    
  /** setter for matchCountAdjustment - sets  
   * @generated */
  public void setMatchCountAdjustment(int v) {
    if (ComparisonSet_Type.featOkTst && ((ComparisonSet_Type)jcasType).casFeat_matchCountAdjustment == null)
      jcasType.jcas.throwFeatMissing("matchCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    jcasType.ll_cas.ll_setIntValue(addr, ((ComparisonSet_Type)jcasType).casFeatCode_matchCountAdjustment, v);}    
  }

    