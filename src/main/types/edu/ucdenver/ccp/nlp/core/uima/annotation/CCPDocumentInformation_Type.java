
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

/** The CCPDocumentInformation annotation includes document metadata such as the document ID, document collection ID, secondary document IDs, document size, etc.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPDocumentInformation_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPDocumentInformation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPDocumentInformation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPDocumentInformation(addr, CCPDocumentInformation_Type.this);
  			   CCPDocumentInformation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPDocumentInformation(addr, CCPDocumentInformation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPDocumentInformation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
 
  /** @generated */
  final Feature casFeat_documentID;
  /** @generated */
  final int     casFeatCode_documentID;
  /** @generated */ 
  public String getDocumentID(int addr) {
        if (featOkTst && casFeat_documentID == null)
      jcas.throwFeatMissing("documentID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_documentID);
  }
  /** @generated */    
  public void setDocumentID(int addr, String v) {
        if (featOkTst && casFeat_documentID == null)
      jcas.throwFeatMissing("documentID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    ll_cas.ll_setStringValue(addr, casFeatCode_documentID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_documentCollectionID;
  /** @generated */
  final int     casFeatCode_documentCollectionID;
  /** @generated */ 
  public int getDocumentCollectionID(int addr) {
        if (featOkTst && casFeat_documentCollectionID == null)
      jcas.throwFeatMissing("documentCollectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_documentCollectionID);
  }
  /** @generated */    
  public void setDocumentCollectionID(int addr, int v) {
        if (featOkTst && casFeat_documentCollectionID == null)
      jcas.throwFeatMissing("documentCollectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    ll_cas.ll_setIntValue(addr, casFeatCode_documentCollectionID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_documentSize;
  /** @generated */
  final int     casFeatCode_documentSize;
  /** @generated */ 
  public int getDocumentSize(int addr) {
        if (featOkTst && casFeat_documentSize == null)
      jcas.throwFeatMissing("documentSize", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_documentSize);
  }
  /** @generated */    
  public void setDocumentSize(int addr, int v) {
        if (featOkTst && casFeat_documentSize == null)
      jcas.throwFeatMissing("documentSize", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    ll_cas.ll_setIntValue(addr, casFeatCode_documentSize, v);}
    
  
 
  /** @generated */
  final Feature casFeat_secondaryDocumentIDs;
  /** @generated */
  final int     casFeatCode_secondaryDocumentIDs;
  /** @generated */ 
  public int getSecondaryDocumentIDs(int addr) {
        if (featOkTst && casFeat_secondaryDocumentIDs == null)
      jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs);
  }
  /** @generated */    
  public void setSecondaryDocumentIDs(int addr, int v) {
        if (featOkTst && casFeat_secondaryDocumentIDs == null)
      jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    ll_cas.ll_setRefValue(addr, casFeatCode_secondaryDocumentIDs, v);}
    
   /** @generated */
  public String getSecondaryDocumentIDs(int addr, int i) {
        if (featOkTst && casFeat_secondaryDocumentIDs == null)
      jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs), i);
  }
   
  /** @generated */ 
  public void setSecondaryDocumentIDs(int addr, int i, String v) {
        if (featOkTst && casFeat_secondaryDocumentIDs == null)
      jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_secondaryDocumentIDs), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_classificationType;
  /** @generated */
  final int     casFeatCode_classificationType;
  /** @generated */ 
  public String getClassificationType(int addr) {
        if (featOkTst && casFeat_classificationType == null)
      jcas.throwFeatMissing("classificationType", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_classificationType);
  }
  /** @generated */    
  public void setClassificationType(int addr, String v) {
        if (featOkTst && casFeat_classificationType == null)
      jcas.throwFeatMissing("classificationType", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    ll_cas.ll_setStringValue(addr, casFeatCode_classificationType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_encoding;
  /** @generated */
  final int     casFeatCode_encoding;
  /** @generated */ 
  public String getEncoding(int addr) {
        if (featOkTst && casFeat_encoding == null)
      jcas.throwFeatMissing("encoding", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_encoding);
  }
  /** @generated */    
  public void setEncoding(int addr, String v) {
        if (featOkTst && casFeat_encoding == null)
      jcas.throwFeatMissing("encoding", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    ll_cas.ll_setStringValue(addr, casFeatCode_encoding, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPDocumentInformation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_documentID = jcas.getRequiredFeatureDE(casType, "documentID", "uima.cas.String", featOkTst);
    casFeatCode_documentID  = (null == casFeat_documentID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentID).getCode();

 
    casFeat_documentCollectionID = jcas.getRequiredFeatureDE(casType, "documentCollectionID", "uima.cas.Integer", featOkTst);
    casFeatCode_documentCollectionID  = (null == casFeat_documentCollectionID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentCollectionID).getCode();

 
    casFeat_documentSize = jcas.getRequiredFeatureDE(casType, "documentSize", "uima.cas.Integer", featOkTst);
    casFeatCode_documentSize  = (null == casFeat_documentSize) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentSize).getCode();

 
    casFeat_secondaryDocumentIDs = jcas.getRequiredFeatureDE(casType, "secondaryDocumentIDs", "uima.cas.StringArray", featOkTst);
    casFeatCode_secondaryDocumentIDs  = (null == casFeat_secondaryDocumentIDs) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_secondaryDocumentIDs).getCode();

 
    casFeat_classificationType = jcas.getRequiredFeatureDE(casType, "classificationType", "uima.cas.String", featOkTst);
    casFeatCode_classificationType  = (null == casFeat_classificationType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_classificationType).getCode();

 
    casFeat_encoding = jcas.getRequiredFeatureDE(casType, "encoding", "uima.cas.String", featOkTst);
    casFeatCode_encoding  = (null == casFeat_encoding) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_encoding).getCode();

  }
}



    