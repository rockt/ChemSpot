
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** The superclass for all CCP annotations.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPAnnotation_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPAnnotation(addr, CCPAnnotation_Type.this);
  			   CCPAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPAnnotation(addr, CCPAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotation");
 
  /** @generated */
  final Feature casFeat_annotationMetadata;
  /** @generated */
  final int     casFeatCode_annotationMetadata;
  /** @generated */ 
  public int getAnnotationMetadata(int addr) {
        if (featOkTst && casFeat_annotationMetadata == null)
      jcas.throwFeatMissing("annotationMetadata", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotationMetadata);
  }
  /** @generated */    
  public void setAnnotationMetadata(int addr, int v) {
        if (featOkTst && casFeat_annotationMetadata == null)
      jcas.throwFeatMissing("annotationMetadata", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotationMetadata, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_annotationMetadata = jcas.getRequiredFeatureDE(casType, "annotationMetadata", "edu.ucdenver.ccp.nlp.core.uima.annotation.AnnotationMetadata", featOkTst);
    casFeatCode_annotationMetadata  = (null == casFeat_annotationMetadata) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationMetadata).getCode();

  }
}



    