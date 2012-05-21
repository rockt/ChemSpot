

/* First created by JCasGen Thu Jul 14 11:57:11 CEST 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.*;


/** Stores names and weights of features. For system use.
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class FeatureWeights extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FeatureWeights.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FeatureWeights() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FeatureWeights(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FeatureWeights(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: labels

  /** getter for labels - gets 
   * @generated */
  public StringArray getLabels() {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_labels == null)
      jcasType.jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_labels)));}
    
  /** setter for labels - sets  
   * @generated */
  public void setLabels(StringArray v) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_labels == null)
      jcasType.jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_labels, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for labels - gets an indexed value - 
   * @generated */
  public String getLabels(int i) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_labels == null)
      jcasType.jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_labels), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_labels), i);}

  /** indexed setter for labels - sets an indexed value - 
   * @generated */
  public void setLabels(int i, String v) { 
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_labels == null)
      jcasType.jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_labels), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_labels), i, v);}
   
    
  //*--------------*
  //* Feature: weights

  /** getter for weights - gets 
   * @generated */
  public DoubleArray getWeights() {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_weights == null)
      jcasType.jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    return (DoubleArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_weights)));}
    
  /** setter for weights - sets  
   * @generated */
  public void setWeights(DoubleArray v) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_weights == null)
      jcasType.jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_weights, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for weights - gets an indexed value - 
   * @generated */
  public double getWeights(int i) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_weights == null)
      jcasType.jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_weights), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_weights), i);}

  /** indexed setter for weights - sets an indexed value - 
   * @generated */
  public void setWeights(int i, double v) { 
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_weights == null)
      jcasType.jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_weights), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_weights), i, v);}
   
    
  //*--------------*
  //* Feature: values

  /** getter for values - gets 
   * @generated */
  public DoubleArray getValues() {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    return (DoubleArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_values)));}
    
  /** setter for values - sets  
   * @generated */
  public void setValues(DoubleArray v) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_values, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for values - gets an indexed value - 
   * @generated */
  public double getValues(int i) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_values), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_values), i);}

  /** indexed setter for values - sets an indexed value - 
   * @generated */
  public void setValues(int i, double v) { 
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_values), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_values), i, v);}
   
    
  //*--------------*
  //* Feature: spans

  /** getter for spans - gets 
   * @generated */
  public FSArray getSpans() {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_spans)));}
    
  /** setter for spans - sets  
   * @generated */
  public void setSpans(FSArray v) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_spans, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for spans - gets an indexed value - 
   * @generated */
  public DiscontinuousAnnotation getSpans(int i) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_spans), i);
    return (DiscontinuousAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_spans), i)));}

  /** indexed setter for spans - sets an indexed value - 
   * @generated */
  public void setSpans(int i, DiscontinuousAnnotation v) { 
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_spans), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_spans), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: outcomeString

  /** getter for outcomeString - gets 
   * @generated */
  public String getOutcomeString() {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_outcomeString == null)
      jcasType.jcas.throwFeatMissing("outcomeString", "org.u_compare.shared.FeatureWeights");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_outcomeString);}
    
  /** setter for outcomeString - sets  
   * @generated */
  public void setOutcomeString(String v) {
    if (FeatureWeights_Type.featOkTst && ((FeatureWeights_Type)jcasType).casFeat_outcomeString == null)
      jcasType.jcas.throwFeatMissing("outcomeString", "org.u_compare.shared.FeatureWeights");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureWeights_Type)jcasType).casFeatCode_outcomeString, v);}    
  }

    