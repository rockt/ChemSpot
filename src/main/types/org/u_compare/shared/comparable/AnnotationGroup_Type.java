
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
public class AnnotationGroup_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnotationGroup_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnotationGroup_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnotationGroup(addr, AnnotationGroup_Type.this);
  			   AnnotationGroup_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnotationGroup(addr, AnnotationGroup_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = AnnotationGroup.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.comparable.AnnotationGroup");
 
  /** @generated */
  final Feature casFeat_types;
  /** @generated */
  final int     casFeatCode_types;
  /** @generated */ 
  public int getTypes(int addr) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getRefValue(addr, casFeatCode_types);
  }
  /** @generated */    
  public void setTypes(int addr, int v) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setRefValue(addr, casFeatCode_types, v);}
    
   /** @generated */
  public String getTypes(int addr, int i) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
  }
   
  /** @generated */ 
  public void setTypes(int addr, int i, String v) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.AnnotationGroup");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_annotations;
  /** @generated */
  final int     casFeatCode_annotations;
  /** @generated */ 
  public int getAnnotations(int addr) {
        if (featOkTst && casFeat_annotations == null)
      jcas.throwFeatMissing("annotations", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotations);
  }
  /** @generated */    
  public void setAnnotations(int addr, int v) {
        if (featOkTst && casFeat_annotations == null)
      jcas.throwFeatMissing("annotations", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotations, v);}
    
  
 
  /** @generated */
  final Feature casFeat_derivingAnnotationGroups;
  /** @generated */
  final int     casFeatCode_derivingAnnotationGroups;
  /** @generated */ 
  public int getDerivingAnnotationGroups(int addr) {
        if (featOkTst && casFeat_derivingAnnotationGroups == null)
      jcas.throwFeatMissing("derivingAnnotationGroups", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroups);
  }
  /** @generated */    
  public void setDerivingAnnotationGroups(int addr, int v) {
        if (featOkTst && casFeat_derivingAnnotationGroups == null)
      jcas.throwFeatMissing("derivingAnnotationGroups", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setRefValue(addr, casFeatCode_derivingAnnotationGroups, v);}
    
  
 
  /** @generated */
  final Feature casFeat_componentID;
  /** @generated */
  final int     casFeatCode_componentID;
  /** @generated */ 
  public String getComponentID(int addr) {
        if (featOkTst && casFeat_componentID == null)
      jcas.throwFeatMissing("componentID", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getStringValue(addr, casFeatCode_componentID);
  }
  /** @generated */    
  public void setComponentID(int addr, String v) {
        if (featOkTst && casFeat_componentID == null)
      jcas.throwFeatMissing("componentID", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setStringValue(addr, casFeatCode_componentID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_latest;
  /** @generated */
  final int     casFeatCode_latest;
  /** @generated */ 
  public boolean getLatest(int addr) {
        if (featOkTst && casFeat_latest == null)
      jcas.throwFeatMissing("latest", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_latest);
  }
  /** @generated */    
  public void setLatest(int addr, boolean v) {
        if (featOkTst && casFeat_latest == null)
      jcas.throwFeatMissing("latest", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_latest, v);}
    
  
 
  /** @generated */
  final Feature casFeat_superTypes;
  /** @generated */
  final int     casFeatCode_superTypes;
  /** @generated */ 
  public int getSuperTypes(int addr) {
        if (featOkTst && casFeat_superTypes == null)
      jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getRefValue(addr, casFeatCode_superTypes);
  }
  /** @generated */    
  public void setSuperTypes(int addr, int v) {
        if (featOkTst && casFeat_superTypes == null)
      jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setRefValue(addr, casFeatCode_superTypes, v);}
    
   /** @generated */
  public String getSuperTypes(int addr, int i) {
        if (featOkTst && casFeat_superTypes == null)
      jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_superTypes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_superTypes), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_superTypes), i);
  }
   
  /** @generated */ 
  public void setSuperTypes(int addr, int i, String v) {
        if (featOkTst && casFeat_superTypes == null)
      jcas.throwFeatMissing("superTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_superTypes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_superTypes), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_superTypes), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_derivingAnnotationGroupTypes;
  /** @generated */
  final int     casFeatCode_derivingAnnotationGroupTypes;
  /** @generated */ 
  public int getDerivingAnnotationGroupTypes(int addr) {
        if (featOkTst && casFeat_derivingAnnotationGroupTypes == null)
      jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes);
  }
  /** @generated */    
  public void setDerivingAnnotationGroupTypes(int addr, int v) {
        if (featOkTst && casFeat_derivingAnnotationGroupTypes == null)
      jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setRefValue(addr, casFeatCode_derivingAnnotationGroupTypes, v);}
    
   /** @generated */
  public int getDerivingAnnotationGroupTypes(int addr, int i) {
        if (featOkTst && casFeat_derivingAnnotationGroupTypes == null)
      jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes), i);
  }
   
  /** @generated */ 
  public void setDerivingAnnotationGroupTypes(int addr, int i, int v) {
        if (featOkTst && casFeat_derivingAnnotationGroupTypes == null)
      jcas.throwFeatMissing("derivingAnnotationGroupTypes", "org.u_compare.shared.comparable.AnnotationGroup");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_derivingAnnotationGroupTypes), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_collectionReaderGenerated;
  /** @generated */
  final int     casFeatCode_collectionReaderGenerated;
  /** @generated */ 
  public boolean getCollectionReaderGenerated(int addr) {
        if (featOkTst && casFeat_collectionReaderGenerated == null)
      jcas.throwFeatMissing("collectionReaderGenerated", "org.u_compare.shared.comparable.AnnotationGroup");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_collectionReaderGenerated);
  }
  /** @generated */    
  public void setCollectionReaderGenerated(int addr, boolean v) {
        if (featOkTst && casFeat_collectionReaderGenerated == null)
      jcas.throwFeatMissing("collectionReaderGenerated", "org.u_compare.shared.comparable.AnnotationGroup");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_collectionReaderGenerated, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AnnotationGroup_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_types = jcas.getRequiredFeatureDE(casType, "types", "uima.cas.StringArray", featOkTst);
    casFeatCode_types  = (null == casFeat_types) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_types).getCode();

 
    casFeat_annotations = jcas.getRequiredFeatureDE(casType, "annotations", "uima.cas.FSList", featOkTst);
    casFeatCode_annotations  = (null == casFeat_annotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotations).getCode();

 
    casFeat_derivingAnnotationGroups = jcas.getRequiredFeatureDE(casType, "derivingAnnotationGroups", "uima.cas.FSList", featOkTst);
    casFeatCode_derivingAnnotationGroups  = (null == casFeat_derivingAnnotationGroups) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_derivingAnnotationGroups).getCode();

 
    casFeat_componentID = jcas.getRequiredFeatureDE(casType, "componentID", "uima.cas.String", featOkTst);
    casFeatCode_componentID  = (null == casFeat_componentID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_componentID).getCode();

 
    casFeat_latest = jcas.getRequiredFeatureDE(casType, "latest", "uima.cas.Boolean", featOkTst);
    casFeatCode_latest  = (null == casFeat_latest) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_latest).getCode();

 
    casFeat_superTypes = jcas.getRequiredFeatureDE(casType, "superTypes", "uima.cas.StringArray", featOkTst);
    casFeatCode_superTypes  = (null == casFeat_superTypes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_superTypes).getCode();

 
    casFeat_derivingAnnotationGroupTypes = jcas.getRequiredFeatureDE(casType, "derivingAnnotationGroupTypes", "uima.cas.FSArray", featOkTst);
    casFeatCode_derivingAnnotationGroupTypes  = (null == casFeat_derivingAnnotationGroupTypes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_derivingAnnotationGroupTypes).getCode();

 
    casFeat_collectionReaderGenerated = jcas.getRequiredFeatureDE(casType, "collectionReaderGenerated", "uima.cas.Boolean", featOkTst);
    casFeatCode_collectionReaderGenerated  = (null == casFeat_collectionReaderGenerated) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_collectionReaderGenerated).getCode();

  }
}



    