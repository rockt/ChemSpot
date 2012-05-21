
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
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class AnnotationGroupCluster_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnotationGroupCluster_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnotationGroupCluster_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnotationGroupCluster(addr, AnnotationGroupCluster_Type.this);
  			   AnnotationGroupCluster_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnotationGroupCluster(addr, AnnotationGroupCluster_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = AnnotationGroupCluster.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.comparable.AnnotationGroupCluster");
 
  /** @generated */
  final Feature casFeat_types;
  /** @generated */
  final int     casFeatCode_types;
  /** @generated */ 
  public int getTypes(int addr) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    return ll_cas.ll_getRefValue(addr, casFeatCode_types);
  }
  /** @generated */    
  public void setTypes(int addr, int v) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    ll_cas.ll_setRefValue(addr, casFeatCode_types, v);}
    
   /** @generated */
  public String getTypes(int addr, int i) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
  }
   
  /** @generated */ 
  public void setTypes(int addr, int i, String v) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_annotationGroups;
  /** @generated */
  final int     casFeatCode_annotationGroups;
  /** @generated */ 
  public int getAnnotationGroups(int addr) {
        if (featOkTst && casFeat_annotationGroups == null)
      jcas.throwFeatMissing("annotationGroups", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotationGroups);
  }
  /** @generated */    
  public void setAnnotationGroups(int addr, int v) {
        if (featOkTst && casFeat_annotationGroups == null)
      jcas.throwFeatMissing("annotationGroups", "org.u_compare.shared.comparable.AnnotationGroupCluster");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotationGroups, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AnnotationGroupCluster_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_types = jcas.getRequiredFeatureDE(casType, "types", "uima.cas.StringArray", featOkTst);
    casFeatCode_types  = (null == casFeat_types) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_types).getCode();

 
    casFeat_annotationGroups = jcas.getRequiredFeatureDE(casType, "annotationGroups", "uima.cas.FSList", featOkTst);
    casFeatCode_annotationGroups  = (null == casFeat_annotationGroups) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationGroups).getCode();

  }
}



    