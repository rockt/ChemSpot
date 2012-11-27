
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

/** The annotation set provides a means for arbitrarily categorizing or clustering groups of annotations. Annotations can be associated with multiple annotation groups. Examples of use include, defining Gold Standard annotation sets, and delineating between the use of different parameters during annotation, among others. Each annotation set is associated with a unique ID, a name and a description.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPAnnotationSet_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPAnnotationSet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPAnnotationSet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPAnnotationSet(addr, CCPAnnotationSet_Type.this);
  			   CCPAnnotationSet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPAnnotationSet(addr, CCPAnnotationSet_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPAnnotationSet.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
 
  /** @generated */
  final Feature casFeat_annotationSetID;
  /** @generated */
  final int     casFeatCode_annotationSetID;
  /** @generated */ 
  public int getAnnotationSetID(int addr) {
        if (featOkTst && casFeat_annotationSetID == null)
      jcas.throwFeatMissing("annotationSetID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    return ll_cas.ll_getIntValue(addr, casFeatCode_annotationSetID);
  }
  /** @generated */    
  public void setAnnotationSetID(int addr, int v) {
        if (featOkTst && casFeat_annotationSetID == null)
      jcas.throwFeatMissing("annotationSetID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    ll_cas.ll_setIntValue(addr, casFeatCode_annotationSetID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_annotationSetName;
  /** @generated */
  final int     casFeatCode_annotationSetName;
  /** @generated */ 
  public String getAnnotationSetName(int addr) {
        if (featOkTst && casFeat_annotationSetName == null)
      jcas.throwFeatMissing("annotationSetName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_annotationSetName);
  }
  /** @generated */    
  public void setAnnotationSetName(int addr, String v) {
        if (featOkTst && casFeat_annotationSetName == null)
      jcas.throwFeatMissing("annotationSetName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    ll_cas.ll_setStringValue(addr, casFeatCode_annotationSetName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_annotationSetDescription;
  /** @generated */
  final int     casFeatCode_annotationSetDescription;
  /** @generated */ 
  public String getAnnotationSetDescription(int addr) {
        if (featOkTst && casFeat_annotationSetDescription == null)
      jcas.throwFeatMissing("annotationSetDescription", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_annotationSetDescription);
  }
  /** @generated */    
  public void setAnnotationSetDescription(int addr, String v) {
        if (featOkTst && casFeat_annotationSetDescription == null)
      jcas.throwFeatMissing("annotationSetDescription", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotationSet");
    ll_cas.ll_setStringValue(addr, casFeatCode_annotationSetDescription, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPAnnotationSet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_annotationSetID = jcas.getRequiredFeatureDE(casType, "annotationSetID", "uima.cas.Integer", featOkTst);
    casFeatCode_annotationSetID  = (null == casFeat_annotationSetID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationSetID).getCode();

 
    casFeat_annotationSetName = jcas.getRequiredFeatureDE(casType, "annotationSetName", "uima.cas.String", featOkTst);
    casFeatCode_annotationSetName  = (null == casFeat_annotationSetName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationSetName).getCode();

 
    casFeat_annotationSetDescription = jcas.getRequiredFeatureDE(casType, "annotationSetDescription", "uima.cas.String", featOkTst);
    casFeatCode_annotationSetDescription  = (null == casFeat_annotationSetDescription) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationSetDescription).getCode();

  }
}



    