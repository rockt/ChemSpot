
/* First created by JCasGen Thu Jul 14 11:57:11 CEST 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** For system use. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class FeatureTrace_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FeatureTrace_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FeatureTrace_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FeatureTrace(addr, FeatureTrace_Type.this);
  			   FeatureTrace_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FeatureTrace(addr, FeatureTrace_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = FeatureTrace.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.FeatureTrace");
 
  /** @generated */
  final Feature casFeat_outcome;
  /** @generated */
  final int     casFeatCode_outcome;
  /** @generated */ 
  public int getOutcome(int addr) {
        if (featOkTst && casFeat_outcome == null)
      jcas.throwFeatMissing("outcome", "org.u_compare.shared.FeatureTrace");
    return ll_cas.ll_getRefValue(addr, casFeatCode_outcome);
  }
  /** @generated */    
  public void setOutcome(int addr, int v) {
        if (featOkTst && casFeat_outcome == null)
      jcas.throwFeatMissing("outcome", "org.u_compare.shared.FeatureTrace");
    ll_cas.ll_setRefValue(addr, casFeatCode_outcome, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nbests;
  /** @generated */
  final int     casFeatCode_nbests;
  /** @generated */ 
  public int getNbests(int addr) {
        if (featOkTst && casFeat_nbests == null)
      jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    return ll_cas.ll_getRefValue(addr, casFeatCode_nbests);
  }
  /** @generated */    
  public void setNbests(int addr, int v) {
        if (featOkTst && casFeat_nbests == null)
      jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    ll_cas.ll_setRefValue(addr, casFeatCode_nbests, v);}
    
   /** @generated */
  public int getNbests(int addr, int i) {
        if (featOkTst && casFeat_nbests == null)
      jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_nbests), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_nbests), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_nbests), i);
  }
   
  /** @generated */ 
  public void setNbests(int addr, int i, int v) {
        if (featOkTst && casFeat_nbests == null)
      jcas.throwFeatMissing("nbests", "org.u_compare.shared.FeatureTrace");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_nbests), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_nbests), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_nbests), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public FeatureTrace_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_outcome = jcas.getRequiredFeatureDE(casType, "outcome", "uima.cas.TOP", featOkTst);
    casFeatCode_outcome  = (null == casFeat_outcome) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_outcome).getCode();

 
    casFeat_nbests = jcas.getRequiredFeatureDE(casType, "nbests", "uima.cas.FSArray", featOkTst);
    casFeatCode_nbests  = (null == casFeat_nbests) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nbests).getCode();

  }
}



    