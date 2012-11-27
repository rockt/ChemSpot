
/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation.metadata;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A metadata property for declaring AnnotationSet membership. This will eventually replace the annotationSets field that is currently part of the CCPTextAnnotation class.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class AnnotationSetMembershipProperty_Type extends AnnotationMetadataProperty_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnotationSetMembershipProperty_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnotationSetMembershipProperty_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnotationSetMembershipProperty(addr, AnnotationSetMembershipProperty_Type.this);
  			   AnnotationSetMembershipProperty_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnotationSetMembershipProperty(addr, AnnotationSetMembershipProperty_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AnnotationSetMembershipProperty.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.metadata.AnnotationSetMembershipProperty");
 
  /** @generated */
  final Feature casFeat_annotationSet;
  /** @generated */
  final int     casFeatCode_annotationSet;
  /** @generated */ 
  public int getAnnotationSet(int addr) {
        if (featOkTst && casFeat_annotationSet == null)
      jcas.throwFeatMissing("annotationSet", "edu.ucdenver.ccp.nlp.core.uima.annotation.metadata.AnnotationSetMembershipProperty");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotationSet);
  }
  /** @generated */    
  public void setAnnotationSet(int addr, int v) {
        if (featOkTst && casFeat_annotationSet == null)
      jcas.throwFeatMissing("annotationSet", "edu.ucdenver.ccp.nlp.core.uima.annotation.metadata.AnnotationSetMembershipProperty");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotationSet, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AnnotationSetMembershipProperty_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_annotationSet = jcas.getRequiredFeatureDE(casType, "annotationSet", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet", featOkTst);
    casFeatCode_annotationSet  = (null == casFeat_annotationSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationSet).getCode();

  }
}



    