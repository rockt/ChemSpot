

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;


/** A special comparison set for similarty matches and gold-test-test ternary comparisons.
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class TernaryComparisonSet extends ComparisonSet {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TernaryComparisonSet.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TernaryComparisonSet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TernaryComparisonSet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TernaryComparisonSet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: assumedGoldComparisonSet

  /** getter for assumedGoldComparisonSet - gets Link to a comparisonSet, which is an evaluation between the real gold standard and the assumed gold annotations.
   * @generated */
  public ComparisonSet getAssumedGoldComparisonSet() {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_assumedGoldComparisonSet == null)
      jcasType.jcas.throwFeatMissing("assumedGoldComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    return (ComparisonSet)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_assumedGoldComparisonSet)));}
    
  /** setter for assumedGoldComparisonSet - sets Link to a comparisonSet, which is an evaluation between the real gold standard and the assumed gold annotations. 
   * @generated */
  public void setAssumedGoldComparisonSet(ComparisonSet v) {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_assumedGoldComparisonSet == null)
      jcasType.jcas.throwFeatMissing("assumedGoldComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_assumedGoldComparisonSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: assumedTestComparisonSet

  /** getter for assumedTestComparisonSet - gets Link to a comparisonSet, which is an evaluation between the real gold standard and the assumed test annotations.
   * @generated */
  public ComparisonSet getAssumedTestComparisonSet() {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_assumedTestComparisonSet == null)
      jcasType.jcas.throwFeatMissing("assumedTestComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    return (ComparisonSet)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_assumedTestComparisonSet)));}
    
  /** setter for assumedTestComparisonSet - sets Link to a comparisonSet, which is an evaluation between the real gold standard and the assumed test annotations. 
   * @generated */
  public void setAssumedTestComparisonSet(ComparisonSet v) {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_assumedTestComparisonSet == null)
      jcasType.jcas.throwFeatMissing("assumedTestComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_assumedTestComparisonSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: ternaryMatchedGoldAnnotations

  /** getter for ternaryMatchedGoldAnnotations - gets Array of annotations which are matched between the real gold standard, assumed gold and assumed test annotations.
   * @generated */
  public FSArray getTernaryMatchedGoldAnnotations() {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_ternaryMatchedGoldAnnotations == null)
      jcasType.jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_ternaryMatchedGoldAnnotations)));}
    
  /** setter for ternaryMatchedGoldAnnotations - sets Array of annotations which are matched between the real gold standard, assumed gold and assumed test annotations. 
   * @generated */
  public void setTernaryMatchedGoldAnnotations(FSArray v) {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_ternaryMatchedGoldAnnotations == null)
      jcasType.jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_ternaryMatchedGoldAnnotations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for ternaryMatchedGoldAnnotations - gets an indexed value - Array of annotations which are matched between the real gold standard, assumed gold and assumed test annotations.
   * @generated */
  public TOP getTernaryMatchedGoldAnnotations(int i) {
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_ternaryMatchedGoldAnnotations == null)
      jcasType.jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_ternaryMatchedGoldAnnotations), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_ternaryMatchedGoldAnnotations), i)));}

  /** indexed setter for ternaryMatchedGoldAnnotations - sets an indexed value - Array of annotations which are matched between the real gold standard, assumed gold and assumed test annotations.
   * @generated */
  public void setTernaryMatchedGoldAnnotations(int i, TOP v) { 
    if (TernaryComparisonSet_Type.featOkTst && ((TernaryComparisonSet_Type)jcasType).casFeat_ternaryMatchedGoldAnnotations == null)
      jcasType.jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_ternaryMatchedGoldAnnotations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((TernaryComparisonSet_Type)jcasType).casFeatCode_ternaryMatchedGoldAnnotations), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    