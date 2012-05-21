
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class ComparisonSet_Type extends AnnotationGroup_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ComparisonSet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ComparisonSet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ComparisonSet(addr, ComparisonSet_Type.this);
  			   ComparisonSet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ComparisonSet(addr, ComparisonSet_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ComparisonSet.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.comparable.ComparisonSet");
 
  /** @generated */
  final Feature casFeat_goldAnnotations;
  /** @generated */
  final int     casFeatCode_goldAnnotations;
  /** @generated */ 
  public int getGoldAnnotations(int addr) {
        if (featOkTst && casFeat_goldAnnotations == null)
      jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations);
  }
  /** @generated */    
  public void setGoldAnnotations(int addr, int v) {
        if (featOkTst && casFeat_goldAnnotations == null)
      jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_goldAnnotations, v);}
    
   /** @generated */
  public int getGoldAnnotations(int addr, int i) {
        if (featOkTst && casFeat_goldAnnotations == null)
      jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations), i);
  }
   
  /** @generated */ 
  public void setGoldAnnotations(int addr, int i, int v) {
        if (featOkTst && casFeat_goldAnnotations == null)
      jcas.throwFeatMissing("goldAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotations), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_testAnnotations;
  /** @generated */
  final int     casFeatCode_testAnnotations;
  /** @generated */ 
  public int getTestAnnotations(int addr) {
        if (featOkTst && casFeat_testAnnotations == null)
      jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations);
  }
  /** @generated */    
  public void setTestAnnotations(int addr, int v) {
        if (featOkTst && casFeat_testAnnotations == null)
      jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_testAnnotations, v);}
    
   /** @generated */
  public int getTestAnnotations(int addr, int i) {
        if (featOkTst && casFeat_testAnnotations == null)
      jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations), i);
  }
   
  /** @generated */ 
  public void setTestAnnotations(int addr, int i, int v) {
        if (featOkTst && casFeat_testAnnotations == null)
      jcas.throwFeatMissing("testAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotations), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_matchedAnnotations;
  /** @generated */
  final int     casFeatCode_matchedAnnotations;
  /** @generated */ 
  public int getMatchedAnnotations(int addr) {
        if (featOkTst && casFeat_matchedAnnotations == null)
      jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations);
  }
  /** @generated */    
  public void setMatchedAnnotations(int addr, int v) {
        if (featOkTst && casFeat_matchedAnnotations == null)
      jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_matchedAnnotations, v);}
    
   /** @generated */
  public int getMatchedAnnotations(int addr, int i) {
        if (featOkTst && casFeat_matchedAnnotations == null)
      jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations), i);
  }
   
  /** @generated */ 
  public void setMatchedAnnotations(int addr, int i, int v) {
        if (featOkTst && casFeat_matchedAnnotations == null)
      jcas.throwFeatMissing("matchedAnnotations", "org.u_compare.shared.comparable.ComparisonSet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_matchedAnnotations), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_goldAnnotationGroup;
  /** @generated */
  final int     casFeatCode_goldAnnotationGroup;
  /** @generated */ 
  public int getGoldAnnotationGroup(int addr) {
        if (featOkTst && casFeat_goldAnnotationGroup == null)
      jcas.throwFeatMissing("goldAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_goldAnnotationGroup);
  }
  /** @generated */    
  public void setGoldAnnotationGroup(int addr, int v) {
        if (featOkTst && casFeat_goldAnnotationGroup == null)
      jcas.throwFeatMissing("goldAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_goldAnnotationGroup, v);}
    
  
 
  /** @generated */
  final Feature casFeat_testAnnotationGroup;
  /** @generated */
  final int     casFeatCode_testAnnotationGroup;
  /** @generated */ 
  public int getTestAnnotationGroup(int addr) {
        if (featOkTst && casFeat_testAnnotationGroup == null)
      jcas.throwFeatMissing("testAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_testAnnotationGroup);
  }
  /** @generated */    
  public void setTestAnnotationGroup(int addr, int v) {
        if (featOkTst && casFeat_testAnnotationGroup == null)
      jcas.throwFeatMissing("testAnnotationGroup", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_testAnnotationGroup, v);}
    
  
 
  /** @generated */
  final Feature casFeat_scores;
  /** @generated */
  final int     casFeatCode_scores;
  /** @generated */ 
  public int getScores(int addr) {
        if (featOkTst && casFeat_scores == null)
      jcas.throwFeatMissing("scores", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_scores);
  }
  /** @generated */    
  public void setScores(int addr, int v) {
        if (featOkTst && casFeat_scores == null)
      jcas.throwFeatMissing("scores", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_scores, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fields;
  /** @generated */
  final int     casFeatCode_fields;
  /** @generated */ 
  public int getFields(int addr) {
        if (featOkTst && casFeat_fields == null)
      jcas.throwFeatMissing("fields", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_fields);
  }
  /** @generated */    
  public void setFields(int addr, int v) {
        if (featOkTst && casFeat_fields == null)
      jcas.throwFeatMissing("fields", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_fields, v);}
    
  
 
  /** @generated */
  final Feature casFeat_goldCountAdjustment;
  /** @generated */
  final int     casFeatCode_goldCountAdjustment;
  /** @generated */ 
  public int getGoldCountAdjustment(int addr) {
        if (featOkTst && casFeat_goldCountAdjustment == null)
      jcas.throwFeatMissing("goldCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getIntValue(addr, casFeatCode_goldCountAdjustment);
  }
  /** @generated */    
  public void setGoldCountAdjustment(int addr, int v) {
        if (featOkTst && casFeat_goldCountAdjustment == null)
      jcas.throwFeatMissing("goldCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setIntValue(addr, casFeatCode_goldCountAdjustment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_testCountAdjustment;
  /** @generated */
  final int     casFeatCode_testCountAdjustment;
  /** @generated */ 
  public int getTestCountAdjustment(int addr) {
        if (featOkTst && casFeat_testCountAdjustment == null)
      jcas.throwFeatMissing("testCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getIntValue(addr, casFeatCode_testCountAdjustment);
  }
  /** @generated */    
  public void setTestCountAdjustment(int addr, int v) {
        if (featOkTst && casFeat_testCountAdjustment == null)
      jcas.throwFeatMissing("testCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setIntValue(addr, casFeatCode_testCountAdjustment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_matchCountAdjustment;
  /** @generated */
  final int     casFeatCode_matchCountAdjustment;
  /** @generated */ 
  public int getMatchCountAdjustment(int addr) {
        if (featOkTst && casFeat_matchCountAdjustment == null)
      jcas.throwFeatMissing("matchCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    return ll_cas.ll_getIntValue(addr, casFeatCode_matchCountAdjustment);
  }
  /** @generated */    
  public void setMatchCountAdjustment(int addr, int v) {
        if (featOkTst && casFeat_matchCountAdjustment == null)
      jcas.throwFeatMissing("matchCountAdjustment", "org.u_compare.shared.comparable.ComparisonSet");
    ll_cas.ll_setIntValue(addr, casFeatCode_matchCountAdjustment, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ComparisonSet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_goldAnnotations = jcas.getRequiredFeatureDE(casType, "goldAnnotations", "uima.cas.FSArray", featOkTst);
    casFeatCode_goldAnnotations  = (null == casFeat_goldAnnotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_goldAnnotations).getCode();

 
    casFeat_testAnnotations = jcas.getRequiredFeatureDE(casType, "testAnnotations", "uima.cas.FSArray", featOkTst);
    casFeatCode_testAnnotations  = (null == casFeat_testAnnotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_testAnnotations).getCode();

 
    casFeat_matchedAnnotations = jcas.getRequiredFeatureDE(casType, "matchedAnnotations", "uima.cas.FSArray", featOkTst);
    casFeatCode_matchedAnnotations  = (null == casFeat_matchedAnnotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matchedAnnotations).getCode();

 
    casFeat_goldAnnotationGroup = jcas.getRequiredFeatureDE(casType, "goldAnnotationGroup", "org.u_compare.shared.comparable.AnnotationGroup", featOkTst);
    casFeatCode_goldAnnotationGroup  = (null == casFeat_goldAnnotationGroup) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_goldAnnotationGroup).getCode();

 
    casFeat_testAnnotationGroup = jcas.getRequiredFeatureDE(casType, "testAnnotationGroup", "org.u_compare.shared.comparable.AnnotationGroup", featOkTst);
    casFeatCode_testAnnotationGroup  = (null == casFeat_testAnnotationGroup) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_testAnnotationGroup).getCode();

 
    casFeat_scores = jcas.getRequiredFeatureDE(casType, "scores", "uima.cas.TOP", featOkTst);
    casFeatCode_scores  = (null == casFeat_scores) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_scores).getCode();

 
    casFeat_fields = jcas.getRequiredFeatureDE(casType, "fields", "uima.cas.TOP", featOkTst);
    casFeatCode_fields  = (null == casFeat_fields) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fields).getCode();

 
    casFeat_goldCountAdjustment = jcas.getRequiredFeatureDE(casType, "goldCountAdjustment", "uima.cas.Integer", featOkTst);
    casFeatCode_goldCountAdjustment  = (null == casFeat_goldCountAdjustment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_goldCountAdjustment).getCode();

 
    casFeat_testCountAdjustment = jcas.getRequiredFeatureDE(casType, "testCountAdjustment", "uima.cas.Integer", featOkTst);
    casFeatCode_testCountAdjustment  = (null == casFeat_testCountAdjustment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_testCountAdjustment).getCode();

 
    casFeat_matchCountAdjustment = jcas.getRequiredFeatureDE(casType, "matchCountAdjustment", "uima.cas.Integer", featOkTst);
    casFeatCode_matchCountAdjustment  = (null == casFeat_matchCountAdjustment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_matchCountAdjustment).getCode();

  }
}



    