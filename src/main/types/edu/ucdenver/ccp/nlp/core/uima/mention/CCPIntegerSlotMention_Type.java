
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
public class CCPIntegerSlotMention_Type extends CCPPrimitiveSlotMention_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPIntegerSlotMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPIntegerSlotMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPIntegerSlotMention(addr, CCPIntegerSlotMention_Type.this);
  			   CCPIntegerSlotMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPIntegerSlotMention(addr, CCPIntegerSlotMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPIntegerSlotMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.mention.CCPIntegerSlotMention");
 
  /** @generated */
  final Feature casFeat_slotValues;
  /** @generated */
  final int     casFeatCode_slotValues;
  /** @generated */ 
  public int getSlotValues(int addr) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPIntegerSlotMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_slotValues);
  }
  /** @generated */    
  public void setSlotValues(int addr, int v) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPIntegerSlotMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_slotValues, v);}
    
   /** @generated */
  public int getSlotValues(int addr, int i) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPIntegerSlotMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i);
	return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i);
  }
   
  /** @generated */ 
  public void setSlotValues(int addr, int i, int v) {
        if (featOkTst && casFeat_slotValues == null)
      jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPIntegerSlotMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i);
    ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_slotValues), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPIntegerSlotMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_slotValues = jcas.getRequiredFeatureDE(casType, "slotValues", "uima.cas.IntegerArray", featOkTst);
    casFeatCode_slotValues  = (null == casFeat_slotValues) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_slotValues).getCode();

  }
}



    