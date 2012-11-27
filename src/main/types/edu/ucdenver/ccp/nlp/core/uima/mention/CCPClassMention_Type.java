
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

/** The CCP ClassMention is the root of a flexible class structure that can be used to store virtually any frame-based representation of a particular class. Common class mention types include, but are not limited to, such things as entities (protein, cell type, cell line, disease, tissue, etc.) and frames (interaction, transport, regulation, etc.).
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPClassMention_Type extends CCPMention_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPClassMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPClassMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPClassMention(addr, CCPClassMention_Type.this);
  			   CCPClassMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPClassMention(addr, CCPClassMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPClassMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
 
  /** @generated */
  final Feature casFeat_slotMentions;
  /** @generated */
  final int     casFeatCode_slotMentions;
  /** @generated */ 
  public int getSlotMentions(int addr) {
        if (featOkTst && casFeat_slotMentions == null)
      jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions);
  }
  /** @generated */    
  public void setSlotMentions(int addr, int v) {
        if (featOkTst && casFeat_slotMentions == null)
      jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_slotMentions, v);}
    
   /** @generated */
  public int getSlotMentions(int addr, int i) {
        if (featOkTst && casFeat_slotMentions == null)
      jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions), i);
  }
   
  /** @generated */ 
  public void setSlotMentions(int addr, int i, int v) {
        if (featOkTst && casFeat_slotMentions == null)
      jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotMentions), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_ccpTextAnnotation;
  /** @generated */
  final int     casFeatCode_ccpTextAnnotation;
  /** @generated */ 
  public int getCcpTextAnnotation(int addr) {
        if (featOkTst && casFeat_ccpTextAnnotation == null)
      jcas.throwFeatMissing("ccpTextAnnotation", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_ccpTextAnnotation);
  }
  /** @generated */    
  public void setCcpTextAnnotation(int addr, int v) {
        if (featOkTst && casFeat_ccpTextAnnotation == null)
      jcas.throwFeatMissing("ccpTextAnnotation", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_ccpTextAnnotation, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPClassMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_slotMentions = jcas.getRequiredFeatureDE(casType, "slotMentions", "uima.cas.FSArray", featOkTst);
    casFeatCode_slotMentions  = (null == casFeat_slotMentions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_slotMentions).getCode();

 
    casFeat_ccpTextAnnotation = jcas.getRequiredFeatureDE(casType, "ccpTextAnnotation", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation", featOkTst);
    casFeatCode_ccpTextAnnotation  = (null == casFeat_ccpTextAnnotation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ccpTextAnnotation).getCode();

  }
}



    