
/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

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

/** The superclass for all CCP Mentions (class mention, complex slot mention, and non-complex slot mention)
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPMention_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPMention(addr, CCPMention_Type.this);
  			   CCPMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPMention(addr, CCPMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
 
  /** @generated */
  final Feature casFeat_mentionName;
  /** @generated */
  final int     casFeatCode_mentionName;
  /** @generated */ 
  public String getMentionName(int addr) {
        if (featOkTst && casFeat_mentionName == null)
      jcas.throwFeatMissing("mentionName", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mentionName);
  }
  /** @generated */    
  public void setMentionName(int addr, String v) {
        if (featOkTst && casFeat_mentionName == null)
      jcas.throwFeatMissing("mentionName", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    ll_cas.ll_setStringValue(addr, casFeatCode_mentionName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mentionID;
  /** @generated */
  final int     casFeatCode_mentionID;
  /** @generated */ 
  public long getMentionID(int addr) {
        if (featOkTst && casFeat_mentionID == null)
      jcas.throwFeatMissing("mentionID", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return ll_cas.ll_getLongValue(addr, casFeatCode_mentionID);
  }
  /** @generated */    
  public void setMentionID(int addr, long v) {
        if (featOkTst && casFeat_mentionID == null)
      jcas.throwFeatMissing("mentionID", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    ll_cas.ll_setLongValue(addr, casFeatCode_mentionID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_traversalIDs;
  /** @generated */
  final int     casFeatCode_traversalIDs;
  /** @generated */ 
  public int getTraversalIDs(int addr) {
        if (featOkTst && casFeat_traversalIDs == null)
      jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs);
  }
  /** @generated */    
  public void setTraversalIDs(int addr, int v) {
        if (featOkTst && casFeat_traversalIDs == null)
      jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_traversalIDs, v);}
    
   /** @generated */
  public String getTraversalIDs(int addr, int i) {
        if (featOkTst && casFeat_traversalIDs == null)
      jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs), i);
  }
   
  /** @generated */ 
  public void setTraversalIDs(int addr, int i, String v) {
        if (featOkTst && casFeat_traversalIDs == null)
      jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalIDs), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_traversalMentionIDs;
  /** @generated */
  final int     casFeatCode_traversalMentionIDs;
  /** @generated */ 
  public int getTraversalMentionIDs(int addr) {
        if (featOkTst && casFeat_traversalMentionIDs == null)
      jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs);
  }
  /** @generated */    
  public void setTraversalMentionIDs(int addr, int v) {
        if (featOkTst && casFeat_traversalMentionIDs == null)
      jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_traversalMentionIDs, v);}
    
   /** @generated */
  public String getTraversalMentionIDs(int addr, int i) {
        if (featOkTst && casFeat_traversalMentionIDs == null)
      jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs), i);
  }
   
  /** @generated */ 
  public void setTraversalMentionIDs(int addr, int i, String v) {
        if (featOkTst && casFeat_traversalMentionIDs == null)
      jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_traversalMentionIDs), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_mentionName = jcas.getRequiredFeatureDE(casType, "mentionName", "uima.cas.String", featOkTst);
    casFeatCode_mentionName  = (null == casFeat_mentionName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mentionName).getCode();

 
    casFeat_mentionID = jcas.getRequiredFeatureDE(casType, "mentionID", "uima.cas.Long", featOkTst);
    casFeatCode_mentionID  = (null == casFeat_mentionID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mentionID).getCode();

 
    casFeat_traversalIDs = jcas.getRequiredFeatureDE(casType, "traversalIDs", "uima.cas.StringArray", featOkTst);
    casFeatCode_traversalIDs  = (null == casFeat_traversalIDs) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_traversalIDs).getCode();

 
    casFeat_traversalMentionIDs = jcas.getRequiredFeatureDE(casType, "traversalMentionIDs", "uima.cas.StringArray", featOkTst);
    casFeatCode_traversalMentionIDs  = (null == casFeat_traversalMentionIDs) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_traversalMentionIDs).getCode();

  }
}



    