
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

/** A class to store annotation metadata, provenance, etc.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class AnnotationMetadata_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnotationMetadata_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnotationMetadata_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnotationMetadata(addr, AnnotationMetadata_Type.this);
  			   AnnotationMetadata_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnotationMetadata(addr, AnnotationMetadata_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AnnotationMetadata.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
 
  /** @generated */
  final Feature casFeat_confidence;
  /** @generated */
  final int     casFeatCode_confidence;
  /** @generated */ 
  public float getConfidence(int addr) {
        if (featOkTst && casFeat_confidence == null)
      jcas.throwFeatMissing("confidence", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_confidence);
  }
  /** @generated */    
  public void setConfidence(int addr, float v) {
        if (featOkTst && casFeat_confidence == null)
      jcas.throwFeatMissing("confidence", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    ll_cas.ll_setFloatValue(addr, casFeatCode_confidence, v);}
    
  
 
  /** @generated */
  final Feature casFeat_metadataProperties;
  /** @generated */
  final int     casFeatCode_metadataProperties;
  /** @generated */ 
  public int getMetadataProperties(int addr) {
        if (featOkTst && casFeat_metadataProperties == null)
      jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    return ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties);
  }
  /** @generated */    
  public void setMetadataProperties(int addr, int v) {
        if (featOkTst && casFeat_metadataProperties == null)
      jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    ll_cas.ll_setRefValue(addr, casFeatCode_metadataProperties, v);}
    
   /** @generated */
  public int getMetadataProperties(int addr, int i) {
        if (featOkTst && casFeat_metadataProperties == null)
      jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties), i);
  }
   
  /** @generated */ 
  public void setMetadataProperties(int addr, int i, int v) {
        if (featOkTst && casFeat_metadataProperties == null)
      jcas.throwFeatMissing("metadataProperties", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_metadataProperties), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AnnotationMetadata_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_confidence = jcas.getRequiredFeatureDE(casType, "confidence", "uima.cas.Float", featOkTst);
    casFeatCode_confidence  = (null == casFeat_confidence) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_confidence).getCode();

 
    casFeat_metadataProperties = jcas.getRequiredFeatureDE(casType, "metadataProperties", "uima.cas.FSArray", featOkTst);
    casFeatCode_metadataProperties  = (null == casFeat_metadataProperties) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_metadataProperties).getCode();

  }
}



    