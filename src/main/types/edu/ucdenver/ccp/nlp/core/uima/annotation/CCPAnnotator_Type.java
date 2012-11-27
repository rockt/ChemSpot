
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

/** The annotator object contains information which is used to determine who/what generated an annotation.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPAnnotator_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPAnnotator_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPAnnotator_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPAnnotator(addr, CCPAnnotator_Type.this);
  			   CCPAnnotator_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPAnnotator(addr, CCPAnnotator_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPAnnotator.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
 
  /** @generated */
  final Feature casFeat_annotatorID;
  /** @generated */
  final int     casFeatCode_annotatorID;
  /** @generated */ 
  public int getAnnotatorID(int addr) {
        if (featOkTst && casFeat_annotatorID == null)
      jcas.throwFeatMissing("annotatorID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return ll_cas.ll_getIntValue(addr, casFeatCode_annotatorID);
  }
  /** @generated */    
  public void setAnnotatorID(int addr, int v) {
        if (featOkTst && casFeat_annotatorID == null)
      jcas.throwFeatMissing("annotatorID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    ll_cas.ll_setIntValue(addr, casFeatCode_annotatorID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_firstName;
  /** @generated */
  final int     casFeatCode_firstName;
  /** @generated */ 
  public String getFirstName(int addr) {
        if (featOkTst && casFeat_firstName == null)
      jcas.throwFeatMissing("firstName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return ll_cas.ll_getStringValue(addr, casFeatCode_firstName);
  }
  /** @generated */    
  public void setFirstName(int addr, String v) {
        if (featOkTst && casFeat_firstName == null)
      jcas.throwFeatMissing("firstName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    ll_cas.ll_setStringValue(addr, casFeatCode_firstName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lastName;
  /** @generated */
  final int     casFeatCode_lastName;
  /** @generated */ 
  public String getLastName(int addr) {
        if (featOkTst && casFeat_lastName == null)
      jcas.throwFeatMissing("lastName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lastName);
  }
  /** @generated */    
  public void setLastName(int addr, String v) {
        if (featOkTst && casFeat_lastName == null)
      jcas.throwFeatMissing("lastName", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    ll_cas.ll_setStringValue(addr, casFeatCode_lastName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_affiliation;
  /** @generated */
  final int     casFeatCode_affiliation;
  /** @generated */ 
  public String getAffiliation(int addr) {
        if (featOkTst && casFeat_affiliation == null)
      jcas.throwFeatMissing("affiliation", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    return ll_cas.ll_getStringValue(addr, casFeatCode_affiliation);
  }
  /** @generated */    
  public void setAffiliation(int addr, String v) {
        if (featOkTst && casFeat_affiliation == null)
      jcas.throwFeatMissing("affiliation", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator");
    ll_cas.ll_setStringValue(addr, casFeatCode_affiliation, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPAnnotator_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_annotatorID = jcas.getRequiredFeatureDE(casType, "annotatorID", "uima.cas.Integer", featOkTst);
    casFeatCode_annotatorID  = (null == casFeat_annotatorID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotatorID).getCode();

 
    casFeat_firstName = jcas.getRequiredFeatureDE(casType, "firstName", "uima.cas.String", featOkTst);
    casFeatCode_firstName  = (null == casFeat_firstName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_firstName).getCode();

 
    casFeat_lastName = jcas.getRequiredFeatureDE(casType, "lastName", "uima.cas.String", featOkTst);
    casFeatCode_lastName  = (null == casFeat_lastName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lastName).getCode();

 
    casFeat_affiliation = jcas.getRequiredFeatureDE(casType, "affiliation", "uima.cas.String", featOkTst);
    casFeatCode_affiliation  = (null == casFeat_affiliation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_affiliation).getCode();

  }
}



    