
/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

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

/** The span object holds span information.This is a supplement to the default UIMA annotation which cannot handle multi-span annotations.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPSpan_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPSpan_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPSpan_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPSpan(addr, CCPSpan_Type.this);
  			   CCPSpan_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPSpan(addr, CCPSpan_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPSpan.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
 
  /** @generated */
  final Feature casFeat_spanStart;
  /** @generated */
  final int     casFeatCode_spanStart;
  /** @generated */ 
  public int getSpanStart(int addr) {
        if (featOkTst && casFeat_spanStart == null)
      jcas.throwFeatMissing("spanStart", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    return ll_cas.ll_getIntValue(addr, casFeatCode_spanStart);
  }
  /** @generated */    
  public void setSpanStart(int addr, int v) {
        if (featOkTst && casFeat_spanStart == null)
      jcas.throwFeatMissing("spanStart", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    ll_cas.ll_setIntValue(addr, casFeatCode_spanStart, v);}
    
  
 
  /** @generated */
  final Feature casFeat_spanEnd;
  /** @generated */
  final int     casFeatCode_spanEnd;
  /** @generated */ 
  public int getSpanEnd(int addr) {
        if (featOkTst && casFeat_spanEnd == null)
      jcas.throwFeatMissing("spanEnd", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    return ll_cas.ll_getIntValue(addr, casFeatCode_spanEnd);
  }
  /** @generated */    
  public void setSpanEnd(int addr, int v) {
        if (featOkTst && casFeat_spanEnd == null)
      jcas.throwFeatMissing("spanEnd", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    ll_cas.ll_setIntValue(addr, casFeatCode_spanEnd, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPSpan_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_spanStart = jcas.getRequiredFeatureDE(casType, "spanStart", "uima.cas.Integer", featOkTst);
    casFeatCode_spanStart  = (null == casFeat_spanStart) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_spanStart).getCode();

 
    casFeat_spanEnd = jcas.getRequiredFeatureDE(casType, "spanEnd", "uima.cas.Integer", featOkTst);
    casFeatCode_spanEnd  = (null == casFeat_spanEnd) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_spanEnd).getCode();

  }
}



    