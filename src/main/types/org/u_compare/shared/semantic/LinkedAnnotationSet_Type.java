
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
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:19 CET 2012
 * @generated */
public class LinkedAnnotationSet_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (LinkedAnnotationSet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = LinkedAnnotationSet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new LinkedAnnotationSet(addr, LinkedAnnotationSet_Type.this);
  			   LinkedAnnotationSet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new LinkedAnnotationSet(addr, LinkedAnnotationSet_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = LinkedAnnotationSet.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.semantic.LinkedAnnotationSet");
 
  /** @generated */
  final Feature casFeat_linkingRelation;
  /** @generated */
  final int     casFeatCode_linkingRelation;
  /** @generated */ 
  public int getLinkingRelation(int addr) {
        if (featOkTst && casFeat_linkingRelation == null)
      jcas.throwFeatMissing("linkingRelation", "org.u_compare.shared.semantic.LinkedAnnotationSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_linkingRelation);
  }
  /** @generated */    
  public void setLinkingRelation(int addr, int v) {
        if (featOkTst && casFeat_linkingRelation == null)
      jcas.throwFeatMissing("linkingRelation", "org.u_compare.shared.semantic.LinkedAnnotationSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_linkingRelation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_linkedAnnotations;
  /** @generated */
  final int     casFeatCode_linkedAnnotations;
  /** @generated */ 
  public int getLinkedAnnotations(int addr) {
        if (featOkTst && casFeat_linkedAnnotations == null)
      jcas.throwFeatMissing("linkedAnnotations", "org.u_compare.shared.semantic.LinkedAnnotationSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations);
  }
  /** @generated */    
  public void setLinkedAnnotations(int addr, int v) {
        if (featOkTst && casFeat_linkedAnnotations == null)
      jcas.throwFeatMissing("linkedAnnotations", "org.u_compare.shared.semantic.LinkedAnnotationSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_linkedAnnotations, v);}
    
   /** @generated */
  public int getLinkedAnnotations(int addr, int i) {
        if (featOkTst && casFeat_linkedAnnotations == null)
      jcas.throwFeatMissing("linkedAnnotations", "org.u_compare.shared.semantic.LinkedAnnotationSet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations), i);
  }
   
  /** @generated */ 
  public void setLinkedAnnotations(int addr, int i, int v) {
        if (featOkTst && casFeat_linkedAnnotations == null)
      jcas.throwFeatMissing("linkedAnnotations", "org.u_compare.shared.semantic.LinkedAnnotationSet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_linkedAnnotations), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public LinkedAnnotationSet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_linkingRelation = jcas.getRequiredFeatureDE(casType, "linkingRelation", "org.u_compare.shared.ExternalReference", featOkTst);
    casFeatCode_linkingRelation  = (null == casFeat_linkingRelation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_linkingRelation).getCode();

 
    casFeat_linkedAnnotations = jcas.getRequiredFeatureDE(casType, "linkedAnnotations", "uima.cas.FSArray", featOkTst);
    casFeatCode_linkedAnnotations  = (null == casFeat_linkedAnnotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_linkedAnnotations).getCode();

  }
}



    