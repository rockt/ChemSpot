
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Base abstract type for annotations with discontinuous regions. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class DiscontinuousAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DiscontinuousAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DiscontinuousAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DiscontinuousAnnotation(addr, DiscontinuousAnnotation_Type.this);
  			   DiscontinuousAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DiscontinuousAnnotation(addr, DiscontinuousAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DiscontinuousAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.DiscontinuousAnnotation");
 
  /** @generated */
  final Feature casFeat_fragments;
  /** @generated */
  final int     casFeatCode_fragments;
  /** @generated */ 
  public int getFragments(int addr) {
        if (featOkTst && casFeat_fragments == null)
      jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_fragments);
  }
  /** @generated */    
  public void setFragments(int addr, int v) {
        if (featOkTst && casFeat_fragments == null)
      jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_fragments, v);}
    
   /** @generated */
  public int getFragments(int addr, int i) {
        if (featOkTst && casFeat_fragments == null)
      jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_fragments), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_fragments), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_fragments), i);
  }
   
  /** @generated */ 
  public void setFragments(int addr, int i, int v) {
        if (featOkTst && casFeat_fragments == null)
      jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_fragments), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_fragments), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_fragments), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DiscontinuousAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_fragments = jcas.getRequiredFeatureDE(casType, "fragments", "uima.cas.FSArray", featOkTst);
    casFeatCode_fragments  = (null == casFeat_fragments) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fragments).getCode();

  }
}



    