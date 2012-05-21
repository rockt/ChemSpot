
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** A special comparison set for similarty matches and gold-test-test ternary comparisons.
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * @generated */
public class TernaryComparisonSet_Type extends ComparisonSet_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TernaryComparisonSet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TernaryComparisonSet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TernaryComparisonSet(addr, TernaryComparisonSet_Type.this);
  			   TernaryComparisonSet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TernaryComparisonSet(addr, TernaryComparisonSet_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TernaryComparisonSet.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.comparable.TernaryComparisonSet");
 
  /** @generated */
  final Feature casFeat_assumedGoldComparisonSet;
  /** @generated */
  final int     casFeatCode_assumedGoldComparisonSet;
  /** @generated */ 
  public int getAssumedGoldComparisonSet(int addr) {
        if (featOkTst && casFeat_assumedGoldComparisonSet == null)
      jcas.throwFeatMissing("assumedGoldComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_assumedGoldComparisonSet);
  }
  /** @generated */    
  public void setAssumedGoldComparisonSet(int addr, int v) {
        if (featOkTst && casFeat_assumedGoldComparisonSet == null)
      jcas.throwFeatMissing("assumedGoldComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_assumedGoldComparisonSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_assumedTestComparisonSet;
  /** @generated */
  final int     casFeatCode_assumedTestComparisonSet;
  /** @generated */ 
  public int getAssumedTestComparisonSet(int addr) {
        if (featOkTst && casFeat_assumedTestComparisonSet == null)
      jcas.throwFeatMissing("assumedTestComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_assumedTestComparisonSet);
  }
  /** @generated */    
  public void setAssumedTestComparisonSet(int addr, int v) {
        if (featOkTst && casFeat_assumedTestComparisonSet == null)
      jcas.throwFeatMissing("assumedTestComparisonSet", "org.u_compare.shared.comparable.TernaryComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_assumedTestComparisonSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ternaryMatchedGoldAnnotations;
  /** @generated */
  final int     casFeatCode_ternaryMatchedGoldAnnotations;
  /** @generated */ 
  public int getTernaryMatchedGoldAnnotations(int addr) {
        if (featOkTst && casFeat_ternaryMatchedGoldAnnotations == null)
      jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations);
  }
  /** @generated */    
  public void setTernaryMatchedGoldAnnotations(int addr, int v) {
        if (featOkTst && casFeat_ternaryMatchedGoldAnnotations == null)
      jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations, v);}
    
   /** @generated */
  public int getTernaryMatchedGoldAnnotations(int addr, int i) {
        if (featOkTst && casFeat_ternaryMatchedGoldAnnotations == null)
      jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations), i);
  }
   
  /** @generated */ 
  public void setTernaryMatchedGoldAnnotations(int addr, int i, int v) {
        if (featOkTst && casFeat_ternaryMatchedGoldAnnotations == null)
      jcas.throwFeatMissing("ternaryMatchedGoldAnnotations", "org.u_compare.shared.comparable.TernaryComparisonSet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ternaryMatchedGoldAnnotations), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TernaryComparisonSet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_assumedGoldComparisonSet = jcas.getRequiredFeatureDE(casType, "assumedGoldComparisonSet", "org.u_compare.shared.comparable.ComparisonSet", featOkTst);
    casFeatCode_assumedGoldComparisonSet  = (null == casFeat_assumedGoldComparisonSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_assumedGoldComparisonSet).getCode();

 
    casFeat_assumedTestComparisonSet = jcas.getRequiredFeatureDE(casType, "assumedTestComparisonSet", "org.u_compare.shared.comparable.ComparisonSet", featOkTst);
    casFeatCode_assumedTestComparisonSet  = (null == casFeat_assumedTestComparisonSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_assumedTestComparisonSet).getCode();

 
    casFeat_ternaryMatchedGoldAnnotations = jcas.getRequiredFeatureDE(casType, "ternaryMatchedGoldAnnotations", "uima.cas.FSArray", featOkTst);
    casFeatCode_ternaryMatchedGoldAnnotations  = (null == casFeat_ternaryMatchedGoldAnnotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ternaryMatchedGoldAnnotations).getCode();

  }
}



    