
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

/** 
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPStringSlotMention_Type extends CCPPrimitiveSlotMention_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPStringSlotMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPStringSlotMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPStringSlotMention(addr, CCPStringSlotMention_Type.this);
  			   CCPStringSlotMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPStringSlotMention(addr, CCPStringSlotMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPStringSlotMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
 
  /** @generated */
  final Feature casFeat_slotValues;
  /** @generated */
  final int     casFeatCode_slotValues;
  /** @generated */ 
  public int getSlotValues(int addr) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_slotValues);
  }
  /** @generated */    
  public void setSlotValues(int addr, int v) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_slotValues, v);}
    
   /** @generated */
  public String getSlotValues(int addr, int i) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i);
  }
   
  /** @generated */ 
  public void setSlotValues(int addr, int i, String v) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPStringSlotMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_slotValues = jcas.getRequiredFeatureDE(casType, "slotValues", "uima.cas.StringArray", featOkTst);
    casFeatCode_slotValues  = (null == casFeat_slotValues) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_slotValues).getCode();

  }
}



    