
/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.semantic;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * @generated */
public class SemanticClassAnnotation_Type extends SemanticAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SemanticClassAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SemanticClassAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SemanticClassAnnotation(addr, SemanticClassAnnotation_Type.this);
  			   SemanticClassAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SemanticClassAnnotation(addr, SemanticClassAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SemanticClassAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.semantic.SemanticClassAnnotation");
 
  /** @generated */
  final Feature casFeat_linkedAnnotationSets;
  /** @generated */
  final int     casFeatCode_linkedAnnotationSets;
  /** @generated */ 
  public int getLinkedAnnotationSets(int addr) {
        if (featOkTst && casFeat_linkedAnnotationSets == null)
      jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets);
  }
  /** @generated */    
  public void setLinkedAnnotationSets(int addr, int v) {
        if (featOkTst && casFeat_linkedAnnotationSets == null)
      jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_linkedAnnotationSets, v);}
    
   /** @generated */
  public int getLinkedAnnotationSets(int addr, int i) {
        if (featOkTst && casFeat_linkedAnnotationSets == null)
      jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets), i);
  }
   
  /** @generated */ 
  public void setLinkedAnnotationSets(int addr, int i, int v) {
        if (featOkTst && casFeat_linkedAnnotationSets == null)
      jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotationSets), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SemanticClassAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_linkedAnnotationSets = jcas.getRequiredFeatureDE(casType, "linkedAnnotationSets", "uima.cas.FSArray", featOkTst);
    casFeatCode_linkedAnnotationSets  = (null == casFeat_linkedAnnotationSets) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_linkedAnnotationSets).getCode();

  }
}



    