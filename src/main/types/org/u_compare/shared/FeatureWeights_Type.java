
/* First created by JCasGen Thu Jul 14 11:57:11 CEST 2011 */
package org.u_compare.shared;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** Stores names and weights of features. For system use.
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class FeatureWeights_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FeatureWeights_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FeatureWeights_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FeatureWeights(addr, FeatureWeights_Type.this);
  			   FeatureWeights_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FeatureWeights(addr, FeatureWeights_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = FeatureWeights.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.FeatureWeights");
 
  /** @generated */
  final Feature casFeat_labels;
  /** @generated */
  final int     casFeatCode_labels;
  /** @generated */ 
  public int getLabels(int addr) {
        if (featOkTst && casFeat_labels == null)
      jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    return ll_cas.ll_getRefValue(addr, casFeatCode_labels);
  }
  /** @generated */    
  public void setLabels(int addr, int v) {
        if (featOkTst && casFeat_labels == null)
      jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    ll_cas.ll_setRefValue(addr, casFeatCode_labels, v);}
    
   /** @generated */
  public String getLabels(int addr, int i) {
        if (featOkTst && casFeat_labels == null)
      jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_labels), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_labels), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_labels), i);
  }
   
  /** @generated */ 
  public void setLabels(int addr, int i, String v) {
        if (featOkTst && casFeat_labels == null)
      jcas.throwFeatMissing("labels", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_labels), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_labels), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_labels), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_weights;
  /** @generated */
  final int     casFeatCode_weights;
  /** @generated */ 
  public int getWeights(int addr) {
        if (featOkTst && casFeat_weights == null)
      jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    return ll_cas.ll_getRefValue(addr, casFeatCode_weights);
  }
  /** @generated */    
  public void setWeights(int addr, int v) {
        if (featOkTst && casFeat_weights == null)
      jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    ll_cas.ll_setRefValue(addr, casFeatCode_weights, v);}
    
   /** @generated */
  public double getWeights(int addr, int i) {
        if (featOkTst && casFeat_weights == null)
      jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_weights), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_weights), i);
  return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_weights), i);
  }
   
  /** @generated */ 
  public void setWeights(int addr, int i, double v) {
        if (featOkTst && casFeat_weights == null)
      jcas.throwFeatMissing("weights", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_weights), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_weights), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_weights), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_values;
  /** @generated */
  final int     casFeatCode_values;
  /** @generated */ 
  public int getValues(int addr) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    return ll_cas.ll_getRefValue(addr, casFeatCode_values);
  }
  /** @generated */    
  public void setValues(int addr, int v) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    ll_cas.ll_setRefValue(addr, casFeatCode_values, v);}
    
   /** @generated */
  public double getValues(int addr, int i) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_values), i);
  return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i);
  }
   
  /** @generated */ 
  public void setValues(int addr, int i, double v) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_values), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_spans;
  /** @generated */
  final int     casFeatCode_spans;
  /** @generated */ 
  public int getSpans(int addr) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    return ll_cas.ll_getRefValue(addr, casFeatCode_spans);
  }
  /** @generated */    
  public void setSpans(int addr, int v) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    ll_cas.ll_setRefValue(addr, casFeatCode_spans, v);}
    
   /** @generated */
  public int getSpans(int addr, int i) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i);
  }
   
  /** @generated */ 
  public void setSpans(int addr, int i, int v) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "org.u_compare.shared.FeatureWeights");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_outcomeString;
  /** @generated */
  final int     casFeatCode_outcomeString;
  /** @generated */ 
  public String getOutcomeString(int addr) {
        if (featOkTst && casFeat_outcomeString == null)
      jcas.throwFeatMissing("outcomeString", "org.u_compare.shared.FeatureWeights");
    return ll_cas.ll_getStringValue(addr, casFeatCode_outcomeString);
  }
  /** @generated */    
  public void setOutcomeString(int addr, String v) {
        if (featOkTst && casFeat_outcomeString == null)
      jcas.throwFeatMissing("outcomeString", "org.u_compare.shared.FeatureWeights");
    ll_cas.ll_setStringValue(addr, casFeatCode_outcomeString, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public FeatureWeights_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_labels = jcas.getRequiredFeatureDE(casType, "labels", "uima.cas.StringArray", featOkTst);
    casFeatCode_labels  = (null == casFeat_labels) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_labels).getCode();

 
    casFeat_weights = jcas.getRequiredFeatureDE(casType, "weights", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_weights  = (null == casFeat_weights) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_weights).getCode();

 
    casFeat_values = jcas.getRequiredFeatureDE(casType, "values", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_values  = (null == casFeat_values) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_values).getCode();

 
    casFeat_spans = jcas.getRequiredFeatureDE(casType, "spans", "uima.cas.FSArray", featOkTst);
    casFeatCode_spans  = (null == casFeat_spans) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_spans).getCode();

 
    casFeat_outcomeString = jcas.getRequiredFeatureDE(casType, "outcomeString", "uima.cas.String", featOkTst);
    casFeatCode_outcomeString  = (null == casFeat_outcomeString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_outcomeString).getCode();

  }
}



    