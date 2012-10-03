
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

/** The CCP TextAnnotation extends the base annotation class to include an annotation ID, the capability for multiple annotation spans, a link to the annotator responsible for generating the annotation, membership to annotation sets, and a link to a class mention which defines the class of this annotation.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPTextAnnotation_Type extends CCPAnnotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPTextAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPTextAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPTextAnnotation(addr, CCPTextAnnotation_Type.this);
  			   CCPTextAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPTextAnnotation(addr, CCPTextAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPTextAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
 
  /** @generated */
  final Feature casFeat_annotationID;
  /** @generated */
  final int     casFeatCode_annotationID;
  /** @generated */ 
  public int getAnnotationID(int addr) {
        if (featOkTst && casFeat_annotationID == null)
      jcas.throwFeatMissing("annotationID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_annotationID);
  }
  /** @generated */    
  public void setAnnotationID(int addr, int v) {
        if (featOkTst && casFeat_annotationID == null)
      jcas.throwFeatMissing("annotationID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_annotationID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_annotator;
  /** @generated */
  final int     casFeatCode_annotator;
  /** @generated */ 
  public int getAnnotator(int addr) {
        if (featOkTst && casFeat_annotator == null)
      jcas.throwFeatMissing("annotator", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotator);
  }
  /** @generated */    
  public void setAnnotator(int addr, int v) {
        if (featOkTst && casFeat_annotator == null)
      jcas.throwFeatMissing("annotator", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotator, v);}
    
  
 
  /** @generated */
  final Feature casFeat_documentSectionID;
  /** @generated */
  final int     casFeatCode_documentSectionID;
  /** @generated */ 
  public int getDocumentSectionID(int addr) {
        if (featOkTst && casFeat_documentSectionID == null)
      jcas.throwFeatMissing("documentSectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_documentSectionID);
  }
  /** @generated */    
  public void setDocumentSectionID(int addr, int v) {
        if (featOkTst && casFeat_documentSectionID == null)
      jcas.throwFeatMissing("documentSectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_documentSectionID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_annotationSets;
  /** @generated */
  final int     casFeatCode_annotationSets;
  /** @generated */ 
  public int getAnnotationSets(int addr) {
        if (featOkTst && casFeat_annotationSets == null)
      jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets);
  }
  /** @generated */    
  public void setAnnotationSets(int addr, int v) {
        if (featOkTst && casFeat_annotationSets == null)
      jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotationSets, v);}
    
   /** @generated */
  public int getAnnotationSets(int addr, int i) {
        if (featOkTst && casFeat_annotationSets == null)
      jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets), i);
  }
   
  /** @generated */ 
  public void setAnnotationSets(int addr, int i, int v) {
        if (featOkTst && casFeat_annotationSets == null)
      jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationSets), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_numberOfSpans;
  /** @generated */
  final int     casFeatCode_numberOfSpans;
  /** @generated */ 
  public int getNumberOfSpans(int addr) {
        if (featOkTst && casFeat_numberOfSpans == null)
      jcas.throwFeatMissing("numberOfSpans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numberOfSpans);
  }
  /** @generated */    
  public void setNumberOfSpans(int addr, int v) {
        if (featOkTst && casFeat_numberOfSpans == null)
      jcas.throwFeatMissing("numberOfSpans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_numberOfSpans, v);}
    
  
 
  /** @generated */
  final Feature casFeat_spans;
  /** @generated */
  final int     casFeatCode_spans;
  /** @generated */ 
  public int getSpans(int addr) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_spans);
  }
  /** @generated */    
  public void setSpans(int addr, int v) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_spans, v);}
    
   /** @generated */
  public int getSpans(int addr, int i) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i);
  }
   
  /** @generated */ 
  public void setSpans(int addr, int i, int v) {
        if (featOkTst && casFeat_spans == null)
      jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_spans), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_classMention;
  /** @generated */
  final int     casFeatCode_classMention;
  /** @generated */ 
  public int getClassMention(int addr) {
        if (featOkTst && casFeat_classMention == null)
      jcas.throwFeatMissing("classMention", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_classMention);
  }
  /** @generated */    
  public void setClassMention(int addr, int v) {
        if (featOkTst && casFeat_classMention == null)
      jcas.throwFeatMissing("classMention", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_classMention, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPTextAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_annotationID = jcas.getRequiredFeatureDE(casType, "annotationID", "uima.cas.Integer", featOkTst);
    casFeatCode_annotationID  = (null == casFeat_annotationID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationID).getCode();

 
    casFeat_annotator = jcas.getRequiredFeatureDE(casType, "annotator", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPAnnotator", featOkTst);
    casFeatCode_annotator  = (null == casFeat_annotator) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotator).getCode();

 
    casFeat_documentSectionID = jcas.getRequiredFeatureDE(casType, "documentSectionID", "uima.cas.Integer", featOkTst);
    casFeatCode_documentSectionID  = (null == casFeat_documentSectionID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentSectionID).getCode();

 
    casFeat_annotationSets = jcas.getRequiredFeatureDE(casType, "annotationSets", "uima.cas.FSArray", featOkTst);
    casFeatCode_annotationSets  = (null == casFeat_annotationSets) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationSets).getCode();

 
    casFeat_numberOfSpans = jcas.getRequiredFeatureDE(casType, "numberOfSpans", "uima.cas.Integer", featOkTst);
    casFeatCode_numberOfSpans  = (null == casFeat_numberOfSpans) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numberOfSpans).getCode();

 
    casFeat_spans = jcas.getRequiredFeatureDE(casType, "spans", "uima.cas.FSArray", featOkTst);
    casFeatCode_spans  = (null == casFeat_spans) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_spans).getCode();

 
    casFeat_classMention = jcas.getRequiredFeatureDE(casType, "classMention", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention", featOkTst);
    casFeatCode_classMention  = (null == casFeat_classMention) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_classMention).getCode();

  }
}



    